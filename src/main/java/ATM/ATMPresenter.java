package ATM;

import main.java.Views.ButtonMaker;
import main.java.Views.PanelMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Objects;

import static ATM.NominatimAPI.getATMCoordinates;


public class ATMPresenter extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    public ATMPresenter() throws Exception {
        super("ATM Locations");
        pack();
        Insets insets = getInsets();
        int frameWidth = WIDTH + insets.left + insets.right;
        int frameHeight = HEIGHT + insets.top + insets.bottom;

        setSize(frameWidth, frameHeight + 50); // Set the size of the frame
        setLayout(null);


        try {
            String address = "University of Toronto";
            //String address = "40 St George St, Toronto, ON";
            double[] addressCoordinate = NominatimAPI.getCoordinates(address);

            double lon = addressCoordinate[0];
            double lat = addressCoordinate[1];
            Image map = StaticMapAPI.generateMap(lon, lat, 600, 600, ATM.ZOOM);
            JPanel panel = new PanelMaker(0, 0, WIDTH, HEIGHT, null) {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(map, 0, 0, this);  // Draw the map image at the top-left corner
                }
            };


            generateATM(address, panel);
            this.add(panel);

            JPanel buttonPanel = new PanelMaker(0, 600, 600, 200, new FlowLayout());
            JTextField input = new JTextField("Enter an Address");
            JButton submit = new ButtonMaker("Enter") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel.removeAll();
                    try {
                        if(!input.getText().isEmpty())
                            generateATM(input.getText(), panel);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            };
            buttonPanel.add(input);
            buttonPanel.add(submit);
            this.add(buttonPanel);

        } catch (Exception e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close Application on clicking X
        setLocationRelativeTo(null); //Puts the Menu in the center
    }

    public List<ATM> generateATM(String address, JPanel panel) throws Exception {
        try {
            double[] addressCoordinate = NominatimAPI.getCoordinates(address);

            double lon = addressCoordinate[0];
            double lat = addressCoordinate[1];
            // Get ATMs
            String atmJson = getATMCoordinates(lon, lat,  1000); // Search within 1 km radius

            return NominatimAPI.createATMS(atmJson, lon, lat, panel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public static JPanel changePanel() {
        return null;
    }

}
