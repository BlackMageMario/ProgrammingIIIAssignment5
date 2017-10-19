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
public class Item implements Serializable{
    private String itemName;
    private float price;
    private int quantity;
    public Item(String itemName, float price, int quantity)
    {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName()
    {
        return itemName;
    }
    public float getPrice()
    {
        return price;
    }
    public int getQuantity()
    {
        return quantity;
    }
    public void setQuantity(int newQuantity)
    {
        quantity = newQuantity;
    }
    @Override
    public String toString()
    {
        return quantity + "\t" + itemName + "\t" + "€" + price*quantity;
    }
}
