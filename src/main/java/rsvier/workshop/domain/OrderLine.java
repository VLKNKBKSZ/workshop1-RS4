package rsvier.workshop.domain;

import java.math.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

//Added this class to match the database EER diagram. Not sure how this will work with the database.

public class OrderLine {

	private int orderLineId;
	private Product product;
	private int numberOfProducts;

	/*
	 * Set the constructor to private to force user to use only the builder to
	 * create new instances.
	 */

	private OrderLine(OrderLineBuilder builder) {

		this.orderLineId = builder.orderLineId;
		this.product = builder.product;
		this.numberOfProducts = builder.numberOfProducts;

	}

	public static class OrderLineBuilder {

		private int orderLineId;
		private Product product;
		private int numberOfProducts;

		public OrderLineBuilder() {
		}

		public OrderLineBuilder(OrderLine orderLine) {

			this.orderLineId = orderLine.orderLineId;
			this.product = orderLine.product;
			this.numberOfProducts = orderLine.numberOfProducts;
		}

		public OrderLineBuilder orderLineId(int orderLineId) {

			this.orderLineId = orderLineId;
			return this;
		}

		public OrderLineBuilder product(Product product) {

			this.product = product;
			return this;
		}

		public OrderLineBuilder numberOfProducts(int numberOfProducts) {

			this.numberOfProducts = numberOfProducts;
			return this;
		}

		public OrderLine build() {

			return new OrderLine(this);
		}

	}

	public int getOrderLineId() {

		return orderLineId;
	}

	public Product getProduct() {

		return product;
	}

	public int getNumberOfProducts() {

		return numberOfProducts;
	}

	// Override the toString method that is inherited from the Object class.

	@Override
	public String toString() {
		return "Id: " + getOrderLineId() + " Naam: " + product.getName() + " Prijs: " + getProduct().getPrice()
		/* + " OrderId " + order.getOrderId() */ + "Aantal: " + product.getStock() + " Total Price: "
				+ getProduct().getPrice().multiply(new BigDecimal(getNumberOfProducts()));
	}

}