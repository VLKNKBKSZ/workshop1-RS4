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
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {

			// Extract data from result set
			while (resultSet.next()) {
				Product.ProductBuilder productBuilder = new Product.ProductBuilder();
				productBuilder.productId(resultSet.getInt(1));
				productBuilder.name(resultSet.getString(2));
				productBuilder.price(resultSet.getBigDecimal(3));
				productBuilder.stock(resultSet.getInt(4));
				productBuilder.dateTime(resultSet.getTimestamp(5));
				Product product = productBuilder.build();
				productList.add(product);
			}
			logger.log(Level.INFO, "Produclist succcesfuly returned");
			return productList;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}

		return null;
	}

	@Override
	public Product getProductByName(String name) {
		Product product = null;
		String query = "SELECT * FROM product WHERE name = ?;";
		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {
			preparedStatement.setString(1, name);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				if (resultSet.next()) {
					Product.ProductBuilder productBuilder = new Product.ProductBuilder();
					productBuilder.productId(resultSet.getInt(1));
					productBuilder.name(resultSet.getString(2));
					productBuilder.price(resultSet.getBigDecimal(3));
					productBuilder.stock(resultSet.getInt(4));
					productBuilder.dateTime(resultSet.getTimestamp(5));
					product = productBuilder.build();

				}
				logger.log(Level.INFO, "List successfully returned");
				return product;
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}
		return null;
	}

	@Override
	public Product getProductById(int productId) {
		Product product = null;
		String query = "SELECT * FROM product WHERE product_id = ?;";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setInt(1, productId);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {


				if (resultSet.next()) {

					Product.ProductBuilder productBuilder = new Product.ProductBuilder();
					productBuilder.productId(resultSet.getInt(1));
					productBuilder.name(resultSet.getString(2));
					productBuilder.price(resultSet.getBigDecimal(3));
					productBuilder.stock(resultSet.getInt(4));
					productBuilder.dateTime(resultSet.getTimestamp(5));
					product = productBuilder.build();
				}
				logger.log(Level.INFO, "List successfully returned");
				return product;

			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
		return null;
	}

	@Override
	public void createProduct(Product product) {
		String query = "INSERT INTO product (name, price, stock) VALUES (?, ?, ?);";
		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {
			preparedStatement.setString(1, product.getName());
			preparedStatement.setBigDecimal(2, product.getPrice());
			preparedStatement.setInt(3, product.getStock());
			preparedStatement.executeUpdate();
			logger.log(Level.INFO, "Product succesfully created");

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}

	}

	@Override
	public void updateProduct(Product product) {
		String query = "UPDATE product SET name = ?, price = ? , stock = ? WHERE product_id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {
			preparedStatement.setString(1, product.getName());
			preparedStatement.setBigDecimal(2, product.getPrice());
			preparedStatement.setInt(3, product.getStock());
			preparedStatement.setInt(4, product.getProductId());
			preparedStatement.executeUpdate();
			logger.log(Level.INFO, "Product successfully updated");
		} catch (SQLException e) {

			logger.log(Level.WARNING, "SQL exception ocurred.", e);
		}
	}

	@Override
	public void deleteProduct(Product product) {
		String query = "DELETE FROM product WHERE product_id =?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {
			preparedStatement.setInt(1, product.getProductId());
			preparedStatement.executeUpdate();
			logger.log(Level.INFO, "Product successfully deleted");
		} catch (SQLException e) {

			logger.log(Level.WARNING, "SQL exception occured", e);
		}

	}

}
