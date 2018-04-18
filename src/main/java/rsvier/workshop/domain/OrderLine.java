package rsvier.workshop.domain;

import java.math.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

//Added this class to match the database EER diagram. Not sure how this will work with the database.

public class OrderLine {

	private int orderLineId;
	private Product product;
	private int numberOfProducts;
	private Timestamp dateTime;

	/*
	 * Set the constructor to private to force user to use only the builder to
	 * create new instances.
	 */

	private OrderLine(OrderLineBuilder builder) {

		this.orderLineId = builder.orderLineId;
		this.product = builder.product;
		this.numberOfProducts = builder.numberOfProducts;
		this.dateTime = builder.dateTime;

	}

	public static class OrderLineBuilder {

		private int orderLineId;
		private Product product;
		private int numberOfProducts;
		private Timestamp dateTime;

		public OrderLineBuilder() {
		}

		public OrderLineBuilder(OrderLine orderLine) {

			this.orderLineId = orderLine.orderLineId;
			this.product = orderLine.product;
			this.numberOfProducts = orderLine.numberOfProducts;
			this.dateTime = orderLine.dateTime;
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

		public OrderLineBuilder dateTime(Timestamp dateTime) {
			this.dateTime = dateTime;
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

	public int getNumber() {
		return numberOfProducts;
	}

	public String getTimeStamp() {
		String s = new SimpleDateFormat().format(dateTime);
		return s;
	}

	// Override the toString method that is inherited from the Object class.

	@Override
	public String toString() {
		return "Id:" + getOrderLineId() + " Name:" + product.getName() + " Price:" + getProduct().getPrice()
		/* + " OrderId " + order.getOrderId() */ + " Total Price:"
				+ getProduct().getPrice().multiply(new BigDecimal(getNumber())) + " Datetime: " + getTimeStamp();
	}

}