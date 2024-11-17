package DataObjects;

import DataAccess.DataAccessController;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UsersController {

    DataAccessController controller = new DataAccessController();

    public void addUser(User user) {
        List<User> users = controller.readData("Users.json", User.class);
        users.add(user);
        controller.saveData("Users.json", users, User.class);
    }

    public User getUser(int userID) {
        List<User> users = controller.readData("Users.json", User.class);
        for (User user : users) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    public void changeUser(int userID, User user) {
        List<User> users = controller.readData("Users.json", User.class);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserID() == userID) {
                users.set(i, user);
            }
        }
        controller.saveData("Users.json", users, User.class);
    }
}
