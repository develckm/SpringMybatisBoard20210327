package com.acorn.springboardteacher.lib;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.io.*;
import java.security.Key;
import java.util.Base64;

public class AESEncryption {
    //AES :(암호화=>해시코드) 대칭키, 블록+패딩, 작동방식 (ECB, CBC, GCM)
    private static final String ALGORITHM="AES";
    private static final String CIPHER_ALGORITHM="AES/ECB/PKCS5Padding";
    private static final int BLOCK_SIZE=128;
    private static Key secretKey; //암호화와 복호화에 사용될 대칭키
    private static final String KEY_FILE_NAME="secretKey.ser"; //톰캣 서버가 배포되는 위치에 저장됨
    static {
        try {
            File secretKeyFile=new File(KEY_FILE_NAME);
            if(secretKeyFile.exists()){ //기존에 있는 파일을 불러와서 오브젝트로 변환
                ObjectInputStream ois=new ObjectInputStream(new FileInputStream(KEY_FILE_NAME));
                secretKey=(Key)ois.readObject();

            }else{ //오브젝트를 파일로 변환
                KeyGenerator kg=KeyGenerator.getInstance(ALGORITHM);//KeyGenerator 간단한 암호화 알고리즘으로 대칭키를 생성
                kg.init(BLOCK_SIZE);
                secretKey=kg.generateKey(); //톰캣 서버가 시작할때 key가 생성됨
                ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(KEY_FILE_NAME)); //Object를 파일로 반환
                oos.writeObject(secretKey);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static String encryptValue(String value) throws Exception{
        //Cipher : 암호화와 복호화하는 클래스
        Cipher cipher=Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey); //해당키로 암호화할 준비
        byte[] encryptBytes=cipher.doFinal(value.getBytes()); //"안녕"=={'안','녕'} char[] == {-20,-80,-107,-21,-113,-117} byte[]
        //암호화 된 해시코드는 바이트로 인코딩이 된다.
        return Base64.getEncoder().encodeToString(encryptBytes); //바이트 인코딩 문자열로 변환
    }
    public static String decryptValue(String encryptValue) throws Exception{
        byte[] encryptBytes=Base64.getDecoder().decode(encryptValue);//문자열을 => 바이트 인코딩으로 변환
        Cipher cipher=Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,secretKey);//대칭키로 복호화 준비
        byte[] decryptBytes=cipher.doFinal(encryptBytes); //유니코드인 바이트 배열을 반환
        return new String(decryptBytes);
    }
}
