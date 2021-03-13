import java.util.Random;
import java.util.Scanner;

/**
 * This class manages all the logics BEFORE, DURING, and AFTER the game of Tic Tac Toe.
 */
final public class TicTacToe {
    private static Scanner sc;
    private static String nameOfPlayer1;
    private static String nameOfPlayer2;
    private static boolean shouldGenerateRandomID;
    private static int boardSize;
    private static int rowPlacement; // Row index of a single placement
    private static int colPlacement; // Column index of a single placement
    private static int numberOfPlacements; // Total number of placements during the game
    private static int numberOfWinsByPlayer1;
    private static int numberOfWinsByPlayer2;

    private Player player1;
    private Player player2;
    private GameBoard gameBoard;

    /*
        "The value x of rows[1][1]" indicates:
            - Player 1 has made x placements on row 1
            - So if rows[i][j] = boardSize, we know that player i has won
            - Columns and diagonals have just the same logics
     */
    private int[][] rows;
    private int[][] columns;
    private int[][] diagonals;

    public TicTacToe() {
        sc = new Scanner(System.in);

        nameOfPlayer1 = "";
        nameOfPlayer2 = "";
        shouldGenerateRandomID = false;
        boardSize = -1;
        rowPlacement = -1;
        colPlacement = -1;
        numberOfPlacements = 0;
        numberOfWinsByPlayer1 = 0;
        numberOfWinsByPlayer2 = 0;

        this.prepare();
        this.getPlayerNames();
        this.getPreferredBoardSize();

        this.player1 = new Player(nameOfPlayer1);
        this.player2 = new Player(nameOfPlayer2);
        this.gameBoard = new GameBoard(boardSize);

        this.rows = new int[3][boardSize];
        this.columns = new int[3][boardSize];
        this.diagonals = new int[3][2];
    }

    /*
        This is the chief method of the class
     */
    public void play() {
        int playerID = 0, winnerID = 0;
        shouldGenerateRandomID = true;

        while (true) {
            // Only when we start a new game should we decide which player to begin
            if (shouldGenerateRandomID) {
                playerID = this.idGenerator();
                shouldGenerateRandomID = false;
            }

            playerID = this.playerTurn(playerID); // Players take turn to play
            ++numberOfPlacements;
            winnerID = this.checkWinner(rowPlacement, colPlacement, playerID);

            if (winnerID == 1) {
                NotificationCenter.winnerCongratulations(player2.getName());
                ++numberOfWinsByPlayer2;
            } else if (winnerID == 2) {
                NotificationCenter.winnerCongratulations(player1.getName());
                ++numberOfWinsByPlayer1;
            } else if ((numberOfPlacements == boardSize * boardSize) && (winnerID == -1)) {
                NotificationCenter.stalemateAnnouncement();
            } else {
                continue; // IMPORTANT! If no winner generated, skip the code below.
            }

            this.gameBoard.printBoard(); // Update and print the current game board
            this.playAgainOrExit(); // After a round has finished, decide to play again or exit
        }
    }

    /*
        BEFORE the game -> Display welcome messages and other important notifications
     */
    private void prepare() {
        NotificationCenter.welcome();
        final String DECISION = sc.nextLine();
        int code = NotificationCenter.startOrExit(DECISION);
        if (code == 0) {System.exit(0);}
    }

    /*
        DURING the game -> Ask for the names of the two players and their preferred board size
     */
    private void getPlayerNames() {
        NotificationCenter.namesAndSize(1);
        nameOfPlayer1 = sc.nextLine();
        NotificationCenter.namesAndSize(2);
        nameOfPlayer2 = sc.nextLine();
    }

    private void getPreferredBoardSize() {
        while (true) {
            NotificationCenter.namesAndSize(3);

            if (sc.hasNextInt()) {
                boardSize = sc.nextInt();

                if (boardSize < 3 || boardSize > 10) {
                    // The range of board size should be [3, 10]
                    NotificationCenter.namesAndSize(4);
                    continue;
                } else {
                    break;
                }
            } else {
                // Input is invalid (i.e. not an integer)
                sc.next(); // IMPORTANT! Free the buffer!
                NotificationCenter.namesAndSize(5);
            }
        }
    }

    /*
        DURING the game -> Choose a player to begin at random
     */
    private int idGenerator() {
        Random seed = new Random();
        int id;
        id = seed.nextInt(2) + 1;

        return id;
    }

    /*
        DURING the game -> Let the designated player play
     */
    private int playerTurn(int playerID) {
        if (playerID == 1) {
            Checker checker1 = new Checker('X');
            this.player1.move(this.gameBoard, checker1);
            rowPlacement = this.player1.getRowIndex();
            colPlacement = this.player1.getColIndex();
            playerID = 2; // Player should take turns to play
        } else {
            Checker checker2 = new Checker('O');
            this.player2.move(this.gameBoard, checker2);
            rowPlacement = this.player2.getRowIndex();
            colPlacement = this.player2.getColIndex();
            playerID = 1; // Player should take turns to play
        }

        return playerID;
    }

    /*
        DURING the game -> Check if there is a winner generated
     */
    private int checkWinner(int row, int col, int playerID) {
        if (++this.rows[playerID][row] == boardSize) {
            return playerID;
        }
        if (++this.columns[playerID][col] == boardSize) {
            return playerID;
        }
        if (row == col && ++this.diagonals[playerID][0] == boardSize) {
            return playerID;
        }
        if ((row + col == boardSize - 1) && ++this.diagonals[playerID][1] == boardSize) {
            return playerID;
        }

        return -1;
    }

    /*
        AFTER the game -> Let players decide to have another round or just exit the game
     */
    private void playAgainOrExit() {
        final String userDecision;

        NotificationCenter.newGamePrompt();

        sc = new Scanner((System.in));
        userDecision = sc.nextLine();

        if (userDecision.equalsIgnoreCase("Y")) {
            // Players decide to play again
            NotificationCenter.startOrExit("Y");
            this.getPreferredBoardSize(); // Allows the players to change the board size

            // Reset all the member variables
            shouldGenerateRandomID = true;
            numberOfPlacements = 0;

            this.gameBoard = new GameBoard(boardSize);
            this.rows = new int[3][boardSize];
            this.columns = new int[3][boardSize];
            this.diagonals = new int[3][2];
        } else {
            // Players decide to exit the game
            NotificationCenter.printSummaryResults(numberOfWinsByPlayer1, player1.getName(),
                                                   numberOfWinsByPlayer2, player2.getName());
            NotificationCenter.startOrExit("Exit");

            // Close all the Scanners
            sc.close();
            this.player1.getSC().close();
            this.player2.getSC().close();

            System.exit(0);
        }
    }
}
