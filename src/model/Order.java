package electronics_store.model;

/**
 * Created by Владимир on 18.07.2015.
 */
public class Order {
    private int idOrder;
    private int idProduct;
    private String ferstName;
    private String secondName;
    private String adress;
    private String phone;
    Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order(int idProduct, String ferstName, String secondName, String phone) {
        this.idProduct = idProduct;
        this.ferstName = ferstName;
        this.secondName = secondName;
        this.phone = phone;
    }

    public Order(int idProduct, String ferstName, String secondName, String adress, String phone) {
        this(idProduct, ferstName, secondName, phone);
        this.adress = adress;
    }

    public Order(int idOrder, int idProduct, String ferstName, String secondName, String adress, String phone) {
        this(idProduct, ferstName, secondName, adress, phone);
        this.idOrder = idOrder;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getFerstName() {
        return ferstName;
    }

    public void setFerstName(String ferstName) {
        this.ferstName = ferstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
