package rsvier.workshop.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Order {

	private int orderId;
	private List<OrderLine> listOfTotalOrderLines = new ArrayList<OrderLine>();
	private Person person;
	private BigDecimal totalPrice;;
	private LocalDate orderDate;

	private Order(OrderBuilder builder) {
		
		this.orderId = builder.orderId;
		this.listOfTotalOrderLines = builder.listOfTotalOrderLines;
		this.person = builder.person;
		this.totalPrice = builder.totalPrice;
		this.orderDate = builder.orderDate;

	}

	public static class OrderBuilder {

		private int orderId;
		private List<OrderLine> listOfTotalOrderLines = new ArrayList<OrderLine>();
		private Person person;
		private BigDecimal totalPrice;
		private LocalDate orderDate;

		public OrderBuilder() {

		}
		
		public OrderBuilder(Order order) {
			
		    this.orderId = order.getOrderId();
		    this.listOfTotalOrderLines = order.getTotalOrderLines();
		    this.person = order.getPerson();
		    this.totalPrice = order.getTotalPrice();
		    this.orderDate = order.getOrderDate();
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

		public OrderBuilder getOrderDate(LocalDate orderDate) {
			
			this.orderDate = orderDate;
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

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public Person getPerson() {
		
		return person;
	}

	public LocalDate getOrderDate() {
		
		return orderDate;
	}

	public BigDecimal getTotalPrice() {
		
		return totalPrice;
	}

	// Override the toString method from the Object class.

	@Override
	public String toString() {
		return getOrderId() + " " + getTotalOrderLines() + getPerson().getLastName() + getPerson().getName() + " "
				+ getTotalPrice();
	}

}