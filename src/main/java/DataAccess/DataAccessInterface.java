package DataAccess;

import DataObjects.UserObject;

import java.util.List;

public interface DataAccessInterface<T> {

    /**
     * Save an object to the specified file.
     *
     * @param userID  the user using the service
     * @param dataPoint the object to save
     */
    UserObject saveData(int userID, T dataPoint);
    /**
     * Read a list of objects from the specified file.
     *
     * @param userID  the user using the service
     * @return a list of objects read from the file
     */
    List<T> readData(int userID);
}
