/**
 * This class defines the game board particularly for Tic Tac Toe,
 * and it inherits all the attributes and methods from the Board class.
 */
final public class TicTacToeBoard extends Board {
    private int boardSize;
    private BoardCell[][] board;

    private TicTacToeBoard() {}

    public TicTacToeBoard(int boardSize) {
        super(boardSize);
        this.boardSize = this.getBoardSize();
        this.board = this.getBoard();
    }
}
