/**
 * This interface provides as a contract to ensure a Player-type object has the ability
 * to move the checkers and place them on the game board.
 */
public interface Movable {
    void move(Board board, Checker checker);
}
