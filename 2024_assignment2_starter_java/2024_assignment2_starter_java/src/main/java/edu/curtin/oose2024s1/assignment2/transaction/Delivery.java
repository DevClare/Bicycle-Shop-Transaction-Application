package edu.curtin.oose2024s1.assignment2.transaction;

import edu.curtin.oose2024s1.assignment2.bikeshop.Bike;
import edu.curtin.oose2024s1.assignment2.bikeshop.Inventory;
import edu.curtin.oose2024s1.assignment2.bikeshop.Sale;
import edu.curtin.oose2024s1.assignment2.bikeshop.Space;

public class Delivery implements Transaction
{
    private final Inventory bikeInventory;
    private final Space space;

    public Delivery(Inventory bikeInventory, Space space)
    {
        this.bikeInventory = bikeInventory;
        this.space = space;
    }

    @Override
    public void doTransaction(String email) throws FailureException
    {
        //initialize new bike 
        Bike bike;

        //check if there is enough space for bike, need to have at least 10 space, cause there will be 10 bikes delivered 
        if (space.getAvailableSpace() >= 10) 
        {
            if (bikeInventory.getCash() >= 10000)    //check if there is sufficient cash for bike delivery
            {
                space.fillSpace(10);
                for (int i = 0; i < 10; i++)    //loop until all 10 bikes is added into the list
                {
                    bike = new Bike(email, new Sale(), 0);
                    bikeInventory.addBike(bike);
                }
                bikeInventory.cashCalculation(-5000);
            }
            else
            {
                //display error message when cash is insufficient
                throw new FailureException("Not enough cash for the shop to purchase new bikes");
            }
        }
        else
        {
            //display error message when the space is not enough for the bike
            throw new FailureException("Space full!!! Not enough space to store more bikes");
        }

    }

}
