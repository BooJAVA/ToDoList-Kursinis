package GUI;

import ds.ToDoList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Start extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        ToDoList todo = new ToDoList();

        try{
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("data.tdl")));
            todo = (ToDoList)in.readObject();
            in.close();
        }catch (Exception e) {
            System.out.println("Failed to load data");
        }

        FXMLLoader load = new FXMLLoader(getClass().getResource("LoginRegister.fxml"));
        Parent root = load.load();
        LoginRegisterController contr = load.getController();
        contr.setToDoList(todo);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
