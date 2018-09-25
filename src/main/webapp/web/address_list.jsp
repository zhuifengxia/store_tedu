<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>我的订单 - 达内学子商城</title>
<link href="../css/orders.css" rel="stylesheet" />
<link href="../css/header.css" rel="stylesheet" />
<link href="../css/footer.css" rel="stylesheet" />
<link href="../css/personage.css" rel="stylesheet" />
<link href="../css/common.css" rel="stylesheet" />
</head>
<body>
	<!-- 页面顶部-->
	<c:import url="indextop.jsp"></c:import>
	<!-- 我的订单导航栏-->
	<div id="nav_order">
		<ul>
			<li><a href="">首页<span>&gt;</span>个人中心
			</a></li>
		</ul>
	</div>
	<!--我的订单内容区域 #container-->
	<div id="container" class="clearfix">
		<!-- 左边栏-->
		<c:import url="user_left.jsp"></c:import>
		<!-- 右边栏-->
		<div class="rightsidebar_box rt">
			<!--标题栏-->
			<div class="rs_header">
				<span class="address_title">收获地址管理</span>
			</div>
			<!--收货人信息填写栏-->
			<div class="rs_content">

				<!--已有地址栏-->
				<div class="address_list">
					<div class="header">
						<span class="tag">地址名称</span> <span class="name">姓名</span> <span
							class="addr">地址详情</span> <span class="phone">联系电话</span> <span
							class="op">操作</span>
					</div>
					<div id="address_list" style="margin-top: 20px;">
						<div class="content content_active">
							<span class="tag tag_active">办公室</span> <span class="name">杨洋</span>
							<span class="addr">北京市海淀区北下关街道中鼎大厦B座331</span> <span
								class="phone">18435110514</span> <span class="op"> <a
								href="#">修改</a>| <a href="#">删除</a>
							</span> <span class="set_default"></span>
						</div>
					</div>
				</div>
				<!-- 新增收货地址按钮 -->
				<div class="buttions-block">
					<a href="#" onclick="showPopup(0)" class="button-blue">新添加收货人地址</a>
				</div>

			</div>

		</div>
	</div>

	<!-- 页面底部信息 -->
	<%@ include file="footer.jsp"%>

	<!-- 弹出窗口，添加/编辑收货信息 -->
	<div id="popup_content">
		<!-- 标题 -->
		<h3>新增加收货地址</h3>
		<div class="rs_content">
			<form method="post" action="" id="address-form">
				<!--收货人姓名-->
				<div class="recipients">
					<span class="red">*</span><span class="kuan">收货人：</span><input
						type="text" name="recvName" id="recvName" />
				</div>
				<!--收货人所在城市等信息-->
				<div data-toggle="distpicker" class="address_content">
					<span class="red">*</span><span class="kuan">省&nbsp;&nbsp;份：</span><select
						data-province="---- 选择省 ----" id="recvProvince"
						name="recvProvince" onclick="getCities(-1,-1)"
						onchange="getCities(-1,-1)"></select> 城市：<select
						data-city="---- 选择市 ----" id="recvCity" name="recvCity"
						onclick="getAreas(-1)" onchange=getAreas(-1)></select> 区/县：<select
						data-district="---- 选择区 ----" id="recvArea" name="recvArea"></select>
				</div>
				<!--收货人详细地址-->
				<div class="address_particular">
					<span class="red">*</span><span class="kuan">详细地址：</span>
					<textarea name="recvAddr" id="recvAddr" cols="60" rows="3"
						placeholder="建议您如实填写详细收货地址"></textarea>
				</div>
				<!--收货人地址-->
				<div class="address_tel">
					<span class="red">*</span><span class="kuan">手机号码：</span><input
						type="tel" id="recvPhone" name="recvPhone" />固定电话：<input
						type="text" name="recvTel" id="recvTel" />
				</div>
				<!--邮政编码-->
				<div class="address_postcode">
					<span class="red">&nbsp;</span><span>邮政编码：</span>&nbsp;<input
						type="text" name="recvZip" id="recvZip" />
				</div>
				<!--地址名称-->
				<div class="address_name">
					<span class="red">&nbsp;</span><span>地址名称：</span>&nbsp;<input
						type="text" id="recvTag" name="recvTag" />如：<span class="sp">家</span><span
						class="sp">公司</span><span class="sp">宿舍</span>
				</div>
				<!--保存收货人信息-->
				<!-- 新增收获地址按钮 -->
				<div class="buttions-block">
					<!-- 添加隐藏域  主要目的是给表单中缺少的值补全-->
					<input type="hidden" name="recvDistrict" id="recvDistrict" /> <input
						type="hidden" name="id" id="id" /> <a href="#"
						onclick="postForm()" class="button-blue">保存收货人信息</a> <a href="#"
						class="button-blue" onclick="dismissPopup()">取消</a>
					<div style="clear: both;"></div>
				</div>

			</form>
		</div>
	</div>
	<div id="mask"></div>
</body>

<script type="text/javascript">
	$(function() {
		$("#leftsidebar_box dd").hide();
		$("#leftsidebar_box .address dd").show();
		$("#leftsidebar_box dt img").attr("src",
				"../images/myOrder/myOrder2.png");
		$("#leftsidebar_box .address dt").find('img').attr("src",
				"../images/myOrder/myOrder1.png");
		//=====加载地址列表====
		showAddressList();
	});
	
	$(".lxdh_normal").each(function(i, e) {
		var phone = $(e).html();
		$(e).html(changePhone(phone));
	});

	var actionId;

	//显示弹出窗口
	function showPopup(id) {
		//清空表单中各个控件的已有的值
		$("#address-form")[0].reset();
		//reset()是js的函数
		actionId = id;
		//将id设置到隐藏域中，以便后续一并提交
		$("#id").val(id);
		//根据id 判断当前的操作是增加还是编辑？
		var title = id == 0 ? "新增收货地址" : "编辑收货地址";
		$("#popup_content h3").html(title);
		//设置弹出区域的尺寸
		var popupWidth = 720;
		var popupHeight = 420;
		//获取窗口的尺寸
		var windowWidth = $(window).width();
		var windowHeight = $(document).height();
		//弹出mask--三层之间的中间层
		$("#mask").css({
			"width" : windowWidth,
			"height" : windowHeight
		});
		$("#mask").show();
		//弹出popup
		$("#popup_content").css({
			"width" : popupWidth,
			"height" : popupHeight,
			"left" : (windowWidth - popupWidth) / 2,
			"top" : 120
		});
		$("#popup_content").show();
		//直接调用该方法
		//getProvinces();
		//发出ajax请求，获取需要编辑的数据，并显示到前端界面上
		if (id != 0) {
			var url = "get.do";
			var data = "id=" + id;
			$.ajax({
				"url" : url,
				"data" : data,
				"type" : "get",
				"dataType" : "json",
				"success" : function(obj) {
					//data是服务层发送过来的数据
					var address = obj.data;
					//里面包含收货人的所有的数据
					//给一般的标签设置内容
					$("#recvName").val(address.recvName);
					$("#recvAddr").val(address.recvAddr);
					$("#recvPhone").val(address.recvPhone);
					$("#recvTel").val(address.recvTel);
					$("#recvZip").val(address.recvZip);
					$("#recvTag").val(address.recvTag);
					//将数值设置到getProvinces中显示，因为通过它进行显示省的下拉菜表
					//需要将数值传递到getProvince()里面
					//加载省的列表的时候，也把市的code提供
					//编辑地址
					getProvinces(address.recvProvince, address.recvCity,
							address.recvArea);

				}
			});
		} else {
			//增加地址
			getProvinces(-1, -1, -1);//provinceCode=-1 “是请选择的-----”
		}

	}
	//隐藏弹出窗口的方法
	function dismissPopup() {
		//隐藏mask
		$("#mask").hide();
		//隐藏popup
		$("#popup_content").hide();
	}

	//=====加载省的类表=====
	function getProvinces(provinceCode, cityCode, areaCode) {
		var url = "../dict/provinces.do";
		$.ajax({
			"url" : url,
			"type" : "GET",
			"dataType" : "json",
			"success" : function(obj) {
				//alert(obj.state);
				//添加默认节点
				var op = document.createElement("option");
				op.value = -1;
				op.text = "----请选择----";
				document.getElementById("recvProvince").appendChild(op);
				for (var i = 0; i < obj.data.length; i++) {
					var op = document.createElement("option");
					op.value = obj.data[i].provinceCode;
					op.text = obj.data[i].provinceName;
					document.getElementById("recvProvince").appendChild(op);
				}
				//选中默认的选项option、
				$("#recvProvince").val(provinceCode);
				//加载城市列表
				getCities(cityCode, areaCode);
			}
		});

	}

	//=====加载市的类表=====
	function getCities(cityCode, areaCode) {
		//清空下拉菜单的内容
		$("#recvCity").empty();
		$("#recvArea").empty();
		var url = "../dict/cities.do";
		var data = "provinceCode=" + $("#recvProvince").val();
		$.ajax({
			"url" : url,
			"type" : "GET",
			"data" : data,
			"dataType" : "json",
			"success" : function(obj) {
				//alert(obj.state);
				//添加默认节点
				var op = document.createElement("option");
				op.value = -1;
				op.text = "----请选择----";
				document.getElementById("recvCity").appendChild(op);
				for (var i = 0; i < obj.data.length; i++) {
					var op = document.createElement("option");
					op.value = obj.data[i].cityCode;
					op.text = obj.data[i].cityName;
					document.getElementById("recvCity").appendChild(op);
				}
				//修改时，选中默认的选项
				//
				$("#recvCity").val(cityCode);
				//加载区列表
				getAreas(areaCode);
			}
		});

	}

	//=====加载市的区表=====
	function getAreas(areaCode) {
		//每次清空下拉菜单的内容
		$("#recvArea").empty();
		var url = "../dict/areas.do";
		var data = "cityCode=" + $("#recvCity").val();
		$.ajax({
			"url" : url,
			"type" : "GET",
			"data" : data,
			"dataType" : "json",
			"success" : function(obj) {
				//添加默认节点
				var op = document.createElement("option");
				op.value = -1;
				op.text = "----请选择----";
				document.getElementById("recvArea").appendChild(op);
				//alert(obj.state);
				for (var i = 0; i < obj.data.length; i++) {
					var op = document.createElement("option");
					op.value = obj.data[i].areaCode;
					op.text = obj.data[i].areaName;
					document.getElementById("recvArea").appendChild(op);
				}
				//添加默认的选项option
				$("#recvArea").val(areaCode);
			}
		});
	}
	
	
	
	//======提交表单信息=======
function postForm(){
	//获取隐藏域中设置的id值
	var id=$("#id").val();//根据数值判断是哪一种操作 id=0 是新增，否则就是修改
	var url=(id==0) ? "add.do":"handle_update.do";
	var distrct=$("#recvProvince").find("option:selected").text()+$("#recvCity").find("option:selected").text()+$("#recvArea").find("option:selected").text();
	
	$("#recvDistrict").val(distrct);
	//序列化中表单的数据，也包括隐藏域的id 只要有name属性 会一并提交 
	var data=$("#address-form").serialize();//提交表单信息
	//发出请求，并处理响应
	$.ajax({
		"url":url,
		"data":data,
		"type":"post",
		"dataType":"json",
		"success":function(obj){
			//提示操作成功
			alert(obj.message);
			//toDo 刷新列表
			showAddressList();
			//关闭弹出窗口 (无论是否添加或删除成功 )
			dismissPopup();
		},
		
		"error":function(){
			//alert("登录过期，重新登录！！");
			//location.href="../user/login.do";
		}
	});
}
	
	
//地址列表中每条数据的模板，使用带有特殊占位符的字符	
var htmlTemplate='<div class="content %CONTENT_ACTIVE%">'
                   +' <span class="tag %TAG_TYPE%">%TAG%</span>'
                   + '<span class="name">%NAME%</span>'
                   + '<span class="addr">%ADDRESS%</span>'
                   + '<span class="phone">%PHONE%</span>'
                   + ' <span class="op">'
                   + ' <a href="## id=%ID%" onclick="showPopup(%ID%)">修改</a> | '
                   + '<a href="##" onclick="deleteAddress(%ID%)">删除</a>'
                   + '</span>'
                   + '<span class="set_default">'
                   +' <a href="##" onclick="setDefault(%ID%)" style="display:%SET_DEFAULT%;">设为默认</a> ' 
                   + '</span>'
                   +'</div>';
    
//展示地址列表
function showAddressList(){	
	var url="get_list.do";
	$.ajax({
		"url":url,
		"type":"GET",
		"dataType":"json",
		"success":function(obj){
			//清空原有的所有的列表内容
			$("#address_list").empty();
			var htmlString="";
			for(var i=0;i<obj.data.length;i++){
			//获取档次遍历的收货的地址数据
			var address=obj.data[i];
				//准备模板并替换占位符
				htmlString+=htmlTemplate;
				htmlString=htmlString.replace("%TAG%",address.recvTag);
				htmlString=htmlString.replace("%NAME%",address.recvName);
				htmlString=htmlString.replace("%ADDRESS%",address.recvDistrict+address.recvAddr);
				htmlString=htmlString.replace("%PHONE%",address.recvPhone);
				htmlString=htmlString.replace(/%ID%/g,address.id);
				if(address.isDefault==1){
					htmlString=htmlString.replace("%CONTENT_ACTIVE%","content_active");
					htmlString=htmlString.replace("%TAG_TYPE%","tag_active");
					htmlString=htmlString.replace("%SET_DEFAULT%","none");
				}else{
				    htmlString=htmlString.replace("%CONTENT_ACTIVE%","");
					htmlString=htmlString.replace("%TAG_TYPE%","tag_normal");
					htmlString=htmlString.replace("%SET_DEFAULT%","inline");
				}
			}	
			//将内容填充到#address_list中
			$("#address_list").html(htmlString);
		}
	});
}

//删除用户的地址
function deleteAddress(id){
	//再次确定是否需要删除
	var c=confirm("你确定要删除这条这条用户地址吗");
		if(!c){
			return;
		}
	var url="delete.do";
	var data="id="+id;
	$.ajax({	
		"url":url,
		"data":data,
		"type":"get",
		"dataType":"json",
		"success":function(obj){
			alert(obj.message);
			if(obj.state==1){
				//删除成功以后，重写刷在一下显示界面
				showAddressList();
			}
		},
		"error":function(){
			alert("登录过期，重新登录！！");
			location.href="../user/login.do";
		}
	});
}

//设置默认地址
function setDefault(id){
	 var data="id="+id;
	 $.ajax({
		"url":"set_default.do",
		"data":data,
		"type":"get",
		"dataType":"json",
		"success":function(obj){
			alert(obj.message);
			//成功
			if(obj.state==1){
				//重新刷新一下显示界面
				showAddressList();
			}else{
			//失败
				alert(obj.message);
			}
			
		},
		"error":function(){
			//重定向 在js中
			alert("登录过期，重新登录！！");
			location.href="../user/login.do";
		}
		 
		 
		 
	 });
	
}   
</script>
</html>