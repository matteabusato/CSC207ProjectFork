package Card;

import DataObjects.UserObject;
import LogIn.Welcome.WelcomeController;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CardController {
    public static List<Card> cardList = new ArrayList<>();
    static UserObject loggedInUser;
    private CardPresenter cardPresenter;
    private WelcomeController welcomeController;
    static CardDBAccess cardDBAccess = new CardDBAccess();

    public CardController(UserObject user) {
        this.loggedInUser = user;
        this.cardPresenter = new CardPresenter(this);
        this.welcomeController = new WelcomeController();
    }

    public void launch() {
        cardPresenter.showView();
    }

    public void logOutTriggered(){
        cardPresenter.disposeView();
        welcomeController.launch();
    }

    /**
     * used to load the file of json
     */
    public static void loadFromFile() {
        cardList = cardDBAccess.readData(loggedInUser.getUserID());
    }

    /**
     * used to get the new if with no same id
     */
    @NotNull
    public static String newId(String name) {
        String id = "";
        for (int i = 0; i < Math.min(name.length(), 3); i++) {
            char c = name.replaceAll("\\s", "").charAt(i);
            int k = c;
            id += String.valueOf(k);
        }
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
        long num = Math.round((Math.pow(10, (10 - id.length()))) * Math.random());
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
    public static String newDate() {
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
    public static String newCode() {
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

    /**
     * used to save the updated data in JSON file
     * @param card cards given in CardMethod CardList
     */
    public static void saveCards(Card card) {
        cardDBAccess.saveData(loggedInUser.getUserID(), card);
    }

    public static void saveDeleteCard(int index) {
        cardDBAccess.saveDeleteData(loggedInUser.getUserID(), index);
    }

//    /**
//     * used to save the updated data in JSON file
//     * @param cards cards given in CardMethod CardList
//     */
//    public static void saveCards(List<Card> cards) {
//        JSONArray jsonArray = new JSONArray();
//        for (Card card : cards) {
//            jsonArray.put(toJSONObject(card));
//        }
//        try (FileWriter writer = new FileWriter(FILE_PATH)) {
//            writer.write(jsonArray.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        //saveData(userID )
//    }

//    /**
//     * used to add the card and renew the GUI
//     * @param cardView the GUI need to renew
//     */
//    public void addCard(CardView cardView) {
//        loadFromFile();
//        String name = cardView.nameField.getText();
//        String securityCode = newCode();
//        String id = newId(name);
//        String expiryDate = newDate();
//
//        try {
//            if (cardList.size() >= 10) {
//                JOptionPane.showMessageDialog
//                        (cardView.frame, "Too much cards", "Error", JOptionPane.ERROR_MESSAGE);
//            } else {
//                // 创建新的card
//                Card newCard = new Card(id, name, expiryDate, securityCode);
//                cardList.add(newCard);
//                cardView.model.addRow(new Object[]{id, name, expiryDate, securityCode, newCard.getAmount()});
//                objectMapper.writeValue(new File(jsonFilePath), cardList);
//                cardView.nameField.setText("");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        refresh(cardView);
//    }

//    /**
//     * used to delete the card and renew the GUI
//     * @param cardView the GUI need to renew
//     */
//    public void deleteCard(CardView cardView) {
//        int selectedRow = cardView.table.getSelectedRow();
//        if (selectedRow == - 1) {
//            JOptionPane.showMessageDialog(cardView.frame, "Please select a row");
//        } else if (cardList.get(selectedRow).getAmount() != 0) {
//            JOptionPane.showMessageDialog(cardView.frame, "Please make the balance to 0 first");
//        }
//        else {
//            cardList.remove(selectedRow);
//            saveCards(cardList);
//        }
//        refresh(cardView);
//    }

    //    /**
//     * used to renew the GUI as a help function to add/delete card
//     * @param cardView the GUI need to renew
//     */
//    public static void refresh(CardView cardView) {
//        loadFromFile();
//        cardView.model.setRowCount(0);
//        for (Card card : cardList) {
//            cardView.model.addRow
//                    (new Object[]{card.getId(), card.getName(), card.getDate(), card.getCode(),card.getAmount() + "$"});
//        }
//    }

//    /**
//     * used to load the file of json
//     */
//    public static void loadFromFile() {
//        // load from the 不同的 account
//        File file = new File(jsonFilePath);
//        if (file.exists()) {
//            try {
//                cardList = objectMapper.readValue(file, new TypeReference<List<Card>>() {});
//            } catch (IOException e) {
//                System.out.println("Error loading from JSON file: " + e.getMessage());
//            }
//        }
//    }
//    // cardList =readData (userID)
//    // File file = new File(jsonFilePath); "\\CardInformation.json"

}
