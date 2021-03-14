import java.util.Scanner;

/**
 * This class is responsible for choosing which game to play (i.e. Tic Tac Toe or Order and Chaos)
 */
public final class GameSelector {
    private Scanner sc;
    private String selection;

    public GameSelector() {
        NotificationCenter.whichGame();
        sc = new Scanner(System.in);
        selection = sc.nextLine();
    }

    public void start() {
        // If the input is neither an 'a/A' not a 'b/B', ask the user to input again
        while (!selection.equalsIgnoreCase("A") && !selection.equalsIgnoreCase("B")) {
            NotificationCenter.notAOrB();
            selection = sc.nextLine();
        }

        if (selection.equalsIgnoreCase("A")) {
            new TicTacToe().play();
        } else {
            new OrderAndChaos().play();
        }
    }
}
