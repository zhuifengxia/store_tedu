package store.mapper;

import store.bean.Order;
import store.bean.OrderItem;

public interface OrderMapper {

	/**
	 * 增加订单记录
	 * 
	 * @param order订单信息
	 * @return受影响的行数
	 */
	Integer insertOrder(Order order);

	/**
	 * 增加订单中的商品记录
	 * 
	 * @param item
	 * @return
	 */
	Integer insertOrderItem(OrderItem item);
}
