let addFriendRequest = new XMLHttpRequest();
let changeStatusRequest = new XMLHttpRequest();
let getStatusRequest = new XMLHttpRequest();


function changeStatus() {
    getStatusRequest.open("GET", "/api/user/status", true);

    //get status data
    getStatusRequest.onreadystatechange = function(){
        if (getStatusRequest.readyState === 4) {
            if (getStatusRequest.status === 200) {
                document.getElementById("status").innerText = getStatusRequest.responseText; //no json parse needed because not in json format
            }
        }
    };

    getStatusRequest.send(null);
}

//change status
document.getElementById("changeStatusButton").onclick = function(){
    //get status value
    let changeStatusInput = document.getElementById("changeStatusInput");
    let payload = "status=" + encodeURIComponent(changeStatusInput.value);

    //prepare request and send
    changeStatusRequest.open("PUT", "/api/user/status", true);

    //change status + user feedback
    changeStatusRequest.onreadystatechange = function(){
        if (changeStatusRequest.status === 200) {
            if(changeStatusRequest.readyState === 4){
                changeStatus();
                changeStatusInput.value = "";

                let success = document.createElement("p");
                success.innerText = "Status changed successfully";
                success.className = "text-success";

                changeStatusInput.parentNode.insertBefore(success,changeStatusInput);

                setTimeout(function(){
                    success.remove();
                },2000);
            }
        }else{
            if(changeStatusRequest.readyState === 4){
                changeStatusInput.value = "";

                let fail = document.createElement("p");
                fail.innerText = "Status changed failed is the service running?";
                fail.className = "text-danger";

                changeStatusInput.parentNode.insertBefore(fail,changeStatusInput);

                setTimeout(function(){
                    fail.remove();
                },2000);
            }
        }
    };

    changeStatusRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    changeStatusRequest.send(payload);
};