package Card.Entity;

public class Card implements CardInterface {

    private final String Id ;
    private final String name;
    private final String date;
    private final int code;
    private final float amount;

    public Card(String id, String name, String date, int code) {
        this.Id = id;
        this.name = name;
        this.date = date;
        this.code = code;
        this.amount = 0;
    }

    public String getId() {
        return this.Id;
    }

    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }

    public int getCode() {
        return this.code;
    }

    public float getAmount() {
        return this.amount;
    }
}
