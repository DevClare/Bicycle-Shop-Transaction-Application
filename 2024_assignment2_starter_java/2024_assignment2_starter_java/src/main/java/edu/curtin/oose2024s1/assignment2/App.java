package edu.curtin.oose2024s1.assignment2;

import java.io.*;
import java.util.logging.Logger;
import java.util.logging.Level;

import edu.curtin.oose2024s1.assignment2.bikeshop.Bike;
import edu.curtin.oose2024s1.assignment2.bikeshop.Inventory;
import edu.curtin.oose2024s1.assignment2.bikeshop.PickUp;
import edu.curtin.oose2024s1.assignment2.bikeshop.Sale;
import edu.curtin.oose2024s1.assignment2.bikeshop.Serviced;
import edu.curtin.oose2024s1.assignment2.bikeshop.Space;
import edu.curtin.oose2024s1.assignment2.observer.DayObserver;
import edu.curtin.oose2024s1.assignment2.transaction.FailureException;
import edu.curtin.oose2024s1.assignment2.transaction.Transaction;
import edu.curtin.oose2024s1.assignment2.transaction.TransactionFactory;

/**
 * Use this code to get started on Assignment 2. You are free to modify or replace this file as
 * needed (to fulfil the assignment requirements, of course).
 */
public class App
{
    private static final Logger logger =  Logger.getLogger(App.class.getName());
    
    public static void main(String[] args) throws FailureException
    {
        String fileName = "sim_results.txt";
        int countInput = 0;

        BikeShopInput inp = new BikeShopInput();
        // BikeShopInput inp = new BikeShopInput(123);  // Seed for the random number generator

        Inventory bikeInventory =  new Inventory();
        Space space = new Space();
        
        int initialBike = 50;
        Bike bike;
        
        for (int i = 0; i < initialBike; i++)
        {
            bike = new Bike("noEmail", new Sale(), 0);
            bikeInventory.addBike(bike);
        }
        
        space.fillSpace(initialBike);

        logger.log(Level.INFO, "Adding the initial bike available for purchase in the shop");

        TransactionFactory transactionFact = new TransactionFactory(bikeInventory, space);
        @SuppressWarnings("PMD")
        Transaction transaction = null;
        
        bikeInventory.addObserver(new DayObserver());
        try(var out = new FileWriter(fileName, false))
        {
            while(System.in.available() == 0)
            {
                logger.log(Level.INFO, "Starting the program...");
                // ... ?

                // For illustration purposes -- this just prints out the messages as they come in.
                System.out.println("---");
                out.write("\n---");
                String msg = inp.nextMessage();
                while(msg != null)
                {
                    System.out.println(msg);
                    out.write("\n" + msg);
                    countInput++;
                    String[] parts = msg.split(" ");  
                    String shopTransaction = parts[0].toLowerCase();
                    String email = "noEmail";

                    if (parts.length >= 2)
                    {
                        email = parts[1]; 
                    }

                    try
                    {
                        transaction = transactionFact.makeTransaction(shopTransaction);
                        transaction.doTransaction(email);
                    }
                    catch(FailureException e)
                    {
                        logger.log(Level.INFO, "Fail to proceed the transaction");
                        System.out.println(e.getMessage());
                        out.write("/n" + e.getMessage());
                    }

                    msg = inp.nextMessage();
                }

                // Wait 1 second
                try
                {
                    bikeInventory.dayPassed();
                    System.out.println("Number of elapsed day: " + bikeInventory.getDay());
                    System.out.println("Total cash in the bank account: " + bikeInventory.getCash());
                    System.out.println("Number of bikes available for purchase: " + bikeInventory.getSaleBike());
                    System.out.println("Number of bikes being serviced: " + bikeInventory.getServicedBike());
                    System.out.println("Number of bikes awaiting pick-up: " + bikeInventory.getWaitingBike());
                    System.out.println();

                    
                    logger.log(Level.INFO, "One simulated day passed");

                    out.write("\nNumber of elapsed day: " + bikeInventory.getDay());
                    out.write("\nTotal cash in the bank account: " + bikeInventory.getCash());
                    out.write("\nNumber of bikes available for purchase: " + bikeInventory.getSaleBike());
                    out.write("\nNumber of bikes being serviced: " + bikeInventory.getServicedBike());
                    out.write("\nNumber of bikes awaiting pick-up: " + bikeInventory.getWaitingBike());
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {
                    logger.log(Level.WARNING, "The thread is interrupted while sleeping");
                    throw new AssertionError(e);
                }
            }
            System.out.println("The total number of input message: " + countInput);
            System.out.println("The total number of failures: " + FailureException.getCount());

            logger.log(Level.INFO, "The program ends...");

            out.write("\nThe total number of input message: " + countInput);
            out.write("\nThe total number of failures: " + FailureException.getCount());

        }
        catch(IOException e)
        {
            logger.log(Level.WARNING, "Error reading user input");
            System.out.println("Error reading user input");
        }
    }

}
