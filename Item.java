import food.*;
import java.util.ArrayList;

public class Item {
    Product type;
    private int qty = 1;
    private ArrayList<Integer> mod;
    private String notes = "-";

    //function
    static ArrayList<Integer> initMod(Product x){
        ArrayList<Integer> num = new ArrayList<Integer>();
        for (int i=0; i<x.getModifierSize(); i++){
            num.add(0);
        }
        return num;
    }

    //constructors
    Item(Product x){
        this.type = x;
        mod = initMod(x);
    }
    Item(Product x, int n){
        this.type = x;
        this.qty = n;
        mod = initMod(x);
    }
    Item(Product x, int n, String add_notes){
        this.type = x;
        this.qty = n;
        this.notes = add_notes;
        mod = initMod(x);
    }
    
    //methods to set the value of attributes
    public void setMod(int index, int modif){
        this.mod.set(index, modif);
    }

    public void setQty(int n){
        this.qty = n;
    }
    public void setNotes(String add_notes){
        this.notes = add_notes;
    }


    //methods to get the value of attributes
    public int getQty(){
        return this.qty;
    }
    public int getMod(int index){
        return this.mod.get(index);
    }
    public int getTotal(){
        return this.qty*this.type.getPrice();
    }
    public String getNotes(){
        return this.notes;
    }
}
