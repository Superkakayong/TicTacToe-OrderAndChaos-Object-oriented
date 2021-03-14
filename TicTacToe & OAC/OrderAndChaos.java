/**
 * This class manages all the logics BEFORE, DURING, and AFTER the game of Order and Chaos.
 * It inherits all the attributes from BoardGame class since it also belongs to a board game.
 */
public final class OrderAndChaos extends BoardGame{
    private int checkerCode = 0; // Represents the codes for the checkers (i.e. 'X' or 'O')
    private boolean firstPlay; // Denotes if it is the first play (turn) of the game

    private Player order;
    private Player chaos;
    private OrderAndChaosBoard orderAndChaosBoard;

    public OrderAndChaos() {
        super(2);

        // Polymorphism Implementation (upcast)
        setBoard(new OrderAndChaosBoard());

        if (getBoard() instanceof OrderAndChaosBoard) {
            // Polymorphism Implementation (downcast)
            this.orderAndChaosBoard = (OrderAndChaosBoard) getBoard();
        }

        /*
            The three 2-D arrays serve as helpers for determining the winner in O(1) TIME !!!
                1.The FIRST dimension defines the number of rows/columns/diagonals on the game board
                2. Note that there are 6 possible diagonals for a 5-in-a-row to happen
                3. The SECOND dimension defines the checker sign (i.e. 'X' or 'O')

            So if rows[1][1] = 5, we know that there are 5 'O's in the second row, which can help
            us determine the winner much easier and faster
         */
        rows = new int[6][2];
        columns = new int[6][2];
        diagonals = new int[6][2];
    }

    @Override
    public void play() {
        int turnID = 0, winnerID = 0;
        shouldGenerateRandomID = true;
        firstPlay = true;

        while (true) {
            if (shouldGenerateRandomID) {
                turnID = idGenerator();
                setP1(nameOfPlayer1);
                setP2(nameOfPlayer2);

                if (turnID == 1) {
                    // If turnID is 1, assign the first player as the order guy
                    order = getP1();
                    chaos = getP2();
                } else {
                    // If turnID is 2, assign the second player as the order guy
                    order = getP2();
                    chaos = getP1();
                }

                // Only in the first turn should we generate a random ID for players to start
                shouldGenerateRandomID = false;
            }

            if (firstPlay) {
                // Enforce Order to play first
                turnID = playerTurn(1);
                firstPlay = false;
            } else {
                // After the first turn, players should take turns to play
                turnID = playerTurn(turnID);
            }

            ++numberOfPlacements;
            winnerID = checkWinner(rowPlacement, colPlacement, checkerCode);

            if (winnerID == 1) {
                // Order wins
                NotificationCenter.winnerCongratulations(order.getName().concat(" (Order)"));
                if (order.getName().equals(getP1().getName())) {
                    ++numberOfWinsByPlayer1;
                } else {
                    ++numberOfWinsByPlayer2;
                }
            } else if (numberOfPlacements == 36 && winnerID == -1) {
                // Chaos wins
                NotificationCenter.winnerCongratulations(chaos.getName().concat(" (Chaos)"));
                if (chaos.getName().equals(getP1().getName())) {
                    ++numberOfWinsByPlayer1;
                } else {
                    ++numberOfWinsByPlayer2;
                }
            } else {
                // Game continues
                orderAndChaosBoard.printBoard();
                continue; // IMPORTANT! If no winner generated, skip the 2 lines below
            }

            orderAndChaosBoard.printBoard(); // Update and print the current game board
            playAgainOrExit(); // After a round has finished, decide to play again or exit
        }
    }

    // DURING the game -> Let the designated player play
    @Override
    public int playerTurn(int turnID) {
        String piece, identification;
        char pieceValue;

        if (turnID == 1) {
            // Order plays
            identification = order.getName().concat(" (Order)");
            NotificationCenter.boardPlacement(4, identification, 6);
            piece = sc.nextLine(); // Choose to play an 'X' or an 'O'

            while (!piece.equalsIgnoreCase("X") && !piece.equalsIgnoreCase("O")) {
                // Invalid input
                NotificationCenter.boardPlacement(5, identification, 6);
                piece = sc.nextLine();
            }

            pieceValue = piece.toUpperCase().toCharArray()[0];
            order.move(orderAndChaosBoard, new Checker(pieceValue)); // Place the move
            rowPlacement = order.getRowIndex();
            colPlacement = order.getColIndex();
            turnID = 2; // Player should take turns to play
        } else {
            // Chaos plays
            identification = chaos.getName().concat(" (Chaos)");
            NotificationCenter.boardPlacement(4, identification, 6);
            piece = sc.nextLine();

            while (!piece.equalsIgnoreCase("X") && !piece.equalsIgnoreCase("O")) {
                NotificationCenter.boardPlacement(5, identification, 6);
                piece = sc.nextLine();
            }

            pieceValue = piece.toUpperCase().toCharArray()[0];
            chaos.move(orderAndChaosBoard, new Checker(pieceValue));
            rowPlacement = chaos.getRowIndex();
            colPlacement = chaos.getColIndex();
            turnID = 1; // Player should take turns to play
        }

        /*
            Get the checkerCode in order to update the three 2-D arrays
            i.e. rows[][], columns[][], and diagonals[][]
         */
        if (pieceValue == 'X') {
            checkerCode = 0;
        } else {
            checkerCode = 1;
        }

        return turnID;
    }

    // DURING the game -> Check if there is a winner generated
    @Override
    public void checkWinner() {}

    /*
        Overload the checkWinner() method
        Please note that we don't count a 6-in-a-row as a win of Order
        Order can win only by achieving a 5-in-a-row, otherwise the game continues
     */
    public int checkWinner(int row, int col, int checkerCode) {
        BoardCell[][] currentBoard = this.orderAndChaosBoard.getBoard();

        if (++rows[row][checkerCode] == 5) {
            // If a row has 5 like pieces
            if (currentBoard[row][0].getChecker() != currentBoard[row][5].getChecker()) {
                // If the 5 like pieces are consecutive (i.e. 5-in-a-row)
                return 1; // 1 means Order wins
            }
        }
        if (++columns[col][checkerCode] == 5) {
            if (currentBoard[0][col].getChecker() != currentBoard[5][col].getChecker()) {
                return 1;
            }
        }
        if (row == col && ++diagonals[0][checkerCode] == 5) {
            if (currentBoard[0][0].getChecker() != currentBoard[5][5].getChecker()) {
                return 1;
            }
        }
        if ((row + col == 5) && ++diagonals[1][checkerCode] == 5) {
            if (currentBoard[0][5].getChecker() != currentBoard[5][0].getChecker()) {
                return 1;
            }
        }
        if ((row == (col - 1)) && ++diagonals[2][checkerCode] == 5) {
            return 1;
        }
        if ((row + col == 4) && ++diagonals[3][checkerCode] == 5) {
            return 1;
        }
        if ((col == (row - 1)) && ++diagonals[4][checkerCode] == 5) {
            return 1;
        }
        if ((row + col == 6) && ++diagonals[5][checkerCode] == 5) {
            return 1;
        }

        return -1; // Order has not won yet
    }

    // AFTER the game -> Let players decide to have another round or just exit the game
    @Override
    public void playAgainOrExit() {
        final String userDecision;
        final String switchGame;

        NotificationCenter.newGamePrompt();

        userDecision = sc.nextLine();

        if (userDecision.equalsIgnoreCase("Y")) {
            // Players decide to play again
            NotificationCenter.startOrExit("Y");

            // Reset all the member variables
            shouldGenerateRandomID = true;
            firstPlay = true;
            numberOfPlacements = 0;
            orderAndChaosBoard = new OrderAndChaosBoard();
            rows = new int[6][2];
            columns = new int[6][2];
            diagonals = new int[6][2];
        } else {
            NotificationCenter.printSummaryResults(numberOfWinsByPlayer1, p1.getName(),
                                                   numberOfWinsByPlayer2, p2.getName());

            // Ask the players if they want to switch to Tic Tac Toe
            NotificationCenter.switchGamePrompt("Tic Tac Toe");

            switchGame = sc.nextLine();
            if (switchGame.equalsIgnoreCase("Y")) {
                // Players want to switch to Tic Tac Toe
                new TicTacToe().play();
            } else {
                // Players want to end the entire game
                NotificationCenter.startOrExit("Exit");

                // Close all the Scanners
                sc.close();
                order.getSC().close();
                chaos.getSC().close();

                System.exit(0);
            }
        }
    }
}
