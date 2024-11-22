package Brokerage;

public class BrokeragePresenter {
    private final BrokerageView brokerageView;

    public BrokeragePresenter(BrokerageController controller) {
        this.brokerageView = new BrokerageView(controller);
    }

    public void showView(){
        brokerageView.setVisible(true);
    }

    public void disposeView(){
        brokerageView.setVisible(false);
        brokerageView.dispose();
    }

}
