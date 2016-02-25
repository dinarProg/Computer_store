package electronics_store.model;

/**
 * Created by Администратор on 07.07.2015.
 */
public class MemoryModule extends Product{
    private int volumeMemory;
    private String typeMemory;

    public MemoryModule(int id, int idCategory, String name, double price, int count, String description,
                    String manufacturer, int volumeMemory, String typeMemory){
        super(id,idCategory,name,description, manufacturer, price,count);
        this.volumeMemory=volumeMemory;
        this.typeMemory=typeMemory;
    }

    public int getVolumeMemory() {
        return volumeMemory;
    }

    public void setVolumeMemory(int volumeMemory) {
        this.volumeMemory = volumeMemory;
    }

    public String getTypeMemory() {
        return typeMemory;
    }

    public void setTypeMemory(String typeMemory) {
        this.typeMemory = typeMemory;
    }
}
