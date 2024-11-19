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
            if (user != null && user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    public void changeUser(int userID, User user) {
        List<User> users = controller.readData("Users.json", User.class);
        for (int i = 0; i < users.size(); i++) {
            if (user != null && users.get(i).getUserID() == userID) {
                users.set(i, user);
            }
        }
        controller.saveData("Users.json", users, User.class);
    }

    // fix the rest:
    public User addUser(String firstName, String lastName, String password){
        int userID = generateUserID();
        String passwordHash = password;
        double balance = 0;
        String fileDirectory = createFileDirectory(firstName, lastName, userID);

        User user = new User(userID, firstName, lastName, passwordHash, balance);
        usersController.addUser(user);

        return user;
    }

    private String generatePassword() {
        return "";
    }

    private int generateUserID() {
        return 0;
    }

    private String createFileDirectory(String firstName, String lastName, int userID){
        return firstName + lastName + userID;
    }
}
