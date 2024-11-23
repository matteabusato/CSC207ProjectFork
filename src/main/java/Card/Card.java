package Card;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Card {

     private final String id ;
     private final String name;
     private final String date;
     private final String code;
     private double amount;
     private int userID;

    @JsonCreator
    public Card(@JsonProperty("id") String id,
                @JsonProperty("name")String name,
                @JsonProperty("expiryDate")String date,
                @JsonProperty("securityCode")String code) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.code = code;
        this.amount = 0;
    }

    @JsonProperty
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }

    public String getCode() {
        return this.code;
    }

    public double getAmount() {
        return this.amount;
    }

    public void updateAmount(float newAmount) {
        this.amount = newAmount;
    }
}
