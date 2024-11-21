package House;

import javax.swing.*;

public class House {

    private String name;
    private String address;
    private int x;
    private int y;
    private double price;
    private String owner;

    public House(String name, String address, int x, int y, double price, String owner) {
        this.name = name;
        this.address = address;
        this.x = x;
        this.y = y;
        this.price = price;
        this.owner = owner;
    }

    public House() {
        name = "";
        address = "";
        x = 0;
        y = 0;
        price = 0;
        owner = "";
    }

    public void createButton(JPanel panel) {
        new HouseButton(this, x, y, 20, 20, panel);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getPrice() {
        return price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
