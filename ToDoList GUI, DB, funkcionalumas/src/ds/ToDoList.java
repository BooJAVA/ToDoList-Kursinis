package ds;

import java.io.File;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList implements Serializable
{
    private ArrayList<User> users = new ArrayList();
    private ArrayList<Project> projects = new ArrayList();
    private ArrayList<Task> tasks = new ArrayList();
    private User loggedIn = null;

    Connection conn = null;

    public void connectToDb()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String DB_URL = "jdbc:mysql://localhost/MYSQL";
            String USER = "root";
            String PASS = "";
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void disconnectFromDb()
    {
        try{
            conn.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //register
    public Person registerPerson(String login, String password, String name, String surname, String email)
    {
        if(getUserByLogin(login) == null && login.length() > 3)
        {
            Person newPerson = new Person(login, password, name, surname, email);
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO todolist.users (login, password, name, surname, email, isPerson) " +
                        "VALUES (?, ?, ?, ?, ?, 1)");
                ps.setString(1, login);
                ps.setString(2, password);
                ps.setString(3, name);
                ps.setString(4, surname);
                ps.setString(5, email);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
            }
            disconnectFromDb();
            return newPerson;
        }
        return null;
    }

    public Company registerCompany(String login, String password, String title, String email)
    {
        if(getUserByLogin(login) == null && login.length() > 3)
        {
            Company newCompany = new Company(login, password, title, email);
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO todolist.users (login, password, name, surname, email, isPerson) " +
                        "VALUES (?, ?, ?, null, ?, 0)");
                ps.setString(1, login);
                ps.setString(2, password);
                ps.setString(3, title);
                ps.setString(4, email);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
            }
            disconnectFromDb();
            return newCompany;
        }
        return null;
    }

    //edit
    public void editPersonInfo (int personId, String password, String name, String surname, String email)
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("UPDATE todolist.users "
                        + "SET password=?, name=?, surname=?, email=?"
                        + "WHERE id=?");
                ps.setString(1, password);
                ps.setString(2, name);
                ps.setString(3, surname);
                ps.setString(4, email);
                ps.setInt(5, personId);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
            }
            disconnectFromDb();
        }
    }

    public void editCompanyInfo (int companyId, String password, String title, String email)
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("UPDATE todolist.users "
                        + "SET password=?, name=?, email=?"
                        + "WHERE id=?");
                ps.setString(1, password);
                ps.setString(2, title);
                ps.setString(3, email);
                ps.setInt(4, companyId);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
            }
            disconnectFromDb();
        }
    }

    public void editProjectInfo(int projectId, String title)
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("UPDATE todolist.project "
                        + "SET title=?"
                        + "WHERE id=?");
                ps.setString(1, title);
                ps.setInt(2, projectId);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
            }
            disconnectFromDb();
        }
    }

    public void editTaskInfo(int taskId, String title)
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("UPDATE todolist.task "
                        + "SET title=?"
                        + "WHERE id=?");
                ps.setString(1, title);
                ps.setInt(2, taskId);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
            }
            disconnectFromDb();
        }
    }

    public void editSubtaskInfo(int subtaskId, String title)
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("UPDATE todolist.subtask "
                        + "SET title=?"
                        + "WHERE id=?");
                ps.setString(1, title);
                ps.setInt(2, subtaskId);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
            }
            disconnectFromDb();
        }
    }

    //add
    public Project addProject(String title)
    {
        if(isLoggedInAndActive() && title.length() > 3)
        {
            Project newProject = new Project(title, loggedIn);
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO todolist.project (title) VALUES (?)");
                ps.setString(1, title);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
            }
            disconnectFromDb();
            return newProject;
        }
        return null;
    }

    public Project addProjectCreator()
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                int projectId = 0;
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM todolist.project");
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    projectId = rs.getInt(1);
                }
                PreparedStatement ps2 = conn.prepareStatement("INSERT INTO todolist.user_project (userid, projectid, creator) " +
                        "VALUES (?, ?, ?)");
                ps2.setInt(1, loggedIn.getId());
                ps2.setInt(2, projectId);
                ps2.setInt(3, 1);
                ps2.executeUpdate();
                ps2.close();
                rs.close();
                ps.close();

            } catch (SQLException e) {
            }
            disconnectFromDb();
        }
        return null;
    }

    public User addProjectMember(int projectId, int userId)
    {
        if (isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO todolist.user_project"
                        + "(userid, projectid, creator) VALUES"
                        + "(?, ?, ?)");
                ps.setInt(1, userId);
                ps.setInt(2, projectId);
                ps.setInt(3, 0);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
            }
            disconnectFromDb();
        }
        return null;
    }

    public Task addTaskToProject(int projectId, String title)
    {
        if (isLoggedInAndActive())
        {
            Task newTask = new Task(title, projectId, loggedIn.getId());
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO todolist.task"
                        + "(title, createdon, createdby, projectid) VALUES"
                        + "(?, ?, ?, ?)");
                ps.setString(1, title);
                ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
                ps.setInt(3, loggedIn.getId());
                ps.setInt(4, projectId);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
            }
            disconnectFromDb();
            return newTask;
        }
        return null;
    }

    public Task addSubtaskToTask(int taskId, String title)
    {
        if (isLoggedInAndActive())
        {
            Task newTask = new Task(title, taskId, loggedIn.getId());
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO todolist.subtask"
                        + "(title, createdon, createdby, taskid) VALUES"
                        + "(?, ?, ?, ?)");
                ps.setString(1, title);
                ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
                ps.setInt(3, loggedIn.getId());
                ps.setInt(4, taskId);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
            }
            disconnectFromDb();
            return newTask;
        }
        return null;
    }

    //delete/disable
    public void deleteProject(int projectId)
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM todolist.project "
                        + "WHERE id=?");
                ps.setInt(1, projectId);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
            }
            disconnectFromDb();
        }
    }

    public void deleteTask(int taskId)
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM todolist.task "
                        + "WHERE id=?");
                ps.setInt(1, taskId);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
            }
            disconnectFromDb();
        }
    }

    public void deleteSubtask(int subtaskId)
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("DELETE FROM todolist.subtask "
                        + "WHERE id=?");
                ps.setInt(1, subtaskId);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
            }
            disconnectFromDb();
        }
    }

    public void disableUser(int userId)
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("UPDATE todolist.users SET active=? WHERE id=?");
                ps.setInt(1, 0);
                ps.setInt(2, userId);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
            }
            disconnectFromDb();
        }
    }

    public void enableUser(int userId)
    {
        if(isLoggedIn())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("UPDATE todolist.users SET active=? WHERE id=?");
                ps.setInt(1,1);
                ps.setInt(2, userId);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
            }
            disconnectFromDb();
        }
    }

    //get
    public ArrayList<User> getAllUsers()
    {
        ArrayList<User> forReturn = new ArrayList();
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM todolist.users");
                while(rs.next())
                {
                    int userId = rs.getInt(1);
                    String login = rs.getString(2);
                    String password = rs.getString(3);
                    String email = rs.getString(6);
                    boolean active = rs.getBoolean(7);
                    User u = new User(login, password, email, active);
                    forReturn.add(u);
                    u.setId(userId);
                }
                rs.close();
                st.close();
            } catch (SQLException e) {
            }
            disconnectFromDb();
            return forReturn;
        }
        return forReturn;
    }

    public ArrayList<User> getAllActiveUsers()
    {
        ArrayList<User> forReturn = new ArrayList();
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM todolist.users WHERE active=1");
                while(rs.next())
                {
                    int userId = rs.getInt(1);
                    String login = rs.getString(2);
                    String password = rs.getString(3);
                    String email = rs.getString(6);
                    boolean active = rs.getBoolean(7);
                    User u = new User(login, password, email, active);
                    forReturn.add(u);
                    u.setId(userId);
                }
                rs.close();
                st.close();
            } catch (SQLException e) {
            }
            disconnectFromDb();
            return forReturn;
        }
        return forReturn;
    }

    public ArrayList<Project> getUserProjects(int userId)
    {
        ArrayList<Project> forReturn = new ArrayList();
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM todolist.user_project WHERE userid=?");
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    Project p = getProjectById(rs.getInt(2));
                    forReturn.add(p);
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
            }
            disconnectFromDb();
            return forReturn;
        }
        return forReturn;
    }

    public ArrayList<User> getProjectMembers(int projectId)
    {
        ArrayList<User> forReturn = new ArrayList();
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM todolist.user_project WHERE projectid=?");
                ps.setInt(1, projectId);
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    User u = getUserById(rs.getInt(1));
                    forReturn.add(u);
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
            }
            disconnectFromDb();
            return forReturn;
        }
        return forReturn;
    }

    public ArrayList<Project> getAllProjects()
    {
        ArrayList<Project> forReturn = new ArrayList();
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM todolist.project");
                while(rs.next())
                {
                    int projectId = rs.getInt(1);
                    String title = rs.getString(2);
                    Project p = new Project(title, loggedIn);
                    forReturn.add(p);
                    p.setId(projectId);
                }
                rs.close();
                st.close();
            } catch (SQLException e) {
            }
            disconnectFromDb();
            return forReturn;
        }
        return forReturn;
    }

    public ArrayList<Task> getProjectTasks(int projectId)
    {
        ArrayList<Task> forReturn = new ArrayList();
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM todolist.task WHERE projectid=?");
                ps.setInt(1, projectId);
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    int taskId = rs.getInt(1);
                    String title = rs.getString(2);
                    Date createdOn = rs.getDate(3);
                    int createdby = rs.getInt(5);
                    int ProjectID = rs.getInt(8);
                    Task t = new Task(title, ProjectID, createdby, createdOn);
                    forReturn.add(t);
                    t.setId(taskId);
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
            }
            disconnectFromDb();
            return forReturn;
        }
        return forReturn;
    }

    public ArrayList<Task> getTaskSubTasks(int taskId)
    {
        if(isLoggedInAndActive())
        {
            Task current = getTaskById(taskId);
            if (current != null)
            {
                return current.getSubTasks();
            }
        }
        return new ArrayList();
    }

    public ArrayList<Task> getAllTasks()
    {
        ArrayList<Task> forReturn = new ArrayList();
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM todolist.task");
                while(rs.next())
                {
                    int taskId = rs.getInt(1);
                    String title = rs.getString(2);
                    Date createdOn = rs.getDate(3);
                    int createdby = rs.getInt(5);
                    int ProjectID = rs.getInt(8);
                    Task t = new Task(title, createdby, ProjectID, createdOn);
                    forReturn.add(t);
                    t.setId(taskId);
                }
                rs.close();
                st.close();
            } catch (SQLException e) {
            }
            disconnectFromDb();
            return forReturn;
        }
        return forReturn;
    }

    public ArrayList<Task> getAllSubtasks()
    {
        ArrayList<Task> forReturn = new ArrayList();
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM todolist.subtask");
                while(rs.next())
                {
                    int subtaskId = rs.getInt(1);
                    String title = rs.getString(2);
                    Date createdOn = rs.getDate(3);
                    int createdby = rs.getInt(5);
                    int taskID = rs.getInt(8);
                    Task t = new Task(title, createdby, taskID, createdOn);
                    forReturn.add(t);
                    t.setId(subtaskId);
                }
                rs.close();
                st.close();
            } catch (SQLException e) {
            }
            disconnectFromDb();
            return forReturn;
        }
        return forReturn;
    }

    //get by
    public Task getTaskById(int taskId)
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM todolist.task WHERE id=?");
                ps.setInt(1, taskId);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int id = rs.getInt(1);
                    String title = rs.getString(2);
                    Date createdOn = rs.getDate(3);
                    int createdBy = rs.getInt(5);
                    int projectId = rs.getInt(8);
                    Task t = new Task(title, projectId, createdBy, createdOn);
                    t.setId(id);
                    return t;
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            disconnectFromDb();
        }
        return null;
    }

    public User getUserById(int userId)
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM todolist.users WHERE id=?");
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int id = rs.getInt(1);
                    String login = rs.getString(2);
                    String password = rs.getString(3);
                    String email = rs.getString(4);
                    User u = new User(login, password, email);
                    u.setId(id);
                    return u;
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            disconnectFromDb();
        }
        return null;
    }

    public User getUserByLogin(String login)
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM todolist.users WHERE login=?");
                ps.setString(1, login);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int id = rs.getInt(1);
                    String password = rs.getString(3);
                    String email = rs.getString(4);
                    User u = new User(login, password, email);
                    u.setId(id);
                    return u;
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            disconnectFromDb();
        }
        return null;
    }

    public Project getProjectById(int projectId)
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM todolist.project WHERE id=?");
                ps.setInt(1, projectId);
                ResultSet rs = ps.executeQuery();
                while (rs.next())
                {
                    int id = rs.getInt(1);
                    String title = rs.getString(2);
                    Project p = new Project(title, loggedIn);
                    p.setId(id);
                    return p;
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            disconnectFromDb();
        }
        return null;
    }

    //login
    public User login(String login, String password) throws Exception
    {
        connectToDb();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT id, login, password, email FROM todolist.users WHERE login=? AND password=? AND active=?");
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setInt(3, 1);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                int userId = rs.getInt(1);
                String email = rs.getString(4);
                User u = new User(login, password, email);
                u.setId(userId);
                loggedIn = u;
                return u;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
        }
        disconnectFromDb();
        return null;
    }

    public void logout()
    {
        loggedIn = null;
    }

    //complete
    public Task completeTask(int taskId)
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("UPDATE todolist.task SET done=? WHERE id=?");
                ps.setInt(1, 1);
                ps.setInt(2, taskId);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public Task completeSubtask(int subtaskId)
    {
        if(isLoggedInAndActive())
        {
            connectToDb();
            try {
                PreparedStatement ps = conn.prepareStatement("UPDATE todolist.subtask SET done=? WHERE id=?");
                ps.setInt(1, 1);
                ps.setInt(2, subtaskId);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public boolean isLoggedIn()
    {
        return loggedIn != null;
    }

    public boolean isUserActive()
    {
        return loggedIn.isActive();
    }

    public boolean isLoggedInAndActive()
    {
        return (isLoggedIn() && isUserActive());
    }

    public int getPersonCount()
    {
        int companyCount = 0;
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM todolist.users WHERE isPerson = true");
            ResultSet rez = ps.executeQuery();
            if(rez.next()) {
                companyCount = rez.getInt(1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return companyCount;
    }

    public int getCompanyCount()
    {
        int companyCount = 0;
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM todolist.users WHERE isPerson = false");
            ResultSet rez = ps.executeQuery();
            if(rez.next()) {
                companyCount = rez.getInt(1);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return companyCount;
    }

    public int[][] getProjectNumbers()
    {
        int[][] mas = new int[projects.size()][2];
        int id = 0;
        for(Project p: projects)
        {
            mas[id][0] = p.getId();
            mas[id][1] = p.getAllTasks().size();
            id++;
        }
        return mas;
    }

}
