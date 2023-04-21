console.log("포함됨~");
//팔로우 링크 버튼
async function following(toId,btn){
    let status=await registerFollow(toId,false);
    if(status==="1"){
        alert("팔로우 성공");
        btn.closest(".followCont").remove();
    }else{
        alert("팔로우 실패");
    }
}
async function registerFollow(toId,follower){
    let url=`/follow/${toId}/${follower}/handler.do`
    const resp=await fetch(url,{method:"POST"});
    if(resp.status===200){
        let status=await resp.text(); //"0" or "1"
        return status;
    }else if(resp.status===400){
        alert("로그인 하셔야 이용할 수 있는 서비스 입니다.");
    }else if(resp.status===500){//팔로잉 했는데 한번더 팔로잉하면 db 오류 발생 or db 통신오류
        alert("이미 팔로잉되었거나 오류가 발생했으니 새로고침하고 다시 시도하세요~");
    }
}
async function removeFollow(fromId,follower){
    //follower : true(팔로워삭제), false(팔로잉삭제)
    let url=`/follow/${fromId}/${follower}/handler.do`
    let method="DELETE";
    const resp=await fetch(url,{method:method});
    if(resp.status===200){
        return await resp.text();// 0 실패, 1성공
    }else if(resp.status===400){
        alert("로그인 하셔야 이용할 수 있는 서비스 입니다.");
    }else if(resp.status===500){//팔로잉 했는데 한번더 팔로잉하면 db 오류 발생 or db 통신오류
        alert("오류가 발생했으니 새로고침하고 다시 시도하세요~");
    }
}
async function toggleFollower(fromId,btn){
    let active=(btn.classList.contains("active"));
    if(active){//이미 삭제됨(팔로워를 다시 등록)
        //btn.classList.remove("active");
        //alert("팔로워 삭제 취소(팔로워 다시 등록)")
        let register=await registerFollow(fromId,true);
        if(register==="1"){
            btn.classList.remove("active");
            alert("팔로워 삭제 취소(팔로워를 다시 등록)");
        }else{
            alert("팔로워 삭제 취소 실패");
        }

    }else{ //아직 삭제되지 않음(팔로워를 삭제)
        let remove=await removeFollow(fromId,true);
        if(remove==="1"){
            btn.classList.add("active");
            alert("팔로워 삭제 성공");
        }else{
            alert("팔로워 삭제 실패");
        }
    }
}


async function toggleFollowing(toId,btn){
    let active=btn.classList.contains("active");
    if (active){//팔로잉 취소
        let remove=await removeFollow(toId,false);
        if(remove>0){
            alert("팔로잉 취소 성공");
            btn.classList.remove("active")
        }else {
            alert("팔로잉 취소 실패");
        }
    }else{//다시 팔로잉
        let register=await registerFollow(toId,false);
        if(register>0){//문자열을 비교연산하면 수로 형변환한다.
            alert("팔로잉 성공");
            btn.classList.add("active");
        }else{
            alert("팔로잉 실패");
        }
    }
}