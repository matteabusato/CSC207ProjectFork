package Card.Method;

import Card.View.CardManagerGUI;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import Card.Entity.Card;

public class CardSave {
    private static final String FILE_PATH = "cards.json";

    public static List<Card> loadCards() {
        List<Card> cards = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                JSONArray jsonArray = new JSONArray(sb.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    cards.add(fromJSONObject(jsonArray.getJSONObject(i)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cards;
    }

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

    public static Card fromJSONObject(JSONObject json) {
        return new Card(
                json.getString("name"),
                json.getString("id"),
                json.getString("expiryDate"),
                json.getString("securityCode")
        );
    }

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
