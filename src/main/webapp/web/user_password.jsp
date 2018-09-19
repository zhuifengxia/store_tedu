<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的订单 - 达内学子商城</title>
    <link href="../css/orders.css" rel="Stylesheet"/>
    <link href="../css/header.css" rel="Stylesheet"/>
    <link href="../css/footer.css" rel="Stylesheet"/>
    <link href="../css/personage.css" rel="stylesheet" />
    <link href="../css/common.css" rel="stylesheet" />
    <style>
    .msg-success {
    background-color: #0d0;
    color:white;
    padding:2px;
}
.msg-error {
    background-color: #d00;
     color:white;
     padding:2px;
}
    </style>
</head>
<body>

<!-- 页面顶部-->
<c:import url="indextop.jsp"></c:import>

<!-- 我的订单导航栏-->
<div id="nav_order">
    <ul>
        <li><a href="../main/index.do">首页<span>&gt;</span>个人中心</a></li>
    </ul>
</div>
<!--我的订单内容区域 #container-->
<div id="container" class="clearfix">
    <!-- 左边栏-->
    <c:import url="user_left.jsp"></c:import>
   
    <!-- 右边栏-->
    <!--个人信息头部-->
    <div class="rightsidebar_box rt">
        <div class="rs_header">
            <span ><a href="profile.do">我的信息</a></span>
            <span class="rs_header_active"><a href="password.do">安全管理</a></span>
        </div>

        <!--安全管理 -->
        <div class="rs_content">
            <p class="change_password_title">更改密码</p>
            <div class="new_password">
                <span class="word">输入旧密码：</span>
                <input type="password" id="old_password" maxlength="32"/>
                <span class="change_hint" id="old_password_hint" ></span>
            </div>
            <div class="new_password">
                <span class="word">输入新密码：</span>
                <input type="password" id="new_password" maxlength="32"/>
                <span class="change_hint" id="new_password_hint" ></span>
            </div>
            <div class="confirm_password">
                <span class="word">确认新密码：</span>
                <input type="password" id="confirm_password" maxlength="32"/>
                <span class="confirm_hint" id="confirm_password_hint"></span>
            </div>
            <div class="buttons-block">
                <a href="#" onclick="changePassword()" class="button-blue">保存更改</a>
            </div>
        </div>


    </div>
</div>
<!-- 页面底部信息 -->
<%@ include file="footer.jsp" %>
<script type="text/javascript">
$(function(){
	$("#leftsidebar_box dd").hide();
    $("#leftsidebar_box .count_managment dd").show();
    $("#leftsidebar_box dt img").attr("src","../images/myOrder/myOrder2.png");
    $("#leftsidebar_box .count_managment dt").find('img').attr("src","../images/myOrder/myOrder1.png");
})
function changePassword(){
	//获取密码框的值
	var pwd1=$("#old_password").val();
	var pwd2=$("#new_password").val();
	var pwd3=$("#confirm_password").val();
	//基础判断
	if(checkPasswordLength(pwd1)&&checkPasswordLength(pwd2)&&checkPasswordLength(pwd3)&&checkPasswordEquals()){
		$.ajax({
			"url":"handle_change_password.do",
			"data":"oldPassword="+pwd1+"&newPassword="+pwd2,
			"type":"POST",
			"dataType":"json",
			"success":function(obj){
				alert(obj.message);
				$("#old_password").val("");
				$("#new_password").val("");
				$("#confirm_password").val("");
				$("#old_password_hint").html("");
				$("#new_password_hint").html("");
				$("#confirm_password_hint").html("");
				$("#old_password_hint").attr("class","");
				$("#new_password_hint").attr("class","");
				$("#confirm_password_hint").attr("class","");
			},
			"error":function(){
				alert("出现未知错误");
				location.href="../main/index.do";
			}
		});
	}else{
		//不满足规则，不提交
		alert("请检查错误，修改后再提交");
	}
	
}

//修改密码验证
//密码至少6位长度
//两个新密码一致

function checkPasswordLength(pwd){
	return pwd.length>=6;
}

//密码校验
function checkPasswordEquals(){
	//验证两个密码是否一致
	var pwd1=$("#new_password").val();
	var pwd2=$("#confirm_password").val();
	if(pwd1==pwd2){
		$("#confirm_password_hint").html("密码一致");
		$("#confirm_password_hint").attr("class","msg-success");
		return true;
	}else{
		//两个密码不一致
		$("#confirm_password_hint").html("两次输入密码不一致");
		$("#confirm_password_hint").attr("class","msg-error");
		return false;
	}
}

//原密码丢失焦点时，
$("#old_password").blur(function(){
	//获取密码
	var pwd=$("#old_password").val();
	//判断密码长度
	if(checkPasswordLength(pwd)){
		$("#old_password_hint").html("密码格式正确");
		$("#old_password_hint").attr("class","msg-success");
	}else{
		//长度不够，旧密码肯定是错的
		$("#old_password_hint").html("密码长度必须是6位以上");
		$("#old_password_hint").attr("class","msg-error");
	}
});

//新密码丢失焦点时，
$("#new_password").blur(function(){
	//获取密码
	var pwd=$("#new_password").val();
	//判断密码长度
	if(checkPasswordLength(pwd)){
		$("#new_password_hint").html("密码格式正确");
		$("#new_password_hint").attr("class","msg-success");
		//验证两个密码是否一致
		checkPasswordEquals();
		return true;
	}else{
		//长度不够，新密码格式错误
		$("#new_password_hint").html("密码长度必须是6位以上");
		$("#new_password_hint").attr("class","msg-error");
		return false;
	}
	
	
});

//确认密码丢失焦点时，
$("#confirm_password").blur(function(){
	//获取密码
	var pwd=$("#confirm_password").val();
	//判断密码长度
	if(checkPasswordLength(pwd)){
		$("#confirm_password_hint").html("密码格式正确");
		$("#confirm_password_hint").attr("class","msg-success");
		//验证两个密码是否一致
		checkPasswordEquals();
		return true;
	}else{
		//长度不够，确认密码格式错误
		$("#confirm_password_hint").html("密码长度必须是6位以上");
		$("#confirm_password_hint").attr("class","msg-error");
		return false;
	}
	
});
</script>
</body>

</html>