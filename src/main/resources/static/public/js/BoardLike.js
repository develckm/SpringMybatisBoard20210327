async function boardLikeHandler(status,bId){
    let url=`/board/like/${status}/${bId}/handler.do`
    const resp=await fetch(url);
    if(resp.status===200){
        const json=await resp.json();
        if (json.handler>0){
            alert(json.status+" "+json.handlerType+" 성공");
        }else{
            alert(json.status+" "+json.handlerType+" 실패");
        }
    }else{
        alert("실패 status:"+resp.status);
    }
}
