package ai;

import java.util.Scanner;

public class bot2 {
	static void nextMove(int n, int r, int c, String[] grid) {
		int[] princess = new int[2];
		for (int i = 0; i < grid.length; i++) {
			String input = grid[i];
			if (input.contains("p")) {
				princess[0] = i;
				princess[1] = input.indexOf("p");
			}
		}
		move(princess, r, c, grid.length);
	}

	static void move(int[] p, int row, int col, int N) {
		if (row == p[0] && col == p[1])
			return;
		if (row == 0 && col == 0)
			return;
		if (row == N - 1 && col == 0)
			return;
		if (col == N - 1 && row == 0)
			return;
		if (row == N - 1 && col == N - 1)
			return;
		if (row < p[0]) {
			System.out.println("DOWN");
			row++;
		} else if (row == p[0]) {
			if (col < p[1]) {
				col++;
				System.out.println("RIGHT");
			} else if (p[1] < col) {
				col--;
				System.out.println("LEFT");
			}

		} else if (p[0] < row) {
			System.out.println("UP");
			row--;
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n, r, c;
		n = in.nextInt();
		r = in.nextInt();
		c = in.nextInt();
		in.useDelimiter("\n");
		String grid[] = new String[n];
		in.next();
		for (int i = 0; i < n; i++) {
			grid[i] = in.next();
		}

		nextMove(n, r, c, grid);

	}
}
