package edu.curtin.oose2024s1.assignment2.transaction;

public class FailureException extends Exception //custom exception handling
{
    private static int countError = 0;  //to count the number of error occuring while running the program
    public FailureException()
    {
        super();
    }

    public FailureException(String errorMessage)
    {
        super("FAILURE! " + errorMessage);
        countError++;
    }

    //return the number of error happens when running the program
    public static int getCount()
    {
        return countError;
    }
}
