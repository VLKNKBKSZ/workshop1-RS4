package rsvier.workshop.domain;

public class Employee extends Person {

	private int employeeId;
	private String password;

	// First call the super builder from class Person, then call EmployeeBuilder to
	// add the password.

	private Employee(EmployeeBuilder builder) {
		super(builder);
		this.employeeId = builder.employeeId;
		this.password = builder.password;
	}

	public static class EmployeeBuilder extends PersonBuilder {

		private int employeeId;
		private String password;

		public EmployeeBuilder() {
		}

		public EmployeeBuilder customerId(int employeeId) {
			this.employeeId = employeeId;
			return this;
		}

		public EmployeeBuilder password(String password) {
			this.password = password;
			return this;
		}

		@Override
		public Employee build() {
			return new Employee(this);

		}

	}

	public int getEmployeeId() {
		return employeeId;
	}

	public String getPassword() {
		return password;
	}

	// Override the toString method that is inherited from the Person class.
	public String toString() {
		return getEmployeeId() + " " + getName() + " " + getLastName();
	}
}