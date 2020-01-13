package ds;

import java.io.Serializable;
import java.util.ArrayList;

public class Project implements Serializable
{
    private int id;
    private static int idCounter = 1;
    private String title;
    private String creator;
    private ArrayList<User> members = new ArrayList();
    private ArrayList<Task> tasks = new ArrayList();

    public Project(String title, User creator)
    {
        this.title = title;
        members.add(creator);
        this.id = idCounter;
        idCounter++;
    }

    public void addMember(User u)
    {
        members.add(u);
    }

    public void addTask(Task t)
    {
        tasks.add(t);
    }

    public ArrayList<Task> getAllTasks()
    {
        ArrayList<Task> all = new ArrayList();
        all.addAll(this.tasks);
        for(Task t:tasks)
        {
            all.addAll(t.getAllTasks());
        }
        return all;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "Project{" +
                "ID=" + id +
                ", title='" + title + '\'' +
                /*", members=" + members +
                ", tasks=" + tasks +*/
                '}';
    }

    public String toString2()
    {
        return "Task{" +
                "id=" + id +
                '}';
    }
}
