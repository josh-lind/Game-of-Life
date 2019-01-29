import javax.swing.JFrame;

/**
 * The frame to represent the matrix.
 * 
 * @author Josh Lind
 *
 */
public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Screen s;

	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		setResizable(false);
		setTitle("Game Of Life");

		init();
	}

	public void init() {
		setLocationRelativeTo(null);

		s = new Screen();
		add(s);

		setVisible(true);
	}

	public void setMatAndPaint(int[][] mat, int state, int endState) {
		s.setMatAndPaint(mat, state, endState);
	}

}
