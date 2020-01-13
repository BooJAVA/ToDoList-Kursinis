package ds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Task implements Serializable
{
    private int id;
    private static int idCounter = 1;
    private String title;
    private Date createdOn, completedOn;
    private int createdBy, completedBy;
    private boolean done = false;
    private ArrayList<Task> subTasks = new ArrayList();
    private int projectId;

    public Task(String title, int projectId, int createdBy)
    {
        this.id = idCounter;
        idCounter++;
        this.title = title;
        this.createdBy = createdBy;
        createdOn = new Date();
        this.projectId = projectId;
    }

    public Task(String title, int projectId, int createdBy, Date createdOn)
    {
        this.title = title;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.projectId = projectId;

    }

    public ArrayList<Task> getAllTasks()
    {
        ArrayList<Task> all = new ArrayList();
        for(Task t:subTasks)
        {
            all.addAll(t.getAllTasks());
        }
        return all;
    }
    public void addTask(Task t)
    {
        subTasks.add(t);
    }

    public static int getIdCounter() {
        return idCounter;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(int completedBy) {
        this.completedBy = completedBy;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setIdCounter(int idCounter) {
        Task.idCounter = idCounter;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }


    public void setSubTasks(ArrayList<Task> subTasks) {
        this.subTasks = subTasks;
    }


    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getCompletedOn() {
        return completedOn;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public ArrayList<Task> getSubTasks() {
        return subTasks;
    }

    public void setCompletedOn(Date completedOn) {
        this.completedOn = completedOn;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                /*", createdOn=" + createdOn +
                ", completedOn=" + completedOn +
                ", createdBy=" + createdBy +
                ", completedBy=" + completedBy +
                ", done=" + done +
                ", subTasks=" + subTasks +*/
                //", project=" + project +
                '}';
    }


}
