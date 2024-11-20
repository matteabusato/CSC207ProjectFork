package House;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import main.java.Views.LabelMaker;
import main.java.Views.PanelMaker;
import main.java.Views.ButtonMaker;

public class HousePopUp extends PanelMaker {


    public HousePopUp(House house, int x, int y, int width, int height) {
        super(x, y, width, height, new GridLayout(2, 1));
        JPanel labels = new PanelMaker(x, y, width, height / 2, new GridLayout(4, 1));
        JLabel name = new LabelMaker(house.getName(), null);
        JLabel address = new LabelMaker("Address: " + house.getAddress(), null);
        JLabel price = new LabelMaker("Price :" + house.getPrice(), null);
        JLabel owner = new LabelMaker("Owner: " + house.getOwner(), null);
        labels.add(name);
        labels.add(address);
        labels.add(price);
        labels.add(owner);

        this.add(labels);

        JPanel buttons = new PanelMaker(0, 0, width, height / 2, new GridLayout(1, 2));
        JButton purchase = new ButtonMaker("Purchase") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Buying");
            }
        };
        JButton deposit = new ButtonMaker("Sell") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Selling");
            }
        };

        buttons.add(purchase);
        buttons.add(deposit);
        this.add(buttons);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                Rectangle bounds = getBounds();
                int x = e.getPoint().x + bounds.x;
                int y = e.getPoint().y + bounds.y;
                if (bounds.contains(x, y)) {
                    setVisible(true); // Hide popup when mouse exits
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Rectangle bounds = getBounds();
                int x = e.getPoint().x + bounds.x;
                int y = e.getPoint().y + bounds.y;
                if (!bounds.contains(x, y)) {
                    setVisible(false); // Hide popup when mouse exits
                }
            }
        });

        this.setVisible(false);

    }
}
