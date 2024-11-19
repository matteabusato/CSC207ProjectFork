package DataAccess;

import DataObjects.UserObject;

import java.util.List;

public interface DataAccessInterface<T> {

    /**
     * Save an object to the specified file.
     *
     * @param user  the user using the service
     * @param dataPoint the object to save
     */
    void saveData(UserObject user, T dataPoint);
    /**
     * Read a list of objects from the specified file.
     *
     * @param user  the user using the service
     * @return a list of objects read from the file
     */
    List<T> readData(UserObject user);
}
