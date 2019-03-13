let addFriendRequest = new XMLHttpRequest();
let changeStatusRequest = new XMLHttpRequest();
let getStatusRequest = new XMLHttpRequest();
let getFriendListRequest = new XMLHttpRequest();

//get friend list
window.onload = getFriendList;
let getFriendListIntervalId = setInterval(getFriendList, 2000);

function getFriendList() {
    getFriendListRequest.open("GET","/api/user/friend",true);

    getFriendListRequest.onreadystatechange = function(){
        if(getFriendListRequest.readyState === 4 && getFriendListRequest.status === 200){
            let response = JSON.parse(getFriendListRequest.responseText);

            //remove tbody childeren
            let tbody = document.getElementById("tableFriendListBody");
            while (tbody.hasChildNodes()) {
                tbody.removeChild(tbody.lastChild);
            }

            for(let i=0;i<response.length;i++){
                let row = document.createElement("tr");

                let col1 = document.createElement("td");
                col1.innerText = String(i+1);
                col1.scope = "row";

                let col2 = document.createElement("td");
                col2.innerText = response[i].firstName + " "  + response[i].lastName;

                let col3 = document.createElement("td");
                col3.innerText = response[i].status;

                row.append(col1);
                row.append(col2);
                row.append(col3);
                tbody.append(row);
            }
        }
    };

    getFriendListRequest.send(null);
}

function changeStatus() {
    getStatusRequest.open("GET", "/api/user/status", true);

    //get status data
    getStatusRequest.onreadystatechange = function () {
        if (getStatusRequest.readyState === 4 && getStatusRequest.status === 200) {
            document.getElementById("status").innerText = getStatusRequest.responseText; //no json parse needed because not in json format
        }
    };

    getStatusRequest.send(null);
}

//change status button action
document.getElementById("changeStatusButton").onclick = function () {
    //get status value
    let changeStatusInput = document.getElementById("changeStatusInput");
    let payload = "status=" + encodeURIComponent(changeStatusInput.value);

    //prepare request and send
    changeStatusRequest.open("PUT", "/api/user/status", true);

    //change status + user feedback
    changeStatusRequest.onreadystatechange = function () {
        if (changeStatusRequest.readyState === 4) {
            if (changeStatusRequest.status === 200) {
                changeStatus();
                changeStatusInput.value = "";

                let success = document.createElement("p");
                success.innerText = "Status changed successfully";
                success.className = "text-success";

                changeStatusInput.parentNode.insertBefore(success, changeStatusInput);

                setTimeout(function () {
                    success.remove();
                }, 2000);
            } else {
                let fail = document.createElement("p");
                fail.innerText = "Status changed failed, status code: " + changeStatusRequest.status;
                fail.className = "text-danger";

                changeStatusInput.parentNode.insertBefore(fail, changeStatusInput);

                setTimeout(function () {
                    fail.remove();
                }, 2000);

            }
        }
    };

    changeStatusRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    changeStatusRequest.send(payload);
};

document.getElementById("addFriendButton").onclick = function () {
    //make payload
    let addFriendInput = document.getElementById("addFriendInput");
    let payload = "email=" + encodeURIComponent(addFriendInput.value);

    //prep and send request
    addFriendRequest.open("POST", "/api/user/friend", true);

    //user feedback
    addFriendRequest.onreadystatechange = function () {
        if (addFriendRequest.readyState === 4) {
            if (addFriendRequest.status === 201) {
                //refresh friend list
                getFriendList();
                clearInterval(getFriendListIntervalId);
                getFriendListIntervalId = setInterval(getFriendList,2000);

                //success message
                addFriendInput.value = "";

                let success = document.createElement("p");
                success.innerText = "Friend added successfully";
                success.className = "text-success";

                addFriendInput.parentNode.insertBefore(success, addFriendInput);

                setTimeout(function () {
                    success.remove();
                }, 2000);
            } else {
                let fail = document.createElement("p");
                fail.innerText = "Adding new friend failed, status code: " + addFriendRequest.status;
                fail.className = "text-danger";

                addFriendInput.parentNode.insertBefore(fail, addFriendInput);

                setTimeout(function () {
                    fail.remove();
                }, 2000);
            }
        }
    };

    addFriendRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    addFriendRequest.send(payload);
};