/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingiiassignment5;

import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author BlackMageMario
 */
public class Main_ShoppingCart {
    public static void main(String... args)
    {
        String date = "13/10/2017";
        ShoppingCart cart1 = new ShoppingCart("Bobby", date);
        
        cart1.addItem("Apple", 2);
        cart1.addItem("Orange", 5);
        cart1.addItem("Milk", 2);
        cart1.addItem("Blue Cheese", 4);
        cart1.addItem("Candy", 25);
        cart1.removeItem("Candy", 5);
        cart1.viewCart();
        ShoppingCart cart2 = new ShoppingCart("Mark", date);
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
        Collections.sort(cart2.getCartItems(), new ShoppingCartComparator());
        
        cart2.viewCart();
    }
    
}
class ShoppingCartComparator implements Comparator<Item>
{
    @Override
    public int compare(Item a, Item b)
    {
        //you can juse a terinary operator inside another terinary operator
        return a.getPrice()*a.getQuantity() < b.getPrice()*b.getQuantity() ? -1 
                : a.getPrice()*a.getQuantity() == b.getPrice()*b.getQuantity() 
                    ? 0 : 1;
    }
}
