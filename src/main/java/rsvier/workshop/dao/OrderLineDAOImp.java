package rsvier.workshop.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;

public class OrderLineDAOImp implements OrderLineDAO {

	private Logger logger = LogConnection.getLogger();
	private ProductDAOImp productDAO = new ProductDAOImp();
	private OrderDAOImp orderDAO = new OrderDAOImp();

	@Override
	public List<OrderLine> getAllOrderLines() {

		List<OrderLine> orderLineList = new ArrayList<>();
		String query = "SELECT * FROM orderline;";
		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery()) {
			while (resultSet.next()) {
				OrderLine.OrderLineBuilder orderLineBuilder = new OrderLine.OrderLineBuilder();
				orderLineBuilder.orderLineId(resultSet.getInt(1));
				Order order = orderDAO.getOrderById(resultSet.getInt(2));
				orderLineBuilder.order(order);
				Product product = productDAO.getProductById(resultSet.getInt(3));
				orderLineBuilder.product(product);
				orderLineBuilder.numberOfProducts(resultSet.getInt(4));
				orderLineBuilder.dateTime(resultSet.getTimestamp(5));
				OrderLine orderLine = orderLineBuilder.build();
				orderLineList.add(orderLine);

			}
			logger.log(Level.INFO, "List successfuly created and returned");
			return orderLineList;

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}
		return null;
	}

	@Override
	public List<OrderLine> getAllOrderLinesFromOrder(Order order) {
		List<OrderLine> orderLineList = new ArrayList<>();
		String query = "SELECT * FROM orderline WHERE order_table_id =?;";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {
			preparedStatement.setInt(1, order.getOrderId());

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					OrderLine.OrderLineBuilder orderLineBuilder = new OrderLine.OrderLineBuilder();
					orderLineBuilder.orderLineId(resultSet.getInt(1));
					orderLineBuilder.order(orderDAO.getOrderById(resultSet.getInt(2)));
					Product product = productDAO.getProductById(resultSet.getInt(3));
					orderLineBuilder.product(product);
					orderLineBuilder.numberOfProducts(resultSet.getInt(4));
					orderLineBuilder.dateTime(resultSet.getTimestamp(5));
					OrderLine orderLine = orderLineBuilder.build();
					orderLineList.add(orderLine);

				}
				logger.log(Level.INFO, "OrderLineList successfully returned");
				return orderLineList;
			}

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occcured", e);
			;
		}
		return null;
	}

	@Override
	public List<OrderLine> getAllOrderLinesFromProduct(Product product) {
		List<OrderLine> orderLineList = new ArrayList<>();
		String query = "SELECT * FROM orderline WHERE product_id = ?;";
		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {
			preparedStatement.setInt(1, product.getProductId());

			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				while (resultSet.next()) {
					OrderLine.OrderLineBuilder orderLineBuilder = new OrderLine.OrderLineBuilder();
					orderLineBuilder.orderLineId(resultSet.getInt(1));
					Order order = orderDAO.getOrderById(resultSet.getInt(2));
					orderLineBuilder.order(order);
					orderLineBuilder.product(productDAO.getProductById(resultSet.getInt(3)));
					orderLineBuilder.numberOfProducts(resultSet.getInt(4));
					orderLineBuilder.dateTime(resultSet.getTimestamp(5));
					OrderLine orderLine = orderLineBuilder.build();
					orderLineList.add(orderLine);
				}
				logger.log(Level.INFO, "OrderLineList from product successfully created and returned");
				return orderLineList;

			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occurred", e);
		}
		return null;
	}

	@Override
	public OrderLine getOrderLine(int orderLineId) {
		OrderLine orderLine = null;
		String query = "SELECT * FROM orderline WHERE orderline_id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {
			preparedStatement.setInt(1, orderLineId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					OrderLine.OrderLineBuilder orderLineBuilder = new OrderLine.OrderLineBuilder();
					orderLineBuilder.orderLineId(resultSet.getInt(1));
					Order order = orderDAO.getOrderById(resultSet.getInt(2));
					orderLineBuilder.order(order);
					Product product = productDAO.getProductById(resultSet.getInt(3));
					orderLineBuilder.product(product);
					orderLineBuilder.numberOfProducts(resultSet.getInt(4));
					orderLineBuilder.dateTime(resultSet.getTimestamp(5));
					orderLine = orderLineBuilder.build();

				}
				logger.log(Level.INFO, "OrderLine successfully created and returned");
				return orderLine;
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}
		return null;
	}

	@Override
	public void createOrderLine(OrderLine orderLine) {
		String query = "INSERT INTO orderline (order_table_id, product_id , number_of_products) VALUES (?, ? ,?)";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {

			preparedStatement.setInt(1, orderLine.getOrder().getOrderId());
			preparedStatement.setInt(2, orderLine.getProduct().getProductId());
			preparedStatement.setInt(3, orderLine.getNumber());

			preparedStatement.executeUpdate();
			logger.log(Level.INFO, "OrderLine succesfully created.");
			
			} catch (SQLException e) {
		
			logger.log(Level.WARNING, "SQL Exception occured", e);
		}

	}

	@Override
	public void deleteOrderLine(OrderLine orderLine) {
		String query = "DELETE FROM orderline WHERE orderline_id=?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {
			preparedStatement.setInt(1, orderLine.getOrderLineId());
			preparedStatement.executeUpdate();
			logger.log(Level.INFO, "OrderLine succesfully deleted");
			System.out.println("OrderLine succesfully deleted");
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}

	}

	@Override
	public void updateOrderLine(OrderLine orderLine) {
		String query = "UPDATE orderline SET product_id = ? , number_of_products = ? WHERE id=?;";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {
			preparedStatement.setInt(1, orderLine.getOrder().getOrderId());
			preparedStatement.setInt(2, orderLine.getProduct().getProductId());
			preparedStatement.setInt(3, orderLine.getNumber());
			preparedStatement.setInt(4, orderLine.getOrderLineId());
			preparedStatement.executeUpdate();
			logger.log(Level.INFO, "OrderLine succesfully updated");
			System.out.println("OrderLine successfully updated");
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}

	}

}
