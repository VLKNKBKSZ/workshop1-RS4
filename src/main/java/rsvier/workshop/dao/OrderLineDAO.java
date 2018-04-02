package rsvier.workshop.dao;
import java.util.*;

import rsvier.workshop.domain.OrderLine;

public interface OrderLineDAO {
	public List<OrderLine> getAllOrderLines();
	public List<OrderLine> getAllOrderLinesFromProduct(int productId);
	public List<OrderLine> getAllOrderLinesFromOrder(int orderId);
	public OrderLine getOrderLine(int orderLineId);
	public void createOrderLine(OrderLine orderLine);
	public void deleteOrderLine(OrderLine orderLine);
	public void updateOrderLine(OrderLine orderLine);
	
}
