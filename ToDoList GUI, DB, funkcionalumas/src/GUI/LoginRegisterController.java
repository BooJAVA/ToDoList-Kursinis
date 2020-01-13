package GUI;

import ds.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.*;
import java.util.List;

public class LoginRegisterController implements Initializable
{
    @FXML
    TextField enterLogin, enterPassword;
    @FXML
    Button loginButton;

    private ToDoList tdl = null;
    public void setToDoList(ToDoList t) throws Exception
    {
        tdl = t;
    }

    public void closeLoginScreen()
    {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }

    public void login() throws Exception
    {

        String login = enterLogin.getText();
        String password = enterPassword.getText();
        if(tdl.login(login, password) != null)
        {
            openDashboard();
            closeLoginScreen();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect login info");
            alert.setContentText("Enter correct login info");
            alert.showAndWait();
        }
        enterLogin.setText("");
        enterPassword.setText("");

    }

    public void openDashboard() throws Exception
    {
        FXMLLoader load = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Parent root = load.load();
        DashboardController contr = load.getController();
        contr.setToDoList(tdl);
        Scene scene = new Scene(root);
        Stage neww = new Stage();
        neww.setTitle("Admin interface");
        neww.setScene(scene);
        neww.show();
    }

    public void register()
    {
        final String [] arrayData = {"Person", "Company"};
        List<String> dialogData;
        dialogData = Arrays.asList(arrayData);
        Dialog<String> dialog = new ChoiceDialog(dialogData.get(0), dialogData);
        dialog.setTitle("Choose user type");
        dialog.setHeaderText(null);

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent())
        {
            if(result.get() == "Person")
            {
                registerPerson();
            }
            else if(result.get() == "Company")
            {
                registerCompany();
            }
        }
    }

    public void registerPerson()
    {
        Dialog<Person> dialog = new Dialog<>();
        dialog.setTitle("Register new person");

        Label lLogin = new Label("Login: ");
        Label lPassword = new Label("Password: ");
        Label lName = new Label("Name: ");
        Label lSurname = new Label("Surname: ");
        Label lEmail = new Label("Email: ");
        TextField textLogin = new TextField();
        TextField textPassword = new TextField();
        TextField textName = new TextField();
        TextField textSurname = new TextField();
        TextField textEmail = new TextField();

        GridPane grid = new GridPane();
        grid.add(lLogin, 1, 1);
        grid.add(textLogin, 2, 1);
        grid.add(lPassword, 1, 2);
        grid.add(textPassword, 2, 2);
        grid.add(lName, 1, 3);
        grid.add(textName, 2, 3);
        grid.add(lSurname, 1, 4);
        grid.add(textSurname, 2, 4);
        grid.add(lEmail, 1, 5);
        grid.add(textEmail, 2, 5);
        dialog.getDialogPane().setContent(grid);

        ButtonType buttonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonType, ButtonType.CANCEL);

        Optional<Person>info = dialog.showAndWait();

        String login = textLogin.getText();
        String password = textPassword.getText();
        String name = textName.getText();
        String surname = textSurname.getText();
        String email = textEmail.getText();
        tdl.registerPerson(login, password, name, surname, email);
    }

    public void registerCompany()
    {
        Dialog<Company> dialog = new Dialog<>();
        dialog.setTitle("Register new company");

        Label lLogin = new Label("Login: ");
        Label lPassword = new Label("Password: ");
        Label lTitle = new Label("Title: ");
        Label lEmail = new Label("Email: ");
        TextField textLogin = new TextField();
        TextField textPassword = new TextField();
        TextField textTitle = new TextField();
        TextField textEmail = new TextField();

        GridPane grid = new GridPane();
        grid.add(lLogin, 1, 1);
        grid.add(textLogin, 2, 1);
        grid.add(lPassword, 1, 2);
        grid.add(textPassword, 2, 2);
        grid.add(lTitle, 1, 3);
        grid.add(textTitle, 2, 3);
        grid.add(lEmail, 1, 4);
        grid.add(textEmail, 2, 4);
        dialog.getDialogPane().setContent(grid);

        ButtonType buttonType = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonType, ButtonType.CANCEL);

        Optional<Company>info = dialog.showAndWait();

        String login = textLogin.getText();
        String password = textPassword.getText();
        String title = textTitle.getText();
        String email = textEmail.getText();
        tdl.registerCompany(login, password, title, email);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }
}
