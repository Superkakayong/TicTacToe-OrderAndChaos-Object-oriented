import java.util.Scanner;

/**
 * This class maintains all the operations that a player can perform.
 * It implements the Movable interface, which ensures a Player-type object can
 * move and place checkers on the board.
 */
public class Player implements Movable{
    private String name;
    private Scanner sc;
    private int rowIndex; // Row index of a single placement
    private int colIndex; // Column index of a single placement

    private Player() {}

    public Player(String name) {
        this.name = name;
        sc = new Scanner(System.in);
        this.rowIndex = -1;
        this.colIndex = -1;
    }

    public String getName() {
        return this.name;
    }

    public Scanner getSC() {
        return this.sc;
    }

    public int getRowIndex() {
        return this.rowIndex;
    }

    public int getColIndex() {
        return this.colIndex;
    }

    /*
        A player can move a checker to place it on the board
     */
    @Override
    public void move(Board board, Checker checker) {
        int movePos = -1; // Position on the board to move

        // Validate the input position that the player wants to place to the board
        while ((board instanceof TicTacToeBoard || board instanceof OrderAndChaosBoard)) {
            NotificationCenter.boardPlacement(1, this.name, board.getBoardSize());
            board.printBoard();

            if (sc.hasNextInt()) {
                movePos = sc.nextInt();

                if (!board.isValidPosition(movePos)) {
                    // Input is an integer, but it leads to an illegal position on the board
                    NotificationCenter.boardPlacement(3, this.name, board.getBoardSize());
                    continue;
                } else {
                    // Input is a valid integer which points to an open vacancy on the board
                    break;
                }
            } else {
                // Input is not an integer
                sc.next();
                NotificationCenter.boardPlacement(2, this.name, board.getBoardSize());
            }
        }

        // Place the move to the board
        placeTheMove(board, checker, movePos);
    }

    /*
        Place the move that a player made to the game board
     */
    public void placeTheMove(Board gameBoard, Checker checker, int movePosition) {
        int count = 1, i = 0, j = 0;
        boolean flag = true;

        for (i = 0; i < gameBoard.getBoard().length; ++i) {
            for (j = 0; j < gameBoard.getBoard()[i].length; ++j) {
                if (count == movePosition) {
                    gameBoard.getBoard()[i][j].setChecker(checker);
                    flag = false; break;
                }
                ++count;
            }

            if (!flag) {break;}
        }

        this.rowIndex = i;
        this.colIndex = j;
    }
}
