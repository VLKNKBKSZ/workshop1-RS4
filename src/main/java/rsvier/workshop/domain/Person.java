package rsvier.workshop.domain;

public class Person {

	// Declare instance variables as protected. According to this, the child classes can be inherited.
	protected String name;
	protected String lastName;
	protected String middleName;
	protected String email;
	protected Address address;

	/*
	 * Make a public constructor for the Person class with the Builder object passed
	 * in. This is solving the wrong order input. To check if all instance variable
	 * are not default there will be a exception.
	 */

	protected Person(PersonBuilder builder) {

		this.name = builder.name;
		this.lastName = builder.lastName;
		this.middleName = builder.middleName;
		this.email = builder.email;
		this.address = builder.address;
	}

	public static class PersonBuilder {

		// Declare instance variables as protected. According to this, the child classes can be inherited.
		protected String name;
		protected String lastName;
		protected String middleName;
		protected String email;
		protected Address address;

		public PersonBuilder() {
		}

		public PersonBuilder name(String name) {
			this.name = name;
			return this;
		}

		public PersonBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public PersonBuilder middleName(String middleName) {
			this.middleName = middleName;
			return this;
		}

		public PersonBuilder email(String email) {
			this.email = email;
			return this;
		}

		public PersonBuilder address(Address address) {
			this.address = address;
			return this;
		}

		public Person build() {
			return new Person(this);

		}

	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getEmail() {
		return email;
	}

	public Address getAddress() {
		return address;
	}

	// Override the toString method that is inherited from the Object class.

	@Override

	public String toString() {
		return getLastName() + " " + getName() + " " + getMiddleName() + " " + getEmail() + " "
				+ getAddress().getStreetName() + " " + getAddress().getHouseNumber() + getAddress().getCity();
	}

}
