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

    /** number of turns per round */
    public static final int MAX_TURNS_PER_ROUND = 2;

    /** the board is a 8x8 grid */
    public static String[][] boardArray = new String[gridLength][gridLength];

    /** object for player 1 */
    public static Player player1;

    /** object for player 2 */
    public static Player player2;
    
    


    public static void main(String[] args) {

        // filling the array with white space initially
        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                boardArray[i][j] = " ";
            }
        }

        Scanner scnr = new Scanner(System.in);
        
        //will ask players for their info
        setUpPlayers(scnr);

        // Display Menu, will loop until a correct option is entered
        String userOption;
        boolean repeat = true;
        do {
            printMenu();

            userOption = scnr.next();

            if (userOption.equalsIgnoreCase("R")) {
                displayRules();
            }
            else if (userOption.equalsIgnoreCase("P")) {               
                System.out.println("\n" + "Enter \"Q\" to quit"); 
                displayBoard();  
                boolean isGameOver = false;

                // if count is even, its player1's turn
                int count = 0; 

                //loops another turn until the game is over
                while (isGameOver == false) { 
                    play(count, scnr); // asks user for spot to place pieces

                    // after each turn, will display the board
                    displayBoard();  
                    
                    // true if there is a tie or connect four, the loop will end
                    isGameOver = checkConnectFour(); 

                    //updates the count after each turn
                    count++;
                }

                repeat = false;
            }
            else if (userOption.equalsIgnoreCase("Q")) {
                System.out.print("Exitted Game");
                System.exit(1);
            }
            else {
                System.out.println("Invalid option");
            }
        } while (repeat == true);


        //once we break out of the loop once we are done playing, display who won
        //TODO: add display

    }


    /**
     * each turn, we will ask for the player's spot
     * if player enters q, it exits the game
     * will ask for the column number, their piece will go to the bottom of the board
     * @param count
     * @param scnr
     */
    
    public static void play(int count, Scanner scnr) {
        // will ask for spot to place piece in
        // count determines if player1 or 2's turn
        // if an int is entered, check its range
        // if range is correct, then place the piece in the board
        // if range is incorrect, ask again for spot
        // or if a word is entered, check that its q and quit,
        // or else you must ask for input again

        int spot;
        String user;

        // will loop if input is incorrect
        boolean incorrectInput = true;
        while (incorrectInput == true) {
            System.out.println();

            if (count % 2 == 0) {
                //if even count, its player1's turn to play
                System.out.print(player1.getName() + " (" + player1.getPiece() + ")" + ", enter your spot: ");
            }
            else {
                System.out.print(player2.getName() + " (" + player2.getPiece() + ")" + ", enter your spot: ");
            }

            if (scnr.hasNextInt()) {
                spot = scnr.nextInt();
                if (checkSpot(spot)) {
                    incorrectInput = false;
                    if (count % 2 == 0){
                        //if even count, player1's piece will be entered
                        dropPiece(player1.getPiece() , spot);
                    }
                    else {
                        // if neg count, player2 will enter piece
                        dropPiece(player2.getPiece() , spot);
                    }      
                }
                else {
                    System.out.println("Invalid spot");
                    continue; //ask original question again
                }             

            }
            else if (scnr.hasNext()) { // if user enters a word
                //check its q and quit
                user = scnr.next();
                if (user.equalsIgnoreCase("Q")) {
                    rageQuitDisplay(count);
                    System.exit(1);
                }
                else {
                    //if it not q, they need to re-enter input 
                    System.out.println("Must enter \"Q\" to quit");
                    continue;
                }

            }
            else {
                System.out.println("ERROR TO FIX"); //TODO: should not reach this point
            }

            

        }

        
    }



    /** this method is called with the column in which the player wants
        to put their piece in 
        will enter the player's piece in the correct row given the 
        player's piece and column chosen
     */
    public static void dropPiece(String piece, int spot) {
        
        int row = spot - 1;
        
        for (int i = gridLength - 1; i >= 1; i--) {
            int col = i;
            String element = boardArray[row][col];
            if (element.equals(" ")){
                boardArray[row][col] = piece;
                break;
            }
        }


        
    }

    public static boolean checkSpot(int spot) {
        boolean result = false;
        if ((spot >= 1) && (spot <= 8)) {
            // if the spot is within the range, place the piece in that spot  
        }
        else {
            // if not in range, will return false
            return result;
        }

        int row = spot - 1;
        
        for (int i = gridLength - 1; i >= 1; i--) {
            int col = i;
            String element = boardArray[row][col];
            if (element.equals(" ")){
                result = true;
                return result;
            }
        }


        return result;
    }

    public static boolean checkConnectFour() {
        boolean result = false;
        
        for (int row = 0; row < gridLength - 1; row++) {
            for (int col = 0; col < gridLength -1; col++) {
                String element = boardArray[row][col]; //current element

                if (element.equals("X") || (element.equals("O"))){
                    //only check connect four if the current element is x or o
                    if ((col <= gridLength - 4 ) 
                        && element.equals(boardArray[row][col + 1])
                        && element.equals(boardArray[row][col + 2])
                        && element.equals(boardArray[row][col + 3])) {
                            // checks if theres a vertical connect 4
                            result = true;
                            return result;
                    }
                    else if ((row <= gridLength - 4 ) 
                        && element.equals(boardArray[row + 1][col])
                        && element.equals(boardArray[row + 2][col]) 
                        && element.equals(boardArray[row + 3][col]) ) {
                            //checks if there is a horiz connect 4
                            result = true;
                            return result; //TODO: fix 
                    }




                }

            }
            
            
        }

        return result;
    }

    public static void rageQuitDisplay(int count) {
        //the count lets us know who's turn it was when they decided to quit
        //if count is positive, it was player1 who rage quit
        System.out.println("Rage quit display...");

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
        System.out.println(" 1 " + " 2 " + " 3 " + " 4 " + " 5 " + " 6 " + " 7 " + " 8 ");
        System.out.println("-------------------------");
        for (int j = 0; j < gridLength; j++) {
            for (int i = 0; i < gridLength; i++) {
                System.out.print("| " + boardArray[i][j]);
            }
            System.out.print("|");
            System.out.println();
            System.out.println("-------------------------");
        }
    }

    public static void setUpPlayers(Scanner scnr) {
        String name1;
        String name2;
        System.out.print("Player 1, Enter your name (ex: Stephen): ");
        name1 = scnr.next();
        System.out.print("Player 2, Enter your name: ");
        name2 = scnr.next();
        
        System.out.println();

        boolean pieceChosen = false;
        String piece = "";
        while (pieceChosen == false) {
            //loops until x or o is chosen
            System.out.print(name1 + ", Choose your piece (X/O): ");
            piece = scnr.next();
            if (piece.equalsIgnoreCase("X") || 
                piece.equalsIgnoreCase("O")) {
                pieceChosen = true;
            }
            else {
                System.out.println("Invalid piece" + "\n");
            }
        }
        
        if(piece.equalsIgnoreCase("X")) {
            // if player1 chooses x, then player2 gets o
            player1 = new Player(name1, "X");
            player2 = new Player(name2, "O");
        }
        else {
            // if player1 chooses o, player2 gets x
            player1 = new Player(name1, "O");
            player2 = new Player(name2, "X");
        }

        System.out.println();

        //print who is which piece using player class methods
        System.out.println(player1.getName() + ", your piece is " +
            player1.getPiece());
        System.out.println(player2.getName() + ", your piece is " +
            player2.getPiece());

        System.out.println();

    }




}
