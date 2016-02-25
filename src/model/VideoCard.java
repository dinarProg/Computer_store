package electronics_store.model;

/**
 * Created by Администратор on 07.07.2015.
 */
public class VideoCard extends Product{
    private int valumeVideoCard;
    private String typeVideoCard;
    private int tireVideoCard;

    public VideoCard(int id, int idCategory, String name, double price, int count, String description,
                    String manufacturer, int valumeVideoCard, String typeVideoCard, int tireVideoCard){
        super(id,idCategory,name,description, manufacturer, price,count);
        this.valumeVideoCard=valumeVideoCard;
        this.typeVideoCard=typeVideoCard;
        this.tireVideoCard=tireVideoCard;
    }

    public int getValumeVideoCard() {
        return valumeVideoCard;
    }

    public void setValumeVideoCard(int valumeVideoCard) {
        this.valumeVideoCard = valumeVideoCard;
    }

    public String getTypeVideoCard() {
        return typeVideoCard;
    }

    public void setTypeVideoCard(String typeVideoCard) {
        this.typeVideoCard = typeVideoCard;
    }

    public int getTireVideoCard() {
        return tireVideoCard;
    }

    public void setTireVideoCard(int tireVideoCard) {
        this.tireVideoCard = tireVideoCard;
    }
}
