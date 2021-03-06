//get friend list and set interval for polling
window.onload = getFriendList;
let getFriendListIntervalId = setInterval(getFriendList, 1500);

//change status button action
document.getElementById("changeStatusButton").onclick = function () {
    let changeStatusInputValue = document.querySelector('input[name="changeStatusRadioOptions"]:checked').value;

    //custom status is selected
    if(changeStatusInputValue === ''){
        changeStatusInputValue = document.getElementById('changeStatusInput').value;
    }

    //build and send request
    let requestHelper = new RequestHelper.Builder()
        .withRequestInfo("PUT","/api/user/status",200)
        .withErrorHandling("Status changed successfully","Changing status failed!",document.getElementById('changeStatusContainer'))
        .withPayload(new RequestParameter("status",changeStatusInputValue))
        .build();

    //response is OK so we can assume the new status has been set server side
    requestHelper.sendRequest(function(){
        document.getElementById("status").innerText = changeStatusInputValue;

        //reset radio buttons
        let radioButtons = document.getElementsByName('changeStatusRadioOptions');
        for(let i=0;i<radioButtons.length;i++){
            radioButtons[i].checked = i === 0;  //first radio button is checked others are unchecked
        }
        //clear input field
        document.getElementById('changeStatusInput').value = '';
    })
};

//add friend button action
document.getElementById("addFriendButton").onclick = function () {
    let addFriendInput = document.getElementById("addFriendInput");

    //build and send request
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
        getFriendListIntervalId = setInterval(getFriendList,1500);
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

        //get and clear tbody
        let tbody = document.getElementById("tableFriendListBody");
        tbody.innerHTML = '';

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
            chatButton.innerText = "Chat now";
            chatButton.onclick = function(){
                createAndInsertChatbox(response[i].userId,response[i].firstName + " " + response[i].lastName);

                //disable button immediately
                chatButton.disabled = true;

                //go to  bottom where chatbox is
                $('html, body').animate({scrollTop:$(document).height()}, 'slow');
            };

            col4.append(chatButton);

            row.append(col1);
            row.append(col2);
            row.append(col3);
            row.append(col4);
            tbody.append(row);

            //disable button if needed
            if($('#chatbox-' + response[i].userId).length){
                chatButton.disabled = true;
            }
        }
    });
}
