package DataObjects;

import DataAccess.DataAccessController;

import java.util.List;

public class UsersDBAccess{
    DataAccessController controller = new DataAccessController();

    public void saveData(UserObject user) {
        List<UserObject> users = controller.readData("Users.json", UserObject.class);
        users.add(user);
        controller.saveData("Users.json", users, UserObject.class);
    }

    public List<UserObject> readData() {
        return controller.readData("Users.json", UserObject.class);
    }

    public UserObject readDataPoint(int userID) {
        List<UserObject> users = readData();
        for (UserObject user : users) {
            if (user != null && user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }
    public void updateDataPoint(int userID, UserObject user) {
        List<UserObject> users = readData();
        for (int i = 0; i < users.size(); i++) {
            if (user != null && users.get(i).getUserID() == userID) {
                users.set(i, user);
            }
        }
        controller.saveData("Users.json", users, UserObject.class);
    }
}
