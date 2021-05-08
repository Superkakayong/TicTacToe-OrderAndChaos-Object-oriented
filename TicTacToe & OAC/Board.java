/**
 * This class serves as the parent class for all types of game boards.
 */
public class Board {
    private int boardSize;
    private BoardCell[][] board; 

    public Board() {}

    public Board(int boardSize) {
        this.setBoardSize(boardSize);
        this.setBoard(boardSize);
    }

    private void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    private void setBoard(int boardSize) {
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
        A game board can print itself by using its size
     */
    public void printBoard() {
        StringBuilder topBottomBoundary = new StringBuilder("");

        // Print the top boundary
        for (int i = 0; i < this.boardSize; ++i) {
            topBottomBoundary.append("+---");
        }
        topBottomBoundary.append("+");

        // Print every cell of the board
        for (BoardCell[] row : this.board) {
            System.out.println(topBottomBoundary);

            for (BoardCell cell : row) {
                System.out.print("| " + cell.getChecker() + " ");
            }
            System.out.println("|");
        }

        // Print the bottom boundary
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

        // Find the [row, column] coordinate of the passed-in value (i.e. int position)
        for (i = 0; i < this.board.length; ++i) {
            for (j = 0; j < this.board[0].length; ++j) {
                if (count == position) {
                    flag = false; break;
                }
                ++count;
            }

            if (!flag) {break;}
        }

        // If the position has already been taken, return false
        if (board[i][j].getChecker() != ' ') {return false;}

        return true;
    }
}
