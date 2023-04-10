async function boardLikeHandler(status,bId){
    const likeCont=document.getElementById("likeCont"+bId);
    console.log(likeCont);
    let url=`/board/like/${status}/${bId}/handler.do`
    const resp=await fetch(url);
    if(resp.status===200){
        const json=await resp.json();
        if (json.handler>0){
            let html=await readLike(bId);
            if(html){
                likeCont.innerHTML=html;
                alert(json.status+" "+json.handlerType+" 성공");
            }else{
                alert(json.status+" "+json.handlerType+" 성공(불러오기 실패 새로고침)");
            }
        }else{
            alert(json.status+" "+json.handlerType+" 실패");
        }
    }else if(resp.status===400){
        alert("로그인 하셔야 이용 가능한 서비스 입니다.(잘못된 요청)")
    }else{
        alert("실패 status:"+resp.status);
    }
}
async function readLike(bId){
    let url=`/board/like/${bId}/read.do`;
    const resp=await fetch(url);
    if(resp.status===200){
        const html=await resp.text();
        return html;
    }
}