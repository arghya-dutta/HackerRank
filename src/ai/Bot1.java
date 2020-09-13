package ai;

import java.util.Scanner;

public class Bot1 {
	public static void main(String[] args) {
		Bot1 b = new Bot1();
		final Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int[] bot = new int[2];
		int[] princess = new int[2];
		String[][] matrix = new String[N][N];
		scanner.nextLine();
		for (int i = 0; i < N; i++) {
			String input = scanner.nextLine();
			if (input.contains("m")) {
				bot[0] = i;
				bot[1] = input.indexOf("m");
			} else if (input.contains("p")) {
				princess[0] = i;
				princess[1] = input.indexOf("p");
			}
			matrix[i] = input.split("");
		}
		scanner.close();
		b.move(princess, bot[0], bot[1], N);
	}

	private void move(int[] p, int row, int col, int N) {
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
		move(p, row, col, N);
	}
}
