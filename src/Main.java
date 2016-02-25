package electronics_store;

/**
 * Created by Администратор on 22.06.2015.
 */
import electronics_store.model.*;
import electronics_store.view.AuthorizationController;
import electronics_store.view.MainController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import oracle.jdbc.driver.*;

public class Main extends Application {

    private Stage primaryStage;
    private Pane rootLayout;
    private AuthorizationController authorizationController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Authorization.fxml"));
            rootLayout = (Pane) loader.load();
            Scene scene = new Scene(rootLayout);

            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("Авторизация");
            this.primaryStage.setResizable(false);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();

            authorizationController=loader.getController();
            authorizationController.setMain(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getStage(){
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}