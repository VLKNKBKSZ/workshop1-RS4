package rsvier.workshop.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.DatabaseConnectionXML;
import rsvier.workshop.utility.LogConnection;

public class ProductDAOImp implements ProductDAO {

	private Logger logger = LogConnection.getLogger();

	@Override
	public List<Product> getAllProducts() {
		List<Product> productList = new ArrayList<>();
		String query = "SELECT * FROM product;";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();) {
			/*
			 * Check all rows and return those products back to the ArrayList<Product>
			 */

			// Extract data from result set
			while (rs.next()) {
				Product.ProductBuilder pb = new Product.ProductBuilder();
				pb.productId(rs.getInt(1));
				pb.name(rs.getString(2));
				pb.price(rs.getBigDecimal(3));
				pb.stock(rs.getInt(4));
				pb.dateTime(rs.getTimestamp(5));
				Product product = pb.build();
				logger.log(Level.INFO, "Build of product is succesfull");
				productList.add(product);
			}

			return productList;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Some problem occured at the preparedStamet or Resultset", e);
		}

		return null;
	}

	@Override
	public Product getProduct(int productId) {
		Product product = null;
		String query = "SELECT * FROM product WHERE id = ?;";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);) {

			pstmt.setInt(1, productId);

			try (ResultSet rs = pstmt.executeQuery()) {

				// Extract data from result set

				while (rs.next()) {

					Product.ProductBuilder pb = new Product.ProductBuilder();
					pb.productId(rs.getInt(1));
					pb.name(rs.getString(2));
					pb.price(rs.getBigDecimal(3));
					pb.stock(rs.getInt(4));
					pb.dateTime(rs.getTimestamp(5));
					product = pb.build();
				}

			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
		return product;
	}

	@Override
	public void createProduct(Product product) {
		String query = "INSERT INTO product (name, price, stock) VALUES (?, ?, ?);";
		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setString(1, product.getName());
			pstmt.setBigDecimal(2, product.getPrice());
			pstmt.setInt(3, product.getStock());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Sql exception occured", e);
		}

	}

	@Override
	public void updateProduct(Product product, int productId) {
		String query = "UPDATE product SET name = ?, price = ? , stock = ? WHERE id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query);) {
			pstmt.setString(1, product.getName());
			pstmt.setBigDecimal(2, product.getPrice());
			pstmt.setInt(3, product.getStock());
			pstmt.setInt(4, productId);
			pstmt.executeUpdate();
		} catch (SQLException e) {

			logger.log(Level.WARNING, "SQL exception ocurred.", e);
		}
	}

	@Override
	public void deleteProduct(int productId) {
		String query = "DELETE FROM product WHERE id =?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, productId);
			pstmt.executeUpdate();
		} catch (SQLException e) {

			logger.log(Level.WARNING, "SQL exception occured", e);
		}

	}

}
