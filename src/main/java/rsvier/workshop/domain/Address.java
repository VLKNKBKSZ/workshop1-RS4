package rsvier.workshop.domain;

public class Address {

	private int addressId;
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

	// Override the toString method that is inherited from the Object class.
	@Override
	public String toString() {
		return getAddressId() + " " + getStreetName() + " " + getHouseNumber() + " " + getAdditionalHouseNumber() + " "
				+ getPostalCode() + " " + getCity() + " " + getCountry() + " ";
	}

}
