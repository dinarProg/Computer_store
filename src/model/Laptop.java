package electronics_store.model;

/**
 * Created by Администратор on 07.07.2015.
 */
public class Laptop extends Product {
    private double diagonalLaptop;
    private String processorLaptop;
    private int countCoreLaptop;
    private int valumeLaptop;
    private int hddLaptop;

    public Laptop(int id, int idCategory, String name, double price, int count, String description,
                  String manufacturer, double diagonal,  String processor, int countCore, int valume, int hdd){
        super(id,idCategory,name,description, manufacturer, price,count);
        this.diagonalLaptop=diagonal;
        this.processorLaptop=processor;
        this.countCoreLaptop=countCore;
        this.valumeLaptop=valume;
        this.hddLaptop=hdd;
    }

    public double getDiagonalLaptop() {
        return diagonalLaptop;
    }

    public void setDiagonalLaptop(int diagonalLaptop) {
        this.diagonalLaptop = diagonalLaptop;
    }

    public String getProcessorLaptop() {
        return processorLaptop;
    }

    public void setProcessorLaptop(String processorLaptop) {
        this.processorLaptop = processorLaptop;
    }

    public int getCountCoreLaptop() {
        return countCoreLaptop;
    }

    public void setCountCoreLaptop(int countCoreLaptop) {
        this.countCoreLaptop = countCoreLaptop;
    }

    public int getValumeLaptop() {
        return valumeLaptop;
    }

    public void setValumeLaptop(int valumeLaptop) {
        this.valumeLaptop = valumeLaptop;
    }

    public int getHddLaptop() {
        return hddLaptop;
    }

    public void setHddLaptop(int hddLaptop) {
        this.hddLaptop = hddLaptop;
    }
}
