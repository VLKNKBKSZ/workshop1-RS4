package rsvier.workshop.dao;

import java.util.*;
import rsvier.workshop.domain.Order;
import rsvier.workshop.domain.Person;

public interface OrderDAO {
	
	public List<Order> getAllOrder();
	public Order getOrderById(int orderId);
	public void createOrder(Order order, Person person);
	public void updateOrder(Order order);
	public void deleteOrder(Order order);
}
