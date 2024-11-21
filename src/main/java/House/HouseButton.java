package House;
import main.java.Views.PanelMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HouseButton extends PanelMaker {

    public static final int POPUPWIDTH = 200;
    public static final int POPUPHEIGHT = 150;
    private final HousePopUp popUp;

    public HouseButton(House house, int x, int y, int width, int height, JPanel panel) {
        super(x, y, width, height, null);
        if (HousePresenter.WIDTH - x < POPUPWIDTH) x -= POPUPWIDTH - width;
        if (HousePresenter.HEIGHT - y < POPUPHEIGHT) y -= POPUPHEIGHT - height;


        this.popUp = new HousePopUp(house, x, y , POPUPWIDTH, POPUPHEIGHT);
        panel.add(popUp);
        panel.add(this);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                popUp.setVisible(true); // Show popup when mouse enters
            }

            @Override
            public void mouseExited(MouseEvent e) {
                popUp.setVisible(false); // Hide popup when mouse exits
            }
        });
    }
}
