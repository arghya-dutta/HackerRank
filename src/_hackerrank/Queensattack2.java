package _hackerrank;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



class Coord {
	int up = 0, down = 0, left = 0, right = 0, leftup_col = 0, leftup_row = 0, rightup_row = 0, rightup_col = 0,
			leftdown_col = 0,

			leftdown_row = 0, rightdown_col = 0, rightdown_row = 0;

	boolean hasright = false, hasleft = false;
	List<Point> red = new ArrayList<Point>();
	List<Point> green = new ArrayList<Point>();
}

public class Queensattack2 {

	// Complete the queensAttack function below.
	static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
		if (n == 1 && k == 1) {
			return 0;
		}

		int counter = 0;
		int row = r_q;
		int col = c_q;
		Coord c = new Coord();
		c.up = n;
		c.right = n;
		c.leftup_row = n + 1;
		c.leftup_col = n + 1;
		c.rightup_row = n + 1;
		c.rightup_col = n + 1;
		// System.out.println(row + " , " + col);
		Arrays.stream(obstacles).forEach(obs -> {
			c.red.add(new Point(obs[1], obs[0]));
			// System.out.println(obs[0] + " , " + obs[1]);
			if (obs[1] < col) {
				c.hasleft = true;
				// left
				if (obs[0] == row) {
					c.left = obs[1] + 1;
				} else if ((obs[0]) > row && (obs[0]) < c.leftup_row
						&& Math.abs(obs[0] - row) == Math.abs(col - obs[1])) {
					// it is on a left up
					c.leftup_row = obs[0] - 1;
					c.leftup_col = obs[1] + 1;
				} else if ((obs[0]) < row && (obs[0]) > c.leftdown_row
						&& Math.abs(obs[0] - row) == Math.abs(col - obs[1])) {
					// it is on a left down
					c.leftdown_row = obs[0] + 1;
					c.leftdown_col = obs[1] + 1;
				}
			} else if (obs[1] > col) {
				// right
				c.hasright = true;
				if (obs[0] == row) {
					c.right = obs[1] - 1;
				} else if ((obs[0]) > row && (obs[0]) < c.rightup_row
						&& Math.abs(obs[0] - row) == Math.abs(col - obs[1])) {
					// it is on a right up
					c.rightup_row = obs[0] - 1;
					c.rightup_col = obs[1] - 1;
				} else if ((obs[0]) < row && (obs[0]) > c.rightdown_row
						&& Math.abs(obs[0] - row) == Math.abs(col - obs[1])) {
					// it is on a right down
					c.rightdown_row = obs[0] + 1;
					c.rightdown_col = obs[1] - 1;
				}
			} else if ((obs[0]) < row && (obs[0]) > c.down) {
				c.down = obs[0] + 1;
			} else if ((obs[0]) > row && (obs[0]) < c.up) {
				c.up = obs[0] - 1;
			}

		});
		if (c.down == 0)
			c.down = 1;
		if (c.left == 0)
			c.left = 1;

		if (obstacles.length == 0) {
			if (col < n) {
				c.hasleft = true;
				c.hasright = true;
			} else if (col == n) {
				c.hasleft = true;
			}
		}

		if (row != c.down) {
			counter = counter + (row - c.down);
		}

		if (c.up != row) {
			counter = counter + (c.up - row);
		}
		if (c.hasright) {
			counter = counter + (c.right - col);
			if (c.rightdown_col == 0) {
				c.rightdown_col = c_q;
				c.rightdown_row = r_q;
				while (c.rightdown_col < n && c.rightdown_row > 1) {
					c.rightdown_col++;
					c.rightdown_row--;
					c.green.add(new Point(c.rightdown_col, c.rightdown_row));
					counter++;
				}
			} else {
				counter = counter + (c.rightdown_col - col);
			}

			if (c.rightup_col == n + 1) {
				c.rightup_row = r_q;
				c.rightup_col = c_q;
				while (c.rightup_row < n && c.rightup_col < n) {
					c.rightup_row++;
					c.rightup_col++;
					c.green.add(new Point(c.rightup_col, c.rightup_row));
					counter++;
				}
			} else {
				counter = counter + (c.rightup_col - col);
			}
		}
		if (c.hasleft) {
			counter = counter + (col - c.left);
			if (c.leftdown_col == 0) {
				c.leftdown_col = c_q;
				c.leftdown_row = r_q;
				while (c.leftdown_col > 1 && c.leftdown_row > 1) {
					c.leftdown_col--;
					c.leftdown_row--;
					c.green.add(new Point(c.leftdown_col, c.leftdown_row));
					counter++;
				}
			} else {
				counter = counter + (col - c.leftdown_col);
			}

			if (c.leftup_col == n + 1) {
				c.leftup_row = r_q;
				c.leftup_col = c_q;
				while (c.leftup_row < n && c.leftup_col > 1) {
					c.leftup_row++;
					c.leftup_col--;
					c.green.add(new Point(c.leftup_col, c.leftup_row));
					counter++;
				}
			} else {
				counter = counter + (col - c.leftup_col);
			}
		}
//		MyImagewriter iw = new MyImagewriter(n, n, new Point(c_q, r_q), c.red, c.green);
//		iw.write();

		return counter;
	}

//	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		 Scanner scanner = new Scanner(new FileReader("q2tc13.txt"));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] nk = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nk[0]);

		int k = Integer.parseInt(nk[1]);

		String[] r_qC_q = scanner.nextLine().split(" ");

		int r_q = Integer.parseInt(r_qC_q[0]);

		int c_q = Integer.parseInt(r_qC_q[1]);

		int[][] obstacles = new int[k][2];

		for (int i = 0; i < k; i++) {
			String[] obstaclesRowItems = scanner.nextLine().split(" ");
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

			for (int j = 0; j < 2; j++) {
				int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
				obstacles[i][j] = obstaclesItem;
			}
		}

		int result = queensAttack(n, k, r_q, c_q, obstacles);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
