<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>商城购物车</title>
<link rel="stylesheet" href="../css/header.css" />
<link rel="stylesheet" href="../css/footer.css" />
<link rel="stylesheet" href="../css/cart.css" />
</head>
<body>

	<!-- 页面顶部-->
	<c:import url="indextop.jsp"></c:import>

	<div class="modal" style="display: none">
		<div class="modal_dialog">
			<div class="modal_header">删除提醒</div>
			<div class="modal_information">
				<img src="../images/model/model_img2.png" alt="" /> <span>确定删除您的这个宝贝吗？</span>
			</div>
			<div class="yes">
				<span>删除</span>
			</div>
			<div class="no">
				<span>不删除</span>
			</div>
		</div>
	</div>
	<div class="modalNo" style="display: none">
		<div class="modal_dialog">
			<div class="modal_header">
				删除提示 <img src="../images/model/model_img1.png" alt=""
					class="rt close" />
			</div>
			<div class="modal_information">
				<img src="../images/model/model_img2.png" alt="" /> <span>请选择商品</span>
			</div>
		</div>
	</div>
	<div class="big">

		<section id="section">
			<div id="title">
				<b>购物车</b>
				<p>
					已选<span class="total color">0</span>件商品<span class="interval"></span>合计(不含运费):<span
						class="totalPrices color susum">0.00</span><span
						class="unit color">元</span>
				</p>
			</div>
			<form name="" action="../order/confirm.do" method="get">
				<div id="box">
					<div id="content_box">
						<div class="imfor_top">
							<div class="check_top">
								<div class="all">
									<span class="normal"> <img
										src="../images/cart/product_normal.png" alt="" />
									</span> <input type="hidden" name="" value="">全选
								</div>
							</div>
							<div class="pudc_top">商品</div>
							<div class="pices_top">单价(元)</div>
							<div class="num_top">数量</div>
							<div class="totle_top">金额</div>
							<div class="del_top">操作</div>
						</div>

						<c:forEach items="${carts}" var="cart">
							<div class="imfor">
								<div class="check">
									<div class="Each">
										<!-- <span class="normal">
                                <img src="../images/cart/product_normal.png" alt=""/>
                            </span>-->
										<input type="checkbox" name="cartId" value="${cart.id }" /> <input
											type="hidden" name="" value="">
									</div>
								</div>
								<div class="pudc">
									<div class="pudc_information" id="pudcId3">
										<img width=100 height=80
											src="..${cart.goods.image }"
											class="lf" /> <input type="hidden" name="" value="">
										<span class="des lf"> ${cart.goods.title } <input
											type="hidden" name="" value="">
										</span>
										<p class="col lf">
											<span>分类：</span><span class="color_des">${cart.goods.itemType }
												<input type="hidden" name="" value="">
											</span>
										</p>
									</div>
								</div>
								<div class="pices">
									<p class="pices_des">达内专享价</p>
									<p class="pices_information">
										<b>￥</b><span>${cart.goods.price }.00 <input
											type="hidden" name="" value=""></span>
									</p>
								</div>
								<div class="num">
									<span class="reduc">&nbsp;-&nbsp;</span><input type="text"
										value="${cart.goodsCount }"><span class="add">&nbsp;+&nbsp;</span>
								</div>
								<div class="totle">
									<span>￥</span> <span class="totle_information">${cart.goods.price*cart.goodsCount }.00</span>
								</div>
								<div class="del">
									<!-- <div>
                            <img src="img/true.png" alt=""/>
                            <span>已移入收藏夹</span>
                        </div>
                         <a href="javascript:;" class="del_yr">移入收藏夹</a>
                        -->
									<a href="javascript:void(${cart.id });" class="del_d">删除</a>
								</div>
							</div>
						</c:forEach>

					</div>
					<div class="foot">
						<div class="foot_check">
							<div class="all">
								<span class="normal"> <img
									src="../images/cart/product_normal.png" alt="" />
								</span> <input type="hidden" name="" value="">全选
							</div>
						</div>
						<a href="javascript:;" class="foot_del">删除</a>
						<!--<a href="javascript:;" class="foot_yr">移入收藏夹</a>-->
						<div class="foot_qk">清空失效商品</div>
						<div class="foot_cash" id="go-buy">
							<input type="submit" class="submit" value="去结算" />
						</div>
						<div class="foot_tol">
							<span>合计(不含运费):</span><span class="foot_pices susumOne">0.00</span><span
								class='foot_des'>元</span>
						</div>
						<div class="foot_selected">
							已选<span class="totalOne color">0</span>件商品
						</div>
					</div>
				</div>
			</form>
		</section>

		<div class="none" style="display: none">
			<p class="none_title">购物车</p>
			<div class="none_top"></div>
			<div class="none_content">
				<img src="../images/30.png" alt="" class="lf" />
				<p class="lf">您的购物车竟然还是空哒( ⊙ o ⊙ )</p>
				<span class="lf">赶快去下单吧！</span> <a href="#" class="lf">去购物>></a>
			</div>

		</div>
	</div>
	<!-- 页面底部信息 -->
	<%@ include file="footer.jsp"%>

	<script>
	<!-- 显示空购物车页面-->
		$(function() {
			if (!$(".imfor")) {
				$('#section').hide();
				$('.none').show();
			}
		})
		$("#go-buy").click(function() {
			window.location.href = "orderConfirm.html";
		})
	</script>
</body>
</html>