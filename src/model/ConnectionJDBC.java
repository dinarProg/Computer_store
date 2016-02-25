package electronics_store.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by Администратор on 01.07.2015.
 */
public class ConnectionJDBC {
    private Connection connect;
    private ObservableList<Computer> computers;
    private ObservableList<Laptop> laptops;
    private ObservableList<Processor> processors;
    private ObservableList<Motherboard> motherboards;
    private ObservableList<MemoryModule> memoryModules;
    private ObservableList<HardDrive> hardDrives;
    private ObservableList<VideoCard> videoCards;
    private ObservableList<Order> orders;
    private ObservableList<Product> products;

    //инициализация соединения и заполнение массивов данными из БД
    public boolean init(String name, String password , String url) {
        if (connect == null) {
            try {
                Properties props = new Properties();
                File currentDir = new File(".");
                String sDirSeparator = System.getProperty("file.separator");
                String sFileName = "conf\\Connection.properties";
                String sFilePath = currentDir.getCanonicalPath() + sDirSeparator + sFileName;
                props.load(new FileInputStream(sFilePath));

                Class.forName(props.getProperty("DRIVER"));  //регистрация драйвера
                Locale loc = Locale.getDefault();
                Locale.setDefault(Locale.ENGLISH);
                //connect = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "SYSTEM", "123");//соединение с БД
                connect = DriverManager.getConnection(url, name, password);
                Locale.setDefault(loc);
                return true;
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка при подключении");
                alert.setHeaderText("Проверьте имя пользователя и пароль");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                return false;
            } catch (ClassNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Не найден драйвер");
                alert.setHeaderText("Не найден драйвер");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                return false;
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка при подключении");
                alert.setHeaderText("Не найден файл: Connection.properties");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                return false;
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Необработанное исключение");
                alert.setHeaderText("Возникла ошибка");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                return false;
            }
        } return false;
    }

    public void init() {
        if (connect == null) {
            try {
                Properties props = new Properties();
                File currentDir = new File(".");
                String sDirSeparator = System.getProperty("file.separator");
                String sFileName = "conf\\Connection.properties";
                String sFilePath = currentDir.getCanonicalPath() + sDirSeparator + sFileName;
                props.load(new FileInputStream(sFilePath));

                Class.forName(props.getProperty("DRIVER"));  //регистрация драйвера
                Locale loc = Locale.getDefault();
                Locale.setDefault(Locale.ENGLISH);
                //connect = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "SYSTEM", "123");//соединение с БД
                connect = DriverManager.getConnection(props.getProperty("URL"), props.getProperty("USER"), props.getProperty("PASSWORD"));
                Locale.setDefault(loc);
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка при подключении");
                alert.setHeaderText("Проверьте имя пользователя и пароль");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                return;
            } catch (ClassNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Не найден драйвер");
                alert.setHeaderText("Не найден драйвер (oracle.jdbc.driver.OracleDriver) ");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                return;
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка при подключении");
                alert.setHeaderText("Не найден файл: Connection.properties");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                return;
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Необработанное исключение");
                alert.setHeaderText("Возникла ошибка");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }

    private void initTableOrders() {
        orders = FXCollections.observableArrayList();;
        try (PreparedStatement prstmt=connect.prepareStatement("SELECT * FROM ORDERS");
             ResultSet rs=prstmt.executeQuery())
        {
            Order order;
            while (rs.next()){
                order = new Order(rs.getInt("ID_ORDER"), rs.getInt("ID_PRODUCT"),rs.getString("FERST_NAME"),rs.getString("SECOND_NAME"),rs.getString("ADRESS"),
                        rs.getString("PHONE"));
                orders.add(order);
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setHeaderText("Возникла ошибка при получении заказов");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void initTableProducts() {
        products=FXCollections.observableArrayList();
        try {
            for (Computer computer : getComputers())
                products.add(computer);

            for (Laptop laptop : getLaptops())
                products.add(laptop);

            for (Processor processor : getProcessors())
                products.add(processor);

            for (Motherboard motherboard : getMotherboards())
                products.add(motherboard);

            for (MemoryModule memoryModule : getMemoryModules())
                products.add(memoryModule);

            for (HardDrive hardDrive : getHardDrives())
                products.add(hardDrive);

            for (VideoCard videoCard : getVideoCards())
                products.add(videoCard);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void initTableMemoryModules() {
        memoryModules = FXCollections.observableArrayList();;
        try (PreparedStatement prstmt=connect.prepareStatement("SELECT * FROM MEMORY_MODULES");
             ResultSet rs=prstmt.executeQuery())
        {
            MemoryModule memoryModule;
            while (rs.next()){
                memoryModule = new MemoryModule(rs.getInt("ID_MEMORY"),rs.getInt("ID_CATEGORY"),rs.getString("NAME_MEMORY"),rs.getDouble("PRICE_MEMORY"),
                        rs.getInt("COUNT_MEMORY"),rs.getString("DESCRIPTION_MEMORY"), rs.getString("MANUFACTURER_MEMORY"),
                        rs.getInt("VOLUME_MEMORY"),rs.getString("TYPE_MEMORY"));
                memoryModules.add(memoryModule);
            }

        }catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setHeaderText("Возникла ошибка при получении значений модулей памяти");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void initTableVideoCards() {
        videoCards = FXCollections.observableArrayList();
        try (PreparedStatement prstmt=connect.prepareStatement("SELECT * FROM VIDEO_CARDS");
             ResultSet rs=prstmt.executeQuery())
        {
            VideoCard videoCard;
            while (rs.next()){
                videoCard = new VideoCard(rs.getInt("ID_VIDEO_CARD"),rs.getInt("ID_CATEGORY"),rs.getString("NAME_VIDEO_CARD"),rs.getDouble("PRICE_VIDEO_CARD"),
                        rs.getInt("COUNT_VIDEO_CARD"),rs.getString("DISCRIPTION_VIDEO_CARD"), rs.getString("MANUFACTURER_VIDEO_CARD"),
                        rs.getInt("VALUME_VIDEO_CARD"),rs.getString("TYPE_VIDEO_CARD"),rs.getInt("TIRE_VIDEO_CARD"));
                videoCards.add(videoCard);
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setHeaderText("Возникла ошибка при получении таблицы видеокарт");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void initTableMotherboards() {
        motherboards = FXCollections.observableArrayList();;
        try (PreparedStatement prstmt=connect.prepareStatement("SELECT * FROM MOTHERBOARDS");
             ResultSet rs=prstmt.executeQuery())
        {
            Motherboard motherboard;
            while (rs.next()){
                motherboard = new Motherboard(rs.getInt("ID_MOTHERBOARD"),rs.getInt("ID_CATEGORY"),rs.getString("NAME_MOTHERBOARD"),rs.getDouble("PRICE_MOTHERBOARD"),
                        rs.getInt("COUNT_MOTHERBOARD"),rs.getString("DESCRIPTION_MOTHERBOARD"), rs.getString("MANUFACTURER_MOTHERBOARD"),
                        rs.getString("FORMFACTOR_MOTHERBOARD"),rs.getString("SOCKET_MOTHERBOARD"), rs.getInt("COUNT_MEMORY_SLOT"));
                motherboards.add(motherboard);
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setHeaderText("Возникла ошибка при получении таблицы материнских плат");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void initTableHardDrives() {
        hardDrives = FXCollections.observableArrayList();;
        try (PreparedStatement prstmt=connect.prepareStatement("SELECT * FROM HARD_DRIVES");
             ResultSet rs=prstmt.executeQuery())
        {
            HardDrive hardDrive;
            while (rs.next()){
                hardDrive = new HardDrive(rs.getInt("ID_DRIVE"),rs.getInt("ID_CATEGORY"),rs.getString("NAME_DRIVE"),rs.getDouble("PRICE_DRIVE"),
                        rs.getInt("COUNT_DRIVE"),rs.getString("DESCRIPTION_DRIVE"), rs.getString("MANUFACTURER_DRIVE"),
                        rs.getInt("VALUME_DRIVE"),rs.getDouble("FORMAN_DRIVE"));
                hardDrives.add(hardDrive);
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setHeaderText("Возникла ошибка при получении таблицы ЖД");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void initTableProcessors() {
        processors = FXCollections.observableArrayList();;
        try (PreparedStatement prstmt=connect.prepareStatement("SELECT * FROM PROCESSORS");
             ResultSet rs=prstmt.executeQuery())
        {
            Processor processor;
            while (rs.next()){
                processor = new Processor(rs.getInt("ID_PROCESSOR"),rs.getInt("ID_CATEGORY"),rs.getString("NAME_PROCESS"),rs.getDouble("PRICE_PROCESSOR"),
                        rs.getInt("COUNT_PROCESSOR"),rs.getString("DESCRIPTION_PROCESSOR"), rs.getString("MANUFACTURER"),
                        rs.getString("FAMILY_PROCESSOR"),rs.getString("SOCKET"), rs.getInt("CORES"), rs.getDouble("FREQUENCY_PROCESSOR"));
                processors.add(processor);
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setHeaderText("Возникла ошибка при получении таблицы процессоров");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void initTableLaptops() {
        laptops = FXCollections.observableArrayList();;
        try (PreparedStatement prstmt=connect.prepareStatement("SELECT * FROM LAPTOPS");
             ResultSet rs=prstmt.executeQuery())
        {
            Laptop laptop;
            while (rs.next()){
                laptop = new Laptop(rs.getInt("ID_LAPTOP"),rs.getInt("ID_CATEGORY"),rs.getString("NAME_LAPTOP"),rs.getDouble("PRICE_LAPTOP"),
                        rs.getInt("COUNT_LAPTOP"),rs.getString("DESCRIPTION_LAPTOP"), rs.getString("MANUFACTURER_LAPTOP"),
                        rs.getDouble("DIAGONAL_LAPTOP"),rs.getString("PROCESSOR_LAPTOP"), rs.getInt("COUNT_CORE_LAPTOP"), rs.getInt("VALUME_LAPTOP"), rs.getInt("HDD_LAPTOP"));
                laptops.add(laptop);
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setHeaderText("Возникла ошибка при получении таблицы ноутбуков");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void initTableComputers() {
        computers = FXCollections.observableArrayList();
        try (PreparedStatement prstmt=connect.prepareStatement("SELECT * FROM COMPUTERS");
             ResultSet rs=prstmt.executeQuery())
        {
            Computer computer;
            while (rs.next()){
                computer = new Computer(rs.getInt("ID_COMPUTER"),rs.getInt("ID_CATEGORY"),rs.getString("NAME_COMPUTER"),rs.getDouble("PRICE_COMPUTER"),
                        rs.getInt("COUNT_COMPUTER"),rs.getString("DESCRIPTION_COMPUTER"), rs.getString("MANUFACTURER_COMPUTER"),
                        rs.getString("PROCESSOR_COMPUTER"), rs.getInt("COUNT_CORE_COMPUTER"),rs.getInt("VALUME_COMPUTER"), rs.getInt("HDD_COMPUTER"));
                computers.add(computer);
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setHeaderText("Возникла ошибка при получении таблицы компьютеров");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    //метод для выполнения запросов
    public ResultSet query(String query){
        ResultSet rs = null;
        try {
            Statement stmt= connect.createStatement();  //получение объекта для отправки запросов
            rs = stmt.executeQuery(query);    // получение результата;
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return rs;
    }

    //обработка запросов не возвращающих значение
    public void updateQuery(String query){
        try {
            Statement stmt= connect.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void close(){
        try {
            connect.close();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setHeaderText("Не удается закрыть соединение с БД");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public ObservableList<Computer> getComputers() {
        if(computers==null || computers.isEmpty()) initTableComputers();
        return computers;
    }

    public ObservableList<Laptop> getLaptops() {
        if(laptops==null || laptops.isEmpty()) initTableLaptops();
        return laptops;
    }

    public ObservableList<Processor> getProcessors() {
        if(processors==null || processors.isEmpty()) initTableProcessors();
        return processors;
    }

    public ObservableList<Motherboard> getMotherboards() {
        if(motherboards==null || motherboards.isEmpty()) initTableMotherboards();
        return motherboards;
    }

    public ObservableList<MemoryModule> getMemoryModules() {
        if(memoryModules==null || memoryModules.isEmpty()) initTableMemoryModules();
        return memoryModules;
    }

    public ObservableList<HardDrive> getHardDrives() {
        if(hardDrives==null || hardDrives.isEmpty()) initTableHardDrives();
        return hardDrives;
    }

    public ObservableList<VideoCard> getVideoCards() {
        if(videoCards==null || videoCards.isEmpty()) initTableVideoCards();
        return videoCards;
    }

    public ObservableList<Order> getOrders() {
        if(orders==null || orders.isEmpty()) initTableOrders();
        return orders;
    }

    public ObservableList<Product> getProducts() {
        if(products==null || products.isEmpty()) initTableProducts();
        return products;
    }

    public Connection getConnect(){
        return connect;
    }

    public boolean insertLaptop(String name, String descroption, String manufacter, Double diagonal, String processor,
                                Integer countCore, Integer valume, Integer valumeHDD, Double price, Integer count) {
        try {
            PreparedStatement ins = connect.prepareStatement("INSERT INTO LAPTOPS(ID_CATEGORY, NAME_LAPTOP, DESCRIPTION_LAPTOP," +
                    "MANUFACTURER_LAPTOP, DIAGONAL_LAPTOP, PROCESSOR_LAPTOP, COUNT_CORE_LAPTOP, VALUME_LAPTOP, HDD_LAPTOP, PRICE_LAPTOP," +
                    "COUNT_LAPTOP) VALUES (2,?,?,?,?,?,?,?,?,?,?)");

            ins.setString(1,name);
            ins.setString(2, descroption);
            ins.setString(3, manufacter);
            ins.setString(5, processor);

            if(diagonal != null)
                ins.setDouble(4, diagonal);
            else ins.setString(4, null);

            if(countCore != null)
                ins.setInt(6, countCore);
            else ins.setString(6, null);

            if(valume != null)
                ins.setInt(7, valume);
            else ins.setString(7, null);

            if(valumeHDD != null)
                ins.setInt(8, valumeHDD);
            else ins.setString(8, null);

            if(price != null)
                ins.setDouble(9, price);
            else ins.setString(9, null);

            if(count != null)
                ins.setInt(10, count);
            else ins.setString(10, null);

            return !ins.execute();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
    }

    public boolean insertCoputer(String name, String descroption, String manufacter, String processor, Integer countCore, Integer valume,
                                 Integer valumeHDD, Double price, Integer count) {
        try {
            PreparedStatement ins = connect.prepareStatement("INSERT INTO COMPUTERS(ID_CATEGORY, NAME_COMPUTER, DESCRIPTION_COMPUTER," +
                    "MANUFACTURER_COMPUTER, PROCESSOR_COMPUTER, COUNT_CORE_COMPUTER, VALUME_COMPUTER, HDD_COMPUTER, PRICE_COMPUTER, COUNT_COMPUTER)" +
                    "VALUES (1,?,?,?,?,?,?,?,?,?)");

            ins.setString(1,name);
            ins.setString(2, descroption);
            ins.setString(3, manufacter);
            ins.setString(4, processor);

            if(countCore != null)
                ins.setInt(5, countCore);
            else ins.setString(5, null);

            if(valume != null)
                ins.setInt(6, valume);
            else ins.setString(6, null);

            if(valumeHDD != null)
                ins.setInt(7, valumeHDD);
            else ins.setString(7, null);

            if(price != null)
                ins.setDouble(8, price);
            else ins.setString(8, null);

            if(count != null)
                ins.setInt(9, count);
            else ins.setString(9, null);

            return !ins.execute();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
    }

    public boolean insertProcessor(String name, String descroption, String manufacter, String familyProcessor, String socket,
                                   Integer countCore, Double frequency, Double price, Integer count) {
        try {
            PreparedStatement ins = connect.prepareStatement("INSERT INTO PROCESSORS(ID_CATEGORY, NAME_PROCESS, DESCRIPTION_PROCESSOR," +
                    "MANUFACTURER, FAMILY_PROCESSOR, SOCKET, CORES, FREQUENCY_PROCESSOR, PRICE_PROCESSOR, COUNT_PROCESSOR)" +
                    "VALUES (3,?,?,?,?,?,?,?,?,?)");

            ins.setString(1,name);
            ins.setString(2, descroption);
            ins.setString(3, manufacter);
            ins.setString(4, familyProcessor);
            ins.setString(5, socket);

            if(countCore != null)
                ins.setInt(6, countCore);
            else ins.setString(6, null);

            if(frequency != null)
                ins.setDouble(7, frequency);
            else ins.setString(7, null);

            if(price != null)
                ins.setDouble(8, price);
            else ins.setString(8, null);

            if(count != null)
                ins.setInt(9, count);
            else ins.setString(9, null);

            return !ins.execute();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
    }

    public boolean insertMotherboard(String name, String descroption, String manufacter, String formfactor, String socket,
                                     Integer countMemorySlot, Double price, Integer count) {
        try {
            PreparedStatement ins = connect.prepareStatement("INSERT INTO MOTHERBOARDS(ID_CATEGORY, NAME_MOTHERBOARD, DESCRIPTION_MOTHERBOARD," +
                    "MANUFACTURER_MOTHERBOARD, FORMFACTOR_MOTHERBOARD, SOCKET_MOTHERBOARD, COUNT_MEMORY_SLOT, PRICE_MOTHERBOARD," +
                    "COUNT_MOTHERBOARD) VALUES (4,?,?,?,?,?,?,?,?)");

            ins.setString(1,name);
            ins.setString(2, descroption);
            ins.setString(3, manufacter);
            ins.setString(4, formfactor);
            ins.setString(5, socket);

            if(countMemorySlot != null)
                ins.setInt(6, countMemorySlot);
            else ins.setString(6, null);

            if(price != null)
                ins.setDouble(7, price);
            else ins.setString(7, null);

            if(count != null)
                ins.setInt(8, count);
            else ins.setString(8, null);

            return !ins.execute();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
    }

    public boolean insertHDD(String name, String descroption, String manufacter, Integer valume,
                                        Double formatDrive, Double price, Integer count) {
        try {
            PreparedStatement ins = connect.prepareStatement("INSERT INTO HARD_DRIVES(ID_CATEGORY, NAME_DRIVE, DESCRIPTION_DRIVE," +
                    "MANUFACTURER_DRIVE, VALUME_DRIVE, FORMAN_DRIVE, PRICE_DRIVE, COUNT_DRIVE)" +
                    "VALUES (6,?,?,?,?,?,?,?)");

            ins.setString(1,name);
            ins.setString(2, descroption);
            ins.setString(3, manufacter);

            if(valume != null)
                ins.setInt(4, valume);
            else ins.setString(4, null);

            if(formatDrive != null)
                ins.setDouble(5, formatDrive);
            else ins.setString(5, null);

            if(price != null)
                ins.setDouble(6, price);
            else ins.setString(6, null);

            if(count != null)
                ins.setInt(7, count);
            else ins.setString(7, null);

            return !ins.execute();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
    }

    public boolean insertMemoryModul(String name, String descroption, String manufacter, Integer valume,
                             String typeMemory, Double price, Integer count) {
        try {
            PreparedStatement ins = connect.prepareStatement("INSERT INTO MEMORY_MODULES(ID_CATEGORY, NAME_MEMORY, DESCRIPTION_MEMORY," +
                    "MANUFACTURER_MEMORY, VOLUME_MEMORY, TYPE_MEMORY, PRICE_MEMORY, COUNT_MEMORY) VALUES (5,?,?,?,?,?,?,?)");

            ins.setString(1,name);
            ins.setString(2, descroption);
            ins.setString(3, manufacter);
            ins.setString(5, typeMemory);

            if(valume != null)
                ins.setInt(4, valume);
            else ins.setString(4, null);

            if(price != null)
                ins.setDouble(6, price);
            else ins.setString(6, null);

            if(count != null)
                ins.setInt(7, count);
            else ins.setString(7, null);

            return !ins.execute();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
    }

    public boolean insertVideoCard(String name, String descroption, String manufacter, Integer valume,
                                     String typeMemory, Integer tire, Double price, Integer count) {
        try {
            PreparedStatement ins = connect.prepareStatement("INSERT INTO VIDEO_CARDS(ID_CATEGORY, NAME_VIDEO_CARD, DISCRIPTION_VIDEO_CARD," +
                    "MANUFACTURER_VIDEO_CARD, VALUME_VIDEO_CARD, TYPE_VIDEO_CARD, TIRE_VIDEO_CARD, PRICE_VIDEO_CARD," +
                    "COUNT_VIDEO_CARD) VALUES (7,?,?,?,?,?,?,?,?)");

            ins.setString(1,name);
            ins.setString(2, descroption);
            ins.setString(3, manufacter);
            ins.setString(5, typeMemory);

            if(valume != null)
                ins.setInt(4, valume);
            else ins.setString(4, null);

            if(tire != null)
                ins.setInt(6, tire);
            else ins.setString(6, null);

            if(price != null)
                ins.setDouble(7, price);
            else ins.setString(7, null);

            if(count != null)
                ins.setInt(8, count);
            else ins.setString(8, null);

            return !ins.execute();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        return false;
    }

    public void deleteComputer(int id){
        try {
            PreparedStatement ins = connect.prepareStatement("DELETE FROM COMPUTERS WHERE ID_COMPUTER = ?");
            ins.setInt(1, id);
            ins.executeUpdate();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void deleteLaptop(int id){
        try {
            PreparedStatement ins = connect.prepareStatement("DELETE FROM LAPTOPS WHERE ID_LAPTOP = ?");
            ins.setInt(1, id);
            ins.executeUpdate();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void deleteProcessor(int id){
        try {
            PreparedStatement ins = connect.prepareStatement("DELETE FROM PROCESSORS WHERE ID_PROCESSOR = ?");
            ins.setInt(1, id);
            ins.executeUpdate();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void deleteMotherboard(int id){
        try {
            PreparedStatement ins = connect.prepareStatement("DELETE FROM MOTHERBOARDS WHERE ID_MOTHERBOARD = ?");
            ins.setInt(1, id);
            ins.executeUpdate();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void deleteMemoryModule(int id){
        try {
            PreparedStatement ins = connect.prepareStatement("DELETE FROM MEMORY_MODULES WHERE ID_MEMORY = ?");
            ins.setInt(1, id);
            ins.executeUpdate();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void deleteHDD(int id){
        try {
            PreparedStatement ins = connect.prepareStatement("DELETE FROM HARD_DRIVES WHERE ID_DRIVE = ?");
            ins.setInt(1, id);
            ins.executeUpdate();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void deleteVideoCard(int id){
        try {
            PreparedStatement ins = connect.prepareStatement("DELETE FROM VIDEO_CARDS WHERE ID_VIDEO_CARD =  ?");
            ins.setInt(1, id);
            ins.executeUpdate();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка SQL запроса");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Необработанное исключение");
            alert.setHeaderText("Возникла ошибка");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
