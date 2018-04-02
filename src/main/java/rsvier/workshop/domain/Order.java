package rsvier.workshop.domain;

import java.math.BigDecimal;
import java.util.*;

public class Order {

	private int orderId;
	private List<OrderLine> totalOrderLines;
	private Customer customer;
	private BigDecimal totalPrice;;

	private Order(OrderBuilder builder) {
		this.orderId = builder.orderId;
		this.totalOrderLines = builder.totalOrderLines;
		this.customer = builder.customer;
		this.totalPrice = builder.totalPrice;

	}

	public static class OrderBuilder {

		private int orderId;
		private List<OrderLine> totalOrderLines;
		private Customer customer;
		private BigDecimal totalPrice;

		public OrderBuilder() {

		}

		public OrderBuilder id(int orderId) {
			this.orderId = orderId;
			return this;
		}

		public OrderBuilder totalOrderLines(List<OrderLine> totalOrderLines) {
			this.totalOrderLines = totalOrderLines;
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

		public Order build() {
			return new Order(this);
		}
	}

	public int getOrderId() {
		return orderId;
	}

	public List<OrderLine> getOrderLine() {
		return totalOrderLines;
	}

	public Customer getCustomer() {
		return customer;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	// Override the toString method from the Object class.

	@Override
	public String toString() {
		return getOrderId() + " " + getOrderLine() + getCustomer().getLastName() + getCustomer().getName() + " "
				+ getTotalPrice();
	}

}