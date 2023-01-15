import food.*;

public class Main{
    static public Product[] sampleProduct(){
        Product a[] = new Product[2];
        a[0] = new Noodle("Bakmi", 15000);
        a[1] = new Beverage("Teh", 6000);
        return a;
    }
    public static void main(String args[]){
        new Order();
    }
}