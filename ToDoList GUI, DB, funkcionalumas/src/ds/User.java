package ds;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable
{
    private int id;
    private String login, password, email;
    private static int idCounter = 1;
    private boolean active = true;
    private ArrayList<Project> projects = new ArrayList();


    public User(String login, String password, String email)
    {
        this.login = login;
        this.password = password;
        this.email = email;
        this.id = idCounter;
        idCounter++;
    }
    public User(String login, String password, String email, boolean active)
    {
        this.login = login;
        this.password = password;
        this.email = email;
        this.id = idCounter;
        this.active = active;
        idCounter++;
    }

    public void addProject(Project p)
    {
        projects.add(p);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        User.idCounter = idCounter;
    }

    @Override
    public String toString() {
        return  "User{" +
                "ID=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                '}';
    }
}
