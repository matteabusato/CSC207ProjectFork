package Card.Entity;

public class CardCreate implements CardCreateInterface {

    public Card creat(String id, String name, String date, int code) {
        return new Card(id, name, date, code);
    }
}
