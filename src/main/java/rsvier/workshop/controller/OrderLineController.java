package rsvier.workshop.controller;

import rsvier.workshop.view.OrderLineView;

public class OrderLineController extends Controller {
    private OrderLineView orderLineView = new OrderLineView();

    @Override
    public void runView() {
        orderLineView.printHeaderMessage();
        orderLineView.printMenuMessage();
        orderLineMenuSwitch(orderLineView.getIntInput());
    }

    public void orderLineMenuSwitch(int menuChoice) {

    }
}
