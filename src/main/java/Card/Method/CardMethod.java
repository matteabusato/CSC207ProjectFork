package Card.Method;

import Card.Entity.Card;
import Card.View.CardManagerGUI;
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

    /**
     * used to add the card and renew the GUI
     * @param cardManagerGUI the GUI need to renew
     */
    public static void addCard(CardManagerGUI cardManagerGUI) {
        loadFromFile();
        String name = cardManagerGUI.nameField.getText();
        String securityCode = newCode();
        String id = newId(name);
        // 加入新的判断，例如不能出现同样id
        String expiryDate = newDate();

        try {
            // for loop used to show when the time login with the account number
            //
            if (cardList.size() >= 10) {
                JOptionPane.showMessageDialog(cardManagerGUI.frame, "Too much cards", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // 创建新的card
                Card newCard = new Card(id, name, expiryDate, securityCode);
                cardList.add(newCard);
                cardManagerGUI.model.addRow(new Object[]{id, name, expiryDate, securityCode, newCard.getAmount()});
                objectMapper.writeValue(new File(jsonFilePath), cardList);
                cardManagerGUI.nameField.setText("");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        refresh(cardManagerGUI);
    }

    /**
     * used to delete the card and renew the GUI
     * @param cardManagerGUI the GUI need to renew
     */
    public static void deleteCard(CardManagerGUI cardManagerGUI) {
        int selectedRow = cardManagerGUI.table.getSelectedRow();
        if (selectedRow == - 1) {
            JOptionPane.showMessageDialog(cardManagerGUI.frame, "Please select a row");
        } else if (cardList.get(selectedRow).getAmount() != 0) {
            JOptionPane.showMessageDialog(cardManagerGUI.frame, "Please make the balance to 0 first");
        }
       else {
            cardList.remove(selectedRow);
            CardSave.saveCards(cardList);
       }
       refresh(cardManagerGUI);
    }

    /**
     * used to renew the GUI as a help function to add/delete card
     * @param cardManagerGUI the GUI need to renew
     */
    public static void refresh(CardManagerGUI cardManagerGUI) {
        loadFromFile();
        cardManagerGUI.model.setRowCount(0);
        for (Card card : cardList) {
            cardManagerGUI.model.addRow
                    (new Object[]{card.getId(), card.getName(), card.getDate(), card.getCode(),card.getAmount() + "$"});
        }
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


    /**
     * used to load the file of json
     */
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



    /**
     * used to get the new if with no same id
     */
    @NotNull
    private static String newId(String name) {
        String id = "";
        for (int i = 0; i < Math.min(name.length(), 3); i++) {
            char c = name.replaceAll("\\s", "").charAt(i);
            int k = c;
            id += String.valueOf(k);
        }
        // try , 让重复的id不能再出现 + 每个人啊account 不超过10张卡
        String insideId = id + getDifferentnumber(id);
        while (!checkId(insideId)) {
            insideId = id + getDifferentnumber(id);
        }
        id = insideId;
        return id;
    }

    /**
     * used to check the repeating of id
     * @param id random id that need to check if there is same id in file
     */
    private static boolean checkId(String id) {
        loadFromFile();
        for (Card card : cardList) {
            if (Objects.equals(card.getId(), id)) {
                return false;
            }
        }
        return true;
    }

    /**
     * used to check the random part of id is with same index
     * @param id random id given by the name
     */
    private static String getDifferentnumber(String id) {
        long num = Math.round((Math.pow(10, (10 - id.length())) - 1) * Math.random());
        switch (10 - id.length()) {
            case 1:
                return String.valueOf(num);
            case 2:
                if (num < 10) {
                    return "0" + num;
                }
                else {
                    return String.valueOf(num);
                }
            case 3:
                if (num < 10) {
                    return "00" + num;
                }
                else if (num < 100) {
                    return "0" + num;
                }
                else {
                    return String.valueOf(num);
                }
        }
        return String.valueOf(num);
    }

    /**
     * used to get the new update date for month and year
     */
    @NotNull
    private static String newDate() {
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

    /**
     * used to get the new code which is random with same index
     */
    @NotNull
    private static String newCode() {
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
