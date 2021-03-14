<h1 align = "center">Tic Tac Toe II - Dayong Wu</h1>

---

> ## Background
>
> In Assignment1 - Part2, I have created two board games, namely **Tic Tac Toe** and **Order and Chaos**, which generally allow two players to play against each other on a virtual game board. The **core** of this project is its **object-oriented design**, which renders the whole project of high **scalability** and **extendability**.

---

## General workflow of the game

1. Ask the players to choose a game from Tic Tac Toe and Order and Chaos.
2. Demonstrate welcome messages and important notifications, get the names of the two players. If the players have chosen Tic Tac Toe, the game will also ask for their preferred game board size.
3. Display the initial empty game board (customized by the board size entered by the users).
4. Randomly select one user to begin (or as the Order guy), and prompt them to enter their moves in alternate turns.
5. Maintain the game board and display the updated board after each move.
6. Check for winners or stalemate of the game in real time, and declare the result once a certain round reaches its finale.
7. At the end of each round, prompt the players if they want to enjoy a new round, or exit the current game.
8. Print out the statistics of the game once the users choose to exit. 
9. Ask the user if they want to switch to the other game (i.e. TTT -> OAC or OAC -> TTT).
10. Switch to that game if the players want, else quit the entire game.

---

## Classes of the project (Recommended Viewing Order)

### 1. GameEntrance.java

- This class serves as the entrance of the entire project.
- Only this class has the main() method.

### 2. GameStarter.java

- This class is responsible for choosing which game to play (i.e. Tic Tac Toe or Order and Chaos).

### 3. NotificationCenter.java

- This class is mainly designed for storing and displaying messages BEFORE, DURING, and AFTER the game.

### 4. BoardCell.java

- This class takes charge of the definition of a single board cell (i.e. a unit of a game board).

### 5. Checker.java

- This class holds the checker (or so-called marker), which shows as a char-type shape (e.g. 'X' or 'O')

### 6. Board.java

- This class serves as the parent class for all types of game boards.

### 7. TicTacToeBoard.java

- This class defines the game board particularly for Tic Tac Toe, and it inherits all the attributes and methods from the Board class.

### 8. OrderAndChaosBoard.java

- This class defines the game board particularly for Order and Chaos, and it inherits all the attributes and methods from the Board class.

### 9. Movable.java

- This **interface** provides as a contract to ensure a Player-type object has the ability to move the checkers and place them on the game board.

###  10. Player.java

- This class maintains all the operations that a player can perform.It implements the Movable interface, which ensures a Player-type object can move and place checkers on the board.

### 11. BoardGame.java

- This class serves as the parent class for all types of board games.

### 12. TicTacToe.java

- This class manages all the logics BEFORE, DURING, and AFTER the game of Tic Tac Toe.
- It inherits all the attributes from BoardGame class since it also belongs to a board game.

### 13. OrderAndChaos.java

- This class manages all the logics BEFORE, DURING, and AFTER the game of Order and Chaos.
- It inherits all the attributes from BoardGame class since it also belongs to a board game.

---

## Instructions on how to compile and run the program via Mac Terminal

1. Create a new folder on your MacBook and copy-paste all the 13 classes mentioned above to that folder.
2. Open Terminal and type "cd " (notice there is a  **whitespace** after "cd" !).
3. Then **drag the folder** to the terminal so that Mac can automatically complete the directory of that folder for you.
4. Press "Enter". Now you are inside the newly created folder.
5. Type "javac GameEntrance.java" in the terminal.
6. Type "java GameEntrance" in the terminal.
7. Now you should be able to play the game through Mac Terminal.
8. Please note that you should have a **JDK** installed in you MacBook with version at least **1.8**. 

---

## Instructions on how to compile and run the program in the IDE

1. Create a new JAVA project in IntelliJ IDEA CE.
2. Copy-paste all the 13 classes mentioned above to the **/src** folder.
3. Click the "Run" button or press Control+R to run the project.
4. Please note that you should have a **JDK** installed in you MacBook with version at least **1.8**. 

---

## * Highlights of the Project

### 1. O(1) time complexity algorithm to check the winner for both Tic Tac Toe and Order and Chaos !!!

- Please make sure to check out the overloaded **checkWinner( )** methods in both TicTacToe.java and OrderAndChaos.java !
- Although in fact both two games cost only very small memory in our computer, we should see from a bigger picture. 
- What if someday we need to use these games to train an AI board game computer? Well, at that time the board size could be very huge (e.g. 1 million x 1 million). Having a O(1) algorithm to detect the winner could speed up the calculation process tremendously.

### 2. Very concise main()

- As only an entrance of the project, the main() method has only **one** line.

- ```Java
  public final class GameEntrance {
      public static void main(String[] args) {
          new GameSelector().start(); // Start the whole game
      }
  }
  ```

### 3. Input validation checking

- Input validation checking happens everywhere throughout the project. Here is an example:
  - When the players enter their preferred board size, the input validation will be checked.
  - First its data type will be checked. If it is not of type int, system will prompt accordingly without crashing.
  - Then, the range of the input will be checked. If it is out of the range of the board size (i.e. out of  **[1, board size * board size]** ), system will prompt accordingly without crashing.

### 4. Neat game board design

- <img src="/Users/superkakayong/Library/Application Support/typora-user-images/image-20210222113849845.png" alt="image-20210222113849845" style="zoom:50%; float: left" />

### 5. High Scalability

- The board can be easily scaled from 3x3 to nxn since in my design, a board is just a collection of many board cells. 
- There is also floor and ceiling of the size of a game board, thus preventing the user to create super large or small board.
- In Order and Chaos, the board is fixed to be 6x6, thus ensures the safety of the game mechanism.
- Since there is a class represents the behaviours and abilities of a player, the game is also capable of being scaled to a team-based game (not implemented). The idea would be just creating two arrays of players and slightly modify the game logic.

### 6. High Extendability

- It is feasible for the game to be extended to turn-based games since in the **BoardGame** class we have the abstract method called **playerTurn( )**. Any specific game class can override this method to implement the turn-based mechanism.
- It is also very easy to extend the game to other cell-based board games since we have the **BoardCell** class and **Checker** class in the project. These two classes can be instantiated as many times as you want, thus any cell-based board games can be implemented without great difficulties.

### 7. Data Statistics

- In both Tic Tac Toe and Order and Chaos, the games can count the winning times of each round for each player and print out the results when they decide to exit the game.

### 8. Proper code format

- Proper indentations, very **detailed** comments, etc.

---

## Things Worth Noting

### 1. Winning Conditions for Order and Chaos

- We **don't** count 6-in-a-row as a win for the Order guy.
- For example, if there are 6 'X's in the first row without a 5-in-a-row happens, we will **not** announce Order as the winner. The game will just continue (if not all 36 cells are filled at that stage, otherwise the Chaos guy wins).
- Chaos wins only if all the 36 cells are filled and there still does not exist a 5-in-a-row.

