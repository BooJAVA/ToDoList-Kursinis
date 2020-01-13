package ds;

import java.io.Serializable;

public class Company extends User implements Serializable
{
    private String title;


    public Company(String login, String password, String title, String email)
    {
        super(login, password, email);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getName() { return title; }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setName(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Company{" +
                "ID='" + getId() + '\'' +
                ", title='" + title + '\'' +
                /*", email='" + getEmail() + '\'' +
                ", active=" + isActive() +*/
                '}';
    }
}
