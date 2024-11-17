package DataObjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class User {
    int userID;
    String firstName;
    String lastName;
    String passwordHash;
    double balance;
    String fileDirectory;

    public User(int userID, String firstName, String lastName, String passwordHash, double balance) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
        this.balance = balance;
        this.fileDirectory = firstName + lastName + userID + "/";
    }
}
