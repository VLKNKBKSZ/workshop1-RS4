package rsvier.workshop.dao;

import java.util.*;
import java.util.logging.*;
import com.mongodb.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;


public class OrderDAOImpMongo implements OrderDAO {
	
	private DB db;
	private DBCollection collection;
	private Logger logger = LogConnection.getLogger();

	public OrderDAOImpMongo() {
		try {
			db = DatabaseConnectionXML.getConnectionMongoDB();
			collection = db.getCollection("order_table");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAllOrdersFromPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrderById(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createOrder(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteOrder(Order order) {
		// TODO Auto-generated method stub
		
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
