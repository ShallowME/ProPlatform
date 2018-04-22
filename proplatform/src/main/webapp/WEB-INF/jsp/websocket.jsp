
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chatting</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        var username = "${sessionScope.get("username")}";
        var ws;
        var target = updateTarget("/webSocket/chat?username=" + username);
        window.onload=function () {
            subOpen();
        }

        function subOpen() {
            if ('WebSocket' in window) {
                ws = new WebSocket(target);
            } else if ('MozWebSocket' in window) {
                ws = new MozWebSocket(target);
            } else {
                alert('WebSocket is not supported by this browser.');
                return;
            }
            $("#host").append(username);

            ws.onmessage = function (event) {
                console.log(event);
                var push = $.parseJSON(event.data);
                alert(push.msg);
                // eval("var msg=" + event.data + ";");

                if (push.msg != null) {
                    $("#content-pane").append(push.msg + "<br/>");
                }

                if (push.usernames != null) {
                    $("#online_list").html("");
                    $(push.usernames).each(function () {
                        $("#online_list").append("<input type='checkbox' value='"+this+"'/>"+this+"<br/>");
                    });
                }
            }
        }

        function updateTarget(target) {
            if (window.location.protocol === 'http:') {
                return 'ws://' + window.location.host + target;
            } else {
                return 'wss://' + window.location.host + target;
            }
        }

        function subSend() {
            var obj = null;
            var msg = document.getElementById("msg").value;
            var checked = $("#online_list").find(":checked");
            console.log(checked.length);
            if (checked.length === 0) {
                obj = {
                    msg:msg,
                    type:1  //群聊
                }
            }else {
                var to = $("#online_list").find(":checked").val();
                // console.log(to);
                obj = {
                    to: to,
                    msg: msg,
                    type:2  //私聊
                };
            }
            var str = JSON.stringify(obj);
            console.log(str);
            ws.send(str);
            document.getElementById("msg").value = "";
        }
    </script>
</head>
<body>
<div id="container" style="border: 1px solid blueviolet; width: 400px;height: 400px; float: left">
    <div id="content-pane" style="height: 350px"></div>

    <div id="host"  style="border-top: 1px solid aqua; width: 400px;height: 50px;">
        <input id="msg"/>
        <button onclick="subSend();">send</button>
    </div>
</div>


<div id="online_list" style="border: 1px solid brown ;width: 100px; height: 400px;float: left;">

</div>


</body>
</html>
