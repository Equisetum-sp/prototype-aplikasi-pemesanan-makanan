package food;

public class Noodle extends Product{
    //constructor
    public Noodle(String x, int n){
        super(x, n);

        this.modifier.add(new Modifier("Rasa"));
        this.modifier.get(0).addVariant("Asin");
        this.modifier.get(0).addVariant("Manis");
    }
    
    /*
    //convert mod attribute from Item to stores noodle flavour, ex: 1 -> manis, 2 -> asin
    @Override
    public Modifier getModifier(int mod){
        switch(mod){
            case 1:
                return "Asin";
            case 2:
                return "Manis";
            default:
                return "Asin";
        }
    }
    */
}