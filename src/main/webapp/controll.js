var ws = null ;
var userName ;
var gender ;
var img ;
$(window).ready(function(){
    var searchParams = new URLSearchParams(window.location.search);
    if(searchParams.has('userName')){
        userName = searchParams.get('userName');
        gender = searchParams.get('gender');
    }
    if(gender ==="male"){
        $("#welcome").html("Welcome Mr. " + userName);

    }else {
        $("#welcome").html("Welcome Mrs. "+ userName);
    }
    ws = new WebSocket("ws://localhost:9090/ajax/wsChat/chat");
    ws.onopen= function(){
        console.log("socket establist hed connection");
        sendMessage();
    };
    ws.onmessage= function (event){
        var messages = JSON.parse(event.data);
        $("#messages").html("");
        $("#users").html("");
        for (i = 0 ; i<messages.length ; i++ ){
            console.log(messages[i].userName);
            var mess = messages[i].message;
            // check if the message represent the user or message itself
            if(mess!==""){

                    $("#messages").append("<div style='background:#ddd; margin: 20px; '><img src='images/"+messages[i].gender+".jpg' width='50px' height='50px' /><span><div>"+messages[i].userName+"</div> <div>"+messages[i].message+"</div></span></div>");


            }else {
                $("#users").append("<div style='background:#ddd; margin: 20px; '><img src='images/"+messages[i].gender+".jpg' width='50px' height='50px' /><span><div>"+messages[i].userName+"</div> <div>"+new Date(messages[i].date)+"</div></span></div>")
            }
        }
    };
});

function sendMessage(){
    var messageObj = {
        userName:userName,
        gender:gender,
        message:$("#userMessage").val(),
        date:Date.now()
    };
    console.log(messageObj);
    ws.send(JSON.stringify(messageObj));
}
function isOpen(ws) {
    return ws.readyState === ws.OPEN
}
function logout(event){
    var messageObj = {
        userName:userName,
        gender:gender,
        message:"logout1010",
        date:Date.now()
    };
    ws.send(messageObj);
    ws.close();
}
