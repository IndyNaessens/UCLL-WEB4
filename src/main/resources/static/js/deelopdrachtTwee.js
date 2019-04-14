window.onload = function () {
    //websocket
    let webSocket = new WebSocket("ws://localhost:8080/blog");

    //set button actions
    let buttons = document.getElementsByTagName("button");

    for(let i=0; i<buttons.length; ++i){
        if(buttons[i].id.split("-")[0] === "commentButton"){
            buttons[i].onclick = function () {
                webSocket.send(new BlogMessage(buttons[i].parentNode).toString());

                //clear input fields
                let currentParent = buttons[i].parentNode;
                let currentParentNumber = currentParent.id.split("-")[1];

                currentParent.querySelector("#name-" + currentParentNumber).value = "";
                currentParent.querySelector("#comment-" + currentParentNumber).value = "";
                currentParent.querySelector("#score-" + currentParentNumber).value = "";
            }
        }
    }

    //parse response from server
    webSocket.onmessage = function(event) {
        let currentForm = document.getElementById(event.data.split(",")[0]);

        let paragraph = document.createElement("p");
        paragraph.innerText = event.data.split(",")[1] + "(" + event.data.split(",")[3] + "): " + event.data.split(",")[2];

        currentForm.parentNode.insertBefore(paragraph,currentForm);
    };

};


class BlogMessage {

    constructor(node){

        this.nodeId = node.id;
        this.sender = node.querySelector("#name-" + this.nodeId.split("-")[1]).value;
        this.comment = node.querySelector("#comment-" + this.nodeId.split("-")[1]).value;
        this.rating = node.querySelector("#score-" + this.nodeId.split("-")[1]).value;
    }

    toString(){
        return this.nodeId + "," + this.sender + "," + this.comment + "," + this.rating;
    }
}
