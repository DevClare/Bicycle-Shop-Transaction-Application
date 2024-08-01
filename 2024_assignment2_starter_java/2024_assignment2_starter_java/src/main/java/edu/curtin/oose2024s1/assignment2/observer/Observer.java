package edu.curtin.oose2024s1.assignment2.observer;

import edu.curtin.oose2024s1.assignment2.bikeshop.Inventory;

public interface Observer 
{
    void updatingBikeDay(Inventory updateBike);
    void updatingCash(Inventory updateCash);
}

