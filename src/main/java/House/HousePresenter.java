package House;
import main.java.Views.PanelMaker;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class HousePresenter extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    public HousePresenter() {
        super("House Map");
        pack();
        Insets insets = getInsets();
        int frameWidth = WIDTH + insets.left + insets.right;
        int frameHeight = HEIGHT + insets.top + insets.bottom;
        setSize(frameWidth, frameHeight); // Set the size of the frame

        //JPanel panel = new PanelMaker(0, 0, width, height, null);

        Image map = Toolkit.getDefaultToolkit().getImage("src/main/java/House/map.png");
        JPanel panel = new PanelMaker(0, 0, WIDTH, HEIGHT, null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(map, 0, 0, this);  // Draw the map image at the top-left corner
            }
        };

        Map<String,House> houses = HouseReader.getHouses();
        for(House house: houses.values()) {
            house.createButton(panel);
        }

        this.add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close Application on clicking X
        setLocationRelativeTo(null); //Puts the Menu in the center

    }

}
