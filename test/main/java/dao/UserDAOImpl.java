package dao;

import models.User;
import models.UserSearchCriteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Vitalii on 8/6/2016.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    public void addUser(User user) {
        getCurrentSession().save(user);
    }

    public void editUser(User user) {
        User editedUser = getUser(user.getId());
        editedUser.setName(user.getName());
        editedUser.setAge(user.getAge());
        getCurrentSession().update(editedUser);
    }

    public User getUser(int id) {
        User user = (User) getCurrentSession().get(User.class, id);
        return user;
    }

    public void deleteUser(int id) {
        User user = getUser(id);
        if (user!=null)
            getCurrentSession().delete(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return getCurrentSession().createQuery("from User").list();
    }

}
