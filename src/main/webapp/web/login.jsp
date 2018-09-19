<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>学子商城登陆页面</title>
    <link href="../css/login.css" rel="stylesheet" />
    <link href="../css/header.css" rel="stylesheet"/>
  <link href="../css/footer.css" rel="stylesheet"/>
  <link href="../css/animate.css" rel="stylesheet"/>
</head>
<body>
<!-- 页面顶部-->
<header id="top">
    <div class="top">
        <img src="../images/header/logo.png" alt=""/>
        <span>欢迎登录</span>
    </div>
</header>
<div id="container">
    <div id="cover" class="rt">
        <form id="login-form" method="post" name="form1">
            <div class="txt">
                <p>
					登录学子商城<span><a href="register.do">新用户注册</a></span>
                </p>
                <div class="text">
                    <input type="text" placeholder="请输入您的用户名" name="lname" id="username" required>
                    <span><img src="../images/login/yhm.png"></span>
                </div>
                
                <div class="text">
                    <input type="password" id="password" placeholder="请输入您的密码" name="lwd" required minlength="6" maxlength="15">
                    <span><img src="../images/login/mm.png"></span>
                </div>
                <div class="chose">
                    <input type="checkbox" class="checkbox" id="ck_rmbUser">自动登录
                    <span>忘记密码？</span>
                </div>
                <input class="button_login" type="button" value="登录" id="bt-login"/>
            </div>
        </form>
    </div>
</div>
<!--错误提示-->
<div id="showResult"></div>
<!-- 页面底部信息 -->
<%@ include file="footer.jsp" %>


<script>
    $("#username").blur(function(){
        var data = $("#username").val();
        if (data == null || data == "") {
            $("#showResult").text("用户名不能为空！");
            $("#showResult").css("color","red");
            return false;
        }
    });
</script>
<script>
    $('#bt-login').click(function(){
        //异步提交登录请求
		$.ajax({
			"url": 'handle_login.do',
			"data": "username="+$("#username").val()+"&password="+$("#password").val(),
            "type": 'POST',
            "dataType":"json",
            success: function(obj){
                if(obj.state==1){  //登录成功
                	//保存cookie
            		SaveCookie();
                   location.href="../main/index.do";
                }else{ //登录失败
                	$("#showResult").css("color","red");
                    $('#showResult').text('登录失败！错误消息为：'+obj.message);
                    
                }
            }
        }); 
		
    });
</script>
<script type="text/javascript">

    $(document).ready(function () {
        if ($.cookie("rmbUser") == "true") {
            $("#ck_rmbUser").attr("checked", true);
            $("#username").val($.cookie("username"));
            $("#password").val($.cookie("password"));
        }
    });

    //记住用户名密码
    function SaveCookie() {
        if ($("#ck_rmbUser").prop("checked")) {
            var str_username = $("#username").val();
            console.log(str_username);
            var str_password = $("#password").val();
            $.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
            $.cookie("username", str_username, { expires: 7 });
            $.cookie("password", str_password, { expires: 7 });
        }
        else {
            $.cookie("rmbUser", "false", { expire: -1 });
            $.cookie("username", "", { expires: -1 });
            $.cookie("password", "", { expires: -1 });
        }
    };
</script>
</body>
</html>