<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>WebSocket Demo</title>
    <style type="text/css">
        html, body {
            margin: 0;
            padding: 0;
        }

        header {
            width: 100%;
            height: 100px;
            text-align: center;
            font-size: 50px;
            line-height: 100px;
            font-family: "微软雅黑";
            border-bottom: 1px solid black;
            margin-bottom: 10px;
        }

        submit {
            width: 200px;
            height: 50px;
            font-size: 20px;
        }

        submit:HOVER {
            cursor: pointer;
        }

        .btn-zone {
            text-align: center;
        }

        #messages {
            padding: 10px;
            font-size: 20px;
        }

        .message:nth-child(2n+1) {
            color: olive;
        }
    </style>
</head>
<body>
<header>
    WebSocket
</header>
<div>
    <div class="btn-zone">
        <input id="submit" type="submit" value="sendMessage" onclick="send()"/>
    </div>
    <div id="messages"></div>
</div>

<script type="text/javascript">
    var webSocket = null;
    if ('WebSocket' in window) {
        webSocket = new WebSocket(
            'ws://localhost:8080/websocket_war/chat?query=Picasso');
    }else {
        alert('当前浏览器不支持 websocket消息通知')
    }
    var reciver = document.getElementById('messages');
    //连接成功建立的回调方法
    webSocket.onopen = function (event) {
        onOpen(event)
    }
    //连接发生错误的回调方法
    webSocket.onerror = function (event) {
        onError(event)
    };
    //接收到消息的回调方法
    webSocket.onmessage = function (event) {
        onMessage(event)
    };

    function onMessage(event) {
        var mess = document.createElement("div")
        mess.setAttribute("class", "message");
        var text = document.createTextNode(new Date().toLocaleTimeString() + " : " + event.data);
        mess.appendChild(text);
        reciver.appendChild(mess);
    }

    function onOpen(event) {
        reciver.innerHTML = new Date().toLocaleTimeString() + " : Connection established";
    }

    function onError(event) {
        reciver.innerHTML = "<b style='color:red;'>Connected faild</b>";
    }

    function send() {
        webSocket.send('hello ' + Math.random());
        return false;
    }
    //连接关闭的回调方法
    websocket.onclose = function () {
        console.log("ws连接关闭");

    }
</script>
</body>
</html>
