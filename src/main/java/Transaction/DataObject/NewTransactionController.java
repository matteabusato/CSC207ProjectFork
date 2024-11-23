package Transaction.DataObject;

import java.time.LocalDateTime;

public class NewTransactionController {
    public TransactionObject createNewTransaction(int senderID, int receiverID, String cardUsed, double amount){
        int newTransactionID = generateTransactionID();
        int newSenderID = senderID;
        int newReceiverID = receiverID;
        String newCardUsed = cardUsed;
        double newAmount = amount;
        LocalDateTime newTimeStamp = getTimeStamp();

        TransactionObject newTransaction = new TransactionObject(newTransactionID, newSenderID, newReceiverID, newCardUsed, newAmount, newTimeStamp);

        return newTransaction;
    }

    private int generateTransactionID(){
        return 1;
    }

    private LocalDateTime getTimeStamp(){
        return LocalDateTime.now();
    }
}
