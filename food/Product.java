package food;

import java.util.ArrayList;

public abstract class Product{
    private String name;
    private int price;
    ArrayList<Modifier> modifier = new ArrayList<Modifier>();

    public Product(String x, int n){
        this.name = x;
        this.price = n;
    }
    
    public String getName(){
        return this.name;
    }
    public int getPrice(){
        return this.price;
    }

    public Modifier getModifier(int index){
        return this.modifier.get(index);
    }
    public int getModifierSize(){
        return this.modifier.size();
    }
}