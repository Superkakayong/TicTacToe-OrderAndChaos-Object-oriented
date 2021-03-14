/**
 * This class defines the game board particularly for Order and Chaos,
 * and it inherits all the attributes and methods from the Board class.
 */
final public class OrderAndChaosBoard extends Board {
    private int boardSize;
    private BoardCell[][] board;

    public OrderAndChaosBoard() {
        super(6); // This enforces the board size to be 6x6!
        this.boardSize = this.getBoardSize();
        this.board = this.getBoard();
    }
}
