package rsvier.workshop.controller;

public class MainController {

    public enum TypeOfController {PERSON, EMPLOYEE, CUSTOMER, MAINMENU, ACCOUNT};

    private TypeOfController controllerType;
    private static PersonController personController = new PersonController();
    private static EmployeeController employeeController = new EmployeeController();
    private static CustomerController customerController = new CustomerController();

    private static Controller currentController;

    //changes currentController and calls the runView method of that controller
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
