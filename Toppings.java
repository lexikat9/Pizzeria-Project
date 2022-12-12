public class Toppings {
    private String name;
    private double costPerInch;

    // Constructor
    public Toppings(String name) {
        
        // Initialize
        this.name = name;
        costPerInch = 0.0;

        // Make if statements and assign costPerInch depending on the topping equal to cost given to us
        if (name.equals("Pepperoni")) {
            costPerInch = 0.02;
        }

        if (name.equals("Onions")) {
            costPerInch = 0.005;
        }

        if (name.equals("Peppers")) {
            costPerInch = 0.01;
        }

        if (name.equals("Bacon")) {
            costPerInch = 0.03;
        }

        if (name.equals("Chicken")) {
            costPerInch = 0.04;
        }

        if (name.equals("Mushrooms")) {
            costPerInch = 0.005;
        }
    }

    // Name accessor
    public String getName() {
        return name;
    }
    
    // Cost per inch accessor
    public double getCostPerInch() {
        return costPerInch;
    }
}