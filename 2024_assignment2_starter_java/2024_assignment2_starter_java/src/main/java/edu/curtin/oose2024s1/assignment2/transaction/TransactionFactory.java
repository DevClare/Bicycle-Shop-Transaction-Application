package edu.curtin.oose2024s1.assignment2.transaction;

import edu.curtin.oose2024s1.assignment2.bikeshop.Inventory;
import edu.curtin.oose2024s1.assignment2.bikeshop.Space;

public class TransactionFactory 
{
    private final Inventory bikeInventory;
    private final Space space;

    public TransactionFactory(Inventory bikeInventory, Space space) 
    {
        this.bikeInventory = bikeInventory; 
        this.space = space;
    }

    //method to create transaction object
    public Transaction makeTransaction(String transaction) throws FailureException
    {
    
        switch (transaction)  
        {
            case "delivery":
            //create object for delivery transaction
                return new Delivery(bikeInventory, space);

            case "drop-off":
            //create object for drop-off transaction
                return new DropOff(bikeInventory, space);

            case "purchase-online":
            //create object for online purchase transaction
                return new OnlinePurchase(bikeInventory);
        
            case "purchase-in-store":
            //create object for in store purchase transaction
                return new InStorePurchase(bikeInventory, space);
        
            case "pick-up":
            //create object for pick up transaction
                return new PickingBike(bikeInventory, space);
        
            default:
            //to handle an unknown transaction
                throw new FailureException("Unknown transaction: " + transaction);
        }

    }
}
