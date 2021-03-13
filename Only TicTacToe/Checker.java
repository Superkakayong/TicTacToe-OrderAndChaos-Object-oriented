/**
 * This class holds the checker (or so-called marker), which can be in the shape of either an 'X' or an 'O'.
 */
public class Checker {
    private char checkerSign;

    private Checker() {}

    public Checker(char checkerSign) {
        this.setCheckerSign(checkerSign);
    }

    public void setCheckerSign(char checkerSign) {
        this.checkerSign = checkerSign;
    }

    public char getCheckerSign() {
        return checkerSign;
    }
}
