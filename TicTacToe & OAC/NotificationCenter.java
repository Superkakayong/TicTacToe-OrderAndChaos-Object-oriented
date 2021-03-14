/**
 * This class is mainly designed for storing and displaying messages BEFORE, DURING, and AFTER the game.
 */
public final class NotificationCenter {
    private NotificationCenter() {}

    /*
        Ask the user to choose a game to play
     */
    public static void whichGame() {
        System.out.println("********************************************************************");
        System.out.println("Welcome to the world of board games! Which game do you want to play?");
        System.out.println("Choose a/A or b/B.");
        System.out.println("********************************************************************");
        System.out.println();
        System.out.println("A: Tic Tac Toe");
        System.out.println("B: Order and Chaos");
        System.out.println();
    }

    /*
        If the input is neither an 'a/A' not a 'b/B', prompt error messages
     */
    public static void notAOrB() {
        System.out.println("Invalid Input! Please choose a/A or b/B!");
    }

    /*
        Display the welcome message at the start of the game
     */
    public static void welcome(int gameType) {
        switch (gameType) {
            case 1:
                System.out.println("Welcome to Tic Tac Toe! Please be noticed of the followings before we starts:");

                System.out.println();
                System.out.println("    1. The system will randomly decide which one of you two to begin first.");
                System.out.println("    2. You can choose the size of the board from 3x3 to 10x10.");
                System.out.println();
                break;
            case 2:
                System.out.println("Welcome to Order and Chaos! Please be noticed of the followings before we starts:");

                System.out.println();
                System.out.println("    1. The system will randomly decide which one of you two to be the Order guy.");
                System.out.println("    2. Order plays first.");
                System.out.println("    3. The size of the board is fixed to be 6x6.");
                System.out.println();
                break;
            default:
                System.out.println("Invalid Input Parameter!");
        }

        System.out.println("Hit \"y/Y\" to start the game. Or hit any other key to exit.");
    }

    /*
        Prompt the players of the opportunity to exit the game
     */
    public static int startOrExit(String message) {
        if (message.equalsIgnoreCase("Y")) {
            System.out.println("**************************************************");
            System.out.println("Game starts! May the odds be ever in your favor :)");
            System.out.println("**************************************************");
            System.out.println();
            return 1;
        } else {
            System.out.println("**********************************************");
            System.out.println("Game exits:( Take care and come back anytime!");
            System.out.println("**********************************************");
            return 0;
        }
    }

    /*
        Ask for the names of the players
     */
    public static void getNames(int index) {
        switch (index) {
            case 1:
                System.out.print("Please enter YOUR name: ");
                break;
            case 2:
                System.out.print("Please enter YOUR FRIEND'S name: ");
                break;
            default:
                System.out.println("Index can only be 1/2!");
        }
    }

    /*
         Ask for players' preferred board size
     */
    public static void getPreferredSize(int index, int floor, int ceiling) {
        switch (index) {
            case 3:
                System.out.print("Please enter your preferred SIZE of the board");
                System.out.println(" (from " + floor + " to " + ceiling + ". 3 -> 3x3; 4 -> 4x4; 10 -> 10x10, etc): ");
                break;
            case 4:
                System.out.print("------------------------------------------------------");
                System.out.println("------------------------------------------");
                System.out.print("Invalid input! Please enter a valid SIZE from " + floor + " to " + ceiling + "!");
                System.out.println("(3 -> 3x3; 4 -> 4x4; 10 -> 10x10, etc)");
                System.out.print("------------------------------------------------------");
                System.out.println("------------------------------------------");
                System.out.println();
                break;
            case 5:
                System.out.println("----------------------------------");
                System.out.println("Invalid input! Must be an INTEGER!");
                System.out.println("----------------------------------");
                System.out.println();
                break;
            default:
                System.out.println("Index can only be 3/4/5!");
        }
    }

    /*
        Provide reminders of how to place a checker on the board to a certain player
     */
    public static void boardPlacement(int index, String playerName, int boardSize) {
        switch (index) {
            case 1:
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                System.out.print("Player " + playerName + ", ");
                System.out.println("please enter your move. (enter a value from 1 - " + boardSize * boardSize + ")");
                System.out.println("Example: 1 (means: cell[1, 1]); 3 (means: cell[1, 3])");
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                System.out.println();
                break;
            case 2:
                System.out.println("----------------------------------");
                System.out.println("Invalid input! Must be an INTEGER!");
                System.out.println("----------------------------------");
                break;
            case 3:
                System.out.println();
                System.out.println("-------------------------------------------------------");
                System.out.println("Input out of range or position taken! Please try again.");
                System.out.println("-------------------------------------------------------");
                break;
            case 4:
                // For Order and Chaos
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                System.out.println("Hi " + playerName + ", please enter the checker you want to place.");
                System.out.println("You can only enter 'x/X' or 'o/O'.");
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                break;
            case 5:
                // For Order and Chaos
                System.out.println("---------------------------------------------");
                System.out.println("Invalid input! Must be either 'x/X' or 'o/O'!");
                System.out.println("---------------------------------------------");
                break;
            default:
                System.out.println("Index for boardPlacement() must be 1/2/3!");
        }
    }

    /*
        Congratulate the winner
     */
    public static void winnerCongratulations(String winnerName) {
        System.out.println();
        System.out.println("***********************************************");
        System.out.println("Congratulations " + winnerName + "! You have won the game!");
        System.out.println("***********************************************");
        System.out.println();
    }

    /*
        Announce the stalemate
     */
    public static void stalemateAnnouncement() {
        System.out.println("*********************************");
        System.out.println("Ops! We have reached a stalemate~");
        System.out.println("*********************************");
    }

    /*
        Ask if the players want to play again
     */
    public static void newGamePrompt() {
        System.out.println("************************************************************");
        System.out.println("Do you guys want to enjoy another round?");
        System.out.println("Hit \"y/Y\" to kick off again! Or hit any other key to exit.");
        System.out.println("************************************************************");
        System.out.println();
    }

    /*
        After the game, print out the statistics of all previous rounds
     */
    public static void printSummaryResults(int wins1, String name1, int wins2, String name2) {
        String finalChampion;

        System.out.println("√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√");
        System.out.println("Fantastic performance guys! Superb!");
        System.out.println("Player " + name1 + " has won: " + wins1 + " time(s).");
        System.out.println("Player " + name2 + " has won: " + wins2 + " time(s).");

        if (wins1 == wins2) {
            System.out.println("Therefore, the final champion is: BOTH YOU GUYS!!!");
        } else {
            finalChampion = wins1 > wins2 ? name1 : name2;
            System.out.println("Therefore, the final winner is: " + finalChampion + "!!!");
        }

        System.out.println("√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√√");
        System.out.println();
    }

    /*
        After the game, ask the players if they want to switch to another board game
     */
    public static void switchGamePrompt(String gameName) {
        System.out.println("ºººººººººººººººººººººººººººººººººººººººººººººººººº");
        System.out.println("Do you guys want to try " + gameName + " and play? ^_^ ^_^");
        System.out.println("Hit \"y/Y\" to switch! Or hit any other key to exit.");
        System.out.println("ºººººººººººººººººººººººººººººººººººººººººººººººººº");
    }
}
