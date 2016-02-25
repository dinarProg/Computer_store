package electronics_store.model;

/**
 * Created by Администратор on 07.07.2015.
 */
public class Computer extends Product {
    private String processorComputer;
    private int countCoreComputer;
    private int valumeComputer;
    private int hddComputer;

    public Computer(int id, int idCategory, String name, double price, int count, String description,
                    String manufacturer, String processor, int countCore, int valume, int hdd){
        super(id,idCategory,name,description, manufacturer, price,count);
        this.processorComputer=processor;
        this.countCoreComputer=countCore;
        this.valumeComputer=valume;
        this.hddComputer=hdd;
    }

    public String getProcessorComputer() {
        return processorComputer;
    }

    public void setProcessorComputer(String processorComputer) {
        this.processorComputer = processorComputer;
    }

    public int getCountCoreComputer() {
        return countCoreComputer;
    }

    public void setCountCoreComputer(int countCoreComputer) {
        this.countCoreComputer = countCoreComputer;
    }

    public int getValumeComputer() {
        return valumeComputer;
    }

    public void setValumeComputer(int valumeComputer) {
        this.valumeComputer = valumeComputer;
    }

    public int getHddComputer() {
        return hddComputer;
    }

    public void setHddComputer(int hddComputer) {
        this.hddComputer = hddComputer;
    }
}
