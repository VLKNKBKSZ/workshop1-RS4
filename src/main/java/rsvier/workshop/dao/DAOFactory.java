package rsvier.workshop.dao;

public class DAOFactory {

	static AccountDAO accountDAO = null;
	static AddressDAO addressDAO = null;
	static OrderDAO orderDAO = null;
	static OrderLineDAO orderLineDAO = null;
	static PersonDAO personDAO = null;
	static ProductDAO productDAO = null;
	
	
	public DAOFactory(int implementation) {
		
		switch(implementation) {
		
		case 1:	System.out.println("U werkt nu met de SQL database");
				accountDAO = new AccountDAOImp();
				addressDAO = new AddressDAOImp();
				orderDAO = new OrderDAOImp();
				orderLineDAO = new OrderLineDAOImp();
				personDAO = new PersonDAOImp();
				productDAO = new ProductDAOImp();
				break;
		case 2:	System.out.println("U werkt nu met de Mongo database");
				accountDAO = new AccountDAOImpMongo();
				//addressDAO = new AddressDAOImpMongo();
				//orderDAO = new OrderDAOImpMongo();
				//orderLineDAO = new OrderLineDAOImpMongo();
				personDAO = new PersonDAOImpMongo();
				//productDAO = new ProductDAOImpMongo();
				break;
		default:	break;
		}
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

