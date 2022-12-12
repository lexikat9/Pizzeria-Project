/****
 * P5 - Adding Customers
 * Lexi Katramados
 * Partners Names: None
 * Partners Usernames: None
 * Due: 12/4/2022
 * CPSC 1060
 ****/

import java.util.Scanner;
import java.util.ArrayList;

public class Pizzeria {
    public static void main(String[] args) {

        // Declare scanner object
        Scanner scnr = new Scanner(System.in);

        // Declare array list
        ArrayList<Pizza> pizzas = new ArrayList<Pizza>();

        // Declare variables to use throughout
        double totalCost = 0;
        double tip = 0.0;
        String customerName = "";
        String wantsMembership = "";
        long phoneNumber = 1234567890;
        String email = "";
        Boolean usePoints = false;
        Boolean isMember = false;

        // Declare boolean to allow program to run until user quits
        boolean userQuit = false;

        // Call printWelcome and printMenu method
        printWelcome();
        customerName = scnr.next();
        Customer customer = new Customer(customerName);
        System.out.println("Here are your options of what you can do:");
        System.out.println();
        userQuit = printMenu();

        // Continue looping until user quits
        while(!userQuit) {

            // Get user input, validate and if invalid, run through while loop until valid
            String userChoice = scnr.next();
            boolean validChoice = false;
            while (!validChoice) {
                if (userChoice.equals("z") || userChoice.equals("t") || userChoice.equals("r") || userChoice.equals("q") || userChoice.equals("c")) {
                    break;
                }
                else {
                    System.out.println("Please input a valid menu option:");
                    userChoice = scnr.next();
                    validChoice = false;
                }
            }

            // If user chooses q, restart program
            if (userChoice.equals("q")) {
                // Clear all previous data
                totalCost = 0;
                tip = 0.0;
                customerName = "";
                wantsMembership = "";
                phoneNumber = 1234567890;
                email = "";
                usePoints = false;
                isMember = false;
                pizzas.clear();
                
                // Recall menu
                printWelcome();
                customerName = scnr.next();
                customer = new Customer(customerName);
                System.out.println("Here are your options of what you can do:");
                System.out.println();
                userQuit = printMenu();
            }
            // Otherwise, if user chooses c, immediately quit program
            else if (userChoice.equals("c")) {
                System.exit(0);
            }
            // Otherwise, is user chooses z, get pizza info and print
            else if (userChoice.equals("z")) {
                // Get name
                System.out.println("What would you like the name of this pizza to be?");
                String pizzaName = scnr.next();
                    
                // Get size and only proceed once user gives a valid number
                System.out.println("Ok, what size would you like your pizza to be (in inches)? Please choose a number between 6 and 54.");
                int pizzaSize = scnr.nextInt();
                boolean validSize = false;
                while (!validSize) { // Runs until size is within range (6-54 inches)
                    if (pizzaSize >= 6 && pizzaSize <= 54) {
                        break;
                    }
                    else {
                        System.out.println("Please put in a correct pizza size (6-54).");
                        pizzaSize = scnr.nextInt();
                        validSize = false;
                    }
                }

                // Get number of toppings and only proceed once user gives a vaild number
                System.out.println("How many toppings would you like on your pizza? Please choose a number between 0 and 6.");
                int numberToppings = scnr.nextInt(); 
                boolean validToppings = false;
                while (!validToppings) { // Runs until number of toppings is within range (0-6)
                    if (numberToppings >= 0 && numberToppings <= 6) {
                        break;
                    }
                    else {
                        System.out.println("Please put in a correct number of toppings (0-6)");
                        numberToppings = scnr.nextInt();
                        validToppings = false;
                    }
                }
                
                // For as many toppings as the user has, prompt them to input desired topping, scan for it, validate it, and put into array
                String currTopping = "";
                Toppings[] userToppings = new Toppings[numberToppings];
                for (int i = 0; i < numberToppings; i++) {
                    System.out.println("What topping would you like? We have pepperoni, bacon, mushrooms, chicken, peppers, and onions");

                    currTopping = scnr.next();

                    // Call isValidTopping method

                    while (!isValidTopping(currTopping)) {
                        System.out.println("Invalid Topping. Please input one of the following available toppings: Pepperoni, Bacon, Mushrooms, Chicken, Peppers, or Onions.");
                        currTopping = scnr.next();
                        isValidTopping(currTopping);
                    }

                    // Take number of toppings are create array of that size
                    Toppings topping = new Toppings(currTopping);

                    userToppings[i] = topping;
                }

                // Put user input into object
                Pizza newPizza = new Pizza (pizzaSize, userToppings, pizzaName);

                // Put pizza in ArrayList
                pizzas.add(newPizza);
                        
                // Print name and size of pizza
                System.out.println();
                System.out.println("Added a pizza named " + pizzaName + " with a " + pizzaSize + " inch diameter.");
                
                // Print selected toppings
                System.out.println();
                System.out.print(pizzaName + " has the following toppings: ");
                System.out.println(newPizza.allToppingsToString());
                System.out.println();
                
                // Print cost
                System.out.printf("This pizza costs $" + "%.2f", newPizza.calculateTotalCost());
                System.out.println(".");
                System.out.println();

                // Add this pizza cost to the total
                totalCost = totalCost + newPizza.calculateTotalCost();

                // Ask user what they want to do next and print menu again
                System.out.println("What would you like to do next?");
                System.out.println();
                userQuit = printMenu();
            }
            // If user input is t, print the total
            else if (userChoice.equals("t")) {

                // Print total cost
                System.out.printf("Total: $" + "%.2f", totalCost);
                System.out.println();

                // Ask user what they want to do next and print menu again
                System.out.println("What would you like to do next?");
                System.out.println();
                userQuit = printMenu();
            }
            // If user input is r, prompt for tip, and validate it
            else if (userChoice.equals("r")) {
                // If there were no pizzas entered yet, print out statement
                if (totalCost == 0) {
                    System.out.println("You can't get a receipt if you haven't ordered anything!");
                    System.out.println("What would you like to do next?");
                }
                else {
                    // Ask if they're a rewards member, validate, and deal with accordingly
                    System.out.println("Alright " + customerName + ", before we print out your receipt, would you like to become a rewards member?");
                    wantsMembership = scnr.next();
                    // Validate wantsMembership
                    while (!wantsMembership.equals("Yes") && !wantsMembership.equals("No") && !wantsMembership.equals("yes") && !wantsMembership.equals("no")) {
                        System.out.println("Please select either Yes or No.");
                        wantsMembership = scnr.next();
                    }

                    // Declare new object
                    Customer updateCustomer = customer;

                    // If they do want a membership...
                    if (wantsMembership.equals("Yes") || wantsMembership.equals("yes")) {
                        isMember = true;
                        System.out.println("Ok, I am going to sign you up for our rewards program.");
                        // Prompt and plug in user phone number and email
                        System.out.println("Can I have your phone number?");
                        phoneNumber = scnr.nextLong();
                        System.out.println("And can I have an email?");
                        email = scnr.next();

                        // Create new customer object
                       
                        updateCustomer = new Customer(customer.getName(), phoneNumber, email);
                        
                        // Ask if they want to use rewards
                        System.out.println("Would you like to use your rewards?");
                        System.out.println("You have 20.0 points");
                        String wantsUsePoints = scnr.next();

                        // Check points for valid input
                        while (!wantsUsePoints.equals("Yes") && !wantsUsePoints.equals("No") && !wantsUsePoints.equals("yes") && !wantsUsePoints.equals("no")) {
                            System.out.println("Please select either Yes or No.");
                            wantsUsePoints = scnr.next();
                        }
                        // If the user wants to use rewards, set usePoints to true and print statement
                        if (wantsUsePoints.equals("Yes") || wantsUsePoints.equals("yes")) {
                            System.out.println("Ok, we will use the rewards points from your account today.");
                            usePoints = true;
                        }
                        // If the user doesn't want to use rewards, set usePoints to false
                        else {
                            usePoints = false;
                        }
                    }

                    // Ask for and validate tip
                    System.out.println("Please input a tip percent (just the number, no % symbol).");
                    tip = scnr.nextDouble();
                    // While tip is invalid, keep prompting
                    while (tip > 100 || tip < 0) {
                        System.out.println("Invalid tip percent. Please input a tip percent between 0 and 100.");
                        tip = scnr.nextDouble();
                    }

                    // Once tip is valid, create receipt object
                    Receipt newReceipt = new Receipt(pizzas, tip, updateCustomer, isMember, usePoints);

                    // Call method with the new object to print receipt
                    System.out.println(newReceipt.getReceiptStringToPrint());
                }

                // Recall menu
                if (userChoice.equals("r") && (totalCost == 0)) {
                    System.out.println();
                    userQuit = printMenu();
                }
                else {
                    // Clear all previous data
                    totalCost = 0;
                    tip = 0.0;
                    customerName = "";
                    wantsMembership = "";
                    phoneNumber = 1234567890;
                    email = "";
                    usePoints = false;
                    isMember = false;
                    pizzas.clear();
                    printWelcome();
                    customerName = scnr.next();
                    customer = new Customer(customerName);
                    System.out.println("Here are your options of what you can do:");
                    System.out.println();
                    userQuit = printMenu();
                }
            }
        } // end of while loop
    }
    
    // Method to print welcome
    private static void printWelcome () {
        System.out.println("Welcome to Adkins Pizzeria!");
        System.out.println();
        System.out.println("What is your name?");
    }
    
    // Method to print menu
    private static boolean printMenu () {
        System.out.println("MENU");
        System.out.println("z - Add a Pizza");
        System.out.println("t - Print the Total");
        System.out.println("r - Print the Receipt");
        System.out.println("d - Delete a pizza that was ordered");
        System.out.println("q - Quit");
        System.out.println("c - Close the pizzeria for the day");
        System.out.println("Please make a selection:");
        
        // Set boolean to false so we can run until user quits
        ///userQuit = false;
        return false;
    }

    // Method to check if user input for topping is valid
    private static boolean isValidTopping(String currTopping) {
        
        currTopping = currTopping.toLowerCase();

        if (currTopping.equals("pepperoni") || (currTopping.equals("onions")) || (currTopping.equals("peppers")) || (currTopping.equals("bacon")) || (currTopping.equals("chicken")) || (currTopping.equals("mushrooms"))) {
            return true;
        }
        else {
            return false;
        }
    }
}