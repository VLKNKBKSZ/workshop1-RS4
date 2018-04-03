package rsvier.workshop.domain;

import java.math.BigDecimal;
import java.util.*;

public class Order {

	private int orderId;
	private List<OrderLine> totalOrderLines;

	/*
	 * Thought of saving all the OrderLines of 1 order inside of this private List.
	 * Maybe its a ArrayList or LinkedList(Set,Map), When implementing this in the
	 * DAO the idea is to loop true all orderLines, get the Price add it all to one
	 * variable an then add that to the TotalPrice in the Order class. Still need to
	 * think about this, Help me EVA &EVY :D
	 */
	private Person person;

	private BigDecimal totalPrice;;

	private Order(OrderBuilder builder) {
		this.orderId = builder.orderId;
		this.totalOrderLines = builder.totalOrderLines;
		this.person = builder.person;
		this.totalPrice = builder.totalPrice;

	}

	public static class OrderBuilder {

		private int orderId;
		private List<OrderLine> totalOrderLines;
		private Person person;
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

		public OrderBuilder customer(Person person) {
			this.person = person;
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

	public Person getCustomer() {
		return person;
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