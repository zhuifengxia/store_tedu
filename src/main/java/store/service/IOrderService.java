package store.service;

import java.util.List;

import store.bean.Order;
import store.bean.OrderItem;

public interface IOrderService {
	/**
	 * 创建订单
	 * 
	 * @param order订单信息
	 * @param items订单商品信息
	 */
	void createOrder(Order order, List<OrderItem> orderItems);
}
