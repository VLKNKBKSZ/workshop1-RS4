package rsvier.workshop.domain;

import java.math.*;

//Added this class to match the database EER diagram. Not sure how this will work with the database.

public class OrderLine {

	private int orderLineId;
	private Order order;
	private Product product;
	private int numberOfProduct;

	/*
	 * Set the constructor to private to force user to use only the builder to
	 * create new instances.
	 */

	private OrderLine(OrderLineBuilder builder) {

		this.orderLineId = builder.orderLineId;
		this.order = builder.order;
		this.product = builder.product;
		this.numberOfProduct = builder.numberOfProduct;

	}

	public static class OrderLineBuilder {

		private int orderLineId;
		private Order order;
		private Product product;
		private int numberOfProduct;

		public OrderLineBuilder() {
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

		public OrderLineBuilder numberOfProduct(int numberOfProduct) {
			this.numberOfProduct = numberOfProduct;
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

	public int getNumberOfProduct() {
		return numberOfProduct;
	}

	// Override the toString method that is inherited from the Object class.

	@Override
	public String toString() {
		return "Id:" + getOrderLineId() + " Name:" + product.getNameOfProduct() + " Price:"
				+ getProduct().getPriceOfProduct() + " OrderId " + order.getOrderId() + "Total Price: "
				+ getProduct().getPriceOfProduct().multiply(new BigDecimal(getNumberOfProduct()));
	}

}