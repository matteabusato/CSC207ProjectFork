package DataObjects;

import DataAccess.DataAccessController;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.net.PasswordAuthentication;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UsersController {
    UsersDBAccess usersDBAccess = new UsersDBAccess();
    NewUserController newUserController = new NewUserController();

    public UserObject addUser(String firstName,String lastName,String password) {
        UserObject newUser = newUserController.createNewUser(firstName, lastName, password);
        usersDBAccess.saveData(newUser);
        return newUser;
    }

    public UserObject getUser(int userID) {
        return usersDBAccess.readDataPoint(userID);
    }

    public void changeUser(int userID, UserObject user) {
        usersDBAccess.updateDataPoint(userID, user);
    }
}
