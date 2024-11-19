package DataObjects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserObject {
    int userID;
    String firstName;
    String lastName;
    String passwordHash;
    double balance;
    String fileDirectory;

    public UserObject(int userID, String firstName, String lastName, String passwordHash, double balance) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
        this.balance = balance;
        this.fileDirectory = firstName + lastName + userID + "/";
    }

    public int getUserID() {
        return userID;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public double getBalance() {
        return balance;
    }
    public String getFileDirectory() {
        return fileDirectory;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }
}
