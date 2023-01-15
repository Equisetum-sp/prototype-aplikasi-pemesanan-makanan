package food;

public class Beverage extends Product{
    //constructor
    public Beverage(String x, int n){
        super(x, n);

        this.modifier.add(new Modifier("Ukuran"));
        this.modifier.get(0).addVariant("Regular");
        this.modifier.get(0).addVariant("Large");
        this.modifier.get(0).addVariant("Jumbo");

        this.modifier.add(new Modifier("Rasa"));
        this.modifier.get(1).addVariant("Tawar");
        this.modifier.get(1).addVariant("Manis");
        this.modifier.get(1).addVariant("Lemon Tea");
    }

    /*
    //convert mod attribute from Item class to beverage size, ex: 1 -> Regular, 2 -> Large
    @Override
    public String getModifier(int mod){
        switch(mod){
            case 1:
                return "Regular";
            case 2:
                return "Large";
            case 3:
                return "Jumbo";
            default:
                return "Regular";
        }
    }
    */
}
