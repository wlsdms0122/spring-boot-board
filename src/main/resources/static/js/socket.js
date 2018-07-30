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
        stompClient.subscribe("/topic/in", function (message) {
        	// parse message with JSON
            showIn(JSON.parse(message.body));
        });
        
        stompClient.subscribe("/topic/out", function (message) {
            showOut(JSON.parse(message.body));
        });
        
        stompClient.subscribe("/topic/chat", function (message) {
            showChat(JSON.parse(message.body));
        });
        
        sendIn(nickname);
    });
}

// disconnect with server socket
function disconnect() {
	if (stompClient != null) {
        stompClient.disconnect();
    }
	
    console.log("Disconnected");
}

function sendIn(nickname) {
	// send message to server. full path will become "topic/..." because prefix was already set by WebSocketConfig class
	// message equal with HelloMessage's variable
	stompClient.send("/app/in", {}, JSON.stringify({
		"nickname" : nickname
	}));
}

function sendOut(nickname) {
	stompClient.send("/app/out", {}, JSON.stringify({
		"nickname" : nickname
	}));
}

function sendChat(nickname, content) {
	stompClient.send("/app/chat", {}, JSON.stringify({
		"nickname" : nickname,
		"content" : content
	}));
}

function showIn(message) {
	var join = "<tr>\n";
	join += "<td class='border-0 p-0'>\n";
	join += "<small class='text-muted'>[SYSTEM] " + message.nickname + " joined.</small>\n";
	join += "</td>"
	join += "</tr>\n";
	$("#list").append(join);
	$("#list").parent().scrollTop($(document).height());
}

function showOut(message) {
	var join = "<tr>\n";
	join += "<td class='border-0 p-0'>\n";
	join += "<small class='text-muted'>[SYSTEM] " + message.nickname + " leaved.</small>\n";
	join += "</td>"
	join += "</tr>\n";
	$("#list").append(join);
	$("#list").parent().scrollTop($(document).height());
}

function showChat(message) {
	var join = "<tr>\n";
	join += "<td class='border-0 p-0'>\n";
	join += "<small class='text-muted'>" + message.nickname + " - " + new Date(message.time).format("HH : MM : ss") + "</small>\n";
	join += "<p class='mb-1'>" + message.content + "</p>\n";
	join += "</td>"
	join += "</tr>\n";
	$("#list").append(join);
	$("#list").parent().scrollTop($(document).height());
}