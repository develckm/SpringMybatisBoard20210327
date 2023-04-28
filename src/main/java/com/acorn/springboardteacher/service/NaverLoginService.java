package com.acorn.springboardteacher.service;

import com.acorn.springboardteacher.dto.NaverUserDto;
import com.acorn.springboardteacher.dto.UserDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class NaverLoginService {
    @Value("${naver.login.token.url}")
    private String tokenUrl;
    @Value("${naver.login.redirect.url}")
    private String redirectUrl;
    @Value("${naver.login.profile.url}")
    private String profileUrl;
    @Value("${naver.login.client.id}")
    private String clientId;
    @Value("${naver.login.client.secret}")
    private String clientSecret;

    public String getToken(String code) throws IOException {
        // 인가코드로 토큰받기
        String host = tokenUrl;
        URL url = new URL(host);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String token = "";
        try {
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true); // 데이터 기록 알려주기

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id="+clientId);
            sb.append("&client_secret="+clientSecret);
            sb.append("&redirect_uri="+redirectUrl);
            sb.append("&code=" + code);

            bw.write(sb.toString());
            bw.flush();

            int responseCode = urlConnection.getResponseCode();
            System.out.println("responseCode = " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("result = " + result);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode obj = objectMapper.readTree(result);

            // json parsing

            String access_token = obj.get("access_token").toString();
            String refresh_token = obj.get("refresh_token").toString();
            System.out.println("refresh_token = " + refresh_token);
            System.out.println("access_token = " + access_token);

            token = access_token;

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return token;
    }


    public NaverUserDto getUserInfo(String access_token) throws IOException {
        String host = profileUrl;
        //Map<String, Object> result = new HashMap<>();
        NaverUserDto naverLoginUser=null;
        try {
            URL url = new URL(host);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Authorization", "Bearer " + access_token);
            urlConnection.setRequestMethod("GET");

            int responseCode = urlConnection.getResponseCode();
            System.out.println("responseCode = " + responseCode);


            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            String res = "";
            while((line=br.readLine())!=null)
            {
                res+=line;
            }
//{
//  "resultcode": "00",
//  "message": "success",
//  "response": {
//    "email": "openapi@naver.com",
//    "nickname": "OpenAPI",
//    "profile_image": "https://ssl.pstatic.net/static/pwe/address/nodata_33x33.gif",
//    "age": "40-49",
//    "gender": "F",
//    "id": "32742776",
//    "name": "오픈 API",
//    "birthday": "10-01",
//    "birthyear": "1900",
//    "mobile": "010-0000-0000"
//  }
//}
            System.out.println("res = " + res);


            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode obj = objectMapper.readTree(res);
            JsonNode account =  obj.get("response");
            naverLoginUser = objectMapper.readValue( account.toString().getBytes(), NaverUserDto.class);
//            String email = account.get("email").toString();
//            //String nickname = account.get("nickname").toString();
//            String profile_image = account.get("profile_image").toString();
//            //String age = account.get("age").toString();
//            String id = account.get("id").toString();
//            String name = account.get("name").toString();
//            String birthday = account.get("birthday").toString();
//            String birthyear = account.get("birthyear").toString();
//            String mobile = account.get("mobile").toString();
//
//            result.put("id", id);
//            result.put("email", email);
//            result.put("profile_image", profile_image);
//            result.put("name", name);
//            result.put("birthday", birthday);
//            result.put("birthyear", birthyear);
//            result.put("mobile", mobile);

            br.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return naverLoginUser;
    }

}
