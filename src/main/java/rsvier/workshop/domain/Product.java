package rsvier.workshop.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product {

	private int productId;
	private String nameOfProduct;
	private BigDecimal priceOfProduct;
	private int stock;
	private LocalDate creationOfProduct = LocalDate.now();

	private Product(ProductBuilder builder) {
		this.productId = builder.productId;
		this.nameOfProduct = builder.nameOfProduct;
		this.priceOfProduct = builder.priceOfProduct;
		this.stock = builder.stock;
		this.creationOfProduct = builder.creationOfProduct;
	}

	public static class ProductBuilder {
		private int productId;
		private String nameOfProduct;
		private BigDecimal priceOfProduct;
		private int stock;
		private LocalDate creationOfProduct = LocalDate.now();

		public ProductBuilder() {
		}

		public ProductBuilder productId(int productId) {
			this.productId = productId;
			return this;
		}

		public ProductBuilder nameOfProduct(String nameOfProduct) {
			this.nameOfProduct = nameOfProduct;
			return this;
		}

		public ProductBuilder priceOfProduct(BigDecimal priceOfProduct) {
			this.priceOfProduct = priceOfProduct;
			return this;
		}

		public ProductBuilder stock(int stock) {
			this.stock = stock;
			return this;
		}

		public ProductBuilder creationOfProduct(LocalDate creationOfProduct) {
			this.creationOfProduct = creationOfProduct;
			return this;
		}

		public Product build() {

			return new Product(this);

		}
	}

	public int getProductId() {
		return productId;
	}

	public String getNameOfProduct() {
		return nameOfProduct;
	}

	public BigDecimal getPriceOfProduct() {
		return priceOfProduct;
	}

	public int getStock() {
		return stock;
	}

	public LocalDate getCreationOfProduct() {
		return creationOfProduct;
	}

	// Override the toString inherited from the Object class

	@Override
	public String toString() {

		return "Id of the Product:" + getProductId() + " Name:" + getNameOfProduct() + " Price:" + getPriceOfProduct()
				+ " Stock" + getStock() + " " + creationOfProduct.toString();

	}

}