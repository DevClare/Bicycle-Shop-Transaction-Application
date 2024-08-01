package edu.curtin.oose2024s1.assignment2.bikeshop;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import edu.curtin.oose2024s1.assignment2.App;
import edu.curtin.oose2024s1.assignment2.observer.DayCount;
import edu.curtin.oose2024s1.assignment2.observer.Observer;

public class Inventory implements DayCount  //setter and getter class for inventory
{
    private static final Logger logger =  Logger.getLogger(App.class.getName());
    private List<Observer> observers = new ArrayList<>();   //list to store the observer
    private List<Bike> bikeInventory = new ArrayList<>();   //list to store the bike
    private int cash = 15000;   //inital cash in the shop
    private int day;   

    public Inventory() {}

    public List<Bike> getInventory()
    {
        return bikeInventory;
    }

    public void setInventory(List<Bike> newInventory)
    {
        bikeInventory = newInventory;
    }

    public int getCash()    //return cash owned by cash
    {
        return cash;
    }

    public int getDay() //return the number of current day
    {
        return day;
    }

    public void dayPassed() //do increment for the day and notify the observer
    {
        day++;
        notifyObserver();
    }
    
    public void cashCalculation(int change) //method to update the shop cash whenever there is income and expenses
    {
        cash += change;
    }

    public void addBike(Bike bike) //method to add new bike into the list
    {
        bikeInventory.add(bike);
    }

    public Bike findBike(String email) //to check if the bike exist the list
    {
        Bike bikeFound = null;
        for (Bike bike : bikeInventory)
        {
            if(bike.getEmail().equals(email))
            {
                bikeFound = bike;
                break;
            }
        }
        return bikeFound;
    }

    public void removeBike(String email) //to remove bike from the list
    {
        for (Bike bike : bikeInventory)
        {
            if((bike.getEmail().equals(email)))
            {
                bikeInventory.remove(bike);
                break;
            }
        }
    }

    public int getSaleBike()    //return no of bike available for purchase
    {
        int countState = 0;
        for (Bike bike : bikeInventory)
        {
            if((bike.getEmail().equals("noEmail"))) //bike for purchase is set with "noEmail"
            {
                countState += 1;
            }
        }
        return countState;
    }
    
    public int getServicedBike()    //return no of bike being serviced
    {
        int countState = 0;
        for (Bike bike : bikeInventory)
        {
            if(bike.getBikeDay() > 0)   //bike that still being serviced have days left to be serviced
            {
                countState += 1;
            }
        }
        return countState;
    }

    public int getWaitingBike() //return no of bike waiting to be pick up
    {
        int countState = 0;
        for (Bike bike : bikeInventory)
        {
            //bike waiting to be pick up have emails and the day is set to 0
            if(!(bike.getEmail().equals("noEmail")) && (bike.getBikeDay() == 0))    
            {
                countState += 1;
            }
        }
        return countState;
    }

    @Override
    public void addObserver(Observer newObserver)
    {
        logger.log(Level.INFO, "Adding observer");
        observers.add(newObserver);
    }

    @Override
    public void notifyObserver() 
    {
        logger.log(Level.INFO, "Notify the observer");
        for (Observer observer : observers)
        {
            observer.updatingBikeDay(this);
            observer.updatingCash(this);
        }
    }

    //update the day of serviced bike when notify by observer
    public void updateDropOff()
    {
        for (Bike bike : bikeInventory)
        {
            if(bike.getBikeDay() != 0)
            {
                logger.log(Level.INFO, "Update the serviced bike date");
                bike.dayChange();
                if (bike.getBikeDay() == 0)
                {
                    logger.log(Level.INFO, "Notify the bike to change the state");
                    bike.doneServiced();
                }
            }
        }
    }

    //update the cash amount when notify by observer
    public void updateCash()
    {
        if ((day % 7) == 0) //deduct cash only every 7 days for worker salary
        { 
            logger.log(Level.INFO, "update the cash after paying worker salary");
            cashCalculation(-1000);
        }
    }

}
