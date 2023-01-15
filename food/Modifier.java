package food;
import java.util.ArrayList;

public class Modifier {
    private String type;
    private ArrayList<String> variant;

    public Modifier(String x){
        this.type = x;
        variant = new ArrayList<String>();
    }

    public void addVariant(String x){
        this.variant.add(x);
    }

    public String getType(){
        return this.type;
    }
    public String getVariant(int index){
        if (0 <= index && index < this.variant.size()){
            return variant.get(index);
        }
        else{
            return variant.get(0);
        }
    }
    public int getVariantSize(){
        return this.variant.size();
    }
}
