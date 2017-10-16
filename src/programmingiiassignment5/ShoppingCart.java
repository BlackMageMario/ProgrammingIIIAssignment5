/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingiiassignment5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author BlackMageMario
 */
public class ShoppingCart {
    private String customerName;
    private String date;
    private Inventory[] itemsToBuy;
    private ArrayList<Item> shoppingCartItems;
    public ShoppingCart(String customerName, String date, 
            Inventory[] items)
    {
        this.shoppingCartItems = new ArrayList<Item>();
        this.itemsToBuy = new Inventory[items.length];
        this.itemsToBuy = items;
        this.customerName = customerName;
        this.date = date;
        //nothing!
        //lets see what we added:
    }
    public Inventory searchInventory(String itemName) throws ArrayIndexOutOfBoundsException//perhaps this should be private?
    {
        Inventory test = new Inventory(itemName);
        Arrays.sort(itemsToBuy, new InventoryComparator());//need to sort 
        int index = Arrays.binarySearch(itemsToBuy, test, 
                new InventoryComparator());
        if(index < 0)
        {
            throw new ArrayIndexOutOfBoundsException("Item " + itemName + " not found.");//this is abusive...
            //but technically correct. ;)
        }
        return itemsToBuy[index];
    }
    //... where do we get our inventory items from... ?
    public void addItem(String itemName, int quantity)
    {
        try
        {
            Inventory itemRequired = searchInventory(itemName);
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
        catch(ArrayIndexOutOfBoundsException ex)
        {
            System.err.println("Message: " + ex.getMessage());
        }
    }
    public void removeItem(String itemName, int quantity)
    {
        try
        {
            Inventory itemRequired = searchInventory(itemName);//need this to add back
            //into the quantity
            Item itemToRemove = null;
            for(Item item : shoppingCartItems)
            {
                if(item.getName().equalsIgnoreCase(itemName))
                {
                    itemToRemove = item;
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
        catch(ArrayIndexOutOfBoundsException ex)
        {
            System.err.println("Message: " + ex.getMessage());
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
    public void sortByPrice()
    {
        shoppingCartItems.sort(new ShoppingCartComparator());
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
