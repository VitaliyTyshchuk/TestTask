package models;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Vitalii on 8/6/2016.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private int age;

    private boolean isAdmin;

    private Timestamp createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}
