package rsvier.workshop.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

	// added also id of order/product/customer and employee. Would be good to link
	// database tables.

	private int orderId;
	private int customerId;
	private BigDecimal totalPrice;
	private LocalDateTime orderDateTime = LocalDateTime.now();

	private Order(OrderBuilder builder) {
		this.orderId = builder.orderId;
		this.customerId = builder.customerId;
		this.totalPrice = builder.totalPrice;
		this.orderDateTime = builder.orderDateTime;

	}

	public class OrderBuilder {

		private int orderId;
		private int customerId;
		private BigDecimal totalPrice;
		private LocalDateTime orderDateTime = LocalDateTime.now();

		public OrderBuilder() {

		}

		public OrderBuilder id(int orderId) {
			this.orderId = orderId;
			return this;
		}

		public OrderBuilder customer(int customerId) {
			this.customerId = customerId;
			return this;
		}

		public OrderBuilder totalPrice(BigDecimal totalPrice) {
			this.totalPrice = totalPrice;
			return this;
		}

		public OrderBuilder orderDateTime(LocalDateTime orderDateTime) {
			this.orderDateTime = orderDateTime;
			return this;
		}

		public Order build() {
			return new Order(this);
		}
	}

	public int getOrderId() {
		return orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public String getOrderDateTime() {
		return orderDateTime.toString();
	}

	// Override the toString method from the Object class.

	@Override
	public String toString() {
		return getOrderId() + " " + getCustomerId() + " " + getTotalPrice() + " " + getOrderDateTime();
	}

}