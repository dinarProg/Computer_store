package electronics_store.model;

/**
 * Created by Администратор on 07.07.2015.
 */
public class Motherboard extends Product {
    private String formFactor;
    private String socket;
    private int countMemorySlot;

    public Motherboard(int id, int idCategory, String name, double price, int count, String description,
                    String manufacturer, String formFactor, String socket, int countMemorySlot){
        super(id,idCategory,name,description, manufacturer, price,count);
        this.formFactor=formFactor;
        this.socket=socket;
        this.countMemorySlot=countMemorySlot;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public int getCountMemorySlot() {
        return countMemorySlot;
    }

    public void setCountMemorySlot(int countMemorySlot) {
        this.countMemorySlot = countMemorySlot;
    }
}
