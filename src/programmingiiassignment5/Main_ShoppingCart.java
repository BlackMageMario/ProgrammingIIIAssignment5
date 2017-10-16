/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingiiassignment5;

import java.util.ArrayList;

/**
 *
 * @author BlackMageMario
 */
public class Main_ShoppingCart {
    public static void main(String... args)
    {
        ArrayList<Inventory> inventoryItems = new ArrayList<Inventory>();
        inventoryItems.add(new Inventory("1001", "Apple", 30, 2.50f, 1.25f));
        inventoryItems.add(new Inventory("1002", "Orange", 40, 2f, 1f));
        inventoryItems.add(new Inventory("2001", "Milk", 10, 2.39f, 1.50f));
        inventoryItems.add(new Inventory("2002", "Orange Juice", 20, 1.99f, 1.25f));
        inventoryItems.add(new Inventory("3001", "Blue Cheese", 10, 2.25f, 1.50f));
        inventoryItems.add(new Inventory("3002", "Chedder", 20, 2.79f, 1.60f));
        inventoryItems.add(new Inventory("4001", "Chocolate", 40, 2.99f, 1.70f));
        inventoryItems.add(new Inventory("4002", "Candy", 30, 0.99f, 0.50f));
        inventoryItems.add(new Inventory("5001", "Beef", 10, 5.00f, 3.00f));
        inventoryItems.add(new Inventory("5002", "Chicken", 10, 4.00f, 2.00f));
        ShoppingCart cart1 = new ShoppingCart("Bobby", "13/10/2017", 
                inventoryItems.toArray(new Inventory[inventoryItems.size()]));
        ShoppingCart cart2 = new ShoppingCart("Mark", "13/10/2017", 
                inventoryItems.toArray(new Inventory[inventoryItems.size()]));
        cart1.addItem("Apple", 2);
        cart1.addItem("Orange", 5);
        cart1.addItem("Milk", 2);
        cart1.addItem("Blue Cheese", 4);
        cart1.addItem("Candy", 25);
        cart1.removeItem("Candy", 5);
        cart1.viewCart();
        cart2.addItem("Apple", 2);

        cart2.addItem("Orange", 5);

        cart2.addItem("Milk", 2);

        cart2.addItem("Blue Cheese", 4);

        cart2.addItem("Chedder", 3);

        cart2.addItem("Beef", 6);

        cart2.addItem("Candy", 20);

        cart2.addItem("Chocolate", 10);

        cart2.addItem("Chicken", 2);

        cart2.removeItem("Chocolate", 5);

        cart2.removeItem("Blue Cheese", 1);
        //now we sort cart2
    
        cart2.viewCart();
        cart2.sortByPrice();
        cart2.viewCart();
    }
    
}
