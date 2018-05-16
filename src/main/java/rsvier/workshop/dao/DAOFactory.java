package rsvier.workshop.dao;

public class DAOFactory {

	private AccountDAO accountDAO = null;
	private AddressDAO addressDAO = null;
	private OrderDAO orderDAO = null;
	private OrderLineDAO orderLineDAO = null;
	private PersonDAO personDAO = null;
	private ProductDAO productDAO = null;
	
	public DAOFactory(int implementation) {
		
		switch(implementation) {
		
		case 1:	accountDAO = new AccountDAOImp();
				addressDAO = new AddressDAOImp();
				orderDAO = new OrderDAOImp();
				orderLineDAO = new OrderLineDAOImp();
				personDAO = new PersonDAOImp();
				productDAO = new ProductDAOImp();
				break;
		case 2:	accountDAO = new AccountDAOImpMongo();
				addressDAO = new AddressDAOImpMongo();
				orderDAO = new OrderDAOImpMongo();
				orderLineDAO = new OrderLineDAOImpMongo();
				personDAO = new PersonDAOImpMongo();
				productDAO = new ProductDAOImpMongo();
				break;
		default:	break;
		}
	}
	
	public AccountDAO getAccountDAO() {
		return accountDAO;
	}
	
	public AddressDAO getAddressDAO() {
		return addressDAO;
	}
	
	public OrderDAO getOrderDAO() {
		return orderDAO;
	}
	
	public OrderLineDAO getOrderLineDAO() {
		return orderLineDAO;
	}
	
	public PersonDAO getPersonDAO() {
		return personDAO;
	}
	
	public ProductDAO getProductDAO() {
		return productDAO;
	}
}

