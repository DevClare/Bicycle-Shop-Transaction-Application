package edu.curtin.oose2024s1.assignment2.bikeshop;

public class SpaceAvailable implements SpaceState
{
    @Override
    public void fillSpace(Space space, int changes)
    {
        //changing the state to SpaceFull when the bike brought in filled the space left
        if (space.getAvailableSpace() == changes)  
        {
            space.setState(new SpaceFull());
        }
        space.changeSpace(-changes);
    }

    @Override
    public void freeSpace(Space space)  //increase the number of available space
    {
        space.changeSpace(1);
    }
}
