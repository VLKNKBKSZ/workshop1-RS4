package rsvier.workshop.dao;

import rsvier.workshop.view.*;

public class DAOFactory {
	public static boolean hikariEnabled;
	public View view = new MainMenuView();
	
	static AccountDAO accountDAO = null;
	static AddressDAO addressDAO = null;
	static OrderDAO orderDAO = null;
	static OrderLineDAO orderLineDAO = null;
	static PersonDAO personDAO = null;
	static ProductDAO productDAO = null;
	
	View accountView = new AccountView();
	
	public DAOFactory() {
		
		
	}
	
	public  void chooseSQLOrMongoSwitch() {
		
		view.printAskUserToUseSQLOrMongo();
		int menuChoice = view.getIntInput();
		
		switch(menuChoice) {
		
		case 1:	
			hikariEnabled = view.printAskUserToEnableHikariOrNot();
			System.out.println("U werkt nu met de SQL database");
				accountDAO = new AccountDAOImp();
				addressDAO = new AddressDAOImp();
				orderDAO = new OrderDAOImp();
				orderLineDAO = new OrderLineDAOImp();
				personDAO = new PersonDAOImp();
				productDAO = new ProductDAOImp();
				break;
		case 2:	System.out.println("U werkt nu met de MongoDB database");
				accountDAO = new AccountDAOImpMongo();
				addressDAO = new AddressDAOImpMongo();
				orderDAO = new OrderDAOImpMongo();
				orderLineDAO = new OrderLineDAOImpMongo();
				personDAO = new PersonDAOImpMongo();
				productDAO = new ProductDAOImpMongo();
				break;
		default:	System.out.println("Dit is geen geldige invoer.");
					chooseSQLOrMongoSwitch();
				break;
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

