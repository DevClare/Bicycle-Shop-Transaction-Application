package edu.curtin.oose2024s1.assignment2.bikeshop;

public interface BikeState 
{
    void purchasedBike(Bike bike, String email);
    void doneServiced(Bike bike);
}
