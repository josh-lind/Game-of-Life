import java.util.Arrays;

/**
 * This project is an implementation of John Conway's Game of Life.
 * Specifically, this class deals with the matrix interpretation and all
 * alterations while Frame and Screen deal with the graphics.
 * 
 * @author Josh Lind
 *
 */
public class GameOfLife {

	// **Play around with these four variables
	private static final int ROW = 200;
	private static final int COL = 200;
	private final double MIN_FOR_DEAD_START = .7;
	private final int SLEEPTIME = 90;

	// The matrix representing alive vs dead cells
	private int[][] m = new int[ROW][COL]; // n-th (future) state
	private int[][] mOld = new int[ROW][COL]; // (n-1)-th (previous) state
	private int[][] mOlder = new int[ROW][COL]; // (n-2)-th state

	// These variables keep track of how many state changes have occurred.
	private int state = 0;
	private int endState = -1;

	private Frame f;

	public GameOfLife() {
		f = new Frame();
	}

	public void startGame() throws InterruptedException {
		this.initializeMat();
		setOldFromNew(mOld, m);
		this.printMat();
		while (true) {
			this.keepRunning();
		}
	}

	private void keepRunning() throws InterruptedException {
		Thread.sleep(SLEEPTIME);
		this.changeMat();
		if ((Arrays.deepEquals(mOld, m) || Arrays.deepEquals(mOlder, m)) && endState == -1) {
			endState = state;
			System.out.println(endState);
		}
		if (state > 1) {
			setOldFromNew(mOlder, mOld);
		}
		setOldFromNew(mOld, m);
		this.printMat();
	}

	private void changeMat() {
		for (int r = 0; r < m.length; r++) {
			for (int c = 0; c < m[0].length; c++) {
				changeEach(r, c, mOld, m);
			}
		}
		state += 1;
	}

	private static void changeEach(int r, int c, int[][] a, int[][] b) {
		// checks how many neighbors are alive
		int n = 0;
		n += checkOld(r - 1, c - 1, a);
		n += checkOld(r - 1, c, a);
		n += checkOld(r - 1, c + 1, a);
		n += checkOld(r, c - 1, a);
		n += checkOld(r, c + 1, a);
		n += checkOld(r + 1, c - 1, a);
		n += checkOld(r + 1, c, a);
		n += checkOld(r + 1, c + 1, a);

		if (a[r][c] == 0) {
			if (n == 3) {
				b[r][c] = 1;
			}

		} else {
			if (n < 2 || n > 3) {
				b[r][c] = 0;
			}

		}

	}

	private static int checkOld(int r, int c, int[][] a) {
		int newR = r;
		int newC = c;
		if (r == -1) {
			newR = ROW - 1;
		} else if (r == ROW) {
			newR = 0;
		}
		if (c == -1) {
			newC = COL - 1;
		} else if (c == COL) {
			newC = 0;
		}
		return a[newR][newC];
	}

	// Graphics representation. *Much prettier
	// Print line representation can be found at bottom
	private void printMat() {
		this.f.setMatAndPaint(m, state, endState);
	}

	private static void setOldFromNew(int[][] a, int[][] b) {
		for (int r = 0; r < b.length; r++) {
			for (int c = 0; c < b[0].length; c++) {
				a[r][c] = b[r][c];
			}
		}
	}

	private void initializeMat() {
		for (int r = 0; r < m.length; r++) {
			for (int c = 0; c < m[0].length; c++) {
				double rand = Math.random();
				if (rand < MIN_FOR_DEAD_START) {
					m[r][c] = 1;
				}
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		GameOfLife g = new GameOfLife();
		g.startGame();
	}

	// Print line representation
//	private void printMat() {
//		for (int i = 0; i < 10; i++) {
//			System.out.println();
//		}
//
//		for (int r = 0; r < m.length; r++) {
//			for (int c = 0; c < m[0].length; c++) {
//				String s = "";
//				if (m[r][c] == 0) {
//					s = ".";
//				} else {
//					s = "O";
//				}
//				System.out.print(s);
//			}
//			System.out.println();
//		}
//	}

}
