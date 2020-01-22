<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>WebSocket client</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<button onclick="connect()">connect</button>--%>
<%--<button onclick="close()">close</button>--%>
<%--<button onclick="sendMsg()">sendMsg</button>--%>
<%--</body>--%>
<%--<script type="text/javascript">--%>
<%--    var socket;--%>
<%--    if (typeof (WebSocket) == "undefined"){--%>
<%--        alert("This explorer don't support WebSocket")--%>
<%--    }--%>
<%--    function connect() {--%>
<%--        //Connect WebSocket server--%>
<%--        socket =new WebSocket("ws://10.3.6.5:8080/kafka_web_war/wbSocket");--%>
<%--        //open--%>
<%--        socket.onopen = function () {--%>
<%--            setMessageInnerHTML("WebSocket is open");--%>
<%--        }--%>
<%--        //Get message--%>
<%--        socket.onmessage = function (msg) {--%>

<%--            setMessageInnerHTML(msg.data);--%>
<%--            // alert("Message is " + msg.data);--%>
<%--        }--%>
<%--        //close--%>
<%--        socket.onclose = function () {--%>
<%--            setMessageInnerHTML("WebSocket is closed");--%>
<%--        }--%>
<%--        //error--%>
<%--        socket.onerror = function (e) {--%>
<%--            setMessageInnerHTML("Error is " + e);--%>
<%--        }--%>
<%--        //将消息显示在网页上--%>
<%--        function setMessageInnerHTML(innerHTML) {--%>
<%--            document.getElementById('message').innerHTML += innerHTML + '<br/>';--%>
<%--        }--%>
<%--    }--%>

<%--    function close() {--%>
<%--        socket.close();--%>
<%--    }--%>

<%--    function sendMsg() {--%>
<%--        socket.send("This is a client message ");--%>
<%--    }--%>
<%--</script>--%>
<%--</html>--%>


<!DOCTYPE html>
<html>
<head>
    <title>Java后端WebSocket的Tomcat实现</title>
</head>
<body>
Welcome<br/><input id="text" type="text"/>
<button onclick="send()">发送消息</button>
<button onclick="connect()">connect</button>
<hr/>
<button onclick="closeWebSocket()">关闭WebSocket连接</button>
<hr/>
<div id="message"></div>
</body>

<script type="text/javascript">
    var websocket = null;
    function connect() {
        //判断当前浏览器是否支持WebSocket
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://10.3.6.5:8080/kafka_web_war/wbSocket");
        } else {
            alert('当前浏览器 Not support websocket')
        }

        //连接发生错误的回调方法
        websocket.onerror = function () {
            setMessageInnerHTML("WebSocket连接发生错误");
        };

        //连接成功建立的回调方法
        websocket.onopen = function () {
            setMessageInnerHTML("WebSocket连接成功");
        }

        //接收到消息的回调方法
        websocket.onmessage = function (event) {
            setMessageInnerHTML(event.data);
        }

        //连接关闭的回调方法
        websocket.onclose = function () {
            setMessageInnerHTML("WebSocket连接关闭");
        }
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        document.getElementById('message').innerHTML += innerHTML + '<br/>';
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send() {
        var message = document.getElementById('text').value;
        websocket.send(message);
    }
</script>
</html>
