package edu.curtin.oose2024s1.assignment2.bikeshop;

public class SpaceFull implements SpaceState
{
    @Override
    public void fillSpace(Space space, int changes) {}  //space already full, so does not do anything

    @Override
    public void freeSpace(Space space)  //increase the number of available space
    {
        space.changeSpace(1);
        space.setState(new SpaceAvailable());   //change the state to SpaceAvailable after space is vacant
    }
}
