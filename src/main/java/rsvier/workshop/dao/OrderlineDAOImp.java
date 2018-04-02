package rsvier.workshop.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import rsvier.workshop.domain.OrderLine;
import rsvier.workshop.domain.Product;
import rsvier.workshop.utility.*;

public class OrderlineDAOImp implements OrderLineDAO {

	Logger logger = LogConnection.getLogger();
	ProductDAOImp productDAO = new ProductDAOImp();

	@Override
	public List<OrderLine> getAllOrderLines() {

		List<OrderLine> list = new ArrayList<>();
		String query = "SELECT * FROM orderline;";
		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				OrderLine.OrderLineBuilder olBuilder = new OrderLine.OrderLineBuilder();
				olBuilder.orderLineId(rs.getInt(1));
				/*
				 * The OrderLine class has a Product product class field. So the return that
				 * object saved in de database we need to create first a new object and call the
				 * getProductById with the DAO
				 */
				Product product = productDAO.getProductById(rs.getInt(2));
				olBuilder.product(product);
				olBuilder.number(rs.getInt(3));
				olBuilder.dateTime(rs.getTimestamp(4));
				OrderLine orderLine = olBuilder.build();
				list.add(orderLine);

			}
			logger.log(Level.INFO, "List succesfuly created and returned");
			return list;

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}
		return null;
	}

	@Override
	public List<OrderLine> getAllOrderLinesFromOrder(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderLine> getAllOrderLinesFromProduct(int productId) {
		List<OrderLine> list = new ArrayList<>();
		String query = "SELECT * FROM orderline WHERE product_id = ?;";
		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, productId);

			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					OrderLine.OrderLineBuilder olBuilder = new OrderLine.OrderLineBuilder();
					olBuilder.orderLineId(rs.getInt(1));
					/*
					 * The OrderLine class has a Product product class field. So the return that
					 * object saved in de database we need to create first a new object and call the
					 * getProductById with the DAO
					 */
					Product product = productDAO.getProductById(rs.getInt(2));
					olBuilder.product(product);
					olBuilder.number(rs.getInt(3));
					olBuilder.dateTime(rs.getTimestamp(4));
					OrderLine orderLine = olBuilder.build();
					list.add(orderLine);
				}
				logger.log(Level.INFO, "List succesfuly created and returned");
				return list;

			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occurred", e);
		}
		return null;
	}

	@Override
	public OrderLine getOrderLine(int orderLineId) {
		OrderLine orderLine = null;
		String query = "SELECT * FROM orderline WHERE id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setInt(1, orderLineId);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					OrderLine.OrderLineBuilder olBuilder = new OrderLine.OrderLineBuilder();
					olBuilder.orderLineId(rs.getInt(1));
					/*
					 * The OrderLine class has a Product product class field. So the return that
					 * object saved in de database we need to create first a new object and call the
					 * getProductById with the DAO
					 */
					Product product = productDAO.getProductById(rs.getInt(2));
					olBuilder.product(product);
					olBuilder.number(rs.getInt(3));
					olBuilder.dateTime(rs.getTimestamp(4));
					orderLine = olBuilder.build();

				}
				logger.log(Level.INFO, "List succesfuly created and returned");
				return orderLine;
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}
		return null;
	}

	@Override
	public void createOrderLine(OrderLine orderLine) {
		String query = "INSERT INTO orderline (product_id , number) VALUES (? ,?)";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, orderLine.getProduct().getProductId());
			pstmt.setInt(2, orderLine.getNumber());
			pstmt.executeUpdate();
			logger.log(Level.INFO, "OrderLine succesfully created.");
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL Exception occured", e);
		}

	}

	@Override
	public void deleteOrderLine(OrderLine orderLine) {
		String query = "DELETE FROM orderline WHERE id=?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setInt(1, orderLine.getOrderLineId());
			pstmt.executeUpdate();
			logger.log(Level.INFO, "OrderLine succesfully deleted");
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}

	}

	@Override
	public void updateOrderLine(OrderLine orderLine) {
		String query = "UPDATe orderline SET number =? WHERE id=?;";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setInt(1, orderLine.getNumber());
			pstmt.setInt(2, orderLine.getOrderLineId());
			pstmt.executeUpdate();
			logger.log(Level.INFO, "OrderLine succesfully updated");
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}

	}

}
