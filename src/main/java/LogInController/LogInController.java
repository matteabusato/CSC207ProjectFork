package LogInController;

import DataObjects.User;
import DataObjects.UsersController;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LogInController {
    UsersController usersController = new UsersController();
    User loggedUser = null;

    public void login(int userID, String firstName, String lastName, String password) {
        // TODO: add call to api for hashing password
        if (usersController.getUser(userID) != null){
            User user = usersController.getUser(userID);
            if (user.getPasswordHash().equals(password)){
                loggedUser = user;
            }
            else {
            }
        }
    }
    public void logout() {
        loggedUser = null;
    }
}
