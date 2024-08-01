package edu.curtin.oose2024s1.assignment2.observer;

import edu.curtin.oose2024s1.assignment2.bikeshop.Inventory;

public class DayObserver implements Observer
{

    public DayObserver() {}

    @Override
    public void updatingBikeDay(Inventory updateBike)
    {
        updateBike.updateDropOff();
    }

    @Override
    public void updatingCash(Inventory updateCash)
    {
        updateCash.updateCash();;
    }
}
