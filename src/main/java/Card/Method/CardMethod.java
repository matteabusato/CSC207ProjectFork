package Card.Method;

import Card.Entity.Card;
import Card.View.CardManagerGUI;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import org.jetbrains.annotations.NotNull;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardMethod {
    private static final String jsonFilePath = "cards.json";
    private static List<Card> cardList = new ArrayList<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void addCard(CardManagerGUI cardManagerGUI) {
        loadFromFile();
        String name = cardManagerGUI.nameField.getText();
        String securityCode = newSecurityCode();
        String id = newId(name);
        // 加入新的判断，例如不能出现同样id
        String expiryDate = newExpirydate();

        try {
            // for loop used to show when the time login with the account number
            for (Card card : cardList) {
                cardManagerGUI.model.addRow
                        (new Object[]{card.getId(), card.getName(), card.getDate(), card.getCode(),card.getAmount() + "$"});
            }
            //
            if (cardList.size() >= 10) {
                JOptionPane.showMessageDialog(cardManagerGUI.frame, "Too much cards", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // 创建新的card
                Card newCard = new Card(id, name, expiryDate, securityCode);
                cardList.add(newCard);
                cardManagerGUI.model.addRow(new Object[]{id, name, expiryDate, securityCode, newCard.getAmount()});
                objectMapper.writeValue(new File(jsonFilePath), cardList);
                cardManagerGUI.idField.setText("");
                cardManagerGUI.nameField.setText("");
                cardManagerGUI.expiryDateField.setText("");
                cardManagerGUI.securityCodeField.setText("");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCard() {

    }
//    private static boolean checkId(String id) {
//        File file = new File(jsonFilePath);
//        if (file.exists()) {
//            try {
//                List<Card> checkList = objectMapper.readValue(file, new TypeReference<>() {
//                });
//                for (Card card : checkList) {
//                    if (Objects.equals(card.getId(), id)) {
//                        return false;
//                    }
//                }
//            } catch (IOException e) {
//                System.out.println("Error get new id: " + e.getMessage());
//                return true;
//            }
//        }
//        return true;
//    }

    private static boolean checkId(String id) {
        loadFromFile();
        for (Card card : cardList) {
            if (Objects.equals(card.getId(), id)) {
                return false;
            }
        }
        return true;
    }

    private static void loadFromFile() {
        // load from the 不同的 account
        File file = new File(jsonFilePath);
        if (file.exists()) {
            try {
                cardList = objectMapper.readValue(file, new TypeReference<List<Card>>() {});
            } catch (IOException e) {
                System.out.println("Error loading from JSON file: " + e.getMessage());
            }
        }
    }

//    private void saveToFile() {
//        try {
//            objectMapper.writeValue(new File(jsonFilePath), cardList);
//        } catch (IOException e) {
//        }
//    }

    @NotNull
    private static String newExpirydate() {
        String expiryDate;
        LocalDate today = LocalDate.now();
        String month;
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();
        if (currentMonth < 10) {
            month = "0" + currentMonth;
        } else {
            month = String.valueOf(currentMonth);
        }
        expiryDate = month + "/" + (currentYear + 5);
        return expiryDate;
    }

    @NotNull
    private static String newId(String name) {
        String id = "";
        for (int i = 0; i < Math.min(name.length(), 3); i++) {
            char c = name.replaceAll("\\s", "").charAt(i);
            int k = c;
            id += String.valueOf(k);
        }
        // try , 让重复的id不能再出现 + 每个人啊account 不超过10张卡
        String insideId = id + Math.round((Math.pow(10, (10 - id.length())) - 1) * Math.random());
        while (!checkId(insideId)) {
            insideId = id + Math.round((Math.pow(10, (10 - id.length())) - 1) * Math.random());
        }
        id = insideId;
        return id;
    }

    @NotNull
    private static String newSecurityCode() {
        String securityCode;
        long num = Math.round(1000 * Math.random());
        if (num >= 100) {
            securityCode = String.valueOf(num);
        } else if (num >= 10) {
            securityCode = "0" + num;
        } else {
            securityCode = "00" + num;
        }
        return securityCode;
    }
}
