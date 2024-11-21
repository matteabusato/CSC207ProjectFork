package ATM;

import main.java.Views.ButtonMaker;
import main.java.Views.LabelMaker;
import main.java.Views.PanelMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ATMPopUp extends PanelMaker {

    private boolean visible;

    public ATMPopUp(ATM atm, int x, int y, int width, int height) {
        super(x, y, width, height, new GridLayout(2, 1));
        JPanel labels = new PanelMaker(x, y, width, height / 2, new GridLayout(3, 1));
        JLabel name = new LabelMaker(atm.getName(), null);
        JLabel cash = new LabelMaker("Remaining Cash :" + atm.getRemainingCash(), null);
        JLabel fee = new LabelMaker("TransactionFee: " + atm.getTransactionFee(), null);
        labels.add(name);
        labels.add(cash);
        labels.add(fee);

        this.add(labels);

        JPanel buttons = new PanelMaker(0, 0, width, height / 2, new GridLayout(1, 2));
        JButton withdraw = new ButtonMaker("Withdraw") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("withdrawing");
            }
        };
        JButton deposit = new ButtonMaker("Deposit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("depositing");
            }
        };

        buttons.add(deposit);
        buttons.add(withdraw);
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
