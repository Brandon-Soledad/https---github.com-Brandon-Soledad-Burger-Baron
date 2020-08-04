package app;

public class Burger {
    private final MyStack<String> myBurger;
    private final MyStack<String> ingredients;
    private final MyStack<String> recipe;
    private final MyStack<String> BaronBurger;
    private final MyStack<String> temp;
    private String pattyType;
    private int numOfPatties;
    /**
     * Constructer that creates a plain burger if theWorks is false and a baron burger if the works is true
     * @param theWorks determines whether the burger to be initialized is a plain burger or a baron burger
     */
    public Burger(final boolean theWorks) {
        myBurger = new MyStack<String>();
        recipe = new MyStack<String>();
        ingredients = new MyStack<String>();
        BaronBurger = new MyStack<String>();
        temp = new MyStack<String>();
        numOfPatties = 1;
        pattyType = "Beef";

        // Constructs a baron burger
        BaronBurger.push("Bun");
        BaronBurger.push("Ketchup");
        BaronBurger.push("Mustard");
        BaronBurger.push("Mushrooms");
        BaronBurger.push(pattyType);
        BaronBurger.push("Cheddar");
        BaronBurger.push("Mozzarella");
        BaronBurger.push("Pepperjack");
        BaronBurger.push("Onions");
        BaronBurger.push("Tomato");
        BaronBurger.push("Lettuce");
        BaronBurger.push("BaronSauce");
        BaronBurger.push("Mayonnaise");
        BaronBurger.push("Bun");
        BaronBurger.push("Pickle");

        if (!theWorks) {
            ingredients.push("Bun");
            ingredients.push(pattyType);
            ingredients.push("Bun");
        } else {
            ingredients.push("Bun");
            ingredients.push("Ketchup");
            ingredients.push("Mustard");
            ingredients.push("Mushrooms");
            ingredients.push(pattyType);
            ingredients.push("Cheddar");
            ingredients.push("Mozzarella");
            ingredients.push("Pepperjack");
            ingredients.push("Onions");
            ingredients.push("Tomato");
            ingredients.push("Lettuce");
            ingredients.push("BaronSauce");
            ingredients.push("Mayonnaise");
            ingredients.push("Bun");
            ingredients.push("Pickle");
        }
        createRecipe();
        makeBurger();
    }

    /*
     * Creates a recipe based on the amount of patties. If there is more than one
     * add it below the cheese.
     */
    private void createRecipe() {
        recipe.push("Bun");
        recipe.push("Ketchup");
        recipe.push("Mustard");
        recipe.push("Mushrooms");
        recipe.push(pattyType);
        recipe.push("Cheddar");
        recipe.push("Mozzarella");
        recipe.push("Pepperjack");
        if (numOfPatties > 1) {
            for (int i = 1; i < numOfPatties; i++) {
                recipe.push(pattyType);
            }
        }
        recipe.push("Onions");
        recipe.push("Tomato");
        recipe.push("Lettuce");
        recipe.push("BaronSauce");
        recipe.push("Mayonnaise");
        recipe.push("Bun");
        recipe.push("Pickle");
    }

    /**
     * Method that switches the patties based on the customers order
     * @param pattyType represents the patty type (Beef, Chicken, Veggie)
     */
    public void changePatties(final String pattyType) {
        if (pattyType == this.pattyType) return;
        while (!myBurger.isEmpty()) {
            if (myBurger.peek().equals(this.pattyType)) {
                myBurger.pop();
                myBurger.push(pattyType);
            } else {
                temp.push(myBurger.pop());
            }
        }
        burgerStack();
        this.pattyType = pattyType;
        updateRecipe();
    }
    // creates the actual burger.
    private void makeBurger() {
		while (!recipe.isEmpty()) {
			if (hasIngredient(recipe.peek())) {
				myBurger.push(recipe.pop());
			} else {
				recipe.pop();
			}
		}
		createRecipe();
    }

    // refills myBurger
    private void burgerStack() {
		while (!temp.isEmpty()) {
			myBurger.push(temp.pop());
		}
	}
    // Updates the recipe based on the order made
    private void updateRecipe() {
		while (!recipe.isEmpty()) {
			recipe.pop();
		}
		createRecipe();
	}
    // adds a patty to the burger
    public void addPatty() {
        numOfPatties++;
        updateRecipe();
        addIngredient(this.pattyType);
    }
    // places a category in the proper location
    public void addCategory(final String type) {
        if (type.equals("Sauce")) {
            ingredients.push("Mayonnaise");
            ingredients.push("BaronSauce");
            ingredients.push("Mustard");
            ingredients.push("Ketchup");
        } else if (type.equals("Veggies")) {
            ingredients.push("Mushrooms");
            ingredients.push("Lettuce");
            ingredients.push("Tomato");
            ingredients.push("Onions");
            ingredients.push("Pickle");
        } else if (type.equals("Cheese")) {
            ingredients.push("Cheddar");
            ingredients.push("Mozzarella");
            ingredients.push("Pepperjack");
        }
        swapIngredients();
        makeBurger();
    }
    // Removes ingredient type from the burger
    public void removeCategory(final String type) {
        if (type.equals("Sauce")) {
            removeIngredient("Mayonnaise");
            removeIngredient("Baron-Sauce");
            removeIngredient("Mustard");
            removeIngredient("Ketchup");
        } else if (type.equals("Cheese")) {
            removeIngredient("Cheddar");
            removeIngredient("Mozzarella");
            removeIngredient("Pepperjack");
        } else if (type.equals("Veggies")) {
            removeIngredient("Mushrooms");
            removeIngredient("Onions");
            removeIngredient("Tomato");
            removeIngredient("Lettuce");
            removeIngredient("Pickle");
        }
    }

    // swaps ingredients based on the order
    private void swapIngredients() {
        while (!myBurger.isEmpty()) {
            ingredients.push(myBurger.pop());
        }
    }

    // adds an ingredient type to the burger
    public void addIngredient(final String type) {
        ingredients.push(type);
        swapIngredients();
        makeBurger();
    }

    // removes ingredient type from the burger
    public void removeIngredient(final String type) {
        while (!myBurger.isEmpty()) {
            if (type.equals(myBurger.peek())) {
                myBurger.pop();
                break;
            } else {
                temp.push(myBurger.pop());
            }
        }
        burgerStack();
    }

    // checks to see if the ingredient stack has that item already if not add it.
    /**
     * Method that checks if the ingredient stack has the item type already if not then add it
     * @param type ingredient type
     * @return true if the ingredient type exists in the stack, false if the item type is not in the stack.
     */
    private boolean hasIngredient(final String type) {
        boolean hasIngredient = false;
        while (!ingredients.isEmpty()) {
            if (type.equals(ingredients.peek())) {
                ingredients.pop();
                hasIngredient = true;
                break;
            }
            temp.push(ingredients.pop());
        }
        while (!temp.isEmpty()) {
            ingredients.push(temp.pop());
        }
        return hasIngredient;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(!myBurger.isEmpty()) {
            sb.append(myBurger.pop());
            if (!myBurger.isEmpty()) {
                sb.append(", ");
            } else {
                sb.append("]");
            }
        }
        return sb.toString();
    }

}