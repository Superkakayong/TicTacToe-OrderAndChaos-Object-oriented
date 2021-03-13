/**
 * This class controls all the attributes that a game board possesses.
 */
public class GameBoard {
    private int boardSize;
    private BoardCell[][] board;

    private GameBoard() {}

    public GameBoard(int boardSize) {
        this.setBoardSize(boardSize);
        this.setBoard(boardSize);
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public void setBoard(int boardSize) {
        this.board = new BoardCell[boardSize][boardSize];

        // initialize an empty board for the game
        for (int i = 0; i < boardSize; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                board[i][j] = new BoardCell(new Checker(' '));
            }
        }
    }

    public BoardCell[][] getBoard() {
        return this.board;
    }

    /*
        A game board can print itself
     */
    public void printBoard() {
        StringBuilder topBottomBoundary = new StringBuilder("");

        for (int i = 0; i < this.boardSize; ++i) {
            topBottomBoundary.append("+---");
        }
        topBottomBoundary.append("+");

        for (BoardCell[] row : this.board) {
            System.out.println(topBottomBoundary);

            for (BoardCell cell : row) {
                System.out.print("| " + cell.getChecker() + " ");
            }
            System.out.println("|");
        }
        System.out.println(topBottomBoundary);
        System.out.println();
    }

    /*
        A game board can self-check if it can accommodate a particular placement
     */
    public boolean isValidPosition(int position) {
        if (position < 1 || position > (this.boardSize * this.boardSize)) {return false;}

        int count = 1, i = 0, j = 0;
        boolean flag = true;

        for (i = 0; i < this.board.length; ++i) {
            for (j = 0; j < this.board[0].length; ++j) {
                if (count == position) {
                    flag = false; break;
                }
                ++count;
            }

            if (!flag) {break;}
        }

        if (board[i][j].getChecker() != ' ') {return false;}

        return true;
    }
}
