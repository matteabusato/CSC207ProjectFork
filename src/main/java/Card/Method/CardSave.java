package Card.Method;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.List;
import Card.Entity.Card;

public class CardSave {
    private static final String FILE_PATH = "cards.json";

    /**
     * used to save the updated data in JSON file
     * @param cards cards given in CardMethod CardList
     */
    public static void saveCards(List<Card> cards) {
        JSONArray jsonArray = new JSONArray();
        for (Card card : cards) {
            jsonArray.put(toJSONObject(card));
        }
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * used to transfer the card into the String to store
     * @param card one single card with the parameters
     */
    public static JSONObject toJSONObject(Card card) {
        JSONObject json = new JSONObject();
        json.put("name", card.getName());
        json.put("id", card.getId());
        json.put("expiryDate", card.getDate());
        json.put("securityCode", card.getCode());
        json.put("amount", card.getAmount());
        return json;
    }

}
