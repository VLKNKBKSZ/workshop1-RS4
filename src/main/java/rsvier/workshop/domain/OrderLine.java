package rsvier.workshop.domain;

import java.math.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

//Added this class to match the database EER diagram. Not sure how this will work with the database.

public class OrderLine {

	private int orderLineId;
	private Order order;
	private Product product;
	private int number;
	private Timestamp dateTime;

	/*
	 * Set the constructor to private to force user to use only the builder to
	 * create new instances.
	 */

	private OrderLine(OrderLineBuilder builder) {

		this.orderLineId = builder.orderLineId;
		this.order = builder.order;
		this.product = builder.product;
		this.number = builder.number;
		this.dateTime = builder.dateTime;

	}

	public static class OrderLineBuilder {

		private int orderLineId;
		private Order order;
		private Product product;
		private int number;
		private Timestamp dateTime;

		public OrderLineBuilder() {
		}
		
		public OrderLineBuilder(OrderLine orderLine) {
			
			this.orderLineId = orderLine.orderLineId;
			this.order = orderLine.order;
			this.product = orderLine.product;
			this.number = orderLine.number;
			this.dateTime = orderLine.dateTime;
		}
		public OrderLineBuilder orderLineId(int orderLineId) {
			this.orderLineId = orderLineId;
			return this;
		}

		public OrderLineBuilder order(Order order) {
			this.order = order;
			return this;
		}

		public OrderLineBuilder product(Product product) {
			this.product = product;
			return this;
		}

		public OrderLineBuilder number(int number) {
			this.number = number;
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

	public Order getOrder() {
		return order;
	}

	public Product getProduct() {
		return product;
	}

	public int getNumber() {
		return number;
	}

	public String getTimeStamp() {
		String s = new SimpleDateFormat().format(dateTime);
		return s;
	}

	// Override the toString method that is inherited from the Object class.

	@Override
	public String toString() {
		return "Id:" + getOrderLineId() + " Name:" + product.getName() + " Price:" + getProduct().getPrice()
		/* + " OrderId " + order.getOrderId() */+ " Total Price:"
				+ getProduct().getPrice().multiply(new BigDecimal(getNumber())) + " Datetime: " + getTimeStamp(); 
	}

}