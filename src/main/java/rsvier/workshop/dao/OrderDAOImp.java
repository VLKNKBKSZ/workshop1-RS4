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
	PersonDAOImp personDAO = new PersonDAOImp();

	@Override
	public List<Order> getAllOrders() {
		List<Order> orderList = new ArrayList<>();
		String query = "SELECT * FROM order_table;";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {
			while (resultSet.next()) {
				Order.OrderBuilder orderBuilder = new Order.OrderBuilder();
				orderBuilder.orderId(resultSet.getInt(1));
				Person person = personDAO.getPersonById(resultSet.getInt(2));
				orderBuilder.person(person);
				orderBuilder.dateTime(resultSet.getTimestamp(3));
				Order order = orderBuilder.build();
				orderList.add(order);

			}
			logger.log(Level.INFO, "List successfully returned");
			return orderList;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		} catch (Exception ex) {
			logger.log(Level.WARNING, "Exception occured", ex);
		}

		return null;
	}

	@Override
	public List<Order> getAllOrdersFromPerson(Person person) {
		List<Order> orderList = new ArrayList<>();
		String query = "SELECT * FROM order_table WHERE person_id = ?;";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {
			preparedStatement.setInt(1, person.getPersonId());
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				while (resultSet.next()) {
					Order.OrderBuilder orderBuilder = new Order.OrderBuilder();
					orderBuilder.orderId(resultSet.getInt(1));
					orderBuilder.person(personDAO.getPersonById(resultSet.getInt(2)));
					orderBuilder.dateTime(resultSet.getTimestamp(3));
					Order order = orderBuilder.build();
					orderList.add(order);

				}
			}
			logger.log(Level.INFO, "List successfully returned");
			return orderList;
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
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {
			preparedStatement.setInt(1, orderId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					Order.OrderBuilder orderBuilder = new Order.OrderBuilder();
					orderBuilder.orderId(resultSet.getInt(1));
					Person person = personDAO.getPersonById(resultSet.getInt(2));
					orderBuilder.person(person);
					orderBuilder.dateTime(resultSet.getTimestamp(3));
					order = orderBuilder.build();
				}
				logger.log(Level.INFO, "Order succesfully returned");
				return order;
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}
		return null;
	}

	@Override
	public int createOrder(Order order, Person person) {
		int generatedId = 0;

		String query = "INSERT INTO order_table (person_id) VALUES (?);";
		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query,
						PreparedStatement.RETURN_GENERATED_KEYS);) {

			preparedStatement.setInt(1, person.getPersonId());
			preparedStatement.executeUpdate();

			try (ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
				if (resultSet.next()) {
					generatedId = resultSet.getInt(1);

					logger.info("Succesfully created new order.");
				}
			} catch (SQLException e) {
				System.out.println("Creating new order failed.");
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}
		return generatedId;
	}

	@Override
	public void updateOrder(Order order) {
		String query = "UPDATE order_table (person_id) WHERE orderline_id = ?;";
		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {
			preparedStatement.setInt(1, order.getPerson().getPersonId());
			preparedStatement.executeUpdate();
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

}