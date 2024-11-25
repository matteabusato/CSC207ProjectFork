package House.HouseMap;

import ATM.ATMMap.ATMMapController;
import ATM.ATMMap.ATMMapView;
import App.PresenterInterface;
import DataObjects.UserObject;
import Functionality.FunctionalityView;
import LogIn.LoggedIn.LoggedInController;

import javax.swing.*;

public class HouseMapPresenter implements PresenterInterface<HouseMapController> {

    private final JFrame houseMapView;

    public HouseMapPresenter(UserObject user, HouseMapController controller) {
        HouseMapView houseView = new HouseMapView(user,controller);
        this.houseMapView = new FunctionalityView(houseView, controller);
    }

    @Override
    public void showView(){
        houseMapView.setVisible(true);
    }

    @Override
    public void disposeView(){
        houseMapView.setVisible(false);
        houseMapView.dispose();
    }
}
