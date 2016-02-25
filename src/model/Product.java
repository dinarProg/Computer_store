package electronics_store.model;

/**
 * Created by Администратор on 07.07.2015.
 */
public abstract class Product {
    int id;
    int idCategory;
    String name;
    String description;
    String manufacturer;
    double price;
    int count;

    public Product (int id, int idCategory, String name, String description, String manufacturer, double price, int count){
        this.id=id;
        this.idCategory=idCategory;
        this.description=description;
        this.manufacturer=manufacturer;
        this.name=name;
        this.price=price;
        this.count=count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
