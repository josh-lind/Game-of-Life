import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * The screen within the frame where the matrix is drawn
 * 
 * @author Josh Lind
 *
 */
public class Screen extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int[][] m;

	public Screen() {
		repaint();
	}

	public void paint(Graphics g) {
		int rows = m.length;
		int cols = m[0].length;
		for (int r = 0; r < m.length; r++) {
			for (int c = 0; c < m[0].length; c++) {
				if (m[r][c] == 1) {
					g.setColor(Color.white);
				} else {
					g.setColor(Color.darkGray);
				}
				g.fillRect(r * (800 / rows), c * (800 / cols), (800 / rows), (800 / cols));

			}
		}
	}

	public void setMat(int[][] mat) {
		m = mat;
		repaint();
	}

}
