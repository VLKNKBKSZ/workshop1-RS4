package rsvier.workshop.dao;

import java.sql.*;

import java.util.*;
import java.util.logging.*;
import rsvier.workshop.domain.Order;
import rsvier.workshop.domain.Person;
import rsvier.workshop.utility.DatabaseConnectionXML;
import rsvier.workshop.utility.LogConnection;

public class OrderDAOImp implements OrderDAO {

	Logger logger = LogConnection.getLogger();

	@Override
	public List<Order> getAllOrders() {
		List<Order> list = new ArrayList<>();
		String query = "SELECT * FROM order_table;";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				Order.OrderBuilder orBuilder = new Order.OrderBuilder();
				orBuilder.id(rs.getInt(1));
				orBuilder.dateTime(rs.getTimestamp(2));
				Order order = orBuilder.build();
				list.add(order);

			}
			return list;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		} catch (Exception ex) {
			logger.log(Level.WARNING, "Exception occured", ex);
		}

		return null;
	}

	@Override
	public Order getOrderById(int orderId) {
		String query = "SELECT * FROM order_table WHERE order_table_id =?";
		Order order = null;

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, orderId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Order.OrderBuilder orBuilder = new Order.OrderBuilder();
					orBuilder.id(rs.getInt(1));
					orBuilder.dateTime(rs.getTimestamp(2));
					order = orBuilder.build();
				}
				return order;
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}
		return null;
	}

	@Override
	public int createOrder(Order order, Person person) {
		int newOrderId = 0;
	
		
		String query = "INSERT INTO order_table (person_id) VALUES (?);";
		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query, 
						PreparedStatement.RETURN_GENERATED_KEYS);) {
		
			pstmt.setInt(1, person.getPersonId());
			pstmt.executeUpdate();
			
			try (ResultSet rs = pstmt.getGeneratedKeys();){
                if (rs.next()) {
                    newOrderId = rs.getInt(1);
                  
                    logger.info("Succesfully created new order.");
                }
            } catch (SQLException e) {
                System.out.println("Creating new order failed.");
            }
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}
			return newOrderId;
	}

	@Override
	public void updateOrder(Order order) {
		String query = "UPDATE order_table (person_id) WHERE orderline_id = ?;";
		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, order.getPerson().getPersonId());
			pstmt.executeUpdate();
			logger.log(Level.INFO, "Order succesfully updated");
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}

	}

	@Override
	public void deleteOrder(Order order) {
		String query = "DELETE FROM order_table WHERE order_table_id =?;";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setInt(1, order.getOrderId());
			pstmt.executeUpdate();
			logger.log(Level.INFO, "Order succesfully deleted");

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}

	}


	@Override
	public List<Order> getAllOrdersFromPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}


}
