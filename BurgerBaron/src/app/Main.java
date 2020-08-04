package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static int ordernumber;

    public static void main(final String[] args) throws IOException {
			// Used to find and select file
			BufferedReader br = new BufferedReader(new FileReader("customer.txt"));
			while (br.ready()) {
				String order = br.readLine(); // Reads the order from the customer file
				parseLine(order); // Sends the order to parseLine to create the order in Burger.java
				ordernumber++; // Increase the order number everytime an order is read
            }
            br.close();
            testMyStack();
            testBurger();
        }


    /**
     * Parses a line of input from the file and outputs the burger
     * 
     * @throws IOException
     */
    public static void parseLine(String line) throws IOException {
        if (line.equals(""))
            return;
        int numOfPatties = 1;
        String pattyType = "Beef";
        boolean theWorks = false;
        Burger burger = null;
        String[] words = line.split(" ");
        for (String word : words) {
            if (word.equals("Baron")) {
                theWorks = true;
            }
            if (word.equals("Veggies") || word.equals("Chicken")) {
                pattyType = word;
            }
            if (word.equals("Double")) {
                numOfPatties = 2;
            }
            if (word.equals("Triple")) {
                numOfPatties = 3;
            }
        }
        burger = new Burger(theWorks);
        for (int i = 1; i < numOfPatties; i++) {
            burger.addPatty();
        }
        burger.changePatties(pattyType);
        specification: for (int i = 0; i < words.length; i++) {
            if (words[i].equals("with")) {
                if (words[i + 1].equals("no")) {
                    for (int j = i + 2; j < words.length; j++) {
                        if (words[j].equals("but")) {
                            for (int k = j + 1; k < words.length; k++) {
                                burger.addIngredient(words[k].toString());
                            }
                            break specification;
                        }
                        burger.removeCategory(words[j]);
                        burger.removeIngredient(words[j]);
                    }
                } else {
                    for (int j = i + 1; j < words.length; j++) {
                        if (words[j].equals("but")) {
                            for (int k = j + 2; k < words.length; k++) {
                                burger.removeIngredient(words[k].toString());
                            }
                            break specification;
                        }
                        if (words[j].equals("Sauce") || words[j].equals("Cheese") || words[j].equals("Veggies")) {
                            burger.addCategory(words[j]);
                        } else {
                            burger.addIngredient(words[j].toString());
                        }
                    }
                }
            }
        }
        System.out.println("Order " + ordernumber + ": " + line);
		System.out.println(burger.toString());
		System.out.println();
    }
    // Method to test the functionallity of the MyStack class
    public static void testMyStack() {
        MyStack<String> test = new MyStack<String>();
        test.push("This");
        test.push("Is");
        test.push("To");
        test.push("Test");
        test.push("The");
        test.push("MyStack Class");
        System.out.println(test.toString());
        System.out.println(test.peek());
        test.pop();
        System.out.println(test.toString());
        System.out.println(test.size());
        System.out.println(test.isEmpty());
        MyStack<Integer> test2 = new MyStack<Integer>();
        System.out.println(test2.size());
        System.out.println(test2.isEmpty());
    }
    // Method to test the funcionallity of the burger class
    public static void testBurger() {
        System.out.println("Order: Baron Burger with Veggies but no Pickle");
        Burger burger = new Burger(true);
        burger.removeIngredient("Pickle");
        System.out.println(burger.toString());

        System.out.println("Order: Double Veggie Burger with Sauce Cheese but Onions");
        Burger burger2 = new Burger(false);
        burger2.addPatty();
        burger2.changePatties("Veggie");
        burger2.removeCategory("Veggies");
        burger2.addIngredient("Onions");
        burger2.addCategory("Sauce");
        burger2.addCategory("Cheese");
        System.out.println(burger2.toString());

        
    }

}
