package Transaction.MakeTransaction;

import javax.swing.*;

public class MakeTransactionPresenter extends JFrame {
    private final MakeTransactionView makeTransactionView;

    public MakeTransactionPresenter(MakeTransactionController controller){
        this.makeTransactionView = new MakeTransactionView(controller);
    }

    public void showView(){
        makeTransactionView.setVisible(true);
    }

    public void disposeView(){
        makeTransactionView.setVisible(false);
        makeTransactionView.dispose();
    }
}
