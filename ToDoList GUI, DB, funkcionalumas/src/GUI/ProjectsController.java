package GUI;

import ds.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProjectsController implements Initializable
{
    @FXML
    private TableView projectsTable;
    @FXML
    private TextField vupUserId, vpmProjectId, vptProjectId, vtsTaskId;
    @FXML
    private TextField addpProjectTitle, addmProjectId, addmUserId, addtProjectId, addtTaskTitle, addsTaskId, addsSubtaskTitle;
    @FXML
    private TextField dProjectId, eProjectId, eProjectTitle;
    @FXML
    private TextField dTaskId, eTaskId, eTaskTitle, cTaskId;
    @FXML
    private TextField dSubtaskId, eSubtaskId, eSubtaskTitle, cSubtaskId;

    ToDoList tdl = null;


    public void setToDoList(ToDoList t)
    {
        tdl = t;
        getAllProjects();
    }

    public void getAllProjects()
    {
        if(tdl != null)
        {
            TableColumn<String, Project> column1 = new TableColumn<>("ID");
            column1.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<String, Project> column2 = new TableColumn<>("Title");
            column2.setCellValueFactory(new PropertyValueFactory<>("title"));
            projectsTable.getColumns().clear();
            projectsTable.getColumns().add(column1);
            projectsTable.getColumns().add(column2);

            projectsTable.getItems().clear();
            projectsTable.getItems().addAll(tdl.getAllProjects());
        }
    }

    public void getAllTasks()
    {
        if(tdl != null)
        {
            TableColumn<String, Task> column1 = new TableColumn<>("ID");
            column1.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<String, Task> column2 = new TableColumn<>("Title");
            column2.setCellValueFactory(new PropertyValueFactory<>("title"));
            TableColumn<String, Task> column3 = new TableColumn<>("Created on");
            column3.setCellValueFactory(new PropertyValueFactory<>("createdOn"));
            TableColumn<String, Task> column4 = new TableColumn<>("Completed on");
            column4.setCellValueFactory(new PropertyValueFactory<>("completedOn"));
            TableColumn<String, Task> column5 = new TableColumn<>("Created by");
            column5.setCellValueFactory(new PropertyValueFactory<>("projectId"));
            TableColumn<String, Task> column6 = new TableColumn<>("Completed by");
            column6.setCellValueFactory(new PropertyValueFactory<>("completedBy"));
            TableColumn<String, Task> column7 = new TableColumn<>("Is it done");
            column7.setCellValueFactory(new PropertyValueFactory<>("done"));
            TableColumn<String, Task> column8 = new TableColumn<>("ProjectID");
            column8.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
            projectsTable.getColumns().clear();
            projectsTable.getColumns().add(column1);
            projectsTable.getColumns().add(column2);
            projectsTable.getColumns().add(column3);
            projectsTable.getColumns().add(column4);
            projectsTable.getColumns().add(column5);
            projectsTable.getColumns().add(column6);
            projectsTable.getColumns().add(column7);
            projectsTable.getColumns().add(column8);

            projectsTable.getItems().clear();
            projectsTable.getItems().addAll(tdl.getAllTasks());
        }
    }

    public void getAllSubtasks()
    {
        if(tdl != null)
        {
            TableColumn<String, Task> column1 = new TableColumn<>("ID");
            column1.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<String, Task> column2 = new TableColumn<>("Title");
            column2.setCellValueFactory(new PropertyValueFactory<>("title"));
            TableColumn<String, Task> column3 = new TableColumn<>("Created on");
            column3.setCellValueFactory(new PropertyValueFactory<>("createdOn"));
            TableColumn<String, Task> column4 = new TableColumn<>("Completed on");
            column4.setCellValueFactory(new PropertyValueFactory<>("completedOn"));
            TableColumn<String, Task> column5 = new TableColumn<>("Created by");
            column5.setCellValueFactory(new PropertyValueFactory<>("projectId"));
            TableColumn<String, Task> column6 = new TableColumn<>("Completed by");
            column6.setCellValueFactory(new PropertyValueFactory<>("completedBy"));
            TableColumn<String, Task> column7 = new TableColumn<>("Is it done");
            column7.setCellValueFactory(new PropertyValueFactory<>("done"));
            TableColumn<String, Task> column8 = new TableColumn<>("TaskID");
            column8.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
            projectsTable.getColumns().clear();
            projectsTable.getColumns().add(column1);
            projectsTable.getColumns().add(column2);
            projectsTable.getColumns().add(column3);
            projectsTable.getColumns().add(column4);
            projectsTable.getColumns().add(column5);
            projectsTable.getColumns().add(column6);
            projectsTable.getColumns().add(column7);
            projectsTable.getColumns().add(column8);

            projectsTable.getItems().clear();
            projectsTable.getItems().addAll(tdl.getAllSubtasks());
        }
    }

    public void getUserProjects()
    {
        if(tdl != null)
        {
            TableColumn<String, Project> column1 = new TableColumn<>("Project ID");
            column1.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<String, Project> column2 = new TableColumn<>("Title");
            column2.setCellValueFactory(new PropertyValueFactory<>("title"));
            projectsTable.getColumns().clear();
            projectsTable.getColumns().add(column1);
            projectsTable.getColumns().add(column2);

            int userId = Integer.parseInt(vupUserId.getText());
            projectsTable.getItems().clear();
            projectsTable.getItems().addAll(tdl.getUserProjects(userId));
            vupUserId.setText("");

        }
    }

    public void getProjectMembers()
    {
        if(tdl != null)
        {
            TableColumn<String, User> column1 = new TableColumn<>("ID");
            column1.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<String, User> column2 = new TableColumn<>("Login");
            column2.setCellValueFactory(new PropertyValueFactory<>("login"));
            TableColumn<String, User> column3 = new TableColumn<>("Email");
            column3.setCellValueFactory(new PropertyValueFactory<>("email"));
            TableColumn<String, User> column4 = new TableColumn<>("Is it active?");
            column4.setCellValueFactory(new PropertyValueFactory<>("active"));
            projectsTable.getColumns().clear();
            projectsTable.getColumns().add(column1);
            projectsTable.getColumns().add(column2);
            projectsTable.getColumns().add(column3);
            projectsTable.getColumns().add(column4);

            int projectId = Integer.parseInt(vpmProjectId.getText());
            projectsTable.getItems().clear();
            projectsTable.getItems().addAll(tdl.getProjectMembers(projectId));
            vpmProjectId.setText("");
        }
    }

    public void getProjectTasks()
    {
        if(tdl != null)
        {
            TableColumn<String, Task> column1 = new TableColumn<>("ID");
            column1.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<String, Task> column2 = new TableColumn<>("Title");
            column2.setCellValueFactory(new PropertyValueFactory<>("title"));
            TableColumn<String, Task> column3 = new TableColumn<>("Created on");
            column3.setCellValueFactory(new PropertyValueFactory<>("createdOn"));
            TableColumn<String, Task> column4 = new TableColumn<>("Completed on");
            column4.setCellValueFactory(new PropertyValueFactory<>("completedOn"));
            TableColumn<String, Task> column5 = new TableColumn<>("Created by");
            column5.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
            TableColumn<String, Task> column6 = new TableColumn<>("Completed by");
            column6.setCellValueFactory(new PropertyValueFactory<>("completedBy"));
            TableColumn<String, Task> column7 = new TableColumn<>("Is it done");
            column7.setCellValueFactory(new PropertyValueFactory<>("done"));
            TableColumn<String, Task> column8 = new TableColumn<>("ProjectID");
            column8.setCellValueFactory(new PropertyValueFactory<>("projectId"));
            projectsTable.getColumns().clear();
            projectsTable.getColumns().add(column1);
            projectsTable.getColumns().add(column2);
            projectsTable.getColumns().add(column3);
            projectsTable.getColumns().add(column4);
            projectsTable.getColumns().add(column5);
            projectsTable.getColumns().add(column6);
            projectsTable.getColumns().add(column7);
            projectsTable.getColumns().add(column8);

            int projectId = Integer.parseInt(vptProjectId.getText());
            projectsTable.getItems().clear();
            projectsTable.getItems().addAll(tdl.getProjectTasks(projectId));
            vptProjectId.setText("");
        }
    }

    public void getTaskSubtasks()
    {
        if(tdl != null)
        {
            TableColumn<String, Task> column1 = new TableColumn<>("ID");
            column1.setCellValueFactory(new PropertyValueFactory<>("id"));
            TableColumn<String, Task> column2 = new TableColumn<>("Title");
            column2.setCellValueFactory(new PropertyValueFactory<>("title"));
            TableColumn<String, Task> column3 = new TableColumn<>("Created on");
            column3.setCellValueFactory(new PropertyValueFactory<>("createdOn"));
            TableColumn<String, Task> column4 = new TableColumn<>("Completed on");
            column4.setCellValueFactory(new PropertyValueFactory<>("completedOn"));
            TableColumn<String, Task> column5 = new TableColumn<>("Created by");
            column5.setCellValueFactory(new PropertyValueFactory<>("createdBy"));
            TableColumn<String, Task> column6 = new TableColumn<>("Completed by");
            column6.setCellValueFactory(new PropertyValueFactory<>("completedBy"));
            TableColumn<String, Task> column7 = new TableColumn<>("Is it done");
            column7.setCellValueFactory(new PropertyValueFactory<>("done"));
            TableColumn<String, Task> column8 = new TableColumn<>("Subtasks");
            column8.setCellValueFactory(new PropertyValueFactory<>("subTasks"));
            TableColumn<String, Task> column9 = new TableColumn<>("Project");
            column9.setCellValueFactory(new PropertyValueFactory<>("project"));
            projectsTable.getColumns().clear();
            projectsTable.getColumns().add(column1);
            projectsTable.getColumns().add(column2);
            projectsTable.getColumns().add(column3);
            projectsTable.getColumns().add(column4);
            projectsTable.getColumns().add(column5);
            projectsTable.getColumns().add(column6);
            projectsTable.getColumns().add(column7);
            projectsTable.getColumns().add(column8);
            projectsTable.getColumns().add(column9);

            int taskId = Integer.parseInt(vtsTaskId.getText());
            projectsTable.getItems().clear();
            projectsTable.getItems().addAll(tdl.getTaskSubTasks(taskId));
            vtsTaskId.setText("");
        }
    }

    public void addProject()
    {
        if(tdl != null)
        {
            String projectTitle = addpProjectTitle.getText();
            tdl.addProject(projectTitle);
            tdl.addProjectCreator();
            getAllProjects();
            addpProjectTitle.setText("");
        }
    }

    public void addMemberToProject()
    {
        if(tdl != null)
        {
            int projectId = Integer.parseInt(addmProjectId.getText());
            int userId = Integer.parseInt(addmUserId.getText());
            tdl.addProjectMember(projectId, userId);
            getAllProjects();
            addmUserId.setText("");
            addmProjectId.setText("");
        }
    }

    public void addTaskToProject()
    {
        if(tdl != null)
        {
            int projectId = Integer.parseInt(addtProjectId.getText());
            String taskTitle = addtTaskTitle.getText();
            tdl.addTaskToProject(projectId, taskTitle);
            getAllTasks();
            addtProjectId.setText("");
            addtTaskTitle.setText("");
        }
    }

    public void addSubtaskToTask()
    {
        if(tdl != null)
        {
            int taskId = Integer.parseInt(addsTaskId.getText());
            String subtaskTitle = addsSubtaskTitle.getText();
            tdl.addSubtaskToTask(taskId, subtaskTitle);
            getAllSubtasks();
            addsTaskId.setText("");
            addsSubtaskTitle.setText("");
        }
    }

    public void deleteProject()
    {
        if(tdl != null)
        {
            int projectId = Integer.parseInt(dProjectId.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm deleting project");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete project ID[" + projectId +"]?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                tdl.deleteProject(projectId);
                getAllProjects();
                dProjectId.setText("");
            } else
            {
                dProjectId.setText("");
            }
        }
    }

    public void deleteTask()
    {
        if(tdl != null)
        {
            int taskId = Integer.parseInt(dTaskId.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm deleting task");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete task ID[" + taskId +"]?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                tdl.deleteTask(taskId);
                getAllTasks();
                dTaskId.setText("");
            } else
            {
                dTaskId.setText("");
            }
        }
    }

    public void deleteSubtask()
    {
        if(tdl != null) {
            int subtaskId = Integer.parseInt(dSubtaskId.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm deleting subtask");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete subtask ID[" + subtaskId + "]?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                tdl.deleteSubtask(subtaskId);
                getAllProjects();
                dSubtaskId.setText("");
            } else {
                dSubtaskId.setText("");
            }
        }
    }

    public void editProject()
    {
        if(tdl != null)
        {
            int projectId = Integer.parseInt(eProjectId.getText());
            String newProjectTitle = eProjectTitle.getText();
            tdl.editProjectInfo(projectId, newProjectTitle);
            getAllProjects();
            eProjectId.setText("");
            eProjectTitle.setText("");
        }
    }

    public void editTask()
    {
        if(tdl != null)
        {
            int taskId = Integer.parseInt(eTaskId.getText());
            String newTaskTitle = eTaskTitle.getText();
            tdl.editTaskInfo(taskId, newTaskTitle);
            getAllProjects();
            eTaskId.setText("");
            eTaskTitle.setText("");
        }
    }

    public void editSubtask()
    {
        if(tdl != null)
        {
            int subtaskId = Integer.parseInt(eSubtaskId.getText());
            String newSubtaskTitle = eSubtaskTitle.getText();
            tdl.editSubtaskInfo(subtaskId, newSubtaskTitle);
            getAllProjects();
            eSubtaskId.setText("");
            eSubtaskTitle.setText("");
        }
    }

    public void completeTask()
    {
        if(tdl != null)
        {
            int taskId = Integer.parseInt(cTaskId.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm completing task");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to complete task ID[" + taskId +"]?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                tdl.completeTask(taskId);
                getAllTasks();
                cTaskId.setText("");
            } else
            {
                cTaskId.setText("");
            }
        }
    }

    public void completeSubtask()
    {
        if(tdl != null)
        {
            int subtaskId = Integer.parseInt(cSubtaskId.getText());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm completing subtask");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to complete subtask ID[" + subtaskId +"]?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                tdl.completeSubtask(subtaskId);
                getAllTasks();
                cSubtaskId.setText("");
            } else
            {
                cSubtaskId.setText("");
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }
}
