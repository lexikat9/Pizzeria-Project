import java.util.ArrayList;

public class Receipt {
    private Customer customer;
    private ArrayList<Pizza> pizzas;
    private double total;
    private String receiptString = "";
    private double tipPercent;
    private boolean willUseRewardsPoints;
    private boolean isMember;
    final double salesTaxRate = 0.06;

    Receipt(ArrayList<Pizza> pizzas, double tipPercent, Customer customer, boolean isMember, boolean willUseRewardsPoints) {
        this.customer = customer;
        this.pizzas = pizzas;
        this.tipPercent = tipPercent/100;
        this.total = 0.0;
        this.receiptString = "\n=======RECEIPT========";
        this.isMember = isMember;
        this.willUseRewardsPoints = willUseRewardsPoints;
    }

    // Method to goes through the whole size of the ArrayList and adds information about the pizzas to the receipt string field
    public String pizzaInformation() {
        
        // For loop to iterate through pizzas in ArrayList and add info to a string
        for (int i = 0; i < pizzas.size(); i++) { 
            
            // Make object to call info from whatever pizza we're iterating through (takes value of wathever pizza were on)
            Pizza tempPizza = pizzas.get(i);
            
            // Get each line of info
            String sizeInfo = "Pizza " + (i+1) + ": " + tempPizza.getDiameter() + " inch diameter";
            String nameInfo = tempPizza.getName() + " has the toppings " + tempPizza.allToppingsToString();
            String costInfo = String.format("Cost: $%.2f", tempPizza.calculateTotalCost());

            // Add it to receipt string
            this.receiptString += "\n" + sizeInfo + "\n" + nameInfo + "\n" + costInfo + "\n";
        }

        return this.receiptString;
    }

    // Preform ending calcutions and adds to the receiptString
    private String addEndingCalculations() {
        // Declare necessary variable to use in this method
        double allPizzaTotal = 0;
        double calcTax = 0;
        double calcTip = 0;
        double total = 0;
        String finalInfo = "";
        double pointsUsed;
        double pointsLeft;
        double totalBefore;
        String usedPoints;
        String addedPoints;
        String remainingPoints;

        // For loop to go through every pizza and get total cost of all of the pizzas added up
        for (int i = 0; i < pizzas.size(); i++) { 
            Pizza tempPizza2 = pizzas.get(i);
            allPizzaTotal += tempPizza2.calculateTotalCost();
        }

        // Use that total to calculate tax, tip (based off total with tax), and then final cost and add to string
        calcTax = (allPizzaTotal * salesTaxRate);
        calcTip = ((allPizzaTotal + calcTax) * tipPercent);
        String totalInfo = String.format("Total: $%.2f", allPizzaTotal);
        String taxInfo = String.format("Tax: $%.2f", calcTax);
        String tipInfo = String.format("Tip: $%.2f", calcTip);
        
        // Total of everything
        total = allPizzaTotal + calcTax + calcTip;

        // Rest of receipt print is determined by users status and points
        if (!isMember) {
            finalInfo = String.format("Final Total: $%.2f", total);
            this.receiptString += "======================" + "\n" + totalInfo + "\n" + taxInfo + "\n" + tipInfo + "\n" + "Rewards Points Used: 0.00" + "\n" + finalInfo + "\n" + "======================" + "\n";
            this.receiptString += "Today's Transaction for " + customer.getName() + ":" + "\n" + "Rewards Points Added: 0.00" + "\n" + "Rewards Points Remaining: 0.00" + "\n" + "======================" + "\n";
        }
        else {
            if (!willUseRewardsPoints) {
                finalInfo = String.format("Final Total: $%.2f", total);
                addedPoints = String.format("Rewards Points Added: %.2f", (total * 0.20));
                remainingPoints = String.format("Rewards Points Remaining: %.2f", (20 + (total * 0.20)));
                this.receiptString += "======================" + "\n" + totalInfo + "\n" + taxInfo + "\n" + tipInfo + "\n" + "Rewards Points Used: 0.00" + "\n" + finalInfo + "\n" + "======================" + "\n";
                this.receiptString += "Today's Transaction for " + customer.getName() + ":" + "\n" + addedPoints + "\n" + remainingPoints + "\n" + "======================" + "\n";
            }
            else {
                // If they are using points, determine how many to used and how many are left
                if (total <= 20) {
                    pointsUsed = total;
                    totalBefore = total;
                    pointsLeft = ((20.0 - pointsUsed) + (totalBefore * 0.20));
                    total = total - pointsUsed;
                    finalInfo = String.format("Final Total: $%.2f", total);
                    usedPoints = String.format("Rewards Points Used: %.2f", pointsUsed);
                    addedPoints = String.format("Rewards Points Added: %.2f", (totalBefore * 0.20));
                    remainingPoints = String.format("Rewards Points Remaining: %.2f", pointsLeft);
                    this.receiptString += "======================" + "\n" + totalInfo + "\n" + taxInfo + "\n" + tipInfo + "\n" + usedPoints + "\n" + finalInfo + "\n" + "======================" + "\n";
                    this.receiptString += "Today's Transaction for " + customer.getName() + ":" + "\n" + addedPoints + "\n" + remainingPoints + "\n" + "======================" + "\n";
                }
                else {
                    pointsUsed = 20.0;
                    totalBefore = total;
                    total = total - pointsUsed;
                    finalInfo = String.format("Final Total: $%.2f", total);
                    usedPoints = String.format("Rewards Points Used: %.2f", pointsUsed);
                    addedPoints = String.format("Rewards Points Added: %.2f", (totalBefore * 0.20));
                    remainingPoints = String.format("Rewards Points Remaining: %.2f", (totalBefore * 0.20));
                    this.receiptString += "======================" + "\n" + totalInfo + "\n" + taxInfo + "\n" + tipInfo + "\n" + usedPoints + "\n" + finalInfo + "\n" + "======================" + "\n";
                    this.receiptString += "Today's Transaction for " + customer.getName() + ":" + "\n" + addedPoints + "\n" + remainingPoints + "\n" + "======================" + "\n";
                }
            }
        }
        return this.receiptString;
    }

    // Method to get the string from 2 methods above and print it out
    public String getReceiptStringToPrint() {  
        pizzaInformation();
        return addEndingCalculations();
    }
    
}