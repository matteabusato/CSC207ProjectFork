package DataObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
    String username;
    String passwordHash;
    String firstName;
    String lastName;
    int accountID;
    double balance;
    String fileName;

    public User(String username, String passwordHash, String firstName, String lastName, int accountID, double balance) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountID = accountID;
        this.balance = balance;
        this.fileName = firstName + lastName + accountID + ".json";
    }

}
