package rsvier.workshop.dao;

import java.util.*;
import java.util.logging.*;
import com.mongodb.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;

public class OrderLineDAOImpMongo implements OrderLineDAO {

	private DB db;
	private DBCollection collection;
	private Logger logger = LogConnection.getLogger();
	private ProductDAO productDao = DAOFactory.getProductDAO();

	public OrderLineDAOImpMongo() {
		try {
			db = DatabaseConnectionXML.getConnectionMongoDB();
			collection = db.getCollection("orderLine");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<OrderLine> getAllOrderLines() {

		List<OrderLine> orderLineList = new ArrayList<>();

		try (DBCursor cursor = collection.find().skip(1)) {

			while (cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject orderLineObj = (BasicDBObject) object;

				OrderLine.OrderLineBuilder orderLineBuilder = new OrderLine.OrderLineBuilder();
				orderLineBuilder.orderLineId(orderLineObj.getInt("_id"));
				orderLineBuilder.product(productDao.getProductById(orderLineObj.getInt("product_id")));
				orderLineBuilder.numberOfProducts(orderLineObj.getInt("number_of_products"));
				OrderLine orderLine = orderLineBuilder.build();
				orderLineList.add(orderLine);
			}

		}
		return orderLineList;
	}

	@Override
	public List<OrderLine> getAllOrderLinesFromProduct(Product product) {

		List<OrderLine> orderLineList = new ArrayList<>();
		DBObject query = new BasicDBObject("product_id", product.getProductId());

		try (DBCursor cursor = collection.find(query)) {

			while (cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject orderLineObj = (BasicDBObject) object;

				OrderLine.OrderLineBuilder orderLineBuilder = new OrderLine.OrderLineBuilder();
				orderLineBuilder.orderLineId(orderLineObj.getInt("_id"));
				orderLineBuilder.product(productDao.getProductById(orderLineObj.getInt("product_id")));
				orderLineBuilder.numberOfProducts(orderLineObj.getInt("number_of_products"));
				OrderLine orderLine = orderLineBuilder.build();
				orderLineList.add(orderLine);
			}

		}
		return orderLineList;
	}

	@Override
	public List<OrderLine> getAllOrderLinesFromOrder(Order order) {

		List<OrderLine> orderLineList = new ArrayList<>();
		DBObject query = new BasicDBObject("order_id", order.getOrderId());

		try (DBCursor cursor = collection.find(query)) {

			while (cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject orderLineObj = (BasicDBObject) object;

				OrderLine.OrderLineBuilder orderLineBuilder = new OrderLine.OrderLineBuilder();
				orderLineBuilder.orderLineId(orderLineObj.getInt("_id"));
				orderLineBuilder.product(productDao.getProductById(orderLineObj.getInt("product_id")));
				orderLineBuilder.numberOfProducts(orderLineObj.getInt("number_of_products"));
				OrderLine orderLine = orderLineBuilder.build();
				orderLineList.add(orderLine);
			}

		}
		return orderLineList;
	}

	@Override
	public OrderLine getOrderLine(int orderLineId) {

		OrderLine orderLine = null;
		DBObject query = new BasicDBObject("_id", orderLineId);

		try (DBCursor cursor = collection.find(query)) {

			if (cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject orderLineObj = (BasicDBObject) object;

				OrderLine.OrderLineBuilder orderLineBuilder = new OrderLine.OrderLineBuilder();
				orderLineBuilder.orderLineId(orderLineObj.getInt("_id"));
				orderLineBuilder.product(productDao.getProductById(orderLineObj.getInt("product_id")));
				orderLineBuilder.numberOfProducts(orderLineObj.getInt("number_of_products"));
				orderLine = orderLineBuilder.build();

			}

		}
		return orderLine;
	}

	@Override
	public void createOrderLine(List<OrderLine> orderLines, int orderId) {

		for(OrderLine orderLine : orderLines) {
			
			double generatedIdDouble = (Double) getNextSequence("orderLine_id");
			int generatedIdInteger = (int) generatedIdDouble;
			
		DBObject newOrderLine = new BasicDBObject("_id",generatedIdInteger)
				.append("product_id", orderLine.getProduct().getProductId())
				.append("order_id", orderId)
				.append("number_of_products",orderLine.getNumberOfProducts());
		
				collection.insert(newOrderLine);

	}
	}
	@Override
	public void createOrderLine(OrderLine orderLine, int orderId) {
		
		double generatedIdDouble = (Double) getNextSequence("orderLine_id");
		int generatedIdInteger = (int) generatedIdDouble;
		
	DBObject newOrderLine = new BasicDBObject("_id",generatedIdInteger)
			.append("product_id", orderLine.getProduct().getProductId())
			.append("order_id", orderId)
			.append("number_of_products",orderLine.getNumberOfProducts());
	
			collection.insert(newOrderLine);
	}

	@Override
	public void deleteOrderLine(OrderLine orderLine) {
		collection.remove(new BasicDBObject("_id", orderLine.getOrderLineId()));

	}

	@Override
	public void updateOrderLine(OrderLine orderLine) {
		
		DBObject updateOrderLine = new BasicDBObject("_id",orderLine.getOrderLineId())
				.append("product_id", orderLine.getProduct().getProductId())
				.append("number_of_products",orderLine.getNumberOfProducts());
		
		collection.update(new BasicDBObject("_id",orderLine.getOrderLineId()), updateOrderLine);

	}

	// Method of auto-increment Id
	public Object getNextSequence(String orderLineId) {

		BasicDBObject find = new BasicDBObject();
		find.put("_id", orderLineId);
		BasicDBObject update = new BasicDBObject();
		update.put("$inc", new BasicDBObject("seq", 1));
		DBObject obj = collection.findAndModify(find, update);
		// return Object
		return obj.get("seq");
	}

}
