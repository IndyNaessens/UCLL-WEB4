class Chatbox {

    constructor(){

    }

    createAndInsertChatbox(userIdReceiver, receiverName){
        //check if first chatbox
        if(!($('#chat-flex').length)){
            $('<div/>', {
                id: 'chat-flex',
                class: 'd-flex flex-row-reverse fixed-bottom'
            }).insertAfter('#friends');

            $('#friends').attr('style','margin-bottom: 22.5em;')

        }

        //make header
        let chatSquare = $('<div/>', {
            class: 'border',
            style: 'width: 15em; height: 20em;'
        }).appendTo('#chat-flex');

        let headerParentDiv = $('<div/>', {
            class: 'border-bottom p-2',
            style: 'max-height: 2.5em;'
        }).appendTo(chatSquare);

        $('<h5/>', {
            class: 'mb-0 d-inline',
            text: receiverName
        }).appendTo(headerParentDiv);

        let closeButton = $('<button/>', {
            type: 'button',
            class: 'close d-inline'
        }).appendTo(headerParentDiv);

        $('<span/>', {
            text: '×'
        }).appendTo(closeButton);

        //make body
        let chatBody = $('<div/>', {
            class: 'overflow-auto',
            style: 'height: 15em;',
        }).appendTo(chatSquare);

        //make chat button
        let chatInputGroup = $('<div/>', {
            class: 'input-group',
            style: 'position: absolute; bottom: 0em; width: 15em; max-height: 2.5em;'
        }).appendTo(chatSquare);

        $('<input/>', {
            type: 'text',
            class: 'form-control',
            placeholder: 'Your message',
            id: 'chatInput-' + userIdReceiver
        }).appendTo(chatInputGroup);

        let chatInputGroupAppend = $('<div/>', {
            class: 'input-group-append'
        }).appendTo(chatInputGroup);

        $('<button/>', {
            type: 'button',
            class: 'btn btn btn-dark',
            text: 'Send',
            id: 'chat-' + userIdReceiver,
            click: function () {
                let sendChatMessageDto = {
                    userIdReceiver: userIdReceiver,
                    message: $('#chatInput-' + userIdReceiver).val()
                };

                let sendChatMessageJson = JSON.stringify(sendChatMessageDto);

                $.ajax({
                    type: 'POST',
                    url: '/api/chat',
                    data: sendChatMessageJson,
                    contentType: "application/json",
                    dataType: 'json'
                });

                $('#chatInput-' + userIdReceiver).val('');
            }
        }).appendTo(chatInputGroupAppend);

        //polling
        Chatbox.fetchChatBody(userIdReceiver,chatBody);
        this.intervalId = setInterval(function () {
            Chatbox.fetchChatBody(userIdReceiver,chatBody);
        },500);
    }

    static fetchChatBody(userIdReceiver,chatBody){
        $.get( "/api/chat/" + userIdReceiver, function( data ) {
            //remove all children in chatbody
            chatBody.empty();

            for(let i=0;i<data.length; i++){
                //make message
                let message = null;
                if(data[i].sendByYou){
                    message = $('<div/>', {
                        class: 'd-block w-75 m-2 float-right'
                    });

                    let messageChild = $('<span/>', {
                        class: 'badge badge-pill badge-primary p-2 text-wrap w-auto float-right',
                        text: data[i].message
                    }).appendTo(message);
                }else{
                    message = $('<div/>', {
                        class: 'd-block w-75 m-2 float-left'
                    });

                    let messageChild = $('<span/>', {
                        class: 'badge badge-pill badge-light p-2 text-wrap w-auto float-left',
                        text: data[i].message
                    }).appendTo(message);
                }

                //add message
                if(message !== null){
                    message.appendTo(chatBody);
                }
            }
        }, "json" );
    }

    hide(){

    }

    destroy(){

    }
}
