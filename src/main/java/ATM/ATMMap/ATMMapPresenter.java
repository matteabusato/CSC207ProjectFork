package ATM.ATMMap;


import Functionality.FunctionalityController;
import Functionality.FunctionalityView;

import javax.swing.*;

public class ATMMapPresenter {

    private final JFrame atmMapView;

    public ATMMapPresenter(ATMMapController controller){
        ATMMapView view = new ATMMapView(controller);
        this.atmMapView = new FunctionalityView(view, controller);
    }

    public void showView(){
        atmMapView.setVisible(true);
    }

    public void disposeView(){
        atmMapView.setVisible(false);
        atmMapView.dispose();
    }
}
