package rsvier.workshop.domain;

public class Person {
	
	private int personId;
	private Account account;
	private String name;
	private String lastName;
	private String middleName;


	/*
	 * Make a public constructor for the Person class with the Builder object passed
	 * in. This is solving the wrong order input. To check if all instance variable
	 * are not default there will be a exception.
	 */

	private Person(PersonBuilder builder) {
		
		this.personId = builder.personId;
		this.account = builder.account;
		this.name = builder.name;
		this.lastName = builder.lastName;
		this.middleName = builder.middleName;
	}

	public static class PersonBuilder {

		private int personId;
		private Account account;
		private String name;
		private String lastName;
		private String middleName;


		public PersonBuilder() {
		}
		
		public PersonBuilder(Person person) {
			this.personId = person.personId;
			this.account = person.account;
			this.name = person.name;
			this.lastName = person.lastName;
			this.middleName = person.lastName;
			
		}

		public PersonBuilder personId(int id) {
			this.personId = id;
			return this;
		}
		
		public PersonBuilder account(Account account) {
			this.account = account;
			
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

		
		public Person build() {
			return new Person(this);

		}

	}
	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
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

	public Account getAccount() {
		return account;
	}
		
	// Override the toString method that is inherited from the Object class.

	@Override

	public String toString() {
		return "\nId: " + getPersonId() + ", Voornaam: " + getName() + ", Achternaam: " + 
				getLastName() + ", Tussenvoegsel: " + getMiddleName() + " \n" ;
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