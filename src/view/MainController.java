package electronics_store.view;

import electronics_store.Main;
import electronics_store.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.text.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MainController {
    @FXML
    private TableView tableView = new TableView<>();

    private TableColumn<Product,String> name = new TableColumn<>("Название");
    private TableColumn<Product,String> description= new TableColumn<>("Описание");
    private TableColumn<Product,String> manufacturer = new TableColumn<>("Производитель");
    private TableColumn<Product,Double> price= new TableColumn<>("Цена");
    private TableColumn<Product,Integer> count= new TableColumn<>("Количество");

    @FXML
    private Button buttonDelete = new Button();
    @FXML
    private Button buttonEdit = new Button();

    //Контроллы для управления сортировкой компьютеров
    @FXML
    private CheckBox check_AMD10 = new CheckBox();
    @FXML
    private CheckBox check_AMD6 = new CheckBox();
    @FXML
    private CheckBox check_Celeron = new CheckBox();
    @FXML
    private CheckBox check_i3 = new CheckBox();
    @FXML
    private CheckBox check_i5 = new CheckBox();
    @FXML
    private CheckBox check_i7 = new CheckBox();
    @FXML
    private TextField text_CounCore = new TextField();
    @FXML
    private TextField text_ValumeRAM = new TextField();
    @FXML
    private TextField text_ValumeHDD= new TextField();

    //Контроллы для управления сортировкой ноутбуков
    @FXML
    private ListView<Double> lst_diagonalLaptop = new ListView<>();
    @FXML
    private ListView<String> lst_processorLaptop = new ListView<>();
    @FXML
    private TextField text_manufacturerLaptop = new TextField();
    @FXML
    private TextField text_countCoreLaptop = new TextField();
    @FXML
    private TextField text_valumeLaptop = new TextField();
    @FXML
    private TextField text_hddLaptop = new TextField();

    //Контроллы для управления сортировкой процессоров
    @FXML
    private ListView<String> lst_familyProcessor = new ListView<>();
    @FXML
    private ListView<String> lst_socketProcessor = new ListView<>();
    @FXML
    private TextField text_manufacturerProcessor = new TextField();
    @FXML
    private TextField text_countCoreProcessor = new TextField();
    @FXML
    private CheckBox frequency1 = new CheckBox();
    @FXML
    private CheckBox frequency2 = new CheckBox();
    @FXML
    private CheckBox frequency3 = new CheckBox();
    @FXML
    private CheckBox frequency4 = new CheckBox();

    //Контроллы для управления сортировкой ЖД
    @FXML
    private TextField text_manufacturerHardDrive = new TextField();
    @FXML
    private TextField text_valumeHardDrive = new TextField();
    @FXML
    private CheckBox form_factor1 = new CheckBox();
    @FXML
    private CheckBox form_factor2 = new CheckBox();

    //Контроллы для управления сортировкой видеокарт
    @FXML
    private TextField text_manufacturerVideoCard = new TextField();
    @FXML
    private CheckBox valumeVideoCard1 = new CheckBox();
    @FXML
    private CheckBox valumeVideoCard2 = new CheckBox();
    @FXML
    private CheckBox valumeVideoCard3 = new CheckBox();
    @FXML
    private CheckBox typeVideoCard1 = new CheckBox();
    @FXML
    private CheckBox typeVideoCard2 = new CheckBox();
    @FXML
    private ListView<Integer> lst_tireVideoCard = new ListView<>();

    //Контроллы для управления сортировкой ОП
    @FXML
    private TextField text_manufacturerMemory = new TextField();
    @FXML
    private TextField text_valumeMemory = new TextField();
    @FXML
    private CheckBox typeMemory1 = new CheckBox();
    @FXML
    private CheckBox typeMemory2 = new CheckBox();
    @FXML
    private CheckBox typeMemory3 = new CheckBox();

    //Контроллы для управления сортировкой материнских плат
    @FXML
    private TextField text_manufacturerMotherboard = new TextField();
    @FXML
    private CheckBox formFactor1 = new CheckBox();
    @FXML
    private CheckBox formFactor2 = new CheckBox();
    @FXML
    private CheckBox formFactor3 = new CheckBox();
    @FXML
    private ListView<String> lst_socketProcessorsMotherboards = new ListView<>();
    @FXML
    private CheckBox countSlotMemory1 = new CheckBox();
    @FXML
    private CheckBox countSlotMemory2 = new CheckBox();


    private ConnectionJDBC connectionJDBC= new ConnectionJDBC();
    private Stage mainForm;
    private Main main;
    private NewProductController newProductController;
    private EditProductController editProductController;

    //Этот метод автоматически вызывается после загрузки fxml файла
    @FXML
    private void initialize() {
        try {
            connectionJDBC.init();
            getAllProducts();

            lst_diagonalLaptop.getItems().addAll(10.1, 13.3, 14.0, 15.6, 17.3);
            lst_processorLaptop.getItems().addAll("AMD A10", "AMD A6", "Intel Celeron", "Intel Core i3",
                    "Intel Core i5", "Intel Core i7");
            lst_familyProcessor.getItems().addAll("A10", "A6", "Intel Celeron", "Core i3",
                    "Core i5", "Core i7");
            lst_socketProcessor.getItems().addAll("AMD3+", "AMD2", "AMD2+", "LGA1150", "LGA1155");
            lst_socketProcessorsMotherboards.getItems().addAll("AMD3+", "AMD2", "AMD2+", "LGA1150", "LGA1155");
            lst_tireVideoCard.getItems().addAll(64, 128, 256, 384);

            lst_diagonalLaptop.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lst_processorLaptop.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lst_familyProcessor.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lst_socketProcessor.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lst_tireVideoCard.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            lst_socketProcessorsMotherboards.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            disabledButtons();

        }
        catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ошибка открытия главной формы");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }

    }

    @FXML
    public void getAllProducts() {
        try {
            if (tableView.getItems().size() >= 0) tableView.getItems().clear();
            if (tableView.getColumns().size() >= 0) tableView.getColumns().clear();

            name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
            description.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
            manufacturer.setCellValueFactory(new PropertyValueFactory<Product, String>("manufacturer"));
            price.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
            count.setCellValueFactory(new PropertyValueFactory<Product, Integer>("count"));

            tableView.setItems(connectionJDBC.getProducts());
            tableView.getColumns().addAll(name, description, manufacturer, price, count);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Возникла ошибка при получении таблицы товаров");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }

    public void showTableComputers(ObservableList<Computer> computers){
        try {
            tableView.getItems().clear();
            tableView.getColumns().clear();

            TableColumn<Computer, String> processorComputer = new TableColumn<>("Процессор");
            TableColumn<Computer, Integer> countCoreComputer = new TableColumn<>("Количество ядер");
            TableColumn<Computer, Integer> valumeComputer = new TableColumn<>("Объем ОП");
            TableColumn<Computer, Integer> hddComputer = new TableColumn<>("Объем жесткого диска");

            processorComputer.setCellValueFactory(new PropertyValueFactory<Computer, String>("processorComputer"));
            countCoreComputer.setCellValueFactory(new PropertyValueFactory<Computer, Integer>("countCoreComputer"));
            valumeComputer.setCellValueFactory(new PropertyValueFactory<Computer, Integer>("valumeComputer"));
            hddComputer.setCellValueFactory(new PropertyValueFactory<Computer, Integer>("hddComputer"));

            tableView.setItems(computers);
            tableView.getColumns().addAll(name, description, manufacturer, processorComputer, countCoreComputer,
                    valumeComputer, hddComputer, price, count);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Возникла ошибка при получении таблицы компьютеров");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }

    public void showTableLaptops(ObservableList<Laptop> laptops){
        try {
            tableView.getItems().clear();
            tableView.getColumns().clear();

            TableColumn<Laptop, Integer> diagonalLaptop = new TableColumn<>("Диагональ экрана");
            TableColumn<Laptop, String> processorLaptop = new TableColumn<>("Процессор");
            TableColumn<Laptop, Integer> countCoreLaptop = new TableColumn<>("Количество ядер процессора");
            TableColumn<Laptop, Integer> valumeLaptop = new TableColumn<>("Объем ОП");
            TableColumn<Laptop, Integer> hddLaptop = new TableColumn<>("Объем жесткого диска");

            diagonalLaptop.setCellValueFactory(new PropertyValueFactory<Laptop, Integer>("diagonalLaptop"));
            processorLaptop.setCellValueFactory(new PropertyValueFactory<Laptop, String>("processorLaptop"));
            countCoreLaptop.setCellValueFactory(new PropertyValueFactory<Laptop, Integer>("countCoreLaptop"));
            valumeLaptop.setCellValueFactory(new PropertyValueFactory<Laptop, Integer>("valumeLaptop"));
            hddLaptop.setCellValueFactory(new PropertyValueFactory<Laptop, Integer>("hddLaptop"));

            tableView.setItems(laptops);
            tableView.getColumns().addAll(name, description, manufacturer, diagonalLaptop,
                    processorLaptop, countCoreLaptop, valumeLaptop, hddLaptop, price, count);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Возникла ошибка при получении таблицы ноутбуков");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }

    public void showTableProcessors(ObservableList<Processor> processors){
        try {
            tableView.getItems().clear();
            tableView.getColumns().clear();

            TableColumn<Processor, String> familyProcessor = new TableColumn<>("Семейство процессора");
            TableColumn<Processor, String> socket = new TableColumn<>("Сокет");
            TableColumn<Processor, Integer> cores = new TableColumn<>("Количество ядер");
            TableColumn<Processor, Integer> frequencyProcessor = new TableColumn<>("Тактовая частота");

            familyProcessor.setCellValueFactory(new PropertyValueFactory<>("familyProcessor"));
            socket.setCellValueFactory(new PropertyValueFactory<>("socket"));
            cores.setCellValueFactory(new PropertyValueFactory<>("cores"));
            frequencyProcessor.setCellValueFactory(new PropertyValueFactory<>("frequencyProcessor"));

            tableView.setItems(processors);
            tableView.getColumns().addAll(name, description, manufacturer, familyProcessor, socket,
                    cores, frequencyProcessor, price, count);
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Возникла ошибка при получении таблицы процессоров");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }

    public void showTableHDD(ObservableList<HardDrive> hardDrives){
        try {
            tableView.getItems().clear();
            tableView.getColumns().clear();

            TableColumn<HardDrive, String> valumeDrive = new TableColumn<>("Объем памяти ЖД");
            TableColumn<HardDrive, Double> formanDrive = new TableColumn<>("Формат ЖД");

            valumeDrive.setCellValueFactory(new PropertyValueFactory<HardDrive, String>("valumeDrive"));
            formanDrive.setCellValueFactory(new PropertyValueFactory<HardDrive, Double>("formanDrive"));

            tableView.setItems(hardDrives);
            tableView.getColumns().addAll(name, description, manufacturer, valumeDrive, formanDrive, price, count);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Возникла ошибка при получении таблицы ЖД");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }

    public void showTableVideoCards(ObservableList<VideoCard> videoCards){
        try {
            tableView.getItems().clear();
            tableView.getColumns().clear();

            TableColumn<VideoCard, Integer> valumeVideoCard = new TableColumn<>("Объем памяти");
            TableColumn<VideoCard, String> typeVideoCard = new TableColumn<>("Тип");
            TableColumn<VideoCard, Integer> tireVideoCard = new TableColumn<>("Шина памяти");

            valumeVideoCard.setCellValueFactory(new PropertyValueFactory<>("valumeVideoCard"));
            typeVideoCard.setCellValueFactory(new PropertyValueFactory<>("typeVideoCard"));
            tireVideoCard.setCellValueFactory(new PropertyValueFactory<>("tireVideoCard"));

            tableView.setItems(videoCards);
            tableView.getColumns().addAll(name, description, manufacturer, valumeVideoCard, typeVideoCard,
                    tireVideoCard, price, count);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Возникла ошибка при получении таблицы видеокарт");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }

    public void showTableMemoryModuls(ObservableList<MemoryModule> memoryModules){
        try {
            tableView.getItems().clear();
            tableView.getColumns().clear();

            TableColumn<MemoryModule, Integer> volumeMemory = new TableColumn<>("Объем памяти");
            TableColumn<MemoryModule, String> typeMemory = new TableColumn<>("Тип памяти");

            volumeMemory.setCellValueFactory(new PropertyValueFactory<>("volumeMemory"));
            typeMemory.setCellValueFactory(new PropertyValueFactory<>("typeMemory"));

            tableView.setItems(memoryModules);
            tableView.getColumns().addAll(name, description, manufacturer, volumeMemory, typeMemory, price, count);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Возникла ошибка при получении таблицы модулей памяти");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }

    public void showTableMotherboards(ObservableList<Motherboard> motherboards){
        try {
            tableView.getItems().clear();
            tableView.getColumns().clear();

            TableColumn<Motherboard, String> formFactor = new TableColumn<>("Форм-фактор");
            TableColumn<Motherboard, String> socket = new TableColumn<>("Сокет процессора");
            TableColumn<Motherboard, Integer> countMemorySlot = new TableColumn<>("Количество слотов памати");

            formFactor.setCellValueFactory(new PropertyValueFactory<>("formFactor"));
            socket.setCellValueFactory(new PropertyValueFactory<>("socket"));
            countMemorySlot.setCellValueFactory(new PropertyValueFactory<>("countMemorySlot"));

            tableView.setItems(motherboards);
            tableView.getColumns().addAll(name, description, manufacturer, formFactor, socket, countMemorySlot
                    , price, count);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Возникла ошибка при получении таблицы материнских плат");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }

    public void showTableOrders(ObservableList<Order> orders){
        /*tableView.getItems().clear();
        tableView.getColumns().clear();

        TableColumn<Product,Integer> price = new TableColumn<>("Цена");
        TableColumn<Product,String> name = new TableColumn<>("Название");

        TableColumn<Order,String> ferstName = new TableColumn<>("Имя");
        TableColumn<Order,String> secondName = new TableColumn<>("Фамилия");
        TableColumn<Order,String> adress = new TableColumn<>("Адресс");
        TableColumn<Order,String> phone = new TableColumn<>("Телефон");

        ferstName.setCellValueFactory(new PropertyValueFactory<>("ferstName"));
        secondName.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        adress.setCellValueFactory(new PropertyValueFactory<>("adress"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        //idProduct.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        //name.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableView.setItems(orders);
        tableView.getColumns().addAll(name, description, manufacturer, idProduct, name, ferstName, secondName, adress,
                phone, price, count);*/
    }

    @FXML
    public void handleSerchOrders(){

    }

    @FXML
    public void handleSerchComputers(){

        if(getQueryComputers()!=null) {
            ObservableList<Computer> computers=FXCollections.observableArrayList();
            try (ResultSet rs = connectionJDBC.query(getQueryComputers())) {
                while (rs.next())
                    computers.add(new Computer(rs.getInt("ID_COMPUTER"), rs.getInt("ID_CATEGORY"), rs.getString("NAME_COMPUTER"), rs.getDouble("PRICE_COMPUTER"),
                            rs.getInt("COUNT_COMPUTER"), rs.getString("DESCRIPTION_COMPUTER"), rs.getString("MANUFACTURER_COMPUTER"),
                            rs.getString("PROCESSOR_COMPUTER"), rs.getInt("COUNT_CORE_COMPUTER"), rs.getInt("VALUME_COMPUTER"), rs.getInt("HDD_COMPUTER")));
                showTableComputers(computers);
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("SQLException");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }
    @FXML
    public void handleSerchMotherboards(){

        if(getQueryMotherboards()!=null) {
            ObservableList<Motherboard> motherboards=FXCollections.observableArrayList();
            try (ResultSet rs = connectionJDBC.query(getQueryMotherboards())) {
                while (rs.next())
                    motherboards.add(new Motherboard(rs.getInt("ID_MOTHERBOARD"), rs.getInt("ID_CATEGORY"), rs.getString("NAME_MOTHERBOARD"), rs.getDouble("PRICE_MOTHERBOARD"),
                            rs.getInt("COUNT_MOTHERBOARD"), rs.getString("DESCRIPTION_MOTHERBOARD"), rs.getString("MANUFACTURER_MOTHERBOARD"),
                            rs.getString("FORMFACTOR_MOTHERBOARD"), rs.getString("SOCKET_MOTHERBOARD"), rs.getInt("COUNT_MEMORY_SLOT")));
                showTableMotherboards(motherboards);
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("SQLException");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }
    @FXML
    private void handleSerchLaptops() {

        if(getQueryLaptops() != null) {
            //Получение ноутбуков по собранному запросу
            ObservableList<Laptop> laptops=FXCollections.observableArrayList();
            try (ResultSet rs = connectionJDBC.query(getQueryLaptops())) {
                while (rs.next())
                    laptops.add(new Laptop(rs.getInt("ID_LAPTOP"), rs.getInt("ID_CATEGORY"), rs.getString("NAME_LAPTOP"), rs.getDouble("PRICE_LAPTOP"),
                            rs.getInt("COUNT_LAPTOP"), rs.getString("DESCRIPTION_LAPTOP"), rs.getString("MANUFACTURER_LAPTOP"),
                            rs.getDouble("DIAGONAL_LAPTOP"), rs.getString("PROCESSOR_LAPTOP"), rs.getInt("COUNT_CORE_LAPTOP"),
                            rs.getInt("VALUME_LAPTOP"), rs.getInt("HDD_LAPTOP")));
                showTableLaptops(laptops);
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("SQLException");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }
    @FXML
    private void handleSerchProcesoors(){

        if(getQueryProcessors() != null) {
            ObservableList<Processor> processors = FXCollections.observableArrayList();
            try (ResultSet rs = connectionJDBC.query(getQueryProcessors())) {
                while (rs.next())
                    processors.add(new Processor(rs.getInt("ID_PROCESSOR"), rs.getInt("ID_CATEGORY"), rs.getString("NAME_PROCESS"), rs.getDouble("PRICE_PROCESSOR"),
                            rs.getInt("COUNT_PROCESSOR"), rs.getString("DESCRIPTION_PROCESSOR"), rs.getString("MANUFACTURER"),
                            rs.getString("FAMILY_PROCESSOR"), rs.getString("SOCKET"), rs.getInt("CORES"), rs.getDouble("FREQUENCY_PROCESSOR")));
                showTableProcessors(processors);
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("SQLException");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }
    @FXML
    private void handleSerchVideoCard() {

        if (getQueryVideoCard()!=null) {
            ObservableList<VideoCard> videoCards = FXCollections.observableArrayList();
            try (ResultSet rs = connectionJDBC.query(getQueryVideoCard())) {
                while (rs.next())
                    videoCards.add(new VideoCard(rs.getInt("ID_VIDEO_CARD"), rs.getInt("ID_CATEGORY"), rs.getString("NAME_VIDEO_CARD"), rs.getDouble("PRICE_VIDEO_CARD"),
                            rs.getInt("COUNT_VIDEO_CARD"), rs.getString("DISCRIPTION_VIDEO_CARD"), rs.getString("MANUFACTURER_VIDEO_CARD"),
                            rs.getInt("VALUME_VIDEO_CARD"), rs.getString("TYPE_VIDEO_CARD"), rs.getInt("TIRE_VIDEO_CARD")));
                showTableVideoCards(videoCards);
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("SQLException");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }
    @FXML
    private void handleSerchMemoryModuls() {

        if(getQueryMemoryModuls() != null) {
            ObservableList<MemoryModule> memoryModules = FXCollections.observableArrayList();
            try (ResultSet rs = connectionJDBC.query(getQueryMemoryModuls())) {
                while (rs.next())
                    memoryModules.add(new MemoryModule(rs.getInt("ID_MEMORY"), rs.getInt("ID_CATEGORY"), rs.getString("NAME_MEMORY"), rs.getDouble("PRICE_MEMORY"),
                            rs.getInt("COUNT_MEMORY"), rs.getString("DESCRIPTION_MEMORY"), rs.getString("MANUFACTURER_MEMORY"),
                            rs.getInt("VOLUME_MEMORY"), rs.getString("TYPE_MEMORY")));
                showTableMemoryModuls(memoryModules);
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("SQLException");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }
    @FXML
    private void handleSerchHDD() {

        if(getQueryHDD()!=null) {
            ObservableList<HardDrive> hardDrives = FXCollections.observableArrayList();
            try (ResultSet rs = connectionJDBC.query(getQueryHDD())) {
                while (rs.next())
                    hardDrives.add(new HardDrive(rs.getInt("ID_DRIVE"), rs.getInt("ID_CATEGORY"), rs.getString("NAME_DRIVE"), rs.getDouble("PRICE_DRIVE"),
                            rs.getInt("COUNT_DRIVE"), rs.getString("DESCRIPTION_DRIVE"), rs.getString("MANUFACTURER_DRIVE"),
                            rs.getInt("VALUME_DRIVE"), rs.getDouble("FORMAN_DRIVE")));
                showTableHDD(hardDrives);
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("SQLException");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }

    //метод для подстановки правильного условия в запрос
    private String OrAnd(boolean flag){
        String res;
        if (flag) res = " OR ";
        else res = " AND (";
        return res;
    }

    public String getQueryComputers() {
        StringBuilder query = new StringBuilder("SELECT * FROM COMPUTERS WHERE 1=1");
        boolean flag=false; //указывает было ли добавленно условие

        //Проверка чекбоксов и составление запроса
        if(check_AMD10.isSelected()) {
            query.append(OrAnd(flag));
            query.append("PROCESSOR_COMPUTER LIKE \'%A10%\'");
            flag=true;
        }
        if(check_AMD6.isSelected()) {
            query.append(OrAnd(flag));
            query.append("PROCESSOR_COMPUTER LIKE \'%A6%\'");
            flag = true;
        }
        if(check_Celeron.isSelected()) {
            query.append(OrAnd(flag));
            query.append("PROCESSOR_COMPUTER LIKE \'%Celeron%\'");
            flag=true;
        }
        if(check_i5.isSelected()) {
            query.append(OrAnd(flag));
            query.append("PROCESSOR_COMPUTER LIKE \'%i5%\'");
            flag=true;
        }
        if(check_i3.isSelected()) {
            query.append(OrAnd(flag));
            query.append("PROCESSOR_COMPUTER LIKE \'%i3%\'");
            flag=true;
        }
        if(check_i7.isSelected()) {
            query.append(OrAnd(flag));
            query.append("PROCESSOR_COMPUTER LIKE \'%i7%\'");
            flag=true;
        }
        if(flag) query.append(")");

        //Проверка введеных данных
        if(!text_CounCore.getText().isEmpty()) {
            if(tryParseInt(text_CounCore.getText()))
                query.append(" AND COUNT_CORE_COMPUTER = " +text_CounCore.getText());
            else return null;
        }

        if(!text_ValumeRAM.getText().isEmpty()) {
            if(tryParseInt(text_ValumeRAM.getText()))
                query.append(" AND VALUME_COMPUTER = " + text_ValumeRAM.getText());
            else return null;
        }

        if(!text_ValumeHDD.getText().isEmpty()) {
            if(tryParseInt(text_ValumeHDD.getText()))
                query.append(" AND HDD_COMPUTER = " +text_ValumeHDD.getText());
            else return null;
        }

        return query.toString();
    }
    public String getQueryLaptops(){
        StringBuilder query = new StringBuilder("SELECT * FROM LAPTOPS WHERE 1=1 ");

        ObservableList<Double> lstDiagonal = lst_diagonalLaptop.getSelectionModel().getSelectedItems();
        ObservableList<String> lstProcessor = lst_processorLaptop.getSelectionModel().getSelectedItems();

        if(lstProcessor.size()!=0) {
            query.append(" AND (");
            int i = 0;
            for (String processor : lstProcessor) {
                query.append("PROCESSOR_LAPTOP LIKE \'%" + processor + "%\'");
                if (i != lstProcessor.size() - 1)
                    query.append(" OR ");
                i++;
            }
            query.append(")");
        }

        if(lstDiagonal.size()!=0) {
            query.append(" AND (");
            int i = 0;
            for (Double diagonal : lstDiagonal) {
                query.append("DIAGONAL_LAPTOP = " + diagonal);
                if (i != lstDiagonal.size() - 1)
                    query.append(" OR ");
                i++;
            }
            query.append(")");
        }

        if(!text_manufacturerLaptop.getText().isEmpty())
            query.append(" AND MANUFACTURER_LAPTOP LIKE \'%"+ text_manufacturerLaptop.getText() +"%\'");

        if(!text_countCoreLaptop.getText().isEmpty())
            if(tryParseInt(text_countCoreLaptop.getText()))
                query.append(" AND COUNT_CORE_LAPTOP = " + text_countCoreLaptop.getText());
            else return null;

        if(!text_valumeLaptop.getText().isEmpty())
            if(tryParseInt(text_valumeLaptop.getText()))
                query.append(" AND VALUME_LAPTOP = " + text_valumeLaptop.getText());
            else return null;

        if(!text_hddLaptop.getText().isEmpty())
            if(tryParseInt(text_hddLaptop.getText()))
                query.append(" AND HDD_LAPTOP = " + text_hddLaptop.getText());
            else return null;

        return query.toString();
    }
    public String getQueryProcessors(){
        StringBuilder query = new StringBuilder("SELECT * FROM PROCESSORS WHERE 1=1 ");
        ObservableList<String> lstProcessors = lst_familyProcessor.getSelectionModel().getSelectedItems();
        ObservableList<String> lstSockets = lst_socketProcessor.getSelectionModel().getSelectedItems();

        if(lstProcessors.size()!=0) {
            query.append(" AND (");
            int i = 0;
            for (String processor : lstProcessors) {
                query.append("FAMILY_PROCESSOR LIKE \'%" + processor + "%\'");
                if (i != lstProcessors.size() - 1)
                    query.append(" OR ");
                i++;
            }
            query.append(")");
        }

        if(lstSockets.size()!=0) {
            query.append(" AND (");
            int i = 0;
            for (String socket : lstSockets) {
                query.append("SOCKET LIKE \'%" + socket + "%\'");
                if (i != lstSockets.size() - 1)
                    query.append(" OR ");
                i++;
            }
            query.append(")");
        }

        if(!text_manufacturerProcessor.getText().isEmpty())
            query.append(" AND MANUFACTURER LIKE \'%"+text_manufacturerProcessor.getText()+"%\'");

        if(!text_countCoreProcessor.getText().isEmpty())
        {
            if(tryParseInt(text_countCoreProcessor.getText()))
                query.append(" AND CORES = "+ text_countCoreProcessor.getText());
            else return null;
        }

        boolean flag = false;

        if(frequency1.isSelected()) {
            query.append(OrAnd(flag));
            query.append("FREQUENCY_PROCESSOR BETWEEN 2.0 AND 2.9");
            flag=true;
        }

        if(frequency2.isSelected()) {
            query.append(OrAnd(flag));
            query.append("FREQUENCY_PROCESSOR BETWEEN 3.0 AND 3.9");
            flag=true;
        }

        if(frequency3.isSelected()) {
            query.append(OrAnd(flag));
            query.append("FREQUENCY_PROCESSOR BETWEEN 3.3 AND 3.7");
            flag=true;
        }

        if(frequency4.isSelected()) {
            query.append(OrAnd(flag));
            query.append("FREQUENCY_PROCESSOR >= 4.0");
            flag=true;
        }

        if(flag) query.append(")");

        return query.toString();
    }
    public String getQueryHDD(){
        StringBuilder query = new StringBuilder("SELECT * FROM HARD_DRIVES WHERE 1=1 ");

        if(!text_manufacturerHardDrive.getText().isEmpty()){
            query.append(" AND MANUFACTURER_DRIVE LIKE \'%" + text_manufacturerHardDrive.getText() + "%\'");
        }

        if(!text_valumeHardDrive.getText().isEmpty()){
            if(tryParseInt(text_valumeHardDrive.getText()))
                query.append(" AND VALUME_DRIVE =" + text_valumeHardDrive.getText());
            else return null;
        }

        boolean flag = false;

        if(form_factor1.isSelected()) {
            query.append(OrAnd(flag));
            query.append("FORMAN_DRIVE = 2.5");
            flag=true;
        }

        if(form_factor2.isSelected()) {
            query.append(OrAnd(flag));
            query.append("FORMAN_DRIVE = 3.5");
            flag=true;
        }

        if(flag) query.append(")");

        return query.toString();
    }
    public String getQueryVideoCard(){
        StringBuilder query = new StringBuilder("SELECT * FROM VIDEO_CARDS WHERE 1=1 ");
        ObservableList<Integer> lstTire = lst_tireVideoCard.getSelectionModel().getSelectedItems();

        if(!text_manufacturerVideoCard.getText().isEmpty())
            query.append(" AND MANUFACTURER_VIDEO_CARD LIKE \'%"+text_manufacturerVideoCard.getText()+"%\'");

        boolean flag = false;

        if(valumeVideoCard1.isSelected()) {
            query.append(OrAnd(flag));
            query.append("VALUME_VIDEO_CARD = 1");
            flag=true;
        }

        if(valumeVideoCard2.isSelected()) {
            query.append(OrAnd(flag));
            query.append("VALUME_VIDEO_CARD = 2");
            flag=true;
        }

        if(valumeVideoCard3.isSelected()) {
            query.append(OrAnd(flag));
            query.append("VALUME_VIDEO_CARD = 3");
            flag=true;
        }

        if(flag) query.append(")");

        flag = false;

        if(typeVideoCard1.isSelected()) {
            query.append(OrAnd(flag));
            query.append("TYPE_VIDEO_CARD LIKE \'%GDDR3%\'");
            flag=true;
        }

        if(typeVideoCard2.isSelected()) {
            query.append(OrAnd(flag));
            query.append("TYPE_VIDEO_CARD LIKE \'%GDDR5%\'");
            flag=true;
        }

        if(flag) query.append(")");

        if(lstTire.size()!=0) {
            query.append(" AND (");
            int i = 0;
            for (Integer tire : lstTire) {
                query.append("TIRE_VIDEO_CARD =" + tire);
                if (i != lstTire.size() - 1)
                    query.append(" OR ");
                i++;
            }
            query.append(")");
        }

        return query.toString();
    }
    public String getQueryMemoryModuls(){
        StringBuilder query = new StringBuilder("SELECT * FROM MEMORY_MODULES WHERE 1=1 ");

        if(!text_manufacturerMemory.getText().isEmpty())
            query.append(" AND MANUFACTURER_MEMORY LIKE \'%"+ text_manufacturerMemory.getText() +"%\'");

        boolean flag = false;

        if(typeMemory1.isSelected()) {
            query.append(OrAnd(flag));
            query.append("TYPE_MEMORY LIKE '%DDR'");
            flag=true;
        }

        if(typeMemory2.isSelected()) {
            query.append(OrAnd(flag));
            query.append("TYPE_MEMORY LIKE '%DDR2%'");
            flag=true;
        }

        if(typeMemory3.isSelected()) {
            query.append(OrAnd(flag));
            query.append("TYPE_MEMORY LIKE '%DDR3%'");
            flag=true;
        }

        if(flag) query.append(")");

        if(!text_valumeMemory.getText().isEmpty())
            if(tryParseInt(text_valumeMemory.getText()))
                query.append(" AND VOLUME_MEMORY = " + text_valumeMemory.getText());
            else return null;

        return query.toString();
    }
    public String getQueryMotherboards(){
        StringBuilder query = new StringBuilder("SELECT * FROM MOTHERBOARDS WHERE 1=1");
        ObservableList<String> sockets=lst_socketProcessorsMotherboards.getSelectionModel().getSelectedItems();

        if(!text_manufacturerMotherboard.getText().isEmpty())
            query.append(" AND MANUFACTURER_MOTHERBOARD LIKE \'%"+text_manufacturerMotherboard.getText()+"%\'");

        boolean flag = false;

        if(formFactor1.isSelected()) {
            query.append(OrAnd(flag));
            query.append("FORMFACTOR_MOTHERBOARD LIKE \'ATX\'");
            flag=true;
        }

        if(formFactor2.isSelected()) {
            query.append(OrAnd(flag));
            query.append("FORMFACTOR_MOTHERBOARD LIKE \'mATX\'");
            flag=true;
        }

        if(formFactor3.isSelected()) {
            query.append(OrAnd(flag));
            query.append("FORMFACTOR_MOTHERBOARD LIKE \'mITX\'");
            flag=true;
        }

        if(flag) query.append(")");

        flag = false;

        if(countSlotMemory1.isSelected()) {
            query.append(OrAnd(flag));
            query.append("COUNT_MEMORY_SLOT = 2");
            flag=true;
        }

        if(countSlotMemory2.isSelected()) {
            query.append(OrAnd(flag));
            query.append("COUNT_MEMORY_SLOT = 4");
            flag=true;
        }

        if(flag) query.append(")");

        if(sockets.size()!=0) {
            query.append(" AND (");
            int i = 0;
            for (String socket : sockets) {
                query.append("SOCKET_MOTHERBOARD LIKE \'%" + socket +"%\'");
                if (i != sockets.size() - 1)
                    query.append(" OR ");
                i++;
            }
            query.append(")");
        }

        return query.toString();
    }

    public void setMain(Main main){
        this.main=main;
    }

    @FXML
    private void handleInsertProduct(){
        mainForm = main.getStage();
        mainForm.setTitle("Добавление товара");
        mainForm.setResizable(false);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/NewProduct.fxml"));
        try {
            Pane mainPane = (Pane) loader.load();
            Scene mainScence = new Scene(mainPane);
            mainForm.setScene(mainScence);

            newProductController = loader.getController();
            newProductController.setMain(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleDeleteProduct() {
        if(tableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Выберите товар");
            alert.setHeaderText(null);
            alert.setContentText("Выберите товар для удаления");

            alert.showAndWait();
        }
        else {
            Product product = (Product) tableView.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Удаление");
            alert.setHeaderText("Удаление товара");
            alert.setContentText("Вы уверены, что хотите удалить товар под названием: \n" + product.getName() + " ?");
            ButtonType buttonTypeDelete = new ButtonType("Удалить");
            ButtonType buttonTypeClose = new ButtonType("Отмена");
            alert.getButtonTypes().setAll(buttonTypeDelete, buttonTypeClose);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeDelete)
                deleteProduct(product);
            else alert.close();
        }
    }
    @FXML
    private void handleEditProduct(){
        if(tableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Выберите товар");
            alert.setHeaderText(null);
            alert.setContentText("Выберите товар для редактирования");

            alert.showAndWait();
        }
        else {
            Product product = (Product) tableView.getSelectionModel().getSelectedItem();
            tableView.getItems().removeAll(product);
            editProductController = new EditProductController(product);
            editProductController.show();
            tableView.getItems().add(product);
        }
    }
    @FXML
    private void handleClose(){
        connectionJDBC.close();
        mainForm = main.getStage();
        mainForm.close();
    }
    @FXML
    private void enabledButtons() {
        buttonDelete.setDisable(false);
        buttonEdit.setDisable(false);
    }
    @FXML
    private void disabledButtons() {
        buttonDelete.setDisable(true);
        buttonEdit.setDisable(true);
    }

    public void deleteProduct(Product product){
        try{
            if(product.getIdCategory() == 1)
                connectionJDBC.deleteComputer(product.getId());
            if(product.getIdCategory() == 2)
                connectionJDBC.deleteLaptop(product.getId());
            if(product.getIdCategory() == 3)
                connectionJDBC.deleteProcessor(product.getId());
            if(product.getIdCategory() == 4)
                connectionJDBC.deleteMotherboard(product.getId());
            if(product.getIdCategory() == 5)
                connectionJDBC.deleteMemoryModule(product.getId());
            if(product.getIdCategory() == 6)
                connectionJDBC.deleteHDD(product.getId());
            if(product.getIdCategory() == 7)
                connectionJDBC.deleteVideoCard(product.getId());
            tableView.getItems().removeAll(product);
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
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
