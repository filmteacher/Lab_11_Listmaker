import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author matt.bennett@uc.edu
 */

//You will code a simple line editor that lets users create on screen lists.
//A list is just a series of lines which are Strings of text.
//We will store the list in an ArrayList<String>
//Use the SafeInput Library for all inputs.

public class Main
{
    public static void main(String[] args)
    {

        //Initialize variables
        boolean quit = false;
        ArrayList<String> yourList = new ArrayList<>();

        //Menu-driven loop until user inputs Quit command
        do {

            //Display menu and get input
            //Convert to uppercase for easy check
            String input = menu();
            input = input.toUpperCase();

            //Initially stub out the functions so you have a program that you can run almost immediately as you develop it as per the Agile Software Dev approach.
            //You will develop each of the menu options as a separate java method.
            switch (input) {

                //- Add an item always puts it at the end of the list
                case "A":
                    addItem(yourList);
                    break;

                //- Delete an item user has to specify which one using the item number from the display
                case "D":
                    deleteItem(yourList);
                    break;

                //- Insert an item user has to indicate where using a location number
                case "I":
                    insertItem(yourList);
                    break;

                //- Print the list just displays the list
                case "P":
                    printList(yourList);
                    break;

                //- Quit asks the user if they are sure and then terminates the program.
                case "Q":
                    quit = quitList(yourList);
                    break;
            }

        }while(!quit);
    }

    //private static String menu()
    //You will want to display the current list along with the menu of options so the user can see what they are doing.
    //gets input from the user
    private static String menu()
    {
        Scanner in = new Scanner(System.in);
        String input = "";

        // Command Options:
        //A – Add an item to the list
        //D – Delete an item from the list
        //I – Insert an item into the list
        //P – Print (i.e. display) the list
        //Q – Quit the program (This should do an are you sure? type query before exiting.)

        System.out.println("\nA – Add an item to the list");
        System.out.println("D – Delete an item from the list");
        System.out.println("I – Insert an item into the list");
        System.out.println("P – Print the list");
        System.out.println("Q – Quit the program");

        //b) The program gets one of these commands from the user and executes that function
        //•	You should use your SafeInput library to bulletproof all input.
        //For instance, use your getRegExString method to get the menu choice from the user.
        // Here, a regEx pattern like this [AaDdIiPpQq] creates a set where a match will be any one of these characters which of course are the menu choices that the user will make.
        // Note that we have to include both the upper and lower case.
        input = (SafeInput.getRegExString(in, "What would you like to do [AaDdIiPpQq]? ", "[AaDdIiPpQq]"));
        return input;
    }

    //•	You need to display a numbered version of the list to allow users to pick list elements for deletion.
    // Here the user looks at the display and then indicates the item to delete by the number.
    private static void numList(ArrayList<String> theList)
    {
        for(int x = 0; x < theList.size(); x++) // size NOT length
        {
            System.out.println((x +1 ) + ": " +theList.get(x));
        }
    }

    //private static boolean quitlist()
    //confirm that they want to quit
    //print the final list
    //return true if they want to quit to set the quit variable
    private static boolean quitList(ArrayList<String> theList)
    {
        //•	Use your getYNConfirm method for the quit prompt
        boolean confirm = SafeInput.getYNConfirm(new Scanner(System.in), "Are you sure you want to quit? ");

        if(!confirm)
        {
            return false;
        }
        else {
            System.out.println("\nThank you. This is your final list:");
            System.out.println(theList);
            return true;
        }
    }

    //private static void printlist()
    //print the list
    private static void printList(ArrayList<String> theList)
    {
        System.out.println("\nThis is your list so far:");
            System.out.println(theList);
    }

    //private static void addItem()
    //user inputs the item
    //add to the list
    //print the list
    private static void addItem(ArrayList<String> theList)
    {
        Scanner in = new Scanner(System.in);
        String item = "";
        item = (SafeInput.getNonZeroLenString(in, "Type in the item you want to add"));
        theList.add(item);
        System.out.println("\nThis is your list so far:");
        System.out.println(theList);
    }

    //private static void deleteItem()
    //print the list
    //if the list is empty there is nothing to delete
    //•	Use your getRangedInt method to get the item number to delete, etc.
    //confirm they want to delete the item
    //delete item from the list
    //print the list
    private static void deleteItem(ArrayList<String> theList)
    {
        Scanner in = new Scanner(System.in);
        int item = 0;
        boolean confirm = false;

        numList(theList);

        if(theList.isEmpty())
        {
            System.out.println("\nThere is nothing to delete!");
        } else {
            item = (SafeInput.getRangedInt(in, "Enter the number of the item you want to delete", 1, theList.size()));

            confirm = (SafeInput.getYNConfirm(in, "Are you sure you want to delete item " + item + ": " + theList.get(item - 1) + "? "));

            if(confirm) {
                theList.remove(item - 1);
                System.out.println("\nThis is your list after deleting the item:");
                System.out.println(theList);
            } else {
                System.out.println("\nItem was not deleted!");
            }
        }
    }

    //private static void insertItem()
    //print the list
    //if the list is empty, let them know they need to add items first
    //•	Use your getRangedInt method to get the item number to delete, etc.
    //confirm they want to insert the item at the location
    //insert item into the list at the location
    //print the list
    private static void insertItem(ArrayList<String> theList)
    {
        Scanner in = new Scanner(System.in);
        int location = 0;
        String item = "";
        boolean confirm = false;

        numList(theList);

        if(theList.isEmpty())
        {
            System.out.println("\nThe list is empty. You need to add items before you can insert them!");
            return;
        } else {
            location = (SafeInput.getRangedInt(in, "Enter the number of the list item you want to insert a new item before", 1, theList.size()));

            item = (SafeInput.getNonZeroLenString(in, "Type in the item you want to insert"));

            confirm = (SafeInput.getYNConfirm(in, "Are you sure you want to insert " + item + " before " + location + ":" + theList.get(location - 1) + "? "));

            if(confirm) {
                theList.add(location - 1, item);
                System.out.println("\nThis is your list after inserting the item:");
                System.out.println(theList);
            } else {
                System.out.println("\nItem was not inserted!");
            }
        }
    }
}