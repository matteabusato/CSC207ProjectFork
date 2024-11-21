package main.java.Transaction;
import main.java.Views.ButtonMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TransactionButton extends ButtonMaker{

    public TransactionButton(String description, Color textColor, Color backgroundColor,
                             int x, int y, int width, int height) {
        super(description, textColor, backgroundColor, x, y, width, height);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Transaction");
    }
}
