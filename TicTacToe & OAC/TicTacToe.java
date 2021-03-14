import java.util.Scanner;

/**
 * This class manages all the logics BEFORE, DURING, and AFTER the game of Tic Tac Toe.
 * It inherits all the attributes from BoardGame class since it also belongs to a board game.
 */
public final class TicTacToe extends BoardGame{
    private Player player1;
    private Player player2;
    private TicTacToeBoard ticTacToeBoard;

    public TicTacToe() {
        super(1);

        setP1(nameOfPlayer1);
        setP2(nameOfPlayer2);
        player1 = getP1();
        player2 = getP2();

        // Enforce the floor and ceiling of the board size of Tic Tac Toe
        getPreferredBoardSize(3, 10);

        // Polymorphism Implementation (upcast)
        setBoard(new TicTacToeBoard(boardSize));

        if (getBoard() instanceof TicTacToeBoard) {
            // Polymorphism Implementation (downcast)
            ticTacToeBoard = (TicTacToeBoard) getBoard();
        }

        /*
        "The value x of rows[1][1]" indicates:
            - Player 1 has made x placements on row 1
            - So if rows[i][j] = boardSize, we know that player i has won
            - Columns and diagonals have just the same logics
        */
        rows = new int[3][boardSize];
        columns = new int[3][boardSize];
        diagonals = new int[3][2];
    }

    // This is the chief method of the class
    @Override
    public void play() {
        int playerID = 0, winnerID = 0;
        shouldGenerateRandomID = true;

        while (true) {
            // Only when we start a new game should we decide which player to begin
            if (shouldGenerateRandomID) {
                playerID = idGenerator();
                shouldGenerateRandomID = false;
            }

            playerID = playerTurn(playerID); // Players take turn to play
            ++numberOfPlacements;
            winnerID = checkWinner(rowPlacement, colPlacement, playerID);

            if (winnerID == 1) {
                // Player 2 wins (because playerTurn() always returns the opposite playerID)
                NotificationCenter.winnerCongratulations(player2.getName());
                ++numberOfWinsByPlayer2;
            } else if (winnerID == 2) {
                // Player 1 wins
                NotificationCenter.winnerCongratulations(player1.getName());
                ++numberOfWinsByPlayer1;
            } else if ((numberOfPlacements == boardSize * boardSize) && (winnerID == -1)) {
                // Stalemate
                NotificationCenter.stalemateAnnouncement();
            } else {
                continue; // IMPORTANT! If no winner generated, skip the code below.
            }

            ticTacToeBoard.printBoard(); // Update and print the current game board
            playAgainOrExit(); // After a round has finished, decide to play again or exit
        }
    }

    // DURING the game -> Let the designated player play
    @Override
    public int playerTurn(int playerID) {
        if (playerID == 1) {
            Checker checker1 = new Checker('X');
            player1.move(ticTacToeBoard, checker1);
            rowPlacement = player1.getRowIndex();
            colPlacement = player1.getColIndex();
            playerID = 2; // Player should take turns to play
        } else {
            Checker checker2 = new Checker('O');
            player2.move(ticTacToeBoard, checker2);
            rowPlacement = player2.getRowIndex();
            colPlacement = player2.getColIndex();
            playerID = 1; // Player should take turns to play
        }

        return playerID;
    }

    // DURING the game -> Check if there is a winner generated
    @Override
    public void checkWinner() {}

    // Overload the checkWinner() method
    public int checkWinner(int row, int col, int playerID) {
        if (++rows[playerID][row] == boardSize) {
            /*
                If a player has placed n = boardSize checkers on the board,
                (s)he wins
             */
            return playerID;
        }
        if (++columns[playerID][col] == boardSize) {
            return playerID;
        }
        if (row == col && ++diagonals[playerID][0] == boardSize) {
            return playerID;
        }
        if ((row + col == boardSize - 1) && ++diagonals[playerID][1] == boardSize) {
            return playerID;
        }

        return -1; // No winner generated
    }

    // AFTER the game -> Let players decide to have another round or just exit the game
    @Override
    public void playAgainOrExit() {
        final String userDecision;
        final String switchGame;

        NotificationCenter.newGamePrompt();

        sc = new Scanner((System.in));
        userDecision = sc.nextLine();

        if (userDecision.equalsIgnoreCase("Y")) {
            // Players decide to play again
            NotificationCenter.startOrExit("Y");

            getPreferredBoardSize(3, 10); // Allows the players to change the board size

            // Reset all the member variables
            shouldGenerateRandomID = true;
            numberOfPlacements = 0;

            ticTacToeBoard = new TicTacToeBoard(boardSize);
            rows = new int[3][boardSize];
            columns = new int[3][boardSize];
            diagonals = new int[3][2];
        } else {
            // Players decide to exit the game
            NotificationCenter.printSummaryResults(numberOfWinsByPlayer1, player1.getName(),
                                                   numberOfWinsByPlayer2, player2.getName());

            // Ask the players if they want to switch to Order and Chaos
            NotificationCenter.switchGamePrompt("Order and Chaos");

            switchGame = sc.nextLine();
            if (switchGame.equalsIgnoreCase("Y")) {
                // Players agree to switch
                new OrderAndChaos().play();
            } else {
                // Players want to exit the entire game
                NotificationCenter.startOrExit("Exit");

                // Close all the Scanners
                sc.close();
                player1.getSC().close();
                player2.getSC().close();

                System.exit(0);
            }
        }
    }
}
