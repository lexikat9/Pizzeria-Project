default: Pizza.java Pizzeria.java Toppings.java Receipt.java Customer.java
	javac Pizza.java Pizzeria.java Toppings.java Receipt.java Customer.java

run: Pizza.class Pizzeria.class Toppings.class Receipt.class Customer.class
	java Pizzeria

clean:
	rm -f *.class
