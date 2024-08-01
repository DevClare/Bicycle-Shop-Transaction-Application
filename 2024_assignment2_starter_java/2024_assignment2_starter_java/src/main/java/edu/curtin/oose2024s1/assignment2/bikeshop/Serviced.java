package edu.curtin.oose2024s1.assignment2.bikeshop;

public class Serviced implements BikeState
{
    @Override
    public void purchasedBike(Bike bike, String email) {}

    @Override
    public void doneServiced(Bike bike)
    {
        bike.isServiced();
        bike.setState(new PickUp());
    }

}
