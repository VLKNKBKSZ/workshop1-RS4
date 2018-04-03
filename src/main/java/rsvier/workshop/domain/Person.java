package rsvier.workshop.domain;

public class Person {
	
	private int personId;
	private int accountId;
	private String personType;
	private String name;
	private String lastName;
	private String middleName;
	private Address address;


	/*
	 * Make a public constructor for the Person class with the Builder object passed
	 * in. This is solving the wrong order input. To check if all instance variable
	 * are not default there will be a exception.
	 */

	private Person(PersonBuilder builder) {
		
		this.personId = builder.personId;
		this.accountId = builder.accountId;
		this.personType = builder.accountType;
		this.name = builder.name;
		this.lastName = builder.lastName;
		this.middleName = builder.middleName;
		this.address = builder.address;
	}

	public static class PersonBuilder {

		private int personId;
		private int accountId;
		private String accountType;
		private String name;
		private String lastName;
		private String middleName;
		private Address address;


		public PersonBuilder() {
		}
		
		public PersonBuilder id(int id) {
			this.personId = id;
			return this;
		}
		
		public PersonBuilder accountId(int accountId) {
			this.accountId = accountId;
			
			return this;
		}
		
		public PersonBuilder accountType(String accountType) {
			this.accountType = accountType;
			return this;
		}

		public PersonBuilder name(String name) {
			this.name = name;
			return this;
		}

		public PersonBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public PersonBuilder middleName(String middleName){

			if(middleName == null){
			this.middleName = " ";
			} else {
			this.middleName = middleName;
			}
			return this;			
		}

		public PersonBuilder address(Address address) {
			this.address = address;
			return this;
		}

		public Person build() {
			return new Person(this);

		}

	public int getAccountId() {
		return accountId;
	}
		
	}
	public int getPersonId() {
		return personId;
	}
	
	public String getPersonType() {
		return personType;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		if (middleName == null)
		return " ";
		else
		return middleName;
	}

	public Address getAddress() {
		return address;
	}

	// Override the toString method that is inherited from the Object class.

	@Override

	public String toString() {
		return getPersonType() + " " + getLastName() + " " + getName() + " " + getMiddleName() + " " +
				getAddress().toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (personId != other.personId)
			return false;
		return true;
	}

}
