package dao;

import models.User;
import models.UserSearchCriteria;

import java.util.List;

/**
 * Created by Vitalii on 8/6/2016.
 */
public interface UserDAO {
    void addUser(User user);
    void editUser(User user);
    User getUser(int id);
    void deleteUser(int id);
    List<User> getUsers();
}
