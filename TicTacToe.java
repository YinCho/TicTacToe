import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        String[][] gameBoard = new String[3][3];
        gameBoard = initializeBoard(gameBoard);
        int currentRound = 1;
        //Make it run at all times to play the game until something happens
        while (true) {
            while (true) {
                // Display currentround and the game board
                System.out.println("Round " + currentRound + ":");
                displayBoard(gameBoard);

                // Check if the game is complete
                if (isGameComplete(gameBoard)) {
                    break;
                }

                // Player X's turn
                if (currentRound % 2 != 0) {
                    while (true) {
                        System.out.println("Player X, make your move (row, col):");
                        String playerMove = inputScanner.nextLine();
                        int row = Integer.parseInt(playerMove.substring(0, 1));
                        int col = Integer.parseInt(playerMove.substring(2, 3));

                        // Validate and make the move
                        if (isValidMove(gameBoard, row, col)) {
                            gameBoard[row][col] = "X";
                            break;
                        } else {
                            System.out.println("Invalid move. Try again.");
                        }
                    }
                } else {
                    // Player O's turn (bot)
                    System.out.println("Player O, make your move (row, col):");
                    while (true) {
                        int randomRow = getRandomNumber();
                        int randomCol = getRandomNumber();

                        // Validate and make the move
                        if (isValidMove(gameBoard, randomRow, randomCol)) {
                            gameBoard[randomRow][randomCol] = "O";
                            break;
                        }
                    }
                }
                currentRound++;
            }

            // Ask if the players want to play again
            System.out.println("Play again? (Y/N)");
            String playAgain = inputScanner.nextLine().toLowerCase();

            if (playAgain.equals("n")) {
                break;
            } else if (playAgain.equals("y")) {
                // Reset the game for a new round
                currentRound = 1;
                gameBoard = initializeBoard(gameBoard);
            }
        }
    }

    // Check if the game is complete 
    public static boolean isGameComplete(String[][] board) {
        // Check rows and columns for a winner
        for (int i = 0; i < 3; i++) {
            if (checkRow(board, i, "X") || checkColumn(board, i, "X")) {
                System.out.println("Player X wins!");
                return true;
            } else if (checkRow(board, i, "O") || checkColumn(board, i, "O")) {
                System.out.println("Player O wins!");
                return true;
            }
        }

        // Check diagonals for a winner
        if (checkDiagonal(board, "X") || checkDiagonal(board, "O")) {
            return true;
        }

        // Check if the board is full (draw)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].equals(" ")) {
                    return false; // The game is not complete
                }
            }
        }

        System.out.println("It's a draw!");
        return true;
    }

    // Initialize the game board with empty spaces
    public static String[][] initializeBoard(String[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = " ";
            }
        }
        return board;
    }

    public static boolean checkRow(String[][] board, int row, String player) {
        return board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player);
    }

    public static boolean checkColumn(String[][] board, int col, String player) {
        return board[0][col].equals(player) && board[1][col].equals(player) && board[2][col].equals(player);
    }

    public static boolean checkDiagonal(String[][] board, String player) {
        if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) {
            System.out.println("Player " + player + " wins!");
            return true;
        }

        if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player)) {
            System.out.println("Player " + player + " wins!");
            return true;
        }

        return false;
    }

    // Validate if a move is within the board and the cell is empty
    public static boolean isValidMove(String[][] board, int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.println("Invalid move. Please try again.");
            return false;
        }

        if (!board[row][col].equals(" ")) {
            System.out.println("Invalid move. Cell already occupied. Please try again.");
            return false;
        }

        return true;
    }

    // Generate a random number for the bot's move
    public static int getRandomNumber() {
        return (int) (Math.random() * 3);
    }

    // Display the current state of the game board
    public static void displayBoard(String[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("[" + board[i][j] + "]");
            }
            System.out.println();
        }
    }
}
