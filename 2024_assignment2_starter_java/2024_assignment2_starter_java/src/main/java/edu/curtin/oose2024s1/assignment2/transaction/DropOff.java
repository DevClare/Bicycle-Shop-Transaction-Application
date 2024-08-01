package edu.curtin.oose2024s1.assignment2.transaction;

import edu.curtin.oose2024s1.assignment2.bikeshop.Bike;
import edu.curtin.oose2024s1.assignment2.bikeshop.Inventory;
import edu.curtin.oose2024s1.assignment2.bikeshop.Serviced;
import edu.curtin.oose2024s1.assignment2.bikeshop.Space;

public class DropOff implements Transaction
{
    private final Inventory bikeInventory;
    private final Space space;

    public DropOff(Inventory bikeInventory, Space space)
    {
        this.bikeInventory = bikeInventory;
        this.space = space;
    }

    @Override
    public void doTransaction(String email) throws FailureException
    {
        //initialize new bike
        Bike bike = new Bike(email, new Serviced(), 3);

        if (space.getAvailableSpace() >= 1)    //check if there is enough space for bike
        {
            space.fillSpace(1); 
            bikeInventory.addBike(bike);    
        }
        else
        {
            //display error message when cash is insufficient
            throw new FailureException("Space full!!! Not enough space to store more bikes");
        }
    }

}
