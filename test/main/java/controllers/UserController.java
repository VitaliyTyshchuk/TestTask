package controllers;

import models.User;
import models.UserSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vitalii on 8/6/2016.
 */
@Controller
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/add", method= RequestMethod.GET)
    public ModelAndView addUserPage() {
        ModelAndView modelAndView = new ModelAndView("add-user");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ModelAndView addingUser(@ModelAttribute User user) {

        ModelAndView modelAndView = new ModelAndView("index");
        Date date = new Date();
        user.setCreatedDate(new Timestamp(date.getTime()));
        userService.addUser(user);

        String message = "User was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/list")
    public ModelAndView listOfUsers() {
        ModelAndView modelAndView = new ModelAndView("user-list");

        List<User> users = userService.getUsers();
        modelAndView.addObject("users", users);

        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editUserPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("edit-user");
        User user = userService.getUser(id);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editingUser(@ModelAttribute User user, @PathVariable Integer id) {

        ModelAndView modelAndView = new ModelAndView("index");

        userService.editUser(user);

        String message = "User was successfully edited.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteTeam(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("index");
        userService.deleteUser(id);
        String message = "User was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(value="/search", method=RequestMethod.GET)
    public ModelAndView searchUsers (@ModelAttribute("userSearchCriteria") UserSearchCriteria criteria) {
        ModelAndView modelAndView = new ModelAndView("search-user");
        List<User> users = userService.getUsers();
        List<User> result = new ArrayList<User>();
        for (User user : users){
            if (user.getName().equals(criteria.getName())){
                result.add(user);
            }
        }
        modelAndView.addObject("userList", result);
        return modelAndView;
    }

}
