package GUI;

import ds.ToDoList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable
{
    @FXML
    private PieChart userStat;
    @FXML
    private AreaChart projectStat;
    private ToDoList tdl = null;

    public void setToDoList(ToDoList t) throws Exception
    {
        tdl = t;
        showUserStat();
        //showProjectStat();
    }

    public void close()
    {
        Platform.exit();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("data.tdl")));
            out.writeObject(tdl);
            out.close();
        } catch (Exception e) {
            System.out.println("Failed to write data");
        }
    }

    public void openUserManager() throws Exception
    {
        FXMLLoader load = new FXMLLoader(getClass().getResource("Users.fxml"));
        Parent root = load.load();
        UsersController contr = load.getController();
        contr.setToDoList(tdl);
        Scene scene = new Scene(root);
        Stage neww = new Stage();
        neww.setTitle("User Manager");
        neww.setScene(scene);
        neww.show();
    }

    public void openProjectManager() throws Exception
    {
        FXMLLoader load = new FXMLLoader(getClass().getResource("Projects.fxml"));
        Parent root = load.load();
        ProjectsController contr = load.getController();
        contr.setToDoList(tdl);
        Scene scene = new Scene(root);
        Stage neww = new Stage();
        neww.setTitle("Project Manager");
        neww.setScene(scene);
        neww.show();
    }



    public void showUserStat()
    {
        if(tdl != null)
        {
            int personCount = tdl.getPersonCount();
            int companyCount = tdl.getCompanyCount();
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Persons - " + personCount, personCount),
                            new PieChart.Data("Companies - " + companyCount, companyCount));
            userStat.setTitle("User statistics");
            userStat.setData(pieChartData);
        }
    }

    public void showProjectStat()
    {
        if(tdl != null)
        {
            XYChart.Series seriesTask = new XYChart.Series();
            seriesTask.setName("Tasks per project");
            int[][] m = tdl.getProjectNumbers();
            for(int[] row:m)
            {
                seriesTask.getData().add(new XYChart.Data(row[0], row[1]));
            }
            projectStat.getData().addAll(seriesTask);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }


}
