package electronics_store.view;

import electronics_store.Main;
import electronics_store.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Владимир on 19.07.2015.
 */
public class EditProductController {
    @FXML
    HBox mainBox=new HBox();

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

    private ConnectionJDBC connectionJDBC = new ConnectionJDBC();
    private Stage mainForm;
    private Product product;

    public EditProductController(Product product){
        this.product=product;
    }

    @FXML
    private void initialize() {
        connectionJDBC.init();
        chbx_DiagonalLaptop.getItems().addAll(10.1, 13.3, 14.0, 15.6, 17.3);
        chbx_FormFactors.getItems().addAll("ATX", "mATX", "mITX");
        chbx_TireVideoCard.getItems().addAll(64, 128, 256, 384);
        chbx_FormanDrive.getItems().addAll(2.5, 3.5);
        gridProducts.setPadding(new Insets(10, 10, 10, 10));
        gridProducts.setVgap(10);
        gridProducts.setHgap(10);
        if (product != null) {
            text_Name.setText(product.getName());
            text_Description.setText(product.getDescription());
            text_Manufacturer.setText(product.getManufacturer());
            text_Price.setText(""+product.getPrice());
            text_Count.setText(""+product.getCount());
            getGridPane();
        }
    }

    private void getGridPane(){
        switch (product.getIdCategory()){
            case 1: mainBox.getChildren().add(initGridComputers()); break;
            case 2: mainBox.getChildren().add(initGridLaptops()); break;
            case 3: mainBox.getChildren().add(initGridProcessors()); break;
            case 4: mainBox.getChildren().add(initGridMotherboards()); break;
            case 5: mainBox.getChildren().add(initGridMemoryModuls()); break;
            case 6: mainBox.getChildren().add(initGridHDD()); break;
            case 7: mainBox.getChildren().add(initGridVideoCards()); break;
        }
    }

    private GridPane initGridComputers() {
        if(gridProducts.getChildren().size() != 0) gridProducts.getChildren().clear();
        Computer computer = (Computer) product;
        text_ProcessorComputer.setText(computer.getProcessorComputer());
        text_CountCoreComputer.setText(""+computer.getCountCoreComputer());
        text_ValumeComputer.setText("" + computer.getValumeComputer());
        text_HddComputer.setText("" + computer.getHddComputer());

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
        Laptop laptop = (Laptop) product;
        text_ProcessorLaptop.setText(laptop.getProcessorLaptop());
        text_CountCoreLaptop.setText(""+laptop.getCountCoreLaptop());
        text_ValumeLaptop.setText(""+laptop.getValumeLaptop());
        text_HddLaptop.setText(""+laptop.getHddLaptop());

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
        Processor processor = (Processor) product;
        text_FamilyProcessor.setText(processor.getFamilyProcessor());
        text_SocketProcessor.setText(processor.getSocket());
        text_CoresProcessor.setText(""+processor.getCores());
        text_FrequencyProcessor.setText(""+processor.getFrequencyProcessor());

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
        Motherboard motherboard = (Motherboard) product;
        text_SocketMotherboard.setText(motherboard.getSocket());
        text_CountMemorySlot.setText(""+motherboard.getCountMemorySlot());

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
        HardDrive hardDrive = (HardDrive) product;
        text_ValumeDrive.setText(""+hardDrive.getValumeDrive());

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
        MemoryModule memoryModule=(MemoryModule) product;
        text_VolumeMemory.setText("" + memoryModule.getVolumeMemory());
        text_TypeMemory.setText(memoryModule.getTypeMemory());

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
        VideoCard videoCard = (VideoCard) product;
        text_ValumeVideoCard.setText(""+videoCard.getValumeVideoCard());
        text_TypeVideoCard.setText(videoCard.getTypeVideoCard());

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

    private void handleClose(){
        mainForm.close();
    }

    private void handleUpdateProduct(){
        if(mainBox.getChildren().size()> 0) {
            switch (product.getIdCategory()) {
                case 1: {
                    if(updateCoputer()){
                        showSuccessAlert();
                        handleClose();
                    }
                    break;
                }
                case 2:
                    if(updateLaptop()) {
                        showSuccessAlert();
                        handleClose();
                    }
                    break;
                case 3:
                    if(updateProcessor()){
                        showSuccessAlert();
                        handleClose();
                    }
                    break;
                case 4:
                    if(updateMotherboard()){
                        showSuccessAlert();
                        handleClose();
                    }
                    break;
                case 6:
                    if(updateHDD()){
                        showSuccessAlert();
                        handleClose();
                    }
                    break;
                case 5:
                    if(updateMemoryModul()){
                        showSuccessAlert();
                        handleClose();
                    }
                    break;
                case 7:
                    if(updateVideoCard()){
                        showSuccessAlert();
                        handleClose();
                    }
                    break;
            }
        }
    }

    public boolean updateCoputer() {
        StringBuilder query = new StringBuilder("UPDATE COMPUTERS SET ");
        String name = null, description = null, processor = null, manufactor = null;

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
        query.append("NAME_COMPUTER = \'" + name + "\', DESCRIPTION_COMPUTER =\'" + description + "\'," +
                " MANUFACTURER_COMPUTER = \'" + manufactor + "\', PROCESSOR_COMPUTER = \'" + processor + "\',");

        if (!text_CountCoreComputer.getText().isEmpty())
            if(tryParseInt(text_CountCoreComputer.getText()))
                query.append(" COUNT_CORE_COMPUTER = "+text_CountCoreComputer.getText() + ",");
            else return false;

        if (!text_ValumeComputer.getText().isEmpty())
            if(tryParseInt(text_ValumeComputer.getText()))
                query.append(" VALUME_COMPUTER = "+ text_ValumeComputer.getText() + ",");
            else return false;

        if (!text_HddComputer.getText().isEmpty())
            if (tryParseInt(text_HddComputer.getText()))
                query.append(" HDD_COMPUTER = " + text_HddComputer.getText() + ",");
            else return false;

        if(checkPriceAndCount())
            query.append("PRICE_COMPUTER = "+text_Price.getText() + ", COUNT_COMPUTER = " + text_Count.getText());
        else return false;

        query.append(" WHERE ID_COMPUTER = " + product.getId());

        connectionJDBC.updateQuery(query.toString());
        return true;
    }

    public boolean updateLaptop(){
        StringBuilder query = new StringBuilder("UPDATE LAPTOPS SET ");
        String name = null, description = null, processor = null, manufactor = null;

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

        query.append("NAME_LAPTOP = \'" + name + "\',DESCRIPTION_LAPTOP = \'" + description + "\'," +
                "MANUFACTURER_LAPTOP = \'" + manufactor + "\', DIAGONAL_LAPTOP = " + chbx_DiagonalLaptop.getValue()
                + ",PROCESSOR_LAPTOP = \'" + processor + "\',");

        if(!text_CountCoreLaptop.getText().isEmpty())
            if(tryParseInt(text_CountCoreLaptop.getText()))
                query.append("COUNT_CORE_LAPTOP = "+text_CountCoreLaptop.getText() + ",");
            else return false;

        if(!text_ValumeLaptop.getText().isEmpty())
            if(tryParseInt(text_ValumeLaptop.getText()))
                query.append("VALUME_LAPTOP = "+text_ValumeLaptop.getText() + ",");
            else return false;

        if(!text_HddLaptop.getText().isEmpty())
            if (tryParseInt(text_HddLaptop.getText()))
                query.append("HDD_LAPTOP = "+text_HddLaptop.getText() + ",");
            else return false;

        if(checkPriceAndCount())
            query.append("PRICE_LAPTOP = "+text_Price.getText() + ",COUNT_LAPTOP = " + text_Count.getText());
        else return false;

        query.append(" WHERE ID_LAPTOP = "+ product.getId());

        connectionJDBC.updateQuery(query.toString());
        return true;
    }

    public boolean updateProcessor() {
        StringBuilder query = new StringBuilder("UPDATE PROCESSORS SET ");
        String name = null, description = null, familyProcessor = null, manufactor = null, socket = null;

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

        query.append("NAME_PROCESS = \'"+name+"\',DESCRIPTION_PROCESSOR = \'"+description+"\',MANUFACTURER = \'"+ manufactor+"\'," +
                "FAMILY_PROCESSOR = \'" +familyProcessor+"\', SOCKET = \'"+socket+"\',");

        if(!text_CoresProcessor.getText().isEmpty())
            if(tryParseInt(text_CoresProcessor.getText()))
                query.append("CORES = "+text_CoresProcessor.getText() + ",");
            else return false;

        if(!text_FrequencyProcessor.getText().isEmpty())
            if(tryParseDouble(text_FrequencyProcessor.getText()))
                query.append("FREQUENCY_PROCESSOR = "+text_FrequencyProcessor.getText() + ",");
            else return false;

        if(checkPriceAndCount())
            query.append("PRICE_PROCESSOR = "+text_Price.getText() + ", COUNT_PROCESSOR = " + text_Count.getText());
        else return false;

        query.append(" WHERE ID_PROCESSOR = "+product.getId());

        connectionJDBC.updateQuery(query.toString());
        return true;
    }

    public boolean updateMotherboard() {
        StringBuilder query = new StringBuilder("UPDATE MOTHERBOARDS SET ");
        String name = null, description = null, manufactor = null, socket = null;

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

        query.append("NAME_MOTHERBOARD = \'"+name+"\',DESCRIPTION_MOTHERBOARD = \'"+description+"\'," +
                "MANUFACTURER_MOTHERBOARD = \'"+ manufactor+"\', FORMFACTOR_MOTHERBOARD = \'" +chbx_FormanDrive.getValue()+"\'," +
                "SOCKET_MOTHERBOARD = \'"+ socket+"\',");

        if(!text_CountMemorySlot.getText().isEmpty())
            if(tryParseInt(text_CountMemorySlot.getText()))
                query.append("COUNT_MEMORY_SLOT = "+text_CountMemorySlot.getText() + ",");
            else return false;

        if(checkPriceAndCount())
            query.append("PRICE_MOTHERBOARD = "+text_Price.getText() + ", COUNT_MOTHERBOARD = " + text_Count.getText());
        else return false;

        query.append(" WHERE ID_MOTHERBOARD = "+product.getId());

        connectionJDBC.updateQuery(query.toString());
        return true;
    }

    public boolean updateHDD() {
        StringBuilder query = new StringBuilder("UPDATE HARD_DRIVES SET ");
        String name = null, description = null, manufactor = null;

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

        query.append("NAME_DRIVE = \'"+name+"\',DESCRIPTION_DRIVE = \'"+description+"\', MANUFACTURER_DRIVE = \'"+ manufactor+"\',");

        if(!text_ValumeDrive.getText().isEmpty()) {
            if(tryParseInt(text_ValumeDrive.getText()))
                query.append("VALUME_DRIVE = "+text_ValumeDrive.getText()+ ",");
            else return false;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Поле \'Объем ЖД\' не может быть пустым");
            alert.showAndWait();
            return false;
        }

        query.append("FORMAN_DRIVE = "+chbx_FormanDrive.getValue() +",");

        if(checkPriceAndCount())
            query.append("PRICE_DRIVE = "+text_Price.getText() + ",COUNT_DRIVE = " + text_Count.getText());
        else return false;

        query.append(" WHERE ID_DRIVE = "+product.getId());

        connectionJDBC.updateQuery(query.toString());
        return true;
    }

    public boolean updateMemoryModul() {
        StringBuilder query = new StringBuilder("UPDATE MEMORY_MODULES SET ");
        String name = null, description = null, manufactor = null, type=null;

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

        query.append("NAME_MEMORY = \'"+name+"\',DESCRIPTION_MEMORY = \'"+description+"\',MANUFACTURER_MEMORY = \'"+ manufactor+"\',");

        if(!text_VolumeMemory.getText().isEmpty()) {
            if(tryParseInt(text_VolumeMemory.getText()))
                query.append("VOLUME_MEMORY = "+text_VolumeMemory.getText() + ",");
            else return false;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Поле \'Объем\' не может быть пустым");
            alert.showAndWait();
            return false;
        }

        if(!text_TypeMemory.getText().isEmpty())
            type=text_TypeMemory.getText();
        query.append("TYPE_MEMORY = \'"+type+"\',");

        if(checkPriceAndCount())
            query.append("PRICE_MEMORY = "+text_Price.getText() + ", COUNT_MEMORY = " + text_Count.getText());
        else return false;

        query.append(" WHERE ID_MEMORY = "+ product.getId());

        connectionJDBC.updateQuery(query.toString());
        return true;
    }

    public boolean updateVideoCard() {
        StringBuilder query = new StringBuilder("UPDATE VIDEO_CARDS SET ");
        String name, description = null, manufactor = null, type=null;

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

        query.append("NAME_VIDEO_CARD = \'"+name+"\',DISCRIPTION_VIDEO_CARD = \'"+description+"\',MANUFACTURER_VIDEO_CARD = \'"+ manufactor+"\',");

        if(!text_ValumeVideoCard.getText().isEmpty()) {
            if(tryParseInt(text_ValumeVideoCard.getText()))
                query.append("VALUME_VIDEO_CARD = "+text_ValumeVideoCard.getText() + ",");
            else return false;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Поле \'Объем памяти\' не может быть пустым");
            alert.showAndWait();
            return false;
        }

        if(!text_TypeVideoCard.getText().isEmpty())
            type=text_TypeVideoCard.getText();
        query.append("TYPE_VIDEO_CARD = \'"+type+"\', TIRE_VIDEO_CARD = " + chbx_TireVideoCard.getValue()+",");

        if(checkPriceAndCount())
            query.append("PRICE_VIDEO_CARD = "+text_Price.getText() + ",COUNT_VIDEO_CARD = " + text_Count.getText());
        else return false;

        query.append(" WHERE ID_VIDEO_CARD = "+ product.getId());

        connectionJDBC.updateQuery(query.toString());
        return true;
    }

    public void showSuccessAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Запись изменена");
        alert.setHeaderText("Запись успешно изменена");

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

    public void show() {
        initialize();
        mainForm = new Stage();
        mainForm.initModality(Modality.APPLICATION_MODAL);
        mainForm.setTitle("Редактирование товара");
        mainForm.setResizable(false);

        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.prefHeight(350);

        Button buttonSave = new Button("Сохранить");
        Button buttonClose = new Button("Отмена");

        buttonSave.setOnAction(e -> handleUpdateProduct());
        buttonClose.setOnAction(e -> handleClose());
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 10, 10, 10));
        hBox.setSpacing(40);
        hBox.setAlignment(Pos.TOP_CENTER);
        hBox.getChildren().addAll(buttonSave, buttonClose);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.prefWidth(320);
        vBox.getChildren().addAll(mainBox, hBox);

        Scene scene = new Scene(vBox);
        mainForm.setScene(scene);
        mainForm.showAndWait();
    }
}
