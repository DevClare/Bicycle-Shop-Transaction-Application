package edu.curtin.oose2024s1.assignment2.bikeshop;

public class Space //setter and getter for space
{
    private SpaceState state = new SpaceAvailable();
    private int spaceAvailable = 100;

    public Space() {}

    public void setState(SpaceState newState)
    {
        state = newState;
    }

    public int getAvailableSpace()
    {
        return spaceAvailable;
    }

    public void changeSpace(int bikeNum)    //update the space available whether it is filled of vacant
    {
        spaceAvailable += bikeNum;
    }

    public void fillSpace(int change)   //method to fill in the space
    {
        state.fillSpace(this, change);
    }

    public void freeSpace() //method to free the space
    {
        state.freeSpace(this);
    }
}
