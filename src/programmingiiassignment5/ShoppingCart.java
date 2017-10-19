/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingiiassignment5;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author BlackMageMario
 */
public class ShoppingCart {
    private String customerName;
    private String date;
    private ArrayList<Item> shoppingCartItems;
    private ArrayList<Inventory> itemsToBuy;
    public ShoppingCart(String customerName, String date)
    {
        this.shoppingCartItems = new ArrayList<Item>();
        try
        {
            FileInputStream in = new FileInputStream(Inventory.fileName);
            ObjectInputStream s = new ObjectInputStream(in);
            itemsToBuy = (ArrayList<Inventory>)s.readObject();
        }
        catch(IOException | ClassNotFoundException ex)
        {
            System.err.println(ex.getMessage());
        }
        this.customerName = customerName;
        this.date = date;
        //nothing!
        //lets see what we added:
    }
    public Inventory searchInventory(String itemName)//perhaps this should be private?
    {
        Inventory test = new Inventory(itemName);
        Collections.sort(itemsToBuy, new InventoryComparator());//need to sort 
        int index = Collections.binarySearch(itemsToBuy, test, 
                new InventoryComparator());
        if(index < 0)
        {
            return null;
            //but technically correct. ;)
        }
        return itemsToBuy.get(index);
    }
    //... where do we get our inventory items from... ?
    public void addItem(String itemName, int quantity)
    {
        Inventory itemRequired = searchInventory(itemName);
        if(itemRequired == null)
        {
            System.out.println("Item " + itemName + " not found.");
            return;
        }
        if(quantity > itemRequired.getQuantity())
        {
            System.out.println("Can only purchase " + itemRequired.getQuantity() 
                    + " of " + itemName + ".");
            shoppingCartItems.add(new Item(itemName, itemRequired.getPrice(), quantity));
            itemRequired.setQuantity(0);//none left
        }
        else if(itemRequired.getQuantity() == 0)
        {
            System.out.println("None of " + itemName + " is available.");
        }
        else
        {
            //successful case
            shoppingCartItems.add(new Item(itemName, itemRequired.getPrice(), quantity));
            itemRequired.setQuantity(itemRequired.getQuantity() - quantity);
            System.out.println("Item " + itemName + " successfuly added to inventory.");
        }
    }
    public void removeItem(String itemName, int quantity)
    {
        Inventory itemRequired = searchInventory(itemName);//need this to add back
            //into the quantity
        if(itemRequired == null)
        {
             System.out.println("Item " + itemName + " not found.");
            return;
        }
        Item itemToRemove = null;
        for(Item item : shoppingCartItems)
        {
            if(item.getName().equalsIgnoreCase(itemName))
            {
                    itemToRemove = item;
                    break;
            }
        }
        if(itemToRemove == null)
        {
                return;
        }
        //hopefully thhis works like intended
        //with successful case
        if(quantity > itemToRemove.getQuantity())
        {
            System.out.println("Can only remove " + itemToRemove.getQuantity()
                    + "of " + itemName + ".");
            shoppingCartItems.remove(itemToRemove);//all items gone
            itemRequired.setQuantity(quantity);//add back what was taken
            //itemToRemove.setQuantity(0);
            //shoppingCartItems.add(itemToRemove);//all items gone
        }
        else
        {
            //successful case
            shoppingCartItems.remove(itemToRemove);
            itemToRemove.setQuantity(itemToRemove.getQuantity() - quantity);
            if (itemToRemove.getQuantity() == 0)
            {
                return;
            }
            shoppingCartItems.add(itemToRemove);
            itemRequired.setQuantity(quantity);//set it to the quantity we took
            System.out.println(quantity + " removed from " + itemName + ".");
        }
    }
    public void viewCart()
    {
        System.out.println(date + " Name: " + customerName);
        for(int i = 0; i < shoppingCartItems.size(); i++)
        {
            //we don't need to do the sorting here... apparently?
            System.out.println(shoppingCartItems.get(i).toString());
        }
    }
    public ArrayList<Item> getCartItems()
    {
        return shoppingCartItems;
    }
    //addItem => add item to cart => remove item from inventory
    //removeItem => add item to cart => add item from inventory
    //view cart - view all this bullshit
    //searchInventory - requires a binary search
}
//internal class for comparing
class InventoryComparator implements Comparator<Inventory>
{
    @Override
    public int compare(Inventory a, Inventory b)
    {
        //System.out.println("A: " + a.getItemName() + " B: " + b.getItemName());
        int returnValue = a.getItemName().compareToIgnoreCase(b.getItemName());
        //System.out.println("Compare value: " + returnValue);
        return returnValue;
    }
}

