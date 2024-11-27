package Brokerage;
import app.PresenterInterface;

public class BrokeragePresenter implements PresenterInterface<BrokerageController> {
    private final BrokerageView brokerageView;

    public BrokeragePresenter(BrokerageController controller) {
        brokerageView = new BrokerageView(controller);
    }

    @Override
    public void showView(){
        brokerageView.setVisible(true);
    }

    @Override
    public void disposeView(){
        brokerageView.setVisible(false);
        brokerageView.dispose();
    }

}
