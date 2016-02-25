package electronics_store.model;

/**
 * Created by Администратор on 07.07.2015.
 */
public class HardDrive extends Product{
    private int valumeDrive;
    private double formanDrive;

    public HardDrive(int id, int idCategory, String name, double price, int count, String description,
                    String manufacturer, int valumeDrive, double formanDrive){
        super(id,idCategory,name,description, manufacturer, price,count);
        this.valumeDrive=valumeDrive;
        this.formanDrive=formanDrive;
    }

    public int getValumeDrive() {
        return valumeDrive;
    }

    public void setValumeDrive(int valumeDrive) {
        this.valumeDrive = valumeDrive;
    }

    public double getFormanDrive() {
        return formanDrive;
    }

    public void setFormanDrive(int formanDrive) {
        this.formanDrive = formanDrive;
    }
}
