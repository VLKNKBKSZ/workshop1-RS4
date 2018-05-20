package rsvier.workshop.dao;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import java.util.logging.*;
import com.mongodb.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;

public class OrderDAOImpMongo implements OrderDAO {

	private DBCollection collection;
	private Logger logger = LogConnection.getLogger();



	public OrderDAOImpMongo() {
		try {
			DB db = DatabaseConnectionXML.getConnectionMongoDB();
			collection = db.getCollection("order_table");
		} catch (Exception e) {
			logger.log(Level.WARNING, "SQL exception occured. Connection with MongoDB failed", e);
		}

	}

	@Override
	public List<Order> getAllOrders() {
		
		List<Order> orderList = new ArrayList<>();

		try(DBCursor cursor = collection.find().skip(1);){
			
			while(cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject accountObj = (BasicDBObject) object;
				
				Order.OrderBuilder orderBuilder = new Order.OrderBuilder();
				 orderBuilder.orderId(accountObj.getInt("_id"));
				 Order order1 = orderBuilder.build();
				 orderBuilder.totalOrderLines(DAOFactory.getOrderLineDAO().getAllOrderLinesFromOrder(order1));
				 orderBuilder.person(DAOFactory.getPersonDAO().getPersonById(accountObj.getInt("person_id")));
				 BigDecimal totalPrice = BigDecimal.valueOf(accountObj.getDouble("total_price"));
				 totalPrice.setScale(2);
				 orderBuilder.totalPrice(totalPrice);
				 orderBuilder.getOrderDateTime(LocalDateTime.parse(accountObj.getString("Local_date_time")));
				 Order order = orderBuilder.build();
				 orderList.add(order);
				 
			}
			
			return orderList;
			
		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
		return null;
	}

	@Override
	public List<Order> getAllOrdersFromPerson(Person person) {
		
		List<Order> orderList = new ArrayList<>();
		DBObject query = new BasicDBObject("person_id",person.getPersonId());

		try(DBCursor cursor = collection.find(query);){
			
			while(cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject accountObj = (BasicDBObject) object;
				
				Order.OrderBuilder orderBuilder = new Order.OrderBuilder();
				 orderBuilder.orderId(accountObj.getInt("_id"));
				 Order order1 = orderBuilder.build();
				 orderBuilder.totalOrderLines(DAOFactory.getOrderLineDAO().getAllOrderLinesFromOrder(order1));
				 orderBuilder.person(DAOFactory.getPersonDAO().getPersonById(accountObj.getInt("person_id")));
				 BigDecimal totalPrice = BigDecimal.valueOf(accountObj.getDouble("total_price"));
				 totalPrice.setScale(2);
				 orderBuilder.totalPrice(totalPrice);
				 orderBuilder.getOrderDateTime(LocalDateTime.parse(accountObj.getString("Local_date_time")));
				 Order order = orderBuilder.build();
				 orderList.add(order);
			}
			
			return orderList;
			
		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
		return null;
	}

	@Override
	public Order getOrderById(int orderId) {
		
		Order order = null;
		DBObject query = new BasicDBObject("_id",orderId);

		try(DBCursor cursor = collection.find(query);){
			
			while(cursor.hasNext()) {
				DBObject object = cursor.next();
				BasicDBObject accountObj = (BasicDBObject) object;
				
				Order.OrderBuilder orderBuilder = new Order.OrderBuilder();
				 orderBuilder.orderId(accountObj.getInt("_id"));
				 Order order1 = orderBuilder.build();
				 orderBuilder.totalOrderLines(DAOFactory.getOrderLineDAO().getAllOrderLinesFromOrder(order1));
				 orderBuilder.person(DAOFactory.getPersonDAO().getPersonById(accountObj.getInt("person_id")));
				 BigDecimal totalPrice = BigDecimal.valueOf(accountObj.getDouble("total_price"));
				 totalPrice.setScale(2);
				 orderBuilder.totalPrice(totalPrice);
				 orderBuilder.getOrderDateTime(LocalDateTime.parse(accountObj.getString("Local_date_time")));
				  order = orderBuilder.build();
			}
			return order;
			
		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
		return null;
	}

	@Override
	public int createOrder(Order order) {
				double generatedIdDouble = (Double) getNextSequence("order_id");
				int generatedIdInteger = (int) generatedIdDouble;
				
				DBObject newOrder = new BasicDBObject("_id",generatedIdInteger)
						.append("person_id", order.getPerson().getPersonId())
						.append("total_price", order.getTotalPrice().doubleValue())
						.append("Local_date_time", order.getOrderDateTime().toString());
				
				try {
					
				collection.insert(newOrder);
				logger.log(Level.INFO, "Order successfully created.");
				
				} catch (MongoException e) {
					logger.log(Level.WARNING, "SQL exception occured", e);
				}
		return generatedIdInteger;
	}

	@Override
	public void updateOrder(Order order) {
		
		DBObject updateOrder = new BasicDBObject("_id",order.getOrderId())
				.append("person_id", order.getPerson().getPersonId())
				.append("total_price", order.getTotalPrice().doubleValue())
				.append("Local_date_time", order.getOrderDateTime().toString());
		try {
		collection.update(new BasicDBObject("_id",order.getOrderId()), updateOrder);
		logger.log(Level.INFO, "Order successfully created.");
		
		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
	}

	@Override
	public void deleteOrder(Order order) {
		try {
		collection.remove(new BasicDBObject("_id", order.getOrderId()));
		logger.log(Level.INFO, "Order successfully deleted.");
		
		} catch (MongoException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
	}

	// Method of auto-increment Id
	public Object getNextSequence(String orderId) {

		BasicDBObject find = new BasicDBObject();
		find.put("_id", orderId);
		BasicDBObject update = new BasicDBObject();
		update.put("$inc", new BasicDBObject("seq", 1));
		DBObject obj = collection.findAndModify(find, update);
		// return Object
		return obj.get("seq");
	}

}
