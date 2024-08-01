package edu.curtin.oose2024s1.assignment2.transaction;

import edu.curtin.oose2024s1.assignment2.bikeshop.Bike;
import edu.curtin.oose2024s1.assignment2.bikeshop.Inventory;
import edu.curtin.oose2024s1.assignment2.bikeshop.Space;

public class InStorePurchase implements Transaction
{
    private final Inventory bikeInventory;
    private final Space space;

    public InStorePurchase(Inventory bikeInventory, Space space)
    {
        this.bikeInventory = bikeInventory;
        this.space = space;
    }

    @Override
    public void doTransaction(String email) throws FailureException
    {
        Bike bike = bikeInventory.findBike(email);
        if (bike != null)
        {
            space.freeSpace();  ///call method to free the space
            bikeInventory.removeBike(email);    //remove bike from the the list
            bikeInventory.cashCalculation(1000);    //call method to add the cash
        }
        else
        {
            //display error message when bike is sold out
            throw new FailureException("Apologize, the bike sold out, no bikes left");
        }
        
    }
}

