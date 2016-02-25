package electronics_store.model;

/**
 * Created by Администратор on 07.07.2015.
 */
public class Processor extends Product {
    private String familyProcessor;
    private String socket;
    private int cores;
    private double frequencyProcessor;

    public Processor(int id, int idCategory, String name, double price, int count, String description,
                    String manufacturer, String familyProcessor, String socket, int countCore, double frequency){
        super(id,idCategory,name,description, manufacturer, price,count);
        this.familyProcessor=familyProcessor;
        this.socket=socket;
        this.cores=countCore;
        this.frequencyProcessor=frequency;
    }

    public String getFamilyProcessor() {
        return familyProcessor;
    }

    public void setFamilyProcessor(String familyProcessor) {
        this.familyProcessor = familyProcessor;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public double getFrequencyProcessor() {
        return frequencyProcessor;
    }

    public void setFrequencyProcessor(int frequencyProcessor) {
        this.frequencyProcessor = frequencyProcessor;
    }
}
