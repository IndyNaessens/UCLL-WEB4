$("#friendlistToggle").click(function () {
    $("#friends").slideToggle("fast");
});

//list where inverval id's are kept
let intervalIds = [];

function createAndInsertChatbox(userIdReceiver, receiverName){
    //make header
    let chatSquare = $('<div/>', {
        class: 'border',
        style: 'width: 15em; height: 20em;',
        id: 'chatbox-' + userIdReceiver
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
        class: 'close d-inline',
        click: function () {
            closeChatbox(userIdReceiver);
        }
    }).appendTo(headerParentDiv);

    $('<span/>', {
        text: '×'
    }).appendTo(closeButton);

    //make body
    $('<div/>', {
        class: 'overflow-auto chatbody',
        style: 'height: 15em;',
    }).appendTo(chatSquare);

    //make input + button
    let chatInputGroup = $('<div/>', {
        class: 'input-group',
        style: 'position: absolute; bottom: 0em; width: 15em; max-height: 2.5em;'
    }).appendTo(chatSquare);

    $('<input/>', {
        type: 'text',
        class: 'form-control',
        placeholder: 'Your message'
    }).appendTo(chatInputGroup);

    let chatInputGroupAppend = $('<div/>', {
        class: 'input-group-append'
    }).appendTo(chatInputGroup);

    $('<button/>', {
        type: 'button',
        class: 'btn btn btn-dark',
        text: 'Send',
        click: function () {
            sendChatmessage(userIdReceiver);
        }
    }).appendTo(chatInputGroupAppend);

    //polling
    polChatmessages(userIdReceiver);

    //give space between chatbox & friends table
    $('#addFriendContainer').attr('style','margin-bottom: 25em;');
}

function getChatMessages(userIdReceiver){
    //do get request
    $.get("/api/chat/" + userIdReceiver, function (data) {
        //remove all children in chatbody
        let chatBody = $('#chatbox-' + userIdReceiver + ' .chatbody').empty();

        data.forEach(function (item) {
            //prepare message
            let message = $('<div/>', {
                class: 'd-block w-75 m-2'
            });

            let messageChild = $('<span/>', {
                class: 'badge badge-pill p-2 text-wrap w-auto',
            }).appendTo(message);

            //message position
            if(item.sendByYou){
                message.addClass('float-right');
                messageChild.addClass('badge-primary float-right');
                messageChild.text(item.message);
            }else{
                message.addClass('float-left');
                messageChild.addClass('badge-light float-left');
                messageChild.text(item.message);
            }

            //append message
            chatBody.append(message);
        })
    }, "json");
}

function sendChatmessage(userIdReceiver){
    //prepare transer object to JSON
    let sendChatMessageDto = {
        userIdReceiver: userIdReceiver,
        message: $('#chatbox-' + userIdReceiver + ' input').val()
    };

    let sendChatMessageJson = JSON.stringify(sendChatMessageDto);

    //do POST request with payload
    $.ajax({
        type: 'POST',
        url: '/api/chat',
        data: sendChatMessageJson,
        contentType: "application/json",
        dataType: 'json'
    });

    //clear input
    $('#chatbox-' + userIdReceiver + ' input').val('');
}

function polChatmessages(userIdReceiver) {
    //start polling
    let intervalId = setInterval(function () {
        getChatMessages(userIdReceiver);
    }, 500);

    //save intervalId
    intervalIds.push(userIdReceiver + '|' + intervalId);
}

function closeChatbox(userIdReceiver) {
    //close chatbox
    $('#chatbox-' + userIdReceiver).remove();

    //stop polling and remove intervalId from list
    intervalIds.forEach(function (item,index) {
        if(item.split('|')[0] === userIdReceiver){
            clearInterval(item.split('|')[1]);
            intervalIds.splice(index,1)
        }
    });

    //if last chatbox remove white space after friends table
    if(intervalIds.length === 0){
        $('#addFriendContainer').removeAttr('style');
    }
}