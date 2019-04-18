//get friend list and set interval for polling
window.onload = getFriendList;
let getFriendListIntervalId = setInterval(getFriendList, 2000);

//change status button action
document.getElementById("changeStatusButton").onclick = function () {
    let changeStatusInput = document.getElementById("changeStatusInput");

    let requestHelper = new RequestHelper.Builder()
        .withRequestInfo("PUT","/api/user/status",200)
        .withErrorHandling("Status changed successfully","Changing status failed!",changeStatusInput)
        .withPayload(new RequestParameter("status",changeStatusInput.value))
        .build();

    //response is OK so we can assume the new status has been set server side
    requestHelper.sendRequest(function(){
        document.getElementById("status").innerText = changeStatusInput.value;
        changeStatusInput.value = '';
    })
};

//add friend button action
document.getElementById("addFriendButton").onclick = function () {
    let addFriendInput = document.getElementById("addFriendInput");

    let requestHelper = new RequestHelper.Builder()
        .withRequestInfo("POST","/api/user/friend",201)
        .withErrorHandling("Friend added successfully","Adding new friend failed!",addFriendInput)
        .withPayload(new RequestParameter("email",addFriendInput.value))
        .build();

    //response is OK so we can assume that the friend is added successfully server side so we can pull the new friend list
    requestHelper.sendRequest(function(){
        addFriendInput.value = '';
        //get new friendlist and restart interval
        clearInterval(getFriendListIntervalId);
        getFriendList();
        getFriendListIntervalId = setInterval(getFriendList,2000);
    });
};

//get the friend list and create a table row for each friend
function getFriendList() {
    let requestHelper = new RequestHelper.Builder()
        .withRequestInfo("GET","/api/user/friend",200)
        .build();

    requestHelper.sendRequest(function(responseText){
        //parse to json
        let response = JSON.parse(responseText);

        //remove tbody childeren
        let tbody = document.getElementById("tableFriendListBody");
        while (tbody.hasChildNodes()) {
            tbody.removeChild(tbody.lastChild);
        }

        //add rows to tbody
        for(let i=0;i<response.length;i++) {
            let row = document.createElement("tr");

            let col1 = document.createElement("td");
            col1.innerText = String(i + 1);

            let col2 = document.createElement("td");
            col2.innerText = response[i].firstName + " " + response[i].lastName;

            let col3 = document.createElement("td");
            col3.innerText = response[i].status;

            //deelopdracht3
            let col4 = document.createElement("td");

            let chatButton = document.createElement("button");
            chatButton.className = "btn btn-outline-dark";
            chatButton.type = "button";
            chatButton.id = response[i].userId;
            chatButton.innerText = "Chat now";
            chatButton.onclick = function(){
                //make chatbox and go to chatbox
                let userReceiverId = $(this).attr('id');
                let userReceiverName = $(this).parent().parent().children().eq(1).text();

                let chatbox = new Chatbox();
                chatbox.createAndInsertChatbox(userReceiverId,userReceiverName);

                //disable button immediately
                if($('#chat-' + chatButton.id).length){
                    $('#' + chatButton.id).prop("disabled",true);
                }

                $('html, body').animate({scrollTop:$(document).height()}, 'slow');
            };

            col4.append(chatButton);

            row.append(col1);
            row.append(col2);
            row.append(col3);
            row.append(col4);
            tbody.append(row);

            //disable button if needed
            if($('#chat-' + chatButton.id).length){
                $('#' + chatButton.id).prop("disabled",true);
            }
        }
    });
}