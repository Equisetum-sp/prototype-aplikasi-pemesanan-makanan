import food.*;
import java.util.ArrayList;

public class Cart {
    private ArrayList<Item> content;
    Cart(){
        content = new ArrayList<Item>();
    }

    public void addContent(Item x){
        content.add(x);
    }

    public Item getContent(int index){
        return content.get(index);
    }
    public int getContentSize(){
        return content.size();
    }

    public void displayContent(){
        int subtotal = 0;
        for (int i=0; i<content.size(); i++){
            Item curr = content.get(i);
            int currtotal = curr.getTotal();
            subtotal += currtotal;
            
            //format: No | Product Name | Qty | Total Price
            System.out.printf("%d | %20s |  %d  | %d\n", i, curr.type.getName(), curr.getQty(), currtotal);
            
            for (int j=0; j<curr.type.getModifierSize(); j++){
                Modifier currmod = curr.type.getModifier(j);
                System.out.println(currmod.getType() + ": " + curr.type.getModifier(curr.getMod(j)));
            }
            System.out.println("Notes: " + curr.getNotes());
            System.out.println("--------------------------------------------------------------------------------");
        }
        System.out.println("Total" + subtotal);
    }

    public void removeContent(int index){
        index--;
        if (index >= 0 && index < content.size()){
            content.remove(index-1);
        }
    }

    public void clearContent(){
        content.clear();
    }
}
