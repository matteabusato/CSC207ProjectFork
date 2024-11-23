package House.HouseMap;

import ATM.ATMMap.ATMMapController;
import ATM.ATMMap.ATMMapView;
import Functionality.FunctionalityView;
import LogIn.LoggedIn.LoggedInController;

import javax.swing.*;

public class HouseMapPresenter {

    private final JFrame houseMapView;

    public HouseMapPresenter(HouseMapController controller) {
        HouseMapView houseView = new HouseMapView(controller);
        this.houseMapView = new FunctionalityView(houseView, controller);
    }

    public void showView(){
        houseMapView.setVisible(true);
    }

    public void disposeView(){
        houseMapView.setVisible(false);
        houseMapView.dispose();
    }
}
