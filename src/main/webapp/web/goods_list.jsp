<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>商品搜索页面</title>
<link rel="stylesheet" href="../css/header.css" />
<link rel="stylesheet" href="../css/search.css" />
<link rel="stylesheet" href="../css/footer.css" />
<link rel="stylesheet" href="../css/common.css" />
</head>

<body>
	<!-- 页面顶部-->
	<c:import url="indextop.jsp"></c:import>
	<div class="big">
		<form name="" action="" method="post">
			<section id="section">
				<p class="header">全部结果>笔记本(${goodsCount})</p>
				<div id="wrap">

					<c:forEach items="${goodsList }" var="goods">
						<div class="lf box">
							<div class="info">
								<div class="img pic">
									<a href="###" onclick="showGoodsDetails(${goods.id})"><img
										src="${ pageContext.request.contextPath}${goods.image}"
										alt="${goods.title }" title="${goods.title }" /> </a>
								</div>
								<div class="describe">
									<p>
										<a href="###" onclick="showGoodsDetails(${goods.id})">${fn:substring(goods.title, 0, 40)}...</a>
									</p>
									<span class="price"><b>￥</b><span class="priceContent">${goods.price }</span></span>
									<span class="addCart"><img id="collect"
										src="../images/search/care.png" alt="" /><a
										href="javascript:void(0);" class="add_cart">加入购物车</a></span>
								</div>
							</div>
						</div>
					</c:forEach>
					<div style="clear: both"></div>
				</div>
				<!-- 分页 -->
				<div id="pages">
					<ul>
						<c:forEach begin="1" end="${pages}" var="i">
							<li><a
								<c:if test="${i==currentPage }">class="current"</c:if>
								href="?categoryId=${goodsList[0].categoryId }&page=${i}">${i}</a></li>
						</c:forEach>
						<div style="clear: both"></div>
					</ul>
					<span>共${goodsCount}种商品，每页显示${countPerPage}条&nbsp;&nbsp;|&nbsp;&nbsp;</span>
					<div style="clear: both"></div>
				</div>
			</section>
		</form>
	</div>
	<!-- 尾部-->
	<!-- 页面底部信息 -->
	<%@ include file="footer.jsp"%>

	<div class="modal" style="display: none">
		<div class="modal_dialog">
			<div class="modal_header">操作提醒</div>
			<div class="modal_information">
				<img src="../images/model/model_img2.png" alt="" /> <span>将您的宝贝加入购物车？</span>

			</div>
			<div class="yes">
				<span>确定</span>
			</div>
			<div class="no">
				<span>取消</span>
			</div>
		</div>
	</div>

	<script>
	$(".add_cart").click(function(){
		$(".modal").show();
		$(".modal .modal_information span").html("将您的宝贝加入购物车?");
	})
	$(".yes").click(function(){
	    $(".modal").hide();
	})
	$('.no').click(function(){
    	$('.modal').hide();
    	
    })
</script>
	<!--<script type="text/javascript">
	// var status = ${status};
	var pages = ${pageBean.totalPages};
	var index = ${pageBean.pageIndex};
	$(".tcdPageCode").createPage({
		// 总页数
	    pageCount:pages,
	 	// 起始页
	    current:index,
	    backFn:function(p){
	    	// 执行代码
	    	window.location.href="http://localhost:18888/search.html?q=${q}&page="+p;
	    }
	});
</script>-->
	<script type="text/javascript">
    /* 商品详情页  */
	function showGoodsDetails(id) {
		window.location.href="details.do?id="+id;
	} 
</script>
	<script type="text/javascript">
	/**添加到收藏**/
    $("#collect").click(function(e){
    	$(".modal").show();
		$(".modal .modal_information span").html("将您的宝贝加入收藏夹");
    })
    $(".yes").click(function(){
	    $(".modal").hide();
	    $('#collect').attr("src","../images/search/care1.png");
    })
</script>
</body>
</html>