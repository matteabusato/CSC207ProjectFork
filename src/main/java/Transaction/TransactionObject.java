package Transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TransactionObject {
    int transactionID;
    int senderID;
    int receiverID;
    String cardUsed;
    double amount;
    LocalDateTime timeStamp;

    public TransactionObject(int senderID, int receiverID,
                             String cardUsed, double amount, LocalDateTime timeStamp) {
        this.transactionID = 1;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.cardUsed = cardUsed;
        this.amount = amount;
        this.timeStamp = timeStamp;
    }
}
