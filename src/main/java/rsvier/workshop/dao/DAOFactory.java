package rsvier.workshop.dao;

public class DAOFactory {

	/*
	 * Initializing all dao's to be null, @runtime this doa's will be assignden a
	 * new value based on the input from the constructor
	 */
	
	private static AccountDAO accountDAO = null;
	private static AddressDAO addressDAO = null;
	private static OrderDAO orderDAO = null;
	private static OrderLineDAO orderLineDAO = null;
	private static PersonDAO personDAO = null;
	private static ProductDAO productDAO = null;

	public DAOFactory(int menuChoice) {

		switch (menuChoice) {

		case 1:

			System.out.println("U werkt nu met de SQL database");
			accountDAO = new AccountDAOImp();
			addressDAO = new AddressDAOImp();
			orderDAO = new OrderDAOImp();
			orderLineDAO = new OrderLineDAOImp();
			personDAO = new PersonDAOImp();
			productDAO = new ProductDAOImp();
			break;
		case 2:
			System.out.println("U werkt nu met de MongoDB database");
			accountDAO = new AccountDAOImpMongo();
			addressDAO = new AddressDAOImpMongo();
			orderDAO = new OrderDAOImpMongo();
			orderLineDAO = new OrderLineDAOImpMongo();
			personDAO = new PersonDAOImpMongo();
			productDAO = new ProductDAOImpMongo();
			break;
		default:
			System.out.println("Dit is geen geldige invoer.");

			break;
		}

	}

	public void chooseSQLOrMongoSwitch() {

	}

	public static AccountDAO getAccountDAO() {
		return accountDAO;
	}

	public static AddressDAO getAddressDAO() {
		return addressDAO;
	}

	public static OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public static OrderLineDAO getOrderLineDAO() {
		return orderLineDAO;
	}

	public static PersonDAO getPersonDAO() {
		return personDAO;
	}

	public static ProductDAO getProductDAO() {
		return productDAO;
	}
}
