/**
 * This class is mainly designed for storing and displaying messages BEFORE, DURING, and AFTER the game.
 */
final public class NotificationCenter {
    private NotificationCenter() {}

    public static void welcome() {
        System.out.println("Welcome to Tic Tac Toe! Please be noticed of the followings before our game starts:");

        System.out.println();
        System.out.println("    1. Make sure you are playing this game with one and only one of your friends.");
        System.out.println("    2. The system will randomly decide which one of you two to begin first.");
        System.out.println("    3. You can choose the size of the board from 3x3 to 10x10.");
        System.out.println();

        System.out.println("Hit \"y/Y\" to start the game. Or hit any other key to exit.");
    }

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

    public static void namesAndSize(int index) {
        switch (index) {
            case 1:
                System.out.print("Please enter YOUR name: ");
                break;
            case 2:
                System.out.print("Please enter YOUR FRIEND'S name: ");
                break;
            case 3:
                System.out.print("Please enter your preferred SIZE of the board");
                System.out.println(" (from 3 to 10. 3 -> 3x3; 4 -> 4x4; 10 -> 10x10, etc): ");
                break;
            case 4:
                System.out.print("------------------------------------------------------");
                System.out.println("------------------------------------------");
                System.out.print("Invalid input! Please enter a valid SIZE from 3 to 10! ");
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
                System.out.println("Index can only be 1/2/3/4/5!");
        }
    }

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
            default:
                System.out.println("Index for boardPlacement() must be 1/2/3!");
        }
    }

    public static void winnerCongratulations(String winnerName) {
        System.out.println();
        System.out.println("***********************************************");
        System.out.println("Congratulations " + winnerName + "! You have won the game!");
        System.out.println("***********************************************");
        System.out.println();
    }

    public static void stalemateAnnouncement() {
        System.out.println("*********************************");
        System.out.println("Ops! We have reached a stalemate~");
        System.out.println("*********************************");
    }

    public static void newGamePrompt() {
        System.out.println("************************************************************");
        System.out.println("Do you guys want to enjoy another round?");
        System.out.println("Hit \"y/Y\" to kick off again! Or hit any other key to exit.");
        System.out.println("************************************************************");
        System.out.println();
    }

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
}
