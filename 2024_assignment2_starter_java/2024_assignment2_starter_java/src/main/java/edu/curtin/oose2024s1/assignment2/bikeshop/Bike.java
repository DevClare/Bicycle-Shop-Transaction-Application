package edu.curtin.oose2024s1.assignment2.bikeshop;

public class Bike //setter and getter class for bike
{
    private String email;
    private BikeState state;
    private int bikeDay; 
    private boolean previouslyServiced = false;
    
    public Bike(String email, BikeState state, int bikeDay)
    {
        this.email = email;
        this.state = state;
        this.bikeDay = bikeDay;
    }
 
    public void setState(BikeState newState)
    {
        state = newState;
    }

    public BikeState getState()
    {
        return state;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String newEmail)
    {
        email = newEmail;
    }

    public int getBikeDay()
    {
        return bikeDay;
    }

    public void isServiced()    //called when the bike is done and previously serviced
    {
        previouslyServiced = true;
    }

    public boolean getPrevServiced()    
    {
        return previouslyServiced;
    }

    public void dayChange() //to update the day left for serviced bike;
    {
        bikeDay--;
    }

    public void purchasedBike(String email)
    {
        state.purchasedBike(this, email);
    }

    public void doneServiced()
    {
        state.doneServiced(this);
    }

}
