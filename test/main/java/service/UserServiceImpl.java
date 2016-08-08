package service;

import dao.UserDAO;
import models.User;
import models.UserSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vitalii on 8/6/2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public void editUser(User user) {
        userDAO.editUser(user);
    }

    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    public List<User> getUsers() {
        return userDAO.getUsers();
    }
}
