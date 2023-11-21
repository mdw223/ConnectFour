import java.util.*;

/*
Include the numbers next to the board to know the coordinate of there to put a piece
Make it so the user cannot place a piece in a spot unless its the bottom most spot!
Include: if the the board fills up and nobody wins, the game can end.
Include: if player1 quits, the program displays player2 as the winner. Otherwise the game will display the winner after a connect four. The game can also display a tie if the board fills up before a connect four is reached.
Make sure only two numbers are entered for the coordinate system, with a non-numerical character in between them, otherwise the program will print “Invalid spot” and make the player enter a correct spot again.

 */

public class Game {
    /** this grid length is the number of spots in row */
    public static final int gridLength = 8;

    /** the board is a 8x8 grid */
    public static char[][] boardArray = new char[gridLength][gridLength];

    /** X piece */
    public static final char X = 'X';

    /** O piece */
    public static final char O = 'O';

    public static void main(String[] args) {
        // filling the array
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                boardArray[i][j] = ' ';
            }
        }


        Scanner scnr = new Scanner(System.in);
        String userChoice;
        do {
            System.out.println();
            printMenu();
            userChoice = scnr.next();
            if (userChoice.equalsIgnoreCase("R")) {
                displayRules();
                break;
            }
            else if (userChoice.equalsIgnoreCase("P")) {
                System.out.println("Enter username for player 1: ");
                String playerOneName = scnr.nextLine();
                System.out.println("Enter username for player 2: ");
                String playerTwoName = scnr.nextLine();
                play(playerOneName, playerTwoName, scnr);
                break;
            }
            else {
                System.out.println("Invalid option");
            }
        } while (!userChoice.equalsIgnoreCase("Q"));



    }

    public static void play(String name1, String name2, Scanner scnr) {
        int turns = 0;
        boolean gameOver = false;
        String playerChoice = "";
        int row = 0;
        while ((gameOver != true) || (!playerChoice.equalsIgnoreCase("Q"))) { //TODO: fix
            turns++;
            System.out.println("Turn " + turns + " (Enter \"Q\" to quit)");
            if(scnr.hasNextInt()) {
                row = scnr.nextInt();
                //get column
                // make sure there are two
                //if not
                //what if its a word or letter q?
            }
            displayBoard();

        }

        System.out.println();
        System.out.println();



    }


    public static void printMenu() {
        System.out.println("Play Connect 4 - Please choose an option" + "\n");
        System.out.println("R - Rules" + "\n" + "P - Play" + "\n" + "Q - Quit" + "\n");
        System.out.print("Option: ");

    }

    public static void displayRules() {
        System.out.println("Rules...");
    }

    public static void displayBoard () {
        System.out.println();
        System.out.println("   1 " + " 2 " + " 3 " + " 4 " + " 5 " + " 6 " + " 7 " + " 8 ");
        System.out.println("  -------------------------");
        int letterRow = 'A';
        for (int j = 0; j < gridLength; j++) {
            System.out.print((char)letterRow + " ");
            for (int i = 0; i < gridLength; i++) {
                System.out.print("| " + boardArray[i][j]);
            }
            System.out.print("|");
            System.out.println();
            System.out.println("  -------------------------");
            letterRow++;
        }
    }




}
