package store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import store.bean.Order;
import store.bean.OrderItem;
import store.ex.OrderCreationException;
import store.mapper.OrderMapper;
import store.service.IOrderService;

@Service("orderService")
public class OrderServiceImpl implements IOrderService {
	@Resource(name = "orderMapper")
	private OrderMapper orderMapper;

	@Transactional
	public void createOrder(Order order, List<OrderItem> orderItems) {
		// 增加订单
		Integer affectedRows = orderMapper.insertOrder(order);
		if (affectedRows != 1) {
			throw new OrderCreationException("创建订单失败");
		}
		// 增加订单商品
		for (OrderItem orderItem : orderItems) {
			affectedRows = orderMapper.insertOrderItem(orderItem);
			if (affectedRows != 1) {
				throw new OrderCreationException("创建订单失败");
			}
		}
	}

}
