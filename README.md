<h1 align = "center">TicTacToe-I-Object-oriented => [Dayong Wu]</h1>

---

> ## Background
>
> Hi guys! In this project, I have created a Tic-Tac-Toe game, which generally allows two players to play against each other on a virtual game board. The **core** of this project is its **object-oriented design**, which will be discussed in this doc.
>
> **If you find this project helpful, please consider giving it a STAR ^, which will be the biggest encouragement to me!**

## General workflow of the game

1. Demonstrate welcome messages and important notifications, get the names of the two players, and get their preferred game board size.
2. Display the initial empty game board (customized by the board size entered by the users).
3. Randomly select one user to begin, and prompt them to enter their moves in alternate turns.
4. Maintain the game board and display the updated board after each move.
5. Check for winners or stalemate of the game in real time, and declare the result once a certain round reaches its finale.
6. At the end of each round, prompt the players if they want to enjoy a new round, or exit the game.
7. Print out the statistics of the game once the users choose to exit.

## Classes of the project

### 1. GameStarter.java

- This class serves as the starter/entrance of the entire project.
- **Only** this class has the main() method.

### 2. TicTacToe.java

- This class manages all the logics BEFORE, DURING, and AFTER the game of Tic Tac Toe.

### 3. Player.java

- This class maintains all the operations that a player can perform.

### 4. GameBoard.java

- This class controls all the attributes that a game board possesses.

### 5. BoardCell.java

- This class takes charge of the management of a single board cell (i.e. a unit of a game board).

### 6. Checker.java

- This class holds the checker (or so-called marker), which can be in the shape of either an 'X' or an 'O'.

### 7. NotificationCenter.java

- This class is mainly designed for storing and displaying messages BEFORE, DURING, and AFTER the game.

---

## Instructions on how to compile and run the program via Mac Terminal

1. Create a new folder on your MacBook and copy-paste all the 7 classes mentioned above to that folder.
2. Open Terminal and type "cd " (notice there is a  **whitespace** after "cd" !).
3. Then **drag the folder** to the terminal so that Mac can automatically complete the directory of that folder for you.
4. Press "Enter". Now you are inside the newly created folder.
5. Type "javac GameStarter.java" in the terminal.
6. Type "java GameStarter" in the terminal.
7. Now you should be able to play the game through Mac Terminal.
8. Please note that you should have a **JDK** installed in you MacBook with version at least **1.8**. 

---

## Instructions about how to setup the project in the IDE console

1. Open IntelliJ (or any other IDE like Eclipse). Create a new project.
2. Copy-paste all the above-mentioned  **7** .java files to the **/src** directory of the project.
3. Click the run button. Everything should work!

---

## * Highlights of the project

### 1. O(1) time complexity algorithm to check if there is a winner and retrieve her/his player ID !!!

- Under **TicTacToe.java -> checkWinner()**.

- ```java
  /*
      DURING the game -> Check if there is a winner generated
   */
  private int checkWinner(int row, int col, int playerID) {
      if (++this.rows[playerID][row] == boardSize) {
          return playerID;
      }
      if (++this.columns[playerID][col] == boardSize) {
          return playerID;
      }
      if (row == col && ++this.diagonals[playerID][0] == boardSize) {
          return playerID;
      }
      if ((row + col == boardSize - 1) && ++this.diagonals[playerID][1] == boardSize) {
          return playerID;
      }
  
      return -1;
  }
  ```

### 2. Very concise main().

- As only an entrance of the project, the main() method has only two lines.

- ```java
  public class GameStarter {
      public static void main(String[] args) {
          TicTacToe game = new TicTacToe();
          game.play();
      }
  }
  ```

### 3. Input validation checking

- When the players enter their preferred board size, the input validation will be checked.
- First its data type will be checked. If it is not of type int, system will prompt accordingly without crashing.
- Then, the range of the input will be checked. If it is out of the range of the board size (i.e. out of  **[1, board size * board size]** ), system will prompt accordingly without crashing.

### 4. Neat game board design

- <img src="https://github.com/Superkakayong/Trivia/blob/master/Project_Images/TicTacToe-I-Object-oriented/Game%20Board%20Design.png" alt="image-20210210200440573" style="zoom:50%; float: left" />

### 5. Proper code format

- Proper indentations, very **detailed** comments, etc.

---

## UML Diagram for the Project

<img src="https://github.com/Superkakayong/Trivia/blob/master/Project_Images/TicTacToe-I-Object-oriented/UML.jpeg" alt="image-20210210200440573" style="zoom:75%; float: left" />
