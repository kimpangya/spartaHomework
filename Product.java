package myHomework;

public class Product extends Menu{
    int price;

    public Product(String name, int price, String exp){
        super(name,exp);
        this.price=price;
    }
}
