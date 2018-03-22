package rsvier.workshop.domain;

import java.math.BigDecimal;

//Added this class to match the database eer diagram. Not sure how this will work with the database.
public class OrderLine {

	private int orderLineId;
	private int orderId;
	private String product;
	private BigDecimal productPrice;
	private int numberOfProduct;

	// Set the constructor to private to force user to use only the builder to
	// create new instances.

	private OrderLine(OrderLineBuilder builder) {

		this.orderLineId = builder.orderLineId;
		this.orderId = builder.orderId;
		this.product = builder.product;
		this.productPrice = builder.productPrice;
		this.numberOfProduct = builder.numberOfProduct;

	}

	public static class OrderLineBuilder {

		private int orderLineId;
		private int orderId;
		private String product;
		private BigDecimal productPrice;
		private int numberOfProduct;

		public OrderLineBuilder() {
		}

		public OrderLineBuilder orderLineId(int orderLineId) {
			this.orderLineId = orderLineId;
			return this;
		}

		public OrderLineBuilder orderId(int orderId) {
			this.orderId = orderId;
			return this;
		}

		public OrderLineBuilder product(String product) {
			this.product = product;
			return this;
		}

		public OrderLineBuilder productPrice(BigDecimal productPrice) {
			this.productPrice = productPrice;
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

	public int getOrderId() {
		return orderId;
	}

	public String getProduct() {
		return product;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public int getNumberOfProduct() {
		return numberOfProduct;
	}

	@Override
	public String toString() {
		return getNumberOfProduct() + " x" + getProduct() + " $" + getProductPrice() + "Total price"
				+ getProductPrice().multiply(new BigDecimal(getNumberOfProduct()));
	}

}