package ATM;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;

public class StaticMapAPI extends JFrame {

    public StaticMapAPI() {
        // Set up the window
        setTitle("Static Map with ATM Markers");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            // Construct the URL with parameters for the Static Maps API
            String apiKey = "AIzaSyBsGgjjDB_epRNprDidv8m-bldiGv5EuuM"; // Replace with your actual API key

            // Build the markers string for all ATM locations
            String markers =
                    "&markers=43.6541316,-79.4005452" + // Canada Trust
                            "&markers=43.6645752,-79.3997156"  // Meridian Credit Union ATM
            ;

            // Construct the final URL for the Static Map API
            String urlString = "https://maps.googleapis.com/maps/api/staticmap?center=43.6592043,-79.3975116&zoom=15&size=600x600" + markers + "&key=" + apiKey;

            // Fetch the image from the URL
            URL url = new URL(urlString);
            Image mapImage = ImageIO.read(url);  // Read the image from the URL

            // Panel to display the image
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // No rotation or transformations are applied, it will be displayed as fetched
                    g.drawImage(mapImage, 0, 0, this);  // Draw the map image at the top-left corner
                }
            };

            // Add the panel to the JFrame
            add(panel);
            setVisible(true);  // Display the window

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Image generateMap(double lon, double lat, int width, int height, int zoom) {
        try {
            // Construct the URL with parameters for the Static Maps API
            String apiKey = "AIzaSyBsGgjjDB_epRNprDidv8m-bldiGv5EuuM"; // Replace with your actual API key

            // Build the markers string for all ATM locations
            String baseURL = "https://maps.googleapis.com/maps/api/staticmap?";
            String centerString = "?center=" + lat +"," + lon;
            String markerString = "&markers=" + lat + "," + lon;
            String zoomString = "&zoom=" + zoom;
            String sizeString = "&size=" + width + "x" + height;
            String apiKeyString = "&key=" + apiKey;
            // Construct the final URL for the Static Map API
            String urlString = baseURL + centerString + markerString + zoomString + sizeString + apiKeyString;
            System.out.println(urlString);
            // Fetch the image from the URL
            URL url = new URL(urlString);
            // Read the image from the URL;

            return ImageIO.read(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        // Start the application
    }
}
