package edu.curtin.oose2024s1.assignment2.transaction;

import edu.curtin.oose2024s1.assignment2.bikeshop.Bike;
import edu.curtin.oose2024s1.assignment2.bikeshop.Inventory;

public class OnlinePurchase implements Transaction
{
    private final Inventory bikeInventory;

    public OnlinePurchase(Inventory bikeInventory)
    {
        this.bikeInventory = bikeInventory;
    }

    @Override
    public void doTransaction(String email) throws FailureException
    {
        Bike saleBike = bikeInventory.findBike("noEmail");
        if (saleBike != null)
        {
            bikeInventory.cashCalculation(1000);
            saleBike.purchasedBike(email);  //call method to change the state of the bike
        }
        else    
        {
            //display error message when there is no bike stock
            throw new FailureException("Apologize, the bike sold out, no bikes left");
        }
    }

}
