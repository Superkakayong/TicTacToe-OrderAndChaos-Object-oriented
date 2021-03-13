/**
 * This class takes charge of the management of a single board cell (i.e. a unit of a game board).
 */
public class BoardCell {
    private Checker checker;

    private  BoardCell() {}

    public BoardCell(Checker checker) {
        this.setChecker(checker);
    }

    public void setChecker(Checker checker) {
        this.checker = checker;
    }

    public char getChecker() {
        return checker.getCheckerSign();
    }
}
