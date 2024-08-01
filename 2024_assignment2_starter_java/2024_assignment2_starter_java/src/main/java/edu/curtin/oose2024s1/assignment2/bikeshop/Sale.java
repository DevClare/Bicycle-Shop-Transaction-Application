package edu.curtin.oose2024s1.assignment2.bikeshop;

public class Sale implements BikeState
{
    @Override
    public void doneServiced(Bike bike) {}

    @Override
    public void purchasedBike(Bike bike, String email)
    {
        bike.setState(new PickUp());
        bike.setEmail(email);
    }

}
