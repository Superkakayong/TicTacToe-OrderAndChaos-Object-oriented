import java.util.Random;
import java.util.Scanner;

/**
 * This class serves as the parent class for all types of board games.
 */
public abstract class BoardGame {
    protected Scanner sc;
    protected String nameOfPlayer1;
    protected String nameOfPlayer2;
    protected boolean shouldGenerateRandomID;
    protected int boardSize;
    protected int rowPlacement; // Row index of a single placement
    protected int colPlacement; // Column index of a single placement
    protected int numberOfPlacements; // Total number of placements during the game
    protected int numberOfWinsByPlayer1;
    protected int numberOfWinsByPlayer2;

    protected Player p1;
    protected Player p2;
    protected Board board;

    /*
        The three 2-D arrays serve as helpers for determining the winner in O(1) TIME !!!
     */
    protected int[][] rows;
    protected int[][] columns;
    protected int[][] diagonals;

    public BoardGame() {}

    public BoardGame(int gameType) {
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

        prepare(gameType);
        getPlayerNames();
    }

    public void setP1(String nameOfPlayer1) {
        this.p1 = new Player(nameOfPlayer1);
    }

    public Player getP1() {
        return p1;
    }

    public void setP2(String nameOfPlayer2) {
        this.p2 = new Player(nameOfPlayer2);
    }

    public Player getP2() {
        return p2;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return this.board;
    }

    // The play() method should be the chief method of any board game class
    public abstract void play();

    // DURING the game -> Let the designated player play
    public abstract int playerTurn(int id);

    // BEFORE the game -> Display welcome messages and other important notifications
    private void prepare(int gameType) {
        NotificationCenter.welcome(gameType);
        final String DECISION = sc.nextLine();
        int code = NotificationCenter.startOrExit(DECISION);
        if (code == 0) {System.exit(0);}
    }

    // DURING the game -> Ask for the names of the two players
    protected void getPlayerNames() {
        NotificationCenter.getNames(1);
        nameOfPlayer1 = sc.nextLine();
        NotificationCenter.getNames(2);
        nameOfPlayer2 = sc.nextLine();
    }

    /*
        DURING the game -> Ask for the preferred board size of the two players
        Ceiling and floor should be passed in to limit the board size
     */
    protected void getPreferredBoardSize(int floor, int ceiling) {
        while (true) {
            NotificationCenter.getPreferredSize(3, floor, ceiling);

            if (sc.hasNextInt()) {
                boardSize = sc.nextInt();

                if (boardSize < floor || boardSize > ceiling) {
                    // The range of board size should be [3, 10]
                    NotificationCenter.getPreferredSize(4, floor, ceiling);
                    continue;
                } else {
                    break;
                }
            } else {
                // Input is invalid (i.e. not an integer)
                sc.next(); // IMPORTANT! Free the buffer!
                NotificationCenter.getPreferredSize(5, floor, ceiling);
            }
        }
    }

    // DURING the game -> Choose a player to begin at random
    public int idGenerator() {
        Random seed = new Random();
        int id;
        id = seed.nextInt(2) + 1;

        return id;
    }

    /*
        A board game should have the ability to check if there is a winner generated
     */
    public abstract void checkWinner();

    // A board game should ask the players if they want to play again or exit after a round
    public abstract void playAgainOrExit();
}
