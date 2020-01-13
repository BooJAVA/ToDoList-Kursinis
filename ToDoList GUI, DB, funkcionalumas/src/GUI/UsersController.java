package GUI;

import ds.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UsersController implements Initializable
{
    @FXML
    private TableView userTable;
    @FXML
    private TextField pLogin, pPassword, pName, pSurname, pEmail;
    @FXML
    private TextField cLogin, cPassword, cTitle, cEmail;
    @FXML
    private TextField pEditId, pEditPassword, pEditName, pEditSurname, pEditEmail;
    @FXML
    private TextField cEditId, cEditPassword, cEditTitle, cEditEmail;
    @FXML
    private TextField uDisable, uEnable;

    ToDoList tdl = null;

    public void setToDoList(ToDoList t)
    {
        tdl = t;
        getAllUsers();
    }

    public void addPerson()
    {
        String login = pLogin.getText();
        String password = pPassword.getText();
        String name = pName.getText();
        String surname = pSurname.getText();
        String email = pEmail.getText();
        tdl.registerPerson(login, password, name, surname, email);
        getAllUsers();

        //userTable.getSelectionModel().getSelectedCells()

        pLogin.setText("");
        pPassword.setText("");
        pName.setText("");
        pSurname.setText("");
        pEmail.setText("");
    }

    public void addCompany()
    {
        String login = cLogin.getText();
        String password = cPassword.getText();
        String title = cTitle.getText();
        String email = cEmail.getText();
        tdl.registerCompany(login, password, title, email);
        getAllUsers();

        //userTable.getSelectionModel().getSelectedCells()

        cLogin.setText("");
        cPassword.setText("");
        cTitle.setText("");
        cEmail.setText("");
    }

    public void editPerson()
    {
        int userId = Integer.parseInt(pEditId.getText());
        String password = pEditPassword.getText();
        String name = pEditName.getText();
        String surname = pEditSurname.getText();
        String email = pEditEmail.getText();
        tdl.editPersonInfo(userId, password, name, surname, email);
        getAllUsers();

        //userTable.getSelectionModel().getSelectedCells()

        pEditId.setText("");
        pEditPassword.setText("");
        pEditName.setText("");
        pEditSurname.setText("");
        pEditEmail.setText("");
    }

    public void editCompany()
    {
        int companyId = Integer.parseInt(cEditId.getText());
        String password = cEditPassword.getText();
        String title = cEditTitle.getText();
        String email = cEditEmail.getText();
        tdl.editCompanyInfo(companyId, password, title, email);
        getAllUsers();

        //userTable.getSelectionModel().getSelectedCells()

        cEditId.setText("");
        cEditTitle.setText("");
        cEditEmail.setText("");
    }

    public void getAllUsers()
    {
        if(tdl != null)
        {
            userTable.getItems().clear();
            userTable.getItems().addAll(tdl.getAllUsers());
        }
    }

    public void getAllActiveUsers()
    {
        if(tdl != null)
        {
            userTable.getItems().clear();
            userTable.getItems().addAll(tdl.getAllActiveUsers());
        }
    }

    public void importUsers()
    {
        Scanner file = null;
        try{
            file = new Scanner(new File("users.txt"));
            while(file.hasNext())
            {
                String line = file.nextLine();
                String[] elements = line.split(";");
                tdl.registerPerson(elements[1], elements[2], elements[3], elements[4], elements[5]);
            }
        } catch (Exception e) {
            System.out.println("Error reading data");
        }finally {
            if(file != null)
            {
                file.close();
            }
        }
        getAllUsers();
    }

    public void disableUser()
    {
        int userId = Integer.parseInt(uDisable.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm disabling user");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to disable user ID[" + userId +"]?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            tdl.disableUser(userId);
            uDisable.setText("");
            getAllUsers();
        } else
        {
            uDisable.setText("");
        }
    }

    public void enableUser()
    {
        int userId = Integer.parseInt(uEnable.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm disabling user");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to enable user ID[" + userId +"]?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            tdl.enableUser(userId);
            uEnable.setText("");
            getAllUsers();
        } else
        {
            uEnable.setText("");
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        TableColumn<User, String> column1 = new TableColumn<>("ID");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<User, String> column2 = new TableColumn<>("Login");
        column2.setCellValueFactory(new PropertyValueFactory<>("login"));
        TableColumn<User, String> column3 = new TableColumn<>("Email");
        column3.setCellValueFactory(new PropertyValueFactory<>("email"));
        TableColumn<User, String> column4 = new TableColumn<>("Is it active?");
        column4.setCellValueFactory(new PropertyValueFactory<>("active"));
        /*TableColumn<Person, String> column5 = new TableColumn<>("Name");
        column5.setCellValueFactory(new PropertyValueFactory<>("title"));*/
        /*TableColumn<Person, String> column6 = new TableColumn<>("Surname");
        column6.setCellValueFactory(new PropertyValueFactory<>("surname"));*/
        /*TableColumn<Company, String> column7 = new TableColumn<>("Title");
        column7.setCellValueFactory(new PropertyValueFactory<>("title"));*/
        userTable.getColumns().clear();
        userTable.getColumns().add(column1);
        userTable.getColumns().add(column2);
        userTable.getColumns().add(column3);
        userTable.getColumns().add(column4);
        /*userTable.getColumns().add(column5);*/
        /*userTable.getColumns().add(column6);
        userTable.getColumns().add(column7);*/
    }

}
