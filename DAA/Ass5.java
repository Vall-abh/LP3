import java.util.*;

public class Ass5 {

    static int n;
    static int[][] board;   // 1 = queen, 0 = empty

    // Check if a queen can be placed at (row, col)
    // We only look at rows ABOVE, because queens are placed row by row
    static boolean isSafe(int row, int col) {
        // same column above
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) return false;
        }
        // upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }
        // upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) return false;
        }
        return true;
    }

    // Backtracking: try to place a queen in each row, starting from 'row'
    static boolean solve(int row) {
        if (row == n) return true;              // all queens placed

        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                board[row][col] = 1;            // place queen
                if (solve(row + 1)) return true; // try next row
                board[row][col] = 0;            // backtrack (remove queen)
            }
        }
        return false;                           // no column worked in this row
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        n = sc.nextInt();
        System.out.print("Enter column (0 to " + (n - 1) + ") for the first queen in row 0: ");
        int firstCol = sc.nextInt();

        board = new int[n][n];
        board[0][firstCol] = 1;                 // first queen is fixed

        if (solve(1)) {                         // place the rest from row 1
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution with the first queen at this position.");
        }
    }
}
