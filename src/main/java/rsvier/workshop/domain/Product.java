package rsvier.workshop.domain;

import java.math.BigDecimal;
import java.sql.*;
import java.text.*;
import java.time.LocalDateTime;

public class Product {

	private int productId;
	private String name;
	private BigDecimal price;
	private int stock;

	private Product(ProductBuilder builder) {
		
		this.productId = builder.productId;
		this.name = builder.name;
		this.price = builder.price;
		this.stock = builder.stock;
	}

	public static class ProductBuilder {
		
		private int productId;
		private String name;
		private BigDecimal price;
		private int stock;

		public ProductBuilder() {
		}

		/*
		 * This is constructor is added to match updateDAO of evy. We copy a product
		 * then change it and build it again with this constructor
		 */
		
		public ProductBuilder(Product product) {
			
			this.productId = product.productId;
			this.name = product.name;
			this.price = product.price;
			this.stock = product.stock;
		}

		public ProductBuilder productId(int productId) {
			
			this.productId = productId;
			return this;
		}

		public ProductBuilder name(String name) {
			
			this.name = name;
			return this;
		}

		public ProductBuilder price(BigDecimal price) {
			
			this.price = price;
			return this;
		}

		public ProductBuilder stock(int stock) {
			
			this.stock = stock;
			return this;
		}

		public Product build() {

			return new Product(this);

		}
	}

	public int getProductId() {
		
		return productId;
	}

	public String getName() {
		
		return name;
	}

	public BigDecimal getPrice() {
		
		return price;
	}

	public int getStock() {
		
		return stock;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	// Override the toString inherited from the Object class

	@Override
	public String toString() {

		return "Id: " + getProductId() + " Naam: " + getName() + " Prijs: " + getPrice() + " Voorraad: " + getStock();

	}

}