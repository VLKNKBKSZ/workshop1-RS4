package rsvier.workshop.controller;

public class MainController {

    public enum TypeOfController {PERSON, EMPLOYEE, CUSTOMER, MAINMENU, ACCOUNT, PRODUCT,ORDER};

    private TypeOfController controllerType;
    private static PersonController personController = new PersonController();
    private static EmployeeController employeeController = new EmployeeController();
    private static CustomerController customerController = new CustomerController();
    private static MainMenuController mainMenuController = new MainMenuController();
    private static AccountController accountController = new AccountController();
    private static ProductController productController = new ProductController();
    private static OrderController orderController = new OrderController();

    private static Controller currentController;

    
    //Switch method for setting currentController and calling the runView method of that controller
    
    public static void setController(TypeOfController controllerType) {
       
    		switch (controllerType) {
    		
    			case PERSON:
    				currentController = personController;
    				break;
    				
            case EMPLOYEE:
                currentController = employeeController;
                break;
                
            case CUSTOMER:
                currentController = customerController;
                break;
                
            case MAINMENU:
                currentController = mainMenuController;
                break;
                
            case ACCOUNT:
                currentController = accountController;
                break;
                
            case PRODUCT:
            		currentController = productController;
            		break;
            		
            case ORDER:
            		currentController = orderController;
            		break;
            	
        }

        currentController.runView();
    }

    /*private static void runView() {
        currentController.printView();
    }

    public void queryChoice() {
        while(true) {
            boolean validChoice = false;

            int keuze = -1;
            while(!validChoice) {
                // query keuze
                // zet keuze in keuze
                // zet valideKeuze
            }

            currentController.verwerkKeuze(keuze);
        }
    }*/
}
