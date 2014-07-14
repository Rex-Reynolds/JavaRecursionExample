/**
 *  @author   J. Hollingsworth and Rex Reynolds
 *  
 *  Divide-and-Conquer approach to placing tromino
 *  tiles on a 2^k x 2^k board.
 */
import java.util.Scanner;
import java.awt.Color;

public class Tiling {

	private static Color randColor() {
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);

		return new Color(r, g, b);
	}

	/*
	 * Algorithm : T(n) = 4T(n/2) + 20 a = 4 (4 recursions) b = 2 (board size) 
	 * d = 20 (number of assignments and constants)
	 * 
	 * 4 < 2^20
	 * 
	 * O(n^20)
	 * 
	 * Uses recursion calls to tile a board missing one square. 
	 * Begins with base case of n==2 which returns the proper orientation for the initial tile 
	 * placement in order to "create" four, smaller deficient boards. Then recurses through 
	 * each particular board and tiles.
	 */
	public static void tile(DeficientBoard db, int row, int col, int n) {
		int centRow = row + (n / 2) - 1; 
		int centCol = col + (n / 2) - 1; 
		int orientation;
		int deficientRow = db.getDeficientRow(row, col, n); 
		int deficientCol = db.getDeficientCol(row, col, n); 

		if (n == 2) {
			if (deficientRow == row + 1 && deficientCol == col) { 
				orientation = 1; 
				db.placeTromino(row, col, orientation, randColor()); 

			} else if (deficientRow == row + 1 && deficientCol == col + 1) {
				orientation = 0;
				db.placeTromino(row, col, orientation, randColor());

			} else if (deficientRow == row && deficientCol == col + 1) {
				orientation = 2;
				db.placeTromino(row, col, orientation, randColor());

			} else if (deficientRow == row && deficientCol == col) {
				orientation = 3;
				db.placeTromino(row, col, orientation, randColor());
			}
			

		} else if (n > 0) {
			if (deficientRow <=  centRow && deficientCol <=  centCol) { 
				orientation = 3; 
				db.placeTromino(centRow, centCol, orientation, randColor()); 
			}
			if (deficientRow > centRow && deficientCol <=  centCol) {
				orientation = 1;
				db.placeTromino(centRow, centCol, orientation, randColor());

			}
			if (deficientRow <=  centRow && deficientCol >  centCol) {
				orientation = 2;
				db.placeTromino(centRow, centCol, orientation, randColor());

			}
			if (deficientRow >  centRow && deficientCol >  centCol) {
				orientation = 0;
				db.placeTromino(centRow, centCol, orientation, randColor());

			}
			if (n > 2) {

				tile(db, row, col, (n / 2));
				tile(db, row, col + (n / 2), (n / 2));
				tile(db, row + (n / 2), col, (n / 2));
				tile(db, row + (n / 2), col + (n / 2), (n / 2));
			}
		}

	}

	public static void main(String[] args) {

		DeficientBoard db = new DeficientBoard(4);
		System.out.println(db);

		tile(db, 0, 0, db.getSize());

		BoardViewer bv = new BoardViewer(db);
		bv.setVisible(true);

	}

}
