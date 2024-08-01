package edu.curtin.oose2024s1.assignment2.transaction;

public interface Transaction 
{
    void doTransaction(String email) throws FailureException;
}
