package lab2teorija;

import ds.*;

import java.io.*;
import java.util.Scanner;

public class UI
{
    static Scanner keyboard = null;
    public static void main(String[] args)
    {
        ToDoList todo = new ToDoList();
        try{
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data.tdl")));
            todo = (ToDoList)in.readObject();
            in.close();
        }catch (Exception e) {
            System.out.println("Failed to load data");
            todo.registerPerson("admin", "admin", "admin", "admin", "admin");
        }
        keyboard = new Scanner(System.in);
        login(todo);
        start:
        while(true)
        {
            printCommands();
            String input = keyboard.nextLine().trim();
            switch (input)
            {
                case "user":
                case "1":
                    userSubmenu(todo);
                    break;
                case "project":
                case "2":
                    projectSubmenu(todo);
                    break;
                case "exit":
                case "3":
                    try {
                        ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data.tdl")));
                        out.writeObject(todo);
                        out.close();
                    } catch (Exception e) {
                        System.out.println("Failed to write data");
                    }
                    System.out.println("Exit");
                    break start;
                default: System.out.println("The command does not exist");
            }
        }
    }

    public static void login(ToDoList t)
    {
        int numberOfAttempts = 3;
        while (numberOfAttempts > 0)
        {
            System.out.println("Please login");
            System.out.println("Enter login:");
            String login = keyboard.nextLine().trim();
            System.out.println("Enter password:");
            String password = keyboard.nextLine().trim();
            try {
                t.login(login, password);
                System.out.println("You have successfully logged in");
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                numberOfAttempts--;
                System.out.println("You have " + numberOfAttempts + " attempts left");
            }
        }
        System.exit(0);
    }

    public static void printCommands()
    {
        System.out.println("Choose your action:\n"
                + "\tUsers - 1\n"
                + "\tProjects - 2\n"
                + "\tExit - 3");
    }

    public static void userSubmenu(ToDoList t)
    {
        while(true)
        {
            System.out.println(""
                    + "Users / Choose your action:\n"
                    + "\tGet all users list - 1\n"
                    + "\tGet all active users list - 2\n"
                    + "\tAdd person - 3\n"
                    + "\tAdd company - 4\n"
                    + "\tEdit person info - 5\n"
                    + "\tEdit company info - 6\n"
                    + "\tDeactivate user - 7\n"
                    + "\tActivate user - 8\n"
                    + "\tImport users from users.txt - 9\n"
                    + "\tExit users submenu - 0\n");
            String input = keyboard.nextLine().trim();
            switch(input)
            {
                case "1":
                    System.out.println("All users list");
                    for(User u:t.getAllUsers())
                    {
                        System.out.println(u);
                    }
                    break;
                case "2":
                    System.out.println("All active users list");
                    for(User u:t.getAllActiveUsers())
                    {
                        System.out.println(u);
                    }
                    break;
                case "3":
                    System.out.println("Add person");
                    addNewPerson(t);
                    break;
                case "4":
                    System.out.println("Add company");
                    addNewCompany(t);
                    break;
                case "5":
                    try {
                        System.out.println("Edit person info");
                        editPersonInfo(t);
                        break;
                    } catch (Exception e)
                    {
                        System.out.println("you have to write good number");
                    }
                case "6":
                    System.out.println("Edit company info");
                    editCompanyInfo(t);
                    break;
                case "7":
                    System.out.println("Deactivate user");
                    disableUser(t);
                    break;
                case "8":
                    System.out.println("Activate user");
                    enableUser(t);
                    break;
                case "9":
                    System.out.println("Importing users from users.txt");
                    importUsers(t);
                    break;
                case "0":
                    return;
                default: System.out.println("The command does not exist");
            }
        }
    }

    public static void projectSubmenu(ToDoList t)
    {
        while(true)
        {
            System.out.println(""
                    + "Projects / Choose your action:\n"
                    + "\tGet all projects - 1\n"
                    + "\tGet user projects - 2\n"
                    + "\tGet project members - 3\n"
                    + "\tGet project tasks - 4\n"
                    + "\tGet all existing tasks - 5\n"
                    + "\tGet task subtasks - 6\n"
                    + "\tAdd project - 7\n"
                    + "\tAdd member to project - 8\n"
                    + "\tAdd task to project - 9\n"
                    + "\tAdd subtask to task - 10\n"
                    + "\tDelete project - 11\n"
                    + "\tEdit project info - 12\n"
                    + "\tExit project submenu - 0\n");

            String input = keyboard.nextLine().trim();
            switch(input)
            {
                case "1":
                    System.out.println("All projects list");
                    for(Project p:t.getAllProjects())
                    {
                        System.out.println(p);
                    }
                    break;
                case "2":
                    System.out.println("All user projects list");
                    getUserProjects(t);
                    break;
                case "3":
                    System.out.println("All project members list");
                    getProjectMembers(t);
                    break;
                case "4":
                    System.out.println("All project tasks list");
                    getProjectTasks(t);
                    break;
                case "5":
                    System.out.println("All existing tasks");
                    for(Task ts:t.getAllTasks())
                    {
                        System.out.println(ts);
                    }
                    break;
                case "6":
                    System.out.println("All task subtasks list");
                    getTaskSubtasks(t);
                    break;
                case "7":
                    System.out.println("Add new project");
                    addNewProject(t);
                    break;
                case "8":
                    System.out.println("Add member to project");
                    addProjectMember(t);
                    break;
                case "9":
                    System.out.println("Add task to project");
                    addTaskToProject(t);
                    break;
                case "10":
                    System.out.println("Add subtask to task");
                    addTaskToTask(t);
                    break;
                case "11":
                    System.out.println("Delete project");
                    deleteProject(t);
                    break;
                case "12":
                    System.out.println("Edit project info");
                    editProjectInfo(t);
                    break;
                case "0":
                    return;
                default: System.out.println("The command does not exist");
            }
        }
    }

    //user submenu methods
    public static void importUsers(ToDoList t)
    {
        Scanner file = null;
        try{
            file = new Scanner(new File("users.txt"));
            while(file.hasNext())
            {
                String line = file.nextLine();
                String[] elements = line.split(";");
                t.registerPerson(elements[1], elements[2], elements[3], elements[4], elements[5]);
            }
        } catch (Exception e) {
            System.out.println("Error reading data");
        }finally {
            if(file != null)
            {
                file.close();
            }
        }
    }

    public static void addNewPerson(ToDoList t)
    {
        String login;
        while(true)
        {
            System.out.println("Enter login (at least 3 characters long) (or '0' to exit): ");
            login = keyboard.nextLine().trim();
            if(login.equals("0"))
            {
                return;
            }
            if (login.length() > 3 && t.getUserByLogin(login) == null)
            {
                break;
            }
            else
            {
                System.out.println("Incorrect login. Try again");
            }
        }
        System.out.println("Enter password: ");
        String password = keyboard.nextLine().trim();
        System.out.println("Enter name: ");
        String name = keyboard.nextLine().trim();
        System.out.println("Enter surname: ");
        String surname = keyboard.nextLine().trim();
        System.out.println("Enter email: ");
        String email = keyboard.nextLine().trim();
        t.registerPerson(login, password, name, surname, email);
    }

    public static void addNewCompany(ToDoList t)
    {
        String login;
        while(true)
        {
            System.out.println("Enter login (at least 3 characters long) (or '0' to exit): ");
            login = keyboard.nextLine().trim();
            if(login.equals("0"))
            {
                return;
            }
            if (login.length() > 3 && t.getUserByLogin(login) == null)
            {
                break;
            }
            else
            {
                System.out.println("Incorrect login. Try again");
            }
        }
        System.out.println("Enter password: ");
        String password = keyboard.nextLine().trim();
        System.out.println("Enter title: ");
        String title = keyboard.nextLine().trim();
        System.out.println("Enter email: ");
        String email = keyboard.nextLine().trim();
        t.registerCompany(login, password, title, email);
    }

    public static void editPersonInfo(ToDoList t)
    {
        int userId;
        while(true)
        {
            System.out.println("Enter user ID (or '0' to exit): ");
            userId = keyboard.nextInt();
            String skip = keyboard.nextLine().trim();
            if(userId == 0)
            {
                return;
            }
            if (t.getUserById(userId) != null)
            {
                break;
            }
            else
            {
                System.out.println("Incorrect ID. Try again");
            }
        }
        System.out.println("Enter new password: ");
        String newPassword = keyboard.nextLine().trim();
        System.out.println("Enter new name: ");
        String newName = keyboard.nextLine().trim();
        System.out.println("Enter new surname: ");
        String newSurname = keyboard.nextLine().trim();
        System.out.println("Enter new email: ");
        String newEmail = keyboard.nextLine().trim();
        t.editPersonInfo(userId, newPassword, newName, newSurname, newEmail);
    }

    public static void editCompanyInfo(ToDoList t)
    {
        int userId = 0;

        while(true)
        {
            start:
            try {
                System.out.println("Enter user ID (or '0' to exit): ");
                userId = keyboard.nextInt();
                String skip = keyboard.nextLine().trim();
                if (userId == 0) {
                    return;
                }
                if (t.getUserById(userId) != null) {
                    break;
                } else {
                    System.out.println("Incorrect ID. Try again");
                }
            } catch (Exception e) {

                break start;
            }
        }
        System.out.println("Enter new password: ");
        String newPassword = keyboard.nextLine().trim();
        System.out.println("Enter new title: ");
        String newTitle = keyboard.nextLine().trim();
        System.out.println("Enter new email: ");
        String newEmail = keyboard.nextLine().trim();
        t.editCompanyInfo(userId, newPassword, newTitle, newEmail);
    }

    public static void disableUser(ToDoList t)
    {
        int userId;
        while(true)
        {
            System.out.println("Enter users ID (or '0' to exit): ");
            userId = keyboard.nextInt();
            String skip = keyboard.nextLine().trim();
            if(userId == 0)
            {
                return;
            }
            if (t.getUserById(userId) != null)
            {
                System.out.println("Are you sure you want to disable user ID[" + userId + "]? Write YES to confirm, anything to cancel");
                String confirm = keyboard.nextLine().trim();
                if (confirm.equals("YES"))
                {
                    break;
                }
            }
            else
            {
                System.out.println("Incorrect ID. Try again");
            }
        }
        t.disableUser(userId);
    }

    public static void enableUser(ToDoList t)
    {
        int userId;
        while(true)
        {
            System.out.println("Enter users ID (or '0' to exit): ");
            userId = keyboard.nextInt();
            String skip = keyboard.nextLine().trim();
            if(userId == 0)
            {
                return;
            }
            if (t.getUserById(userId) != null)
            {
                System.out.println("Are you sure you want to enable user ID[" + userId + "]? Write YES to confirm, anything to cancel");
                String confirm = keyboard.nextLine().trim();
                if (confirm.equals("YES"))
                {
                    break;
                }
            }
            else
            {
                System.out.println("Incorrect ID. Try again");
            }
        }
        t.enableUser(userId);
    }

    //project submenu methods
    public static void getUserProjects(ToDoList t)
    {
        int userId;
        while(true)
        {
            System.out.println("Enter user ID (or '0' to exit): ");
            userId = keyboard.nextInt();
            String skip = keyboard.nextLine().trim();
            if(userId == 0)
            {
                return;
            }
            if (t.getUserById(userId) != null)
            {
                break;
            }
            else
            {
                System.out.println("Incorrect ID. Try again");
            }
        }
        System.out.println(t.getUserProjects(userId));
    }

    public static void getProjectMembers(ToDoList t)
    {
        int projectId;
        while(true)
        {
            System.out.println("Enter project ID (or '0' to exit): ");
            projectId = keyboard.nextInt();
            String skip = keyboard.nextLine().trim();
            if(projectId == 0)
            {
                return;
            }
            if (t.getProjectById(projectId) != null)
            {
                break;
            }
            else
            {
                System.out.println("Incorrect ID. Try again");
            }
        }
        System.out.println(t.getProjectMembers(projectId));
    }

    public static void getProjectTasks(ToDoList t)
    {
        int projectId;
        while(true)
        {
            System.out.println("Enter project ID (or '0' to exit): ");
            projectId = keyboard.nextInt();
            String skip = keyboard.nextLine().trim();
            if(projectId == 0)
            {
                return;
            }
            if (t.getProjectById(projectId) != null)
            {
                break;
            }
            else
            {
                System.out.println("Incorrect ID. Try again");
            }
        }
        System.out.println(t.getProjectTasks(projectId));
    }

    public static void getTaskSubtasks(ToDoList t)
    {
        int taskId;
        while(true)
        {
            System.out.println("Enter task ID (or '0' to exit): ");
            taskId = keyboard.nextInt();
            String skip = keyboard.nextLine().trim();
            if(taskId == 0)
            {
                return;
            }
            if (t.getTaskById(taskId) != null)
            {
                break;
            }
            else
            {
                System.out.println("Incorrect ID. Try again");
            }
        }
        System.out.println(t.getTaskSubTasks(taskId));
    }

    public static void addNewProject(ToDoList t)
    {
        String title;
        while(true)
        {
            System.out.println("Enter title (at least 3 characters long) (or '0' to exit): ");
            title = keyboard.nextLine().trim();
            if(title.equals("0"))
            {
                return;
            }
            if (title.length() > 3)
            {
                break;
            }
            else
            {
                System.out.println("Incorrect title. Try again");
            }
        }
        t.addProject(title);
    }

    public static void addProjectMember(ToDoList t)
    {
        int projectId, userId;
        while(true)
        {
            System.out.println("Enter project ID (or '0' to exit): ");
            projectId = keyboard.nextInt();
            String skip = keyboard.nextLine().trim();
            if(projectId == 0)
            {
                return;
            }
            if (t.getProjectById(projectId) != null)
            {
                System.out.println("Enter user ID (or '0' to exit): ");
                userId = keyboard.nextInt();
                String skip2 = keyboard.nextLine().trim();
                if(userId == 0)
                {
                    return;
                }
                if (t.getUserById(userId) != null)
                {
                    break;
                }
                else
                {
                    System.out.println("Incorrect ID. Try again");
                }
            }
            else
            {
                System.out.println("Incorrect ID. Try again");
            }
        }
        t.addProjectMember(projectId, userId);
    }

    public static void addTaskToProject(ToDoList t)
    {
        int projectId;
        while(true)
        {
            System.out.println("Enter project ID (or '0' to exit): ");
            projectId = keyboard.nextInt();
            String skip = keyboard.nextLine().trim();
            if(projectId == 0)
            {
                return;
            }
            if (t.getProjectById(projectId) != null)
            {
                break;
            }
            else
            {
                System.out.println("Incorrect ID. Try again");
            }
        }
        System.out.println("Enter task title: ");
        String title = keyboard.nextLine().trim();
        t.addTaskToProject(projectId, title);
    }

    public static void addTaskToTask(ToDoList t)
    {
        int taskId;
        while(true)
        {
            System.out.println("Enter task ID (or '0' to exit): ");
            taskId = keyboard.nextInt();
            String skip = keyboard.nextLine().trim();
            if(taskId == 0)
            {
                return;
            }
            if (t.getTaskById(taskId) != null)
            {
                break;
            }
            else
            {
                System.out.println("Incorrect ID. Try again");
            }
        }
        System.out.println("Enter task title: ");
        String title = keyboard.nextLine().trim();
        t.addSubtaskToTask(taskId, title);
    }

    public static void deleteProject(ToDoList t)
    {
        int projectId;
        while(true)
        {
            System.out.println("Enter project ID (or '0' to exit): ");
            projectId = keyboard.nextInt();
            String skip = keyboard.nextLine().trim();
            if(projectId == 0)
            {
                return;
            }
            if (t.getProjectById(projectId) != null)
            {
                System.out.println("Are you sure you want to delete project ID[" + projectId + "]? Write YES to confirm, anything to cancel");
                String confirm = keyboard.nextLine().trim();
                if (confirm.equals("YES"))
                {
                    break;
                }
            }
            else
            {
                System.out.println("Incorrect ID. Try again");
            }
        }
        t.deleteProject(projectId);
    }

    public static void editProjectInfo(ToDoList t)
    {
        int projectId;
        while(true)
        {
            System.out.println("Enter project ID (or '0' to exit): ");
            projectId = keyboard.nextInt();
            String skip = keyboard.nextLine().trim();
            if(projectId == 0)
            {
                return;
            }
            if (t.getProjectById(projectId) != null)
            {
                break;
            }
            else
            {
                System.out.println("Incorrect ID. Try again");
            }
        }
        System.out.println("Enter new title ");
        String newTitle = keyboard.nextLine().trim();
        t.editProjectInfo(projectId, newTitle);
    }


}
