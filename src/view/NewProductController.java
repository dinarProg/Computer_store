package electronics_store.view;

import electronics_store.Main;
import electronics_store.model.ConnectionJDBC;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Владимир on 18.07.2015.
 */
public class NewProductController {
    @FXML
    HBox mainBox=new HBox();
    @FXML
    ComboBox<String> cmbx_Category=new ComboBox<>();
    @FXML
    Button saveButton = new Button();

    GridPane gridProducts = new GridPane();

    TextField text_Name = new TextField();
    TextField text_Description = new TextField();
    TextField text_Manufacturer = new TextField();
    TextField text_Price = new TextField();
    TextField text_Count = new TextField();
    Label lbl_Name = new Label("* Название:");
    Label lbl_Description = new Label("Описание:");
    Label lbl_Manufacturer = new Label("Производитель:");
    Label lbl_Price = new Label("* Цена:");
    Label lbl_Count = new Label("* Количество:");
    Label lbl_Processor = new Label("Процессор:");
    Label lbl_CountCore = new Label("Количество ядер:");
    Label lbl_Valume = new Label("Объем ОП:");
    Label lbl_Hdd = new Label("Объем ЖД:");

    TextField text_ProcessorComputer = new TextField();
    TextField text_CountCoreComputer = new TextField();
    TextField text_ValumeComputer = new TextField();
    TextField text_HddComputer = new TextField();

    ChoiceBox<Double> chbx_DiagonalLaptop=new ChoiceBox<>();
    TextField text_ProcessorLaptop= new TextField();
    TextField text_CountCoreLaptop = new TextField();
    TextField text_ValumeLaptop = new TextField();
    TextField text_HddLaptop = new TextField();
    Label lbl_DiagonalLaptop = new Label("Диагональ:");

    TextField text_FamilyProcessor= new TextField();
    TextField text_SocketProcessor = new TextField();
    TextField text_CoresProcessor = new TextField();
    TextField text_FrequencyProcessor = new TextField();
    Label lbl_FamilyProcessor = new Label("Семейство процессоров:");
    Label lbl_SocketProcessor = new Label("Сокет:");
    Label lbl_CoresProcessor = new Label("Количество ядер:");
    Label lbl_FrequencyProcessor = new Label("Тактовая частота:");

    ChoiceBox<String> chbx_FormFactors=new ChoiceBox<>();
    TextField text_SocketMotherboard = new TextField();
    TextField text_CountMemorySlot = new TextField();
    Label lbl_FormfactorMotherboard = new Label("Форм-фактор:");
    Label lbl_SocketMotherboard = new Label("Сокет:");
    Label lbl_CountMemorySlot = new Label("Количество слотов памяти:");

    ChoiceBox<Double> chbx_FormanDrive=new ChoiceBox<>();
    TextField text_ValumeDrive = new TextField();
    Label lbl_FormanDrive = new Label("Формат:");
    Label lbl_ValumeDrive = new Label("* Объем ЖД:");

    TextField text_VolumeMemory = new TextField();
    TextField text_TypeMemory = new TextField();
    Label lbl_VolumeMemory = new Label("* Объем:");
    Label lbl_TypeMemory = new Label("Тип памяти:");


    TextField text_ValumeVideoCard = new TextField();
    TextField text_TypeVideoCard = new TextField();
    ChoiceBox<Integer> chbx_TireVideoCard=new ChoiceBox<>();
    Label lbl_ValumeVideoCard = new Label("* Объем памяти(Гбайт):");
    Label lbl_TypeVideoCard = new Label("Тип памяти:");
    Label lbl_TireVideoCard = new Label("Шина памяти:");

    ConnectionJDBC connectionJDBC = new ConnectionJDBC();
    private Stage mainForm;
    private Main main;
    private MainController mainController;

    @FXML
    private void initialize() {
        connectionJDBC.init();

        cmbx_Category.getItems().addAll("Компьютеры", "Ноутбуки", "Процессоры", "Материнские платы",
                "Жесткие диски", "Модули памяти", "Видеокарты");
        chbx_DiagonalLaptop.getItems().addAll(10.1, 13.3, 14.0, 15.6, 17.3);
        chbx_FormFactors.getItems().addAll("ATX", "mATX", "mITX");
        chbx_TireVideoCard.getItems().addAll(64, 128, 256, 384);
        chbx_FormanDrive.getItems().addAll(2.5, 3.5);
        gridProducts.setPadding(new Insets(10, 10, 10, 10));
        gridProducts.setVgap(10);
        gridProducts.setHgap(10);

        saveButton.setDisable(true);
    }

    @FXML
    private void getGridPane(){
        if(mainBox.getChildren().size()!=0) {
            mainBox.getChildren().clear();
            text_Name.clear();
            text_Description.clear();
            text_Manufacturer.clear();
            text_Price.clear();
            text_Count.clear();
        }
        saveButton.setDisable(false);
        switch (cmbx_Category.getValue()){
            case "Компьютеры": mainBox.getChildren().add(initGridComputers()); break;
            case "Ноутбуки": mainBox.getChildren().add(initGridLaptops()); break;
            case "Процессоры": mainBox.getChildren().add(initGridProcessors()); break;
            case "Материнские платы": mainBox.getChildren().add(initGridMotherboards()); break;
            case "Жесткие диски": mainBox.getChildren().add(initGridHDD()); break;
            case "Модули памяти": mainBox.getChildren().add(initGridMemoryModuls()); break;
            case "Видеокарты": mainBox.getChildren().add(initGridVideoCards()); break;
        }
    }

    private GridPane initGridComputers() {
        if(gridProducts.getChildren().size() != 0) gridProducts.getChildren().clear();

        gridProducts.add(lbl_Name, 0, 0);
        gridProducts.add(lbl_Description, 0, 1);
        gridProducts.add(lbl_Manufacturer, 0, 2);
        gridProducts.add(lbl_Processor, 0, 3);
        gridProducts.add(lbl_CountCore, 0, 4);
        gridProducts.add(lbl_Valume, 0, 5);
        gridProducts.add(lbl_Hdd, 0, 6);
        gridProducts.add(lbl_Price, 0, 7);
        gridProducts.add(lbl_Count, 0, 8);

        gridProducts.add(text_Name, 1,0);
        gridProducts.add(text_Description, 1,1);
        gridProducts.add(text_Manufacturer, 1,2);
        gridProducts.add(text_ProcessorComputer, 1,3);
        gridProducts.add(text_CountCoreComputer, 1,4);
        gridProducts.add(text_ValumeComputer, 1,5);
        gridProducts.add(text_HddComputer, 1,6);
        gridProducts.add(text_Price, 1, 7);
        gridProducts.add(text_Count, 1,8);

        return gridProducts;
    }

    private GridPane initGridLaptops(){
        if(gridProducts.getChildren().size() != 0) gridProducts.getChildren().clear();

        gridProducts.add(lbl_Name, 0, 0);
        gridProducts.add(lbl_Description, 0, 1);
        gridProducts.add(lbl_Manufacturer, 0, 2);
        gridProducts.add(lbl_DiagonalLaptop, 0, 3);
        gridProducts.add(lbl_Processor, 0, 4);
        gridProducts.add(lbl_CountCore, 0, 5);
        gridProducts.add(lbl_Valume, 0, 6);
        gridProducts.add(lbl_Hdd, 0, 7);
        gridProducts.add(lbl_Price, 0, 8);
        gridProducts.add(lbl_Count, 0, 9);

        gridProducts.add(text_Name, 1,0);
        gridProducts.add(text_Description, 1,1);
        gridProducts.add(text_Manufacturer, 1,2);
        gridProducts.add(chbx_DiagonalLaptop, 1, 3 );
        gridProducts.add(text_ProcessorLaptop, 1,4);
        gridProducts.add(text_CountCoreLaptop, 1,5);
        gridProducts.add(text_ValumeLaptop, 1,6);
        gridProducts.add(text_HddLaptop, 1,7);
        gridProducts.add(text_Price, 1, 8);
        gridProducts.add(text_Count, 1,9);

        return gridProducts;
    }

    private GridPane initGridProcessors(){
        if(gridProducts.getChildren().size() != 0) gridProducts.getChildren().clear();

        gridProducts.add(lbl_Name, 0, 0);
        gridProducts.add(lbl_Description, 0, 1);
        gridProducts.add(lbl_Manufacturer, 0, 2);
        gridProducts.add(lbl_FamilyProcessor, 0, 3);
        gridProducts.add(lbl_SocketProcessor, 0, 4);
        gridProducts.add(lbl_CoresProcessor, 0, 5);
        gridProducts.add(lbl_FrequencyProcessor, 0, 6);
        gridProducts.add(lbl_Price, 0, 7);
        gridProducts.add(lbl_Count, 0, 8);

        gridProducts.add(text_Name, 1,0);
        gridProducts.add(text_Description, 1,1);
        gridProducts.add(text_Manufacturer, 1,2);
        gridProducts.add(text_FamilyProcessor, 1,3);
        gridProducts.add(text_SocketProcessor, 1,4);
        gridProducts.add(text_CoresProcessor, 1,5);
        gridProducts.add(text_FrequencyProcessor, 1,6);
        gridProducts.add(text_Price, 1, 7);
        gridProducts.add(text_Count, 1,8);

        return gridProducts;
    }

    private GridPane initGridMotherboards(){
        if(gridProducts.getChildren().size() != 0) gridProducts.getChildren().clear();

        gridProducts.add(lbl_Name, 0, 0);
        gridProducts.add(lbl_Description, 0, 1);
        gridProducts.add(lbl_Manufacturer, 0, 2);
        gridProducts.add(lbl_FormfactorMotherboard, 0, 3);
        gridProducts.add(lbl_SocketMotherboard, 0, 4);
        gridProducts.add(lbl_CountMemorySlot, 0, 5);
        gridProducts.add(lbl_Price, 0, 6);
        gridProducts.add(lbl_Count, 0, 7);

        gridProducts.add(text_Name, 1,0);
        gridProducts.add(text_Description, 1,1);
        gridProducts.add(text_Manufacturer, 1,2);
        gridProducts.add(chbx_FormFactors, 1,3);
        gridProducts.add(text_SocketMotherboard, 1,4);
        gridProducts.add(text_CountMemorySlot, 1,5);
        gridProducts.add(text_Price, 1, 6);
        gridProducts.add(text_Count, 1,7);

        return gridProducts;
    }

    private GridPane initGridHDD() {
        if(gridProducts.getChildren().size() != 0) gridProducts.getChildren().clear();

        gridProducts.add(lbl_Name, 0, 0);
        gridProducts.add(lbl_Description, 0, 1);
        gridProducts.add(lbl_Manufacturer, 0, 2);
        gridProducts.add(lbl_ValumeDrive, 0, 3);
        gridProducts.add(lbl_FormanDrive, 0, 4);
        gridProducts.add(lbl_Price, 0, 5);
        gridProducts.add(lbl_Count, 0, 6);

        gridProducts.add(text_Name, 1,0);
        gridProducts.add(text_Description, 1,1);
        gridProducts.add(text_Manufacturer, 1,2);
        gridProducts.add(text_ValumeDrive, 1,3);
        gridProducts.add(chbx_FormanDrive, 1,4);
        gridProducts.add(text_Price, 1, 5);
        gridProducts.add(text_Count, 1,6);

        return gridProducts;
    }

    private GridPane initGridMemoryModuls(){
        if(gridProducts.getChildren().size() != 0) gridProducts.getChildren().clear();

        gridProducts.add(lbl_Name, 0, 0);
        gridProducts.add(lbl_Description, 0, 1);
        gridProducts.add(lbl_Manufacturer, 0, 2);
        gridProducts.add(lbl_VolumeMemory, 0, 3);
        gridProducts.add(lbl_TypeMemory, 0, 4);
        gridProducts.add(lbl_Price, 0, 5);
        gridProducts.add(lbl_Count, 0, 6);

        gridProducts.add(text_Name, 1,0);
        gridProducts.add(text_Description, 1,1);
        gridProducts.add(text_Manufacturer, 1,2);
        gridProducts.add(text_VolumeMemory, 1,3);
        gridProducts.add(text_TypeMemory, 1,4);
        gridProducts.add(text_Price, 1, 5);
        gridProducts.add(text_Count, 1,6);

        return gridProducts;
    }

    private GridPane initGridVideoCards(){
        if(gridProducts.getChildren().size() != 0) gridProducts.getChildren().clear();

        gridProducts.add(lbl_Name, 0, 0);
        gridProducts.add(lbl_Description, 0, 1);
        gridProducts.add(lbl_Manufacturer, 0, 2);
        gridProducts.add(lbl_ValumeVideoCard, 0, 3);
        gridProducts.add(lbl_TypeVideoCard, 0, 4);
        gridProducts.add(lbl_TireVideoCard, 0, 5);
        gridProducts.add(lbl_Price, 0, 6);
        gridProducts.add(lbl_Count, 0, 7);

        gridProducts.add(text_Name, 1,0);
        gridProducts.add(text_Description, 1,1);
        gridProducts.add(text_Manufacturer, 1,2);
        gridProducts.add(text_ValumeVideoCard, 1,3);
        gridProducts.add(text_TypeVideoCard, 1, 4);
        gridProducts.add(chbx_TireVideoCard, 1,5);
        gridProducts.add(text_Price, 1, 6);
        gridProducts.add(text_Count, 1, 7);

        return gridProducts;
    }

    @FXML
    private void handleSaveProduct(){
        if(mainBox.getChildren().size()> 0) {
            switch (cmbx_Category.getValue()) {
                case "Компьютеры": {
                    if(insertCoputer()){
                        showSuccessAlert();
                        handleClose();
                    }
                    break;
                }
                case "Ноутбуки":
                    if(insertLaptop()) {
                        showSuccessAlert();
                        handleClose();
                    }
                    break;
                case "Процессоры":
                    if(insertProcessor()){
                        showSuccessAlert();
                        handleClose();
                    }
                    break;
                case "Материнские платы":
                    if(insertMotherboard()){
                        showSuccessAlert();
                        handleClose();
                    }
                    break;
                case "Жесткие диски":
                    if(insertHDD()){
                        showSuccessAlert();
                        handleClose();
                    }
                    break;
                case "Модули памяти":
                    if(insertMemoryModul()){
                        showSuccessAlert();
                        handleClose();
                    }
                    break;
                case "Видеокарты":
                    if(insertVideoCard()){
                        showSuccessAlert();
                        handleClose();
                    }
                    break;
            }
        }
    }

    public boolean insertCoputer() {
        String name = null, description = null, processor = null, manufactor = null;
        Integer countCore = null, valume = null, valumeHDD = null , count=null;
        Double price = null;

        if (!text_Name.getText().isEmpty())
            name = text_Name.getText();
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Поле \'Название\' не может быть пустым");
            alert.showAndWait();
            return false;
        }
        if (!text_Description.getText().isEmpty())
            description = text_Description.getText();
        if (!text_Manufacturer.getText().isEmpty())
            manufactor = text_Manufacturer.getText();
        if (!text_ProcessorComputer.getText().isEmpty())
            processor = text_ProcessorComputer.getText();

        if (!text_CountCoreComputer.getText().isEmpty())
            if(tryParseInt(text_CountCoreComputer.getText()))
                countCore = (Integer)Integer.parseInt(text_CountCoreComputer.getText());
            else return false;

        if (!text_ValumeComputer.getText().isEmpty())
            if(tryParseInt(text_ValumeComputer.getText()))
                valume = (Integer)Integer.parseInt(text_ValumeComputer.getText());
            else return false;;

        if (!text_HddComputer.getText().isEmpty())
            if(tryParseInt(text_HddComputer.getText()))
                valumeHDD = (Integer)Integer.parseInt(text_HddComputer.getText());
            else return false;

        if(checkPriceAndCount()) {
            price = Double.parseDouble(text_Price.getText());
            count = Integer.parseInt(text_Count.getText());
        }
        else return false;

        return connectionJDBC.insertCoputer(name, description, manufactor, processor, countCore, valume,
                valumeHDD, price, count);
    }

    public boolean insertLaptop(){
        String name = null, description = null, processor = null, manufactor = null;
        Integer countCore = null, valume = null, valumeHDD = null , count=null;
        Double price = null;

        if (!text_Name.getText().isEmpty())
            name = text_Name.getText();
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Поле \'Название\' не может быть пустым");
            alert.showAndWait();
            return false;
        }
        if (!text_Description.getText().isEmpty())
            description = text_Description.getText();
        if (!text_Manufacturer.getText().isEmpty())
            manufactor = text_Manufacturer.getText();
        if(!text_ProcessorLaptop.getText().isEmpty())
            processor = text_ProcessorLaptop.getText();

        if (!text_CountCoreLaptop.getText().isEmpty())
            if(tryParseInt(text_CountCoreLaptop.getText()))
                countCore = (Integer)Integer.parseInt(text_CountCoreLaptop.getText());
            else return false;

        if (!text_ValumeLaptop.getText().isEmpty())
            if(tryParseInt(text_ValumeLaptop.getText()))
                valume = (Integer)Integer.parseInt(text_ValumeLaptop.getText());
            else return false;;

        if (!text_HddLaptop.getText().isEmpty())
            if(tryParseInt(text_HddLaptop.getText()))
                valumeHDD = (Integer)Integer.parseInt(text_HddLaptop.getText());
            else return false;

        if(checkPriceAndCount()) {
            price = Double.parseDouble(text_Price.getText());
            count = Integer.parseInt(text_Count.getText());
        }
        else return false;

        return connectionJDBC.insertLaptop(name, description, manufactor, chbx_DiagonalLaptop.getValue(), processor, countCore, valume,
                valumeHDD, price, count);
    }

    public boolean insertProcessor() {
        String name = null, description = null, familyProcessor = null, manufactor = null, socket = null;
        Integer countCore = null, count=null;
        Double price = null, frequency = null;

        if (!text_Name.getText().isEmpty())
            name = text_Name.getText();
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Поле \'Название\' не может быть пустым");
            alert.showAndWait();
            return false;
        }
        if (!text_Description.getText().isEmpty())
            description = text_Description.getText();
        if (!text_Manufacturer.getText().isEmpty())
            manufactor = text_Manufacturer.getText();
        if(!text_FamilyProcessor.getText().isEmpty())
            familyProcessor = text_FamilyProcessor.getText();
        if(!text_SocketProcessor.getText().isEmpty())
            socket=text_SocketProcessor.getText();

        if (!text_CoresProcessor.getText().isEmpty())
            if(tryParseInt(text_CoresProcessor.getText()))
                countCore = (Integer)Integer.parseInt(text_CoresProcessor.getText());
            else return false;

        if (!text_FrequencyProcessor.getText().isEmpty())
            if(tryParseDouble(text_FrequencyProcessor.getText()))
                frequency = Double.parseDouble(text_FrequencyProcessor.getText());
            else return false;

        if(checkPriceAndCount()) {
            price = Double.parseDouble(text_Price.getText());
            count = Integer.parseInt(text_Count.getText());
        }
        else return false;

        return connectionJDBC.insertProcessor(name, description, manufactor, familyProcessor, socket, countCore, frequency,
                price, count);
    }

    public boolean insertMotherboard() {
        String name = null, description = null, manufactor = null, socket = null;
        Integer countMemorySlot = null, count = null;
        Double price = null;

        if (!text_Name.getText().isEmpty())
            name = text_Name.getText();
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Поле \'Название\' не может быть пустым");
            alert.showAndWait();
            return false;
        }
        if (!text_Description.getText().isEmpty())
            description = text_Description.getText();
        if (!text_Manufacturer.getText().isEmpty())
            manufactor = text_Manufacturer.getText();
        if(!text_SocketMotherboard.getText().isEmpty())
            socket=text_SocketMotherboard.getText();

        if (!text_CountMemorySlot.getText().isEmpty())
            if(tryParseInt(text_CountMemorySlot.getText()))
                countMemorySlot = (Integer)Integer.parseInt(text_CountMemorySlot.getText());
            else return false;

        if(checkPriceAndCount()) {
            price = Double.parseDouble(text_Price.getText());
            count = Integer.parseInt(text_Count.getText());
        }
        else return false;

        return connectionJDBC.insertMotherboard(name, description, manufactor, chbx_FormFactors.getValue(), socket, countMemorySlot,
                price, count);
    }

    public boolean insertHDD() {
        String name = null, description = null, manufactor = null;
        Integer valume = null, count = null;
        Double price = null;

        if (!text_Name.getText().isEmpty())
            name = text_Name.getText();
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Поле \'Название\' не может быть пустым");
            alert.showAndWait();
            return false;
        }
        if (!text_Description.getText().isEmpty())
            description = text_Description.getText();
        if (!text_Manufacturer.getText().isEmpty())
            manufactor = text_Manufacturer.getText();

        if (!text_ValumeDrive.getText().isEmpty()) {
            if (tryParseInt(text_ValumeDrive.getText()))
                valume = Integer.parseInt(text_ValumeDrive.getText());
            else return false;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Поле \'Объем ЖД\' не может быть пустым");
            alert.showAndWait();
            return false;
        }

        if(checkPriceAndCount()) {
            price = Double.parseDouble(text_Price.getText());
            count = Integer.parseInt(text_Count.getText());
        }
        else return false;

        return connectionJDBC.insertHDD(name, description, manufactor, valume, chbx_FormanDrive.getValue(),
                price, count);
    }

    public boolean insertMemoryModul() {
        String name = null, description = null, manufactor = null, type=null;
        Integer valume = null, count = null;
        Double price = null;

        if (!text_Name.getText().isEmpty())
            name = text_Name.getText();
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Поле \'Название\' не может быть пустым");
            alert.showAndWait();
            return false;
        }
        if (!text_Description.getText().isEmpty())
            description = text_Description.getText();
        if (!text_Manufacturer.getText().isEmpty())
            manufactor = text_Manufacturer.getText();

        if (!text_VolumeMemory.getText().isEmpty())
            if(tryParseInt(text_VolumeMemory.getText()))
                valume = Integer.parseInt(text_VolumeMemory.getText());
            else return false;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Поле \'Объем\' не может быть пустым");
            alert.showAndWait();
            return false;
        }

        if(!text_TypeMemory.getText().isEmpty())
            type=text_TypeMemory.getText();

        if(checkPriceAndCount()) {
            price = Double.parseDouble(text_Price.getText());
            count = Integer.parseInt(text_Count.getText());
        }
        else return false;

        return connectionJDBC.insertMemoryModul(name, description, manufactor, valume, type,
                price, count);
    }

    public boolean insertVideoCard() {
        String name, description = null, manufactor = null, type=null;
        Integer valume = null, count = null;
        Double price = null;

        if (!text_Name.getText().isEmpty())
            name = text_Name.getText();
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Поле \'Название\' не может быть пустым");
            alert.showAndWait();
            return false;
        }
        if (!text_Description.getText().isEmpty())
            description = text_Description.getText();
        if (!text_Manufacturer.getText().isEmpty())
            manufactor = text_Manufacturer.getText();

        if (!text_ValumeVideoCard.getText().isEmpty())
            if(tryParseInt(text_ValumeVideoCard.getText()))
                valume = Integer.parseInt(text_ValumeVideoCard.getText());
            else return false;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Поле \'Объем памяти\' не может быть пустым");
            alert.showAndWait();
            return false;
        }

        if(!text_TypeVideoCard.getText().isEmpty())
            type=text_TypeVideoCard.getText();

        if(checkPriceAndCount()) {
            price = Double.parseDouble(text_Price.getText());
            count = Integer.parseInt(text_Count.getText());
        }
        else return false;

        return connectionJDBC.insertVideoCard(name, description, manufactor, valume, type, chbx_TireVideoCard.getValue(),
                price, count);
    }

    @FXML
    private void handleClose(){
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMain(Main main){
        this.main=main;
    }

    public void showSuccessAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Запись добавлена");
        alert.setHeaderText("Запись успешно добавлена");

        alert.showAndWait();
    }

    //Проверка полей Цена и Количество на наличие числовых значений и отсутствия символов
    private boolean checkPriceAndCount() {
        if (!text_Price.getText().isEmpty()) {
            if(tryParseDouble(text_Price.getText())){
                if(Double.parseDouble(text_Price.getText()) <= 0){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка ввода");
                    alert.setHeaderText("\'Цена\' не может быть меньше либо равно нулю.");
                    alert.showAndWait();
                    return false;
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Поле \'Цена\' не может быть пустым.");
            alert.showAndWait();
            return false;
        }

        if (!text_Count.getText().isEmpty()) {
            if(!tryParseInt(text_Count.getText())){
                return false;
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Поле \'Количество\' не может быть пустым.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    boolean tryParseInt(String value){
        try
        {
            Integer.parseInt(value);
            return true;
        } catch(NumberFormatException nfe)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Значение \'" + value + "\' не является допустимым числом");
            alert.showAndWait();

            return false;
        }
    }

    boolean tryParseDouble(String value){
        try
        {
            Double.parseDouble(value);
            return true;
        } catch(NumberFormatException nfe)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Значение \'" + value + "\' не является допустимым числом");
            alert.showAndWait();

            return false;
        }
    }
}
