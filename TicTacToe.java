package com.mycompany.tictactoe;
import java.util.Scanner;

public class TicTacToe {

    // Define the board
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X'; // Start with player 'X'
    private static boolean gameOver = false;

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        while (!gameOver) {
            playerTurn();
            printBoard();
            checkGameStatus();
            switchPlayer();
        }
    }

    // Method to initialize the board with numbers 1 to 9
    private static void initializeBoard() {
        int count = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = (char) (count + '0'); // Fill with numbers '1' to '9'
                count++;
            }
        }
    }

    // Method to print the current board state
    private static void printBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("---------");
        }
    }

    // Method to handle a player's turn
    private static void playerTurn() {
        Scanner scanner = new Scanner(System.in);
        int move;

        while (true) {
            System.out.println("Player " + currentPlayer + "'s turn");
            System.out.print("Enter a number (1-9): ");
            move = scanner.nextInt();

            // Check if the input is valid (between 1 and 9) and the spot is available
            if (move >= 1 && move <= 9) {
                int row = (move - 1) / 3;
                int col = (move - 1) % 3;

                if (board[row][col] != 'X' && board[row][col] != 'O') {
                    board[row][col] = currentPlayer; // Place the player's symbol
                    break;
                } else {
                    System.out.println("This spot is already taken. Try again.");
                }
            } else {
                System.out.println("Invalid move. Please choose a number between 1 and 9.");
            }
        }
    }

    // Method to check the current game status (win, draw, or ongoing)
    private static void checkGameStatus() {
        if (checkWin()) {
            System.out.println("Player " + currentPlayer + " wins!");
            gameOver = true;
        } else if (checkDraw()) {
            System.out.println("It's a draw!");
            gameOver = true;
        }
    }

    // Method to check if the current player has won
    private static boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) || 
                (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
            (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }
        return false;
    }

    // Method to check if the game is a draw
    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false; // There's still an empty spot
                }
            }
        }
        return true; // All spots are filled, and no one has won
    }

    // Method to switch the current player
    private static void switchPlayer() {
        if (currentPlayer == 'X') {
            currentPlayer = 'O';
        } else {
            currentPlayer = 'X';
        }
    }
}
