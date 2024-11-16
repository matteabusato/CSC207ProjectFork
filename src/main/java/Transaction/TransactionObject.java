package Transaction;

import DataAccess.DataAccessController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TransactionObject {
    int transactionID;
    String senderID;
    String receiverID;
    String cardUsed;
    double amount;
    LocalDateTime timeStamp;

    public TransactionObject(int transactionID, String senderID, String receiverID,
                             String cardUsed, double amount, LocalDateTime timeStamp) {
        this.transactionID = transactionID;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.cardUsed = cardUsed;
        this.amount = amount;
        this.timeStamp = timeStamp;
    }
}
