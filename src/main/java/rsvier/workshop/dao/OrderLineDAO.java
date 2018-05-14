package rsvier.workshop.dao;
import java.util.*;

import rsvier.workshop.domain.*;


public interface OrderLineDAO {
	public List<OrderLine> getAllOrderLines();
	public List<OrderLine> getAllOrderLinesFromProduct(Product product);
	public List<OrderLine> getAllOrderLinesFromOrder(Order order);
	public OrderLine getOrderLine(int orderLineId);
	public void createOrderLine(List<OrderLine> orderLines, int orderId);
	public void createOrderLine(OrderLine orderLine, int orderId);
	public void deleteOrderLine(OrderLine orderLine);
	public void updateOrderLine(OrderLine orderLine);
	
}
