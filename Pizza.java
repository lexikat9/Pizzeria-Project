import static java.lang.Math.PI;
public class Pizza {

    // Declare fields
    private int diameter;
    private Toppings[] toppings;
    private String name;
    private double area;
    private double toppingsCost = 0;

    final double cheeseCostPerInch = .028;
    final double sauceCostPerInch = .039;
    final double doughCostPerInch = .008;

    // Default constructor
    public Pizza(int diameter, String name) {
        diameter = 0;
        name = "No Pizza";
    }
   
    // The constructor for the pizza that sets the fields for number of toppings, name, and diameter given from pizzeria class
    public Pizza(int diameter, Toppings[] toppings, String name) {
        this.diameter = diameter ;
        this.name = name;
        this.toppings = toppings;
        this.area = calculateArea(diameter);
    }
    
    // Method to add the base cost with the toppings cost to get total cost
    public double calculateTotalCost() {
        return calculateBaseCost() + calculateToppingsCost(toppingsCost, toppings);
    }
    
    // Use toppings array to take the all the users toppings and add their costs together
    private double calculateToppingsCost(double toppingsCost, Toppings[] toppings) {

        for (int i = 0; i < toppings.length; i++) {
            if ((toppings[i].getName()).equals("Pepperoni") || (toppings[i].getName()).equals("pepperoni")) {
                toppingsCost = toppingsCost + (0.02 * calculateArea(diameter));
            }
            else if ((toppings[i].getName()).equals("Onions") || (toppings[i].getName()).equals("onions")) {
                toppingsCost = toppingsCost + (0.005 * calculateArea(diameter));
            }
            else if ((toppings[i].getName()).equals("Peppers") || (toppings[i].getName()).equals("peppers")) {
                toppingsCost = toppingsCost + (0.01 * calculateArea(diameter));
            }
            else if ((toppings[i].getName()).equals("Bacon") || (toppings[i].getName()).equals("bacon")) {
                toppingsCost = toppingsCost + (0.03 * calculateArea(diameter));
            }
            else if ((toppings[i].getName()).equals("Chicken") || (toppings[i].getName()).equals("chicken")) {
                toppingsCost = toppingsCost + (0.04 * calculateArea(diameter));
            }
            else {
                toppingsCost = toppingsCost + (0.005 * calculateArea(diameter));
            }
        }
        
        return toppingsCost;
    }

    // First, calculate the area of the pizza using the diameter. Then, multiply by all costs per inch and return.
    // Don't include toppings, we have a method for that.
    public double calculateBaseCost() {
        area = calculateArea(diameter);
        return (cheeseCostPerInch * area) + (sauceCostPerInch * area) + (doughCostPerInch * area);
    }

    // Name accessor: return name of pizza
    public String getName() {
        return name;
    }

    // Diameter accessor: return diameter of pizza
    public int getDiameter() {
        return diameter;
    }

    // Area accessor: return area of pizza
    public double getArea() {
        return area;
    }

    // Method that calculates the area of the pizza using the diameter field and MATH.PI and returns
    private double calculateArea(int diameter) {
        return Math.PI * ((diameter/2.0) * (diameter/2.0));
    }

    // Method to take all the toppings in the array userToppings and put it into a string
    public String allToppingsToString() {

        String toppingString;

        if (toppings.length == 0) {
            toppingString = "NONE";
        }
        else if (toppings.length == 1) {
            toppingString = toppings[0].getName() + ".";
        }
        else {
            toppingString = toppings[0].getName();
            toppingString = toppingString + ", ";

            for (int i = 1; i < toppings.length - 1; i++) {
                toppingString = toppingString + toppings[i].getName();
                toppingString = toppingString + ", ";
            }

            toppingString = toppingString + toppings[toppings.length - 1].getName() + ".";
        }

        return toppingString;
    }
}