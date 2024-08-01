package edu.curtin.oose2024s1.assignment2.transaction;

import edu.curtin.oose2024s1.assignment2.bikeshop.Bike;
import edu.curtin.oose2024s1.assignment2.bikeshop.Inventory;
import edu.curtin.oose2024s1.assignment2.bikeshop.Space;

public class PickingBike implements Transaction
{
    private final Inventory bikeInventory;
    private final Space space;

    public PickingBike(Inventory bikeInventory, Space space)
    {
        this.bikeInventory = bikeInventory;
        this.space = space;
    }

    @Override
    public void doTransaction(String email)throws FailureException
    {
        Bike bike = bikeInventory.findBike(email);
        if((bike != null))
        { 
            if (bike.getBikeDay() == 0) //make sure the bike not being serviced or already being serviced
            {
                if (bike.getPrevServiced()) //check if the bike is previously serviced 
                {
                    bikeInventory.cashCalculation(100); //call method to add cash
                }
                space.freeSpace();  //call method to vacant space when bike is pick up
                bikeInventory.removeBike(email);    //call method to remove the bike from the list
            }
            else
            {
                //display error message when bike is still being serviced
                throw new FailureException("Sorry, the bike is not ready, it is still being serviced");
            }
        }

        else
        {
            //display error message when bike with email insert cannot be found
            throw new FailureException("No bike match the customer email. Please make sure you enter the right email address.");
        }
        
    }

}
