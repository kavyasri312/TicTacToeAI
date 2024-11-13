import java.util.Scanner;

public class TicTacToe {

    private static final char HUMAN = 'X';
    private static final char AI = 'O';
    private static final char EMPTY = ' ';
    
    public static void main(String[] args) {
        char[][] board = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
        };

        Scanner scanner = new Scanner(System.in);
        printBoard(board);

        while (true) {
            // Human move
            humanMove(board, scanner);
            printBoard(board);
            if (isGameOver(board)) {
                break;
            }

            // AI move
            System.out.println("AI is making a move...");
            aiMove(board);
            printBoard(board);
            if (isGameOver(board)) {
                break;
            }
        }
        scanner.close();
    }

    // Prints the current state of the board
    public static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Check if the game is over
    public static boolean isGameOver(char[][] board) {
        if (hasWon(board, HUMAN)) {
            System.out.println("Human wins!");
            return true;
        }
        if (hasWon(board, AI)) {
            System.out.println("AI wins!");
            return true;
        }
        if (isBoardFull(board)) {
            System.out.println("It's a draw!");
            return true;
        }
        return false;
    }

    // Check if someone has won the game
    public static boolean hasWon(char[][] board, char player) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    // Check if the board is full
    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Human move
    public static void humanMove(char[][] board, Scanner scanner) {
        int row, col;
        while (true) {
            System.out.print("Enter your move (row [1-3] and column [1-3]): ");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY) {
                board[row][col] = HUMAN;
                break;
            } else {
                System.out.println("This move is not valid.");
            }
        }
    }

    // AI move using Minimax with Alpha-Beta Pruning
    public static void aiMove(char[][] board) {
        int[] bestMove = minimax(board, AI, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        board[bestMove[1]][bestMove[2]] = AI;
    }

    // Minimax algorithm with Alpha-Beta Pruning
    public static int[] minimax(char[][] board, char currentPlayer, int depth, int alpha, int beta) {
        if (hasWon(board, HUMAN)) return new int[] {-10 + depth, -1, -1};
        if (hasWon(board, AI)) return new int[] {10 - depth, -1, -1};
        if (isBoardFull(board)) return new int[] {0, -1, -1};

        int bestScore = (currentPlayer == AI) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        int[] bestMove = {-1, -1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = currentPlayer;
                    if (currentPlayer == AI) {
                        currentScore = minimax(board, HUMAN, depth + 1, alpha, beta)[0];
                        if (currentScore > bestScore) {
                            bestScore = currentScore;
                            bestMove = new int[] {bestScore, i, j};
                        }
                        alpha = Math.max(alpha, bestScore);
                    } else {
                        currentScore = minimax(board, AI, depth + 1, alpha, beta)[0];
                        if (currentScore < bestScore) {
                            bestScore = currentScore;
                            bestMove = new int[] {bestScore, i, j};
                        }
                        beta = Math.min(beta, bestScore);
                    }
                    board[i][j] = EMPTY;

                    // Alpha-Beta Pruning
                    if (beta <= alpha) {
                        return bestMove;
                    }
                }
            }
        }
        return bestMove;
    }
}

