/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmingiiassignment5;

import java.io.Serializable;

/**
 *
 * @author BlackMageMario
 */
public class Inventory implements Serializable {
    private String sku;
    private float cost;
    private Item item;
    private String itemName;
    public static String fileName = "inventory.txt";
    public Inventory(String sku, String itemName, 
            int quantity, float price, float cost)
    {
        this.itemName = itemName;
        this.sku = sku;
        this.cost = cost;
        item = new Item(itemName, price, quantity);
    }
    public Inventory(String itemName)
    {
        //to make searching easier
        this.itemName = itemName;
    }
    public String getSKU()
    {
        return sku;
    }
    public String getItemName()
    {
        return itemName;
    }
    public float getCost()
    {
        return cost;
    }
    public void setCost(float newCost)
    {
       cost = newCost; 
    }
    public int getQuantity()
    {
        return item.getQuantity();
    }
    public void setQuantity(int newQuantity)
    {
        item.setQuantity(newQuantity);
    }
    public Item getItem()
    {
        return item;
    }
    public float getPrice()
    {
        return item.getPrice();
    }
    @Override
    public String toString()
    {
        
        return sku + "\t" + item.getName() + "\t\t"+ item.getQuantity()
                + "\t" + item.getPrice();
    }
}
