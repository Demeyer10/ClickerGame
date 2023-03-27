package Main;

public class Item {
    String name;
    double price;
    double priceMultiplier;
    double cookiesPerSecond;
    int buttonNumber;
    int numberOwned;

    public Item(String name, double price, double priceMultiplier, double cookiesPerSecond, int numberOwned,int buttonNumber){
        this.name = name;
        this.price = price;
        this.priceMultiplier = priceMultiplier;
        this.cookiesPerSecond = cookiesPerSecond;
        this.buttonNumber = buttonNumber;
        this.numberOwned = numberOwned;
    }
}
