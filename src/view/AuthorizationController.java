package electronics_store.view;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.LeafPropertyLoader;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import electronics_store.Main;
import electronics_store.model.ConnectionJDBC;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AuthorizationController {

    @FXML
    TextField text_userName = new TextField();
    @FXML
    TextField text_url = new TextField();
    @FXML
    PasswordField text_Password = new PasswordField();

    private Main main;
    private Stage mainForm;
    private MainController mainController =new MainController();
    ConnectionJDBC connectionJDBC = new ConnectionJDBC();

    public void setMain(Main main){
        this.main=main;
    }

    @FXML
    private void initialize(){
        try {
            Properties props = new Properties();
            File currentDir = new File(".");
            String sDirSeparator = System.getProperty("file.separator");
            String sFileName = "conf\\Connection.properties";
            String sFilePath = currentDir.getCanonicalPath()+ sDirSeparator + sFileName;;
            props.load(new FileInputStream(sFilePath));

            text_url.setText(props.getProperty("URL"));
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка при подключении");
            alert.setHeaderText("Не найден файл: Connection.properties");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка при подключении");
        alert.setHeaderText(null);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    }

    //Обработчик кнопки входа в основное окно
    @FXML
    public void handleEnter() {
        String userName, password, url;

        if(text_userName.getText().isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка при подключении");
            alert.setHeaderText(null);
            alert.setContentText("Поле \'Имя пользователя\' не может быть пустым");
            alert.showAndWait();
            return;
        }  else userName = text_userName.getText();

        if(text_Password.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка при подключении");
            alert.setHeaderText(null);
            alert.setContentText("Поле \'Пароль\' не может быть пустым");
            alert.showAndWait();
            return;
        }
        else password = text_Password.getText();

        if(text_url.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка при подключении");
            alert.setHeaderText(null);
            alert.setContentText("Поле \'Строка подключения\' не может быть пустым");
            alert.showAndWait();
            return;
        }
        else url = text_url.getText();


        if(connectionJDBC.init(userName , password, url)) {
            mainForm = main.getStage();
            mainForm.setTitle("Управление магазином");
            mainForm.setResizable(true);

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Main.fxml"));

            try {
                Pane mainPane = (Pane) loader.load();
                Scene mainScence = new Scene(mainPane);
                mainForm.setScene(mainScence);

                mainController = loader.getController();
                mainController.setMain(main);


            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка при подключении");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    public void handleExit() {
        mainForm = main.getStage();
        mainForm.close();
    }
}
