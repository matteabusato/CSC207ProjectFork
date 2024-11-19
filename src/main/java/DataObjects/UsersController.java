package DataObjects;

import DataAccess.DataAccessController;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UsersController {
    DataAccessController controller = new DataAccessController();

    public void addUser(UserObject user) {
        List<UserObject> users = controller.readData("Users.json", UserObject.class);
        users.add(user);
        controller.saveData("Users.json", users, UserObject.class);
    }

    public UserObject getUser(int userID) {
        List<UserObject> users = controller.readData("Users.json", UserObject.class);
        for (UserObject user : users) {
            if (user != null && user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }

    public void changeUser(int userID, UserObject user) {
        List<UserObject> users = controller.readData("Users.json", UserObject.class);
        for (int i = 0; i < users.size(); i++) {
            if (user != null && users.get(i).getUserID() == userID) {
                users.set(i, user);
            }
        }
        controller.saveData("Users.json", users, UserObject.class);
    }

    // fix the rest:
    public UserObject addUser(String firstName, String lastName, String password){
        int userID = generateUserID();
        String passwordHash = password;
        double balance = 0;
        String fileDirectory = createFileDirectory(firstName, lastName, userID);

        UserObject user = new UserObject(userID, firstName, lastName, passwordHash, balance);
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
