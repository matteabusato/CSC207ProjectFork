package LogInController;

import DataObjects.User;
import DataObjects.UsersController;


public class SignUpController {
    UsersController usersController = new UsersController();
    
    public SignUpController() {
    }
    
    private void addUser(String firstName, String lastName, String password){
        int userID = generateUserID();
        String passwordHash = generatePassword();
        double balance = 0;
        String fileDirectory = createFileDirectory(firstName, lastName, userID);

        User user = new User(userID, firstName, lastName, passwordHash, balance); 
        usersController.addUser(user);
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
