package rsvier.workshop.dao;

import java.util.*;
import rsvier.workshop.domain.Order;

public interface OrderDAO {
	
	public List<Order> getAllOrder();
	public Order getOrder(Order order);
	public Order getOrderById(int orderId);
	public void createOrder(Order order);
	public void updateOrder(Order order);
	public void deleteOrder(Order order);
}
