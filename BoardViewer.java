/**
 *  @author   J. Hollingsworth
 *  
 *  A simple class to graphical draw a 2-D array
 *  of Colors.
 */

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;

public class BoardViewer extends JFrame {

	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;
	private static final int OFFSET = 30;
	
	public static final int UPVERT = 1;
	public static final int DNVERT = 2;
	public static final int RHORIZ = 4;
	public static final int LHORIZ = 8;
	public static final int FILL = 16;
	
	private Color [][] board;
	
	public BoardViewer(DeficientBoard db) {
		super();
		setSize(WIDTH + (OFFSET*2), HEIGHT + (OFFSET*2));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		board = db.getBoardColor();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		int width = WIDTH/board[0].length;
		int height = HEIGHT/board.length;
		
		for (int r = 0; r < board.length; r++)
			for (int c = 0; c < board[r].length; c++) {
				g.setColor(board[r][c]);
				
				int x = c*width + OFFSET;
				int y = r*height + OFFSET + (OFFSET/2);
				
				g.fillRect(x+1, y+1, width-1, height-1);
			}
	}
}
