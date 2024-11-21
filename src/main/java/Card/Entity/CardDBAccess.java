package Card.Entity;

import DataAccess.DataAccessController;
import DataAccess.DataAccessInterface;
import DataObjects.UserObject;
import DataObjects.UsersController;

import java.util.List;

public class CardDBAccess implements DataAccessInterface<Card> {
    DataAccessController controller = new DataAccessController();
    UsersController usersController = new UsersController();


    @Override
    public UserObject saveData(int userID, Card card) {
        UserObject user = usersController.getUser(userID);
        List<Card> cards = controller.readData(user.getFileDirectory() + "\\CardInformation.json", Card.class);
        controller.saveData(user.getFileDirectory() + "\\CardInformation.json", cards, Card.class);

        return user;
    }

    @Override
    public List<Card> readData(int userID) {
        UserObject user = usersController.getUser(userID);
        List<Card> cards = controller.readData(user.getFileDirectory() + "\\CardInformation.json", Card.class);
        return cards;
    }
}
