package rsvier.workshop.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class Order {

	private int orderId;
	private List<OrderLine> listOfTotalOrderLines = new ArrayList<>();
	private Person person;
	private BigDecimal totalPrice;;
	private LocalDateTime orderDateTime;

	private Order(OrderBuilder builder) {
		
		this.orderId = builder.orderId;
		this.listOfTotalOrderLines = builder.listOfTotalOrderLines;
		this.person = builder.person;
		this.totalPrice = builder.totalPrice;
		this.orderDateTime = builder.orderDateTime;

	}

	public static class OrderBuilder {

		private int orderId;
		private List<OrderLine> listOfTotalOrderLines = new ArrayList<>();
		private Person person;
		private BigDecimal totalPrice;
		private LocalDateTime orderDateTime;

		public OrderBuilder() {

		}
		
		public OrderBuilder(Order order) {
			
		    this.orderId = order.getOrderId();
		    this.listOfTotalOrderLines = order.getTotalOrderLines();
		    this.person = order.getPerson();
		    this.totalPrice = order.getTotalPrice();
		    this.orderDateTime = order.getOrderDateTime();
		}

		public OrderBuilder orderId(int orderId) {
			
			this.orderId = orderId;
			return this;
		}

		public OrderBuilder totalOrderLines(List<OrderLine> totalOrderLines) {
			
			this.listOfTotalOrderLines = totalOrderLines;
			return this;
		}

		public OrderBuilder person(Person person) {
			
			this.person = person;
			return this;
		}

		public OrderBuilder totalPrice(BigDecimal totalPrice) {
			
			this.totalPrice = totalPrice;
			return this;
		}

		public OrderBuilder getOrderDateTime(LocalDateTime orderDateTime) {
			
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

	public List<OrderLine> getTotalOrderLines() {
		
		return listOfTotalOrderLines;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setListOfTotalOrderLines(List<OrderLine> listOfTotalOrderLines) {
		this.listOfTotalOrderLines = listOfTotalOrderLines;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public Person getPerson() {
		
		return person;
	}

	public LocalDateTime getOrderDateTime() {
		
		return orderDateTime;
	}

	public BigDecimal getTotalPrice() {
		
		return totalPrice;
	}

	// Override the toString method from the Object class.

	@Override
	public String toString() {
		return getOrderId() + " " + getTotalOrderLines() + getPerson().getName() + " " + getPerson().getLastName() + " "
				+ getTotalPrice();
	}

}