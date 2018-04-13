package rsvier.workshop.domain;

public class Address {

	private int addressId;
<<<<<<< HEAD
	private int personId;
	private String streetName;
	private int houseNumber;
	private int additionalHouseNumber;
	private String postalCode;
	private String city;
	private String country;
	
	/*
	 * Set the constructor to private to force user to use only the builder to
	 * create new instances.
	 */

	private Address(AddressBuilder builder) {
		this.addressId = builder.addressId;
		this.personId = builder.personId;
		this.streetName = builder.streetName;
		this.houseNumber = builder.houseNumber;
		this.additionalHouseNumber = builder.additionalHouseNumber;
		this.postalCode = builder.postalCode;
		this.city = builder.city;
		this.country = builder.country;

	}

	public static class AddressBuilder {

		private int addressId;
		private int personId;
		private String streetName;
		private int houseNumber;
		private int additionalHouseNumber;
		private String postalCode;
		private String city;
		private String country;

		public AddressBuilder() {
		}
		// Add another constructor to copy AddressBuilder properties from another Address object.
		public AddressBuilder(Address address) {
			
			this.addressId = address.addressId;
			this.personId = address.personId;
			this.streetName = address.streetName;
			this.houseNumber = address.houseNumber;
			this.additionalHouseNumber = address.additionalHouseNumber;
			this.postalCode = address.postalCode;
			this.city = address.city;
			this.country = address.country;
		}

		
		
		public AddressBuilder addressId(int addressId) {
			this.addressId = addressId;
			return this;
		}
		
		public AddressBuilder personId(int personId) {
			this.personId = personId;
			return this;
		}

		public AddressBuilder streetName(String streetName) {
			this.streetName = streetName;
			return this;
		}

		public AddressBuilder houseNumber(int houseNumber) {
			this.houseNumber = houseNumber;
			return this;
		}

		public AddressBuilder additionalHouseNumber(int additionalHouseNumber) {
			this.additionalHouseNumber = additionalHouseNumber;
			return this;
		}

		public AddressBuilder postalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}

		public AddressBuilder city(String city) {
			this.city = city;
			return this;
		}

		public AddressBuilder country(String country) {
			this.country = country;
			return this;
		}

		public Address build() {
			return new Address(this);
		}

	}

	public int getAddressId() {
		return addressId;
	}
	
	public int getPersonId() {
		return personId;
=======
	private String streetName;
	private int houseNumber;
	private int additionalHouseNumber;
	private String postalCode;
	private String city;
	private String country;
	
	/*
	 * Set the constructor to private to force user to use only the builder to
	 * create new instances.
	 */

	private Address(AddressBuilder builder) {
		this.addressId = builder.addressId;
		this.streetName = builder.streetName;
		this.houseNumber = builder.houseNumber;
		this.additionalHouseNumber = builder.additionalHouseNumber;
		this.postalCode = builder.postalCode;
		this.city = builder.city;
		this.country = builder.country;

	}

	public static class AddressBuilder {

		private int addressId;
		private String streetName;
		private int houseNumber;
		private int additionalHouseNumber;
		private String postalCode;
		private String city;
		private String country;

		public AddressBuilder() {
		}
		// Add another constructor to copy AddressBuilder properties from another Address object.
		public AddressBuilder(Address address) {
			
			this.addressId = address.addressId;
			this.streetName = address.streetName;
			this.houseNumber = address.houseNumber;
			this.additionalHouseNumber = address.additionalHouseNumber;
			this.postalCode = address.postalCode;
			this.city = address.city;
			this.country = address.country;
		}

		
		
		public AddressBuilder addressId(int addressId) {
			this.addressId = addressId;
			return this;
		}

		public AddressBuilder streetName(String streetName) {
			this.streetName = streetName;
			return this;
		}

		public AddressBuilder houseNumber(int houseNumber) {
			this.houseNumber = houseNumber;
			return this;
		}

		public AddressBuilder additionalHouseNumber(int additionalHouseNumber) {
			this.additionalHouseNumber = additionalHouseNumber;
			return this;
		}

		public AddressBuilder postalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}

		public AddressBuilder city(String city) {
			this.city = city;
			return this;
		}

		public AddressBuilder country(String country) {
			this.country = country;
			return this;
		}

		public Address build() {
			return new Address(this);
		}

	}

	public int getAddressId() {
		return addressId;
>>>>>>> refs/remotes/origin/master
	}

	public String getStreetName() {
		return streetName;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public int getAdditionalHouseNumber() {
		return additionalHouseNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	/* Override the toString method that is inherited from the Object class.
	 * Not print addressId in toString()
	 */
	
	@Override
	public String toString() {
		return   getStreetName() + " " + getHouseNumber() + " " + getAdditionalHouseNumber() + " "
				+ getPostalCode() + " " + getCity() + " " + getCountry() + " ";
	}

	
	// Override equals method which is inherited from the Object class
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		
		if(addressId != other.addressId)
			return false;
		return true;
	}
	
	

}
