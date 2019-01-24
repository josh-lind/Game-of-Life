/**
 * This project is an implementation of John Conway's Game of Life.
 * Specifically, this class deals with the matrix interpretation and all
 * alterations while Frame and Screen deal with the graphics.
 * 
 * @author Josh Lind
 *
 */
public class GameOfLife {

	private final int ROW = 160;
	private final int COL = 160;
	private final double MIN_FOR_DEAD_START = .05;
	private final int SLEEPTIME = 50;

	// The matrix representing alive vs dead cells
	private int[][] m = new int[ROW][COL];
	private int[][] mOld = new int[ROW][COL];

	private Frame f;

	public GameOfLife() throws InterruptedException {
		f = new Frame();

		this.initializeMat();
		this.setOldFromNew();
		this.printMat();
		this.keepRunning();
	}

	private void keepRunning() throws InterruptedException {
		Thread.sleep(SLEEPTIME);
		this.changeMat();
		this.setOldFromNew();
		this.printMat();
		this.keepRunning();
	}

	private void changeMat() {
		for (int r = 0; r < m.length; r++) {
			for (int c = 0; c < m[0].length; c++) {
				this.changeEach(r, c);
			}
		}
	}

	private void changeEach(int r, int c) {
		// checks how many neighbors are alive
		int n = 0;
		n += checkOld(r - 1, c - 1);
		n += checkOld(r - 1, c);
		n += checkOld(r - 1, c + 1);
		n += checkOld(r, c - 1);
		n += checkOld(r, c + 1);
		n += checkOld(r + 1, c - 1);
		n += checkOld(r + 1, c);
		n += checkOld(r + 1, c + 1);

		if (mOld[r][c] == 0) {
			if (n == 3) {
				m[r][c] = 1;
			}

		} else {
			if (n < 2 || n > 3) {
				m[r][c] = 0;
			}

		}

	}

	private int checkOld(int r, int c) {
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
		return mOld[newR][newC];
	}

	// Graphics representation. *Much prettier
	private void printMat() {
		this.f.setMat(m);
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

	private void setOldFromNew() {
		for (int r = 0; r < m.length; r++) {
			for (int c = 0; c < m[0].length; c++) {
				mOld[r][c] = m[r][c];
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

}
