package rsvier.workshop.controller;

public class MainController {

    private static PersonController personController = new PersonController();
    private static EmployeeController employeeController = new EmployeeController();
    private static CustomerController customerController = new CustomerController();

    private static Controller currentController;

    //TODO make it use enum type

    //changes currentController and calls the runView method of that controller
    public static void setController(int choice) {
        switch (choice) {
            case 1:
                currentController = personController;
                break;
            case 2:
                currentController = employeeController;
                break;
            case 3:
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
