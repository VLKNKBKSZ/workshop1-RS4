package rsvier.workshop.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {

	/* Order has an id, a customer, a total price and a orderdateTime
	 * 
	 * */

	private int orderId;
	private Customer customer;
	private BigDecimal totalPrice;
	private LocalDateTime orderDateTime = LocalDateTime.now();

	private Order(OrderBuilder builder) {
		this.orderId = builder.orderId;
		this.customer = builder.customer;
		this.totalPrice = builder.totalPrice;
		this.orderDateTime = builder.orderDateTime;

	}

	public class OrderBuilder {

		private int orderId;
		private Customer customer;
		private BigDecimal totalPrice;
		private LocalDateTime orderDateTime = LocalDateTime.now();

		public OrderBuilder() {

		}

		public OrderBuilder id(int orderId) {
			this.orderId = orderId;
			return this;
		}

		public OrderBuilder customer(Customer customer) {
			this.customer = customer;
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

	public Customer getCustomer() {
		return customer;
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
		return getOrderId() + " " + getCustomer() + " " + getTotalPrice() + " " + getOrderDateTime();
	}

}