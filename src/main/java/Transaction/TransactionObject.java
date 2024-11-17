package Transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
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
        // TODO: ADD CONSECUTIVE TRANSACTION ID GENERATOR
        this.transactionID = 1;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.cardUsed = cardUsed;
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

    public int getTransactionID() {
        return transactionID;
    }
    public int getSenderID(){
        return senderID;
    }
    public int getReceiverID(){
        return receiverID;
    }
    public String getCardUsed(){
        return cardUsed;
    }
    public double getAmount(){
        return amount;
    }
    public LocalDateTime getTimeStamp(){
        return timeStamp;
    }
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }
    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }
    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }
    public void setCardUsed(String cardUsed) {
        this.cardUsed = cardUsed;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
