window.onload = function () {
    //websocket
    let webSocket = new WebSocket("ws://localhost:8080/blog");

    //set button actions
    let buttons = document.querySelectorAll("button");

    buttons.forEach(function (item) {
        //only set the onclick action for comment buttons
        if(item.id.split("-")[0] === "commentButton"){
            item.onclick = function () {
                webSocket.send(new BlogMessage(item.parentNode).toString());

                //clear input fields for the right 'form'
                let currentParent = item.parentNode;
                let currentParentNumber = currentParent.id.split("-")[1];

                //only clear the name if the user is not logged in
                if(!(document.getElementById('welcomeHeader'))){
                    currentParent.querySelector("#name-" + currentParentNumber).value = "";
                }
                currentParent.querySelector("#comment-" + currentParentNumber).value = "";
                currentParent.querySelector("#score-" + currentParentNumber).value = 5;
            }
        }
    });

    //parse response from server
    webSocket.onmessage = function(event) {
        //get the right form where the message belongs
        let currentForm = document.getElementById(event.data.split(",")[0]);

        //make a comment
        let paragraph = document.createElement("p");
        paragraph.innerText = event.data.split(",")[1] + "(" + event.data.split(",")[3] + "): " + event.data.split(",")[2];

        //insert before the form
        currentForm.parentNode.insertBefore(paragraph,currentForm);
    };

};


class BlogMessage {

    constructor(node){
        this.nodeId = node.id; //form-x
        //get input values from the right 'form-x'
        this.sender = node.querySelector("#name-" + this.nodeId.split("-")[1]).value;
        this.comment = node.querySelector("#comment-" + this.nodeId.split("-")[1]).value;
        this.rating = node.querySelector("#score-" + this.nodeId.split("-")[1]).value;
    }

    toString(){
        return this.nodeId + "," + this.sender + "," + this.comment + "," + this.rating;
    }
}
