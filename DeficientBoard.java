/**
 *  @author  J. Hollingsworth
 *  
 *  A n x n (n = 2^k) board is that is deficient
 *  (missing a 1 x 1 position).
 */

import java.awt.Color;

public class DeficientBoard {
	
	private int n;
	private Color board[][];

	// The four rotations of the tile.
	// UL is XX
	//       X
	// UR is XX
	//        X
	// LL is X
	//       XX
	// LR is  X
	//       XX
	public final int UL = 0;
	public final int UR = 1;
	public final int LL = 2;
	public final int LR = 3;
	
	/**
	 * Create a 2^k x 2^k deficient board.
	 * 
	 * @param k power
	 */
	public DeficientBoard(int k) {
		n = (int)Math.pow(2, k);
		createBoard(Color.LIGHT_GRAY);
	}
	
	/**
	 * Actually create an n x n deficient board.
	 * 
	 * @param color background color
	 */
	private void createBoard(Color color) {
		board = new Color[n][n];
		for (int r = 0; r < board.length; r++)
			for (int c = 0; c < board[0].length; c++)
				board[r][c] = color;
		
		int d_row = (int)(Math.random() * n);
		int d_col = (int)(Math.random() * n);
		board[d_row][d_col] = Color.BLACK;
	}
	
	/**
	 * Given a row and column and shape based on that point
	 * place a tromino of the given color.
	 * 
	 * @param r row
	 * @param c column
	 * @param s shape (UL, UR, LL, LR)
	 * @param theColor a Color
	 */
	public void placeTromino(int r, int c, int s, Color theColor) {
		if (s == UL) {
			board[r][c] = theColor; 
			board[r][c+1] = theColor;
			board[r+1][c] = theColor;
		} else if (s == UR) {
			board[r][c] = theColor;
			board[r][c+1] = theColor;
			board[r+1][c+1] = theColor;
		} else if (s == LL) {
			board[r][c] = theColor;
			board[r+1][c] = theColor;
			board[r+1][c+1] = theColor;
		} else {
			board[r+1][c] = theColor;
			board[r+1][c+1] = theColor;
			board[r][c+1] = theColor;
		}
	}
	
	/**
	 * Get the 2^k x 2^k board.
	 * 
	 * @return the Color board.
	 */
	public Color[][] getBoardColor() {
		return board;
	}
	
	/**
	 * Find and return the deficient row.
	 * 
	 * @param row row
	 * @param col column
	 * @param sz size of the board
	 * @return the row the deficient block is located
	 */
	public int getDeficientRow(int row, int col, int sz) {
	
		for (int r = row; r < (row + sz); r++)
			for (int c = col; c < (col + sz); c++)
				if (board[r][c] != Color.LIGHT_GRAY)
					return r;
		
		return -1;
	}
	
	/**
	 * Find and return the deficient column.
	 * 
	 * @param row row
	 * @param col column
	 * @param sz size of the baord
	 * @return the row the deficient block is located
	 */
	public int getDeficientCol(int row, int col, int sz) {
		for (int r = row; r < (row + sz); r++)
			for (int c = col; c < (col + sz); c++)
				if (board[r][c] != Color.LIGHT_GRAY)
					return c;
		
		return -1;
	}
	
	/**
	 * Get the size of the deficient board.
	 * 
	 * @return the size
	 */
	public int getSize() {
		return n;
	}

	/**
	 * Display information about the deficient board.
	 */
	public String toString() {
		return ("Deficient board of size " 
				 + n + "x" + n
				 + " with position missing at (" 
				 + getDeficientRow(0, 0, n) + "," + getDeficientCol(0, 0, n) +").");
	}
}