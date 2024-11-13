import java.util.Scanner;
public class TicTacToeAI
{
static final int PLAYER_X = 1;
static final int PLAYER_O = -1;
static final int EMPTY = 0;
static final int SIZE = 3;
static int[][] board = new int[SIZE][SIZE];
public static void main(String[] args)
{
Scanner scanner = new Scanner(System.in);
//initialize---
initializeBoard();
System.out.println("You are X,AI is O.");
printBoard();
while(true)
{
humanMove(scanner);
if (isGameOver())
{
break;
}
aiMove();
printBoard();
if (isGameOver())
{
break;
}
}
scanner.close();
}
public static void initializeBoard()
{
for (int i = 0;i<SIZE;i++)
{
for (int j = 0;j<SIZE; j++)
{
board[i][j] = EMPTY;
}
}
}
//Humanplayer
public static void humanMove(Scanner scanner)
{
int row, col;
while (true)
{
System.out.println("Enter your move(row and column: 1 1,1 2, etc.):");
row = scanner.nextInt() -1;
col = scanner.nextInt() -1;
if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == EMPTY)
{
board[row][col] = PLAYER_X;
break;
}
else
{
System.out.println("Invalid move! Try again.");
}
}
printBoard();
}
public static void aiMove()
{
int bestscore = Integer.MIN_VALUE;
int bestRow = -1;
int bestCol = -1;
for(int i = 0;i<SIZE;i++)
{
for(int j = 0;j < SIZE; j++)
{
if(board[i][j] == EMPTY)
{
board[i][j] = PLAYER_O;
int score = minimax(board,0,false);
board[i][j] = EMPTY;
if(score > bestScore)
{
bestScore = score;
bestRow = i;
bestCol = j;
}
}
}
}
board[bestRow][bestCol] = PLAYER_O;
System.out.println("AI moved at: (" + (bestRow + 1) + " ," + (bestCol + 1) + ")");
}
public static int minimax(int[][] board,int depth,boolean isMaximizing)
{
int score = evaluate(board);
if(score == 1)return 1;
if(score == -1) return -1;
if(isFull()) return 0;
if(isMaximizing)
{
int bestScore = Integer.MIN_VALUE;
for(int i = 0;i < SIZE; i++)
{
for(int j=0; j < SIZE; j++)
{
if(board[i][j] == EMPTY)
{
board[i][j] = PLAYER_O;
bestScore = Math.max(bestScore,minimax(board,depth + 1,false));
board[i][j] = EMPTY;
}
}
}
return bestScore;
}
else
{
int bestScore = Integer.Max_VALUE;
for (int i = 0;i <SIZE;i++)
{
for(int j = 0;j < SIZE;j++)
{
if(board[i][j] == EMPTY)
{
board[i][j] = PLAYER_X;
bestScore = Math.min(bestScore,minimax(board,depth + 1,true));
board[i][j] = EMPTY;
}
}
}
return bestScore;
}
}
public static int evaluate(int[][] board)
{
for(int i =0;i < SIZE; i++)
{
if (checkLine(board[i][0],board[i][1],board[i][2]))
{
return board[i][0];
}
if(checkLine(board[0][i],board[1][i], board[2][i]))
{
return board[i][0];
}
}
//check diagonals
if(checkLine(board[0][0],board[1][1], board[2][2]))
{
return board[0][0];
}
if(checkLine(board[0][2],board[1][1],board[2][0]))
{
return board[0][2];
}
return 0;
}
//hepler function
public static boolean checkLine(int a,int b,int c)
{
return (a == b && b == c && a != EMPTY);
}
public static boolean isFull()
{
for (int i = 0; i < SIZE; i++)
{
for (int j = 0; j < SIZE; j++)
{
if (board[i][j] == EMPTY)
{
return false;
}
}
}
return true;
}
public static boolean isGameOver();
{
int winner = evaluate(board);
if(winner == PLAYER_X)
{
System.out.println("You win!");
return true;
}
else if (winner == PLAYER_O)
{
System.out.println("AI wins!");
return true;
}
else if(isFull())
{
System.out.println("Its a draw!");
return true;
}
return false;
}
public static void printBoard()
{
for(int i = 0; i < SIZE; i++)
{
for(int j = 0; j < SIZE; j++)
{
if (board[i][j] == PLAYER_X)
{
System.out.println("X");
}
else if (board[i] [j] == PLAYER_O)
{
System.out.println("O");
}
else
{
System.out.println(" . ");
}
if (j < SIZE - 1)
{
System.out.println("|");
}
}
System.out.println();
if (i < SIZE - 1)
{
System.out.println("---+---+---");
}
}
System.out.println();
}
}


