// declare STOMP client session
var stompClient = null;

// connect web socket with server
function connect(nickname) {
	// create socket to connect to end-point of server socket
    var socket = new SockJS("/websocket");
    
    // assign client session
    stompClient = Stomp.over(socket);
    stompClient.debug = null;
    
    // connect to server
    stompClient.connect({}, function (frame) {
        // if session is connected, subscribe server message to topic
        stompClient.subscribe("/topic/chat", function (message) {
        	// parse message with JSON
        	var msg = JSON.parse(message.body);
        	switch (msg.type) {
        	case "JOIN":
                showIn(msg);
        		break;
        	case "EXIT":
                showOut(msg);
        		break;
        	case "CHAT":
                showChat(msg);
        		break;
        	}
        });
        
    	stompClient.send("/app/chat", {}, JSON.stringify({
    		"type" : "JOIN",
    		"nickname" : nickname
    	}));
    });
}

// disconnect with server socket
function disconnect() {
	if (stompClient != null) {
        stompClient.disconnect();
    }
	
    console.log("Disconnected");
}

function sendChat(nickname, content) {
	stompClient.send("/app/chat", {}, JSON.stringify({
		"type" : "CHAT",
		"nickname" : nickname,
		"content" : content
	}));
}

function showIn(message) {
	var join = "<tr>\n";
	join += "<td class='text-center border-0 p-0'>\n";
	join += "<small class='text-muted'>" + message.nickname + " joined.</small>\n";
	join += "</td>"
	join += "</tr>\n";
	$("#list").append(join);
	$("#list").parent().scrollTop($(document).height());
}

function showOut(message) {
	var join = "<tr>\n";
	join += "<td class='text-center border-0 p-0'>\n";
	join += "<small class='text-muted'>" + message.nickname + " leaved.</small>\n";
	join += "</td>"
	join += "</tr>\n";
	$("#list").append(join);
	$("#list").parent().scrollTop($(document).height());
}

function showChat(message) {
	var join = "<tr>\n";
	join += "<td class='border-0 p-0'>\n";
	join += "<small class='text-muted'>" + message.nickname + " - " + new Date(message.time).format("HH : MM") + "</small>\n";
	join += "<p class='mb-1'>" + message.content + "</p>\n";
	join += "</td>"
	join += "</tr>\n";
	$("#list").append(join);
	$("#list").parent().scrollTop($(document).height());
}