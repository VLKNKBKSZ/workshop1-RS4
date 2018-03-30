package rsvier.workshop.domain;

public class Customer extends Person {

	private int customerId;

	/*
	 * Set the constructor to private to force user to use only the builder to
	 * create new instances.
	 */

	private Customer(CustomerBuilder builder) {
		super(builder);
		this.customerId = builder.customerId;
	}

	public static class CustomerBuilder extends PersonBuilder {

		private int customerId;

		public CustomerBuilder() {

		}
		
		// Create another CustomerBuilder constructor to get a Customer object and copy all properties from a Customer object 
		public CustomerBuilder(Customer customer){
			
			this.customerId = customer.customerId;
			this.name = customer.name;
			this.lastName = customer.lastName;
			this.middleName = customer.middleName;
			this.email = customer.email;
			this.address = customer.address;
		}

		public CustomerBuilder customerId(int customerId) {

			this.customerId = customerId;
			return this;
		}

		// Overriding the build method that is inherited from the Superclass Person
		@Override
		public Customer build() {
			return new Customer(this);
		}
	}

	public int getCustomerId() {
		return customerId;
	}

	/*
	 * Override the toString method from the Superclass Person. Could change this in
	 * the future to other format.
	 */
	
	@Override

	public String toString() {

		return "Id: " + getCustomerId() + " lastname: " + getLastName() + " name:" + getName() + " Address: "  + getAddress().toString();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customerId != other.customerId)
			return false;
		return true;
	}
	
	
	
	
}

