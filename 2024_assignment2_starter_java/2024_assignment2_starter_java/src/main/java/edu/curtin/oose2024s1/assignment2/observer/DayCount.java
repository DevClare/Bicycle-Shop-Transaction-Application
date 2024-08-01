package edu.curtin.oose2024s1.assignment2.observer;

public interface DayCount 
{
    public void addObserver(Observer o);
    public void notifyObserver();
}
