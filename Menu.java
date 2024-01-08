package myHomework;

public class Menu {
    String name;
    String exp;
    Product[] product;

    public Menu(String name, String exp){
        this.name=name;
        this.exp=exp;
    }

    public void setProduct(Product ... product){
        this.product=product;
    }
}
