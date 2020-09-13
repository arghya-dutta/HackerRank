package ai;

import java.util.Scanner;

public class BotClean {

	void next_move(int posr, int posc, String board[]) {
		int currpos = posr * 5 + posc;
		if (board[currpos].contains("d")) {
			System.out.println("CLEAN");
		} else if (currpos + 5 < board.length && board[currpos + 5].contains("d")) {
			System.out.println("DOWN");
		} else if (currpos - 1 >= 0 && board[currpos - 1].contains("d")) {
			System.out.println("LEFT");
		} else if (currpos + 1 < board.length && board[currpos + 1].contains("d")) {
			System.out.println("RIGHT");
		} else if (currpos - 5 >= 0 && board[currpos - 5].contains("d")) {
			System.out.println("UP");
		} else {
			int nearest = 99;
			int nearestpos = 0;
			for (int i = 0; i < 25; i++) {
				if (board[i].equals("d")) {
					int temp = currpos - i;
					if (Math.abs(temp) < nearest) {
						nearest = Math.abs(temp);
						nearestpos = i;
					}
				}
			}

			int rowOfNearest = 0;
			if (nearestpos < 5) {
				rowOfNearest = 0;
			} else if (nearestpos < 10) {
				rowOfNearest = 1;
			} else if (nearestpos < 15) {
				rowOfNearest = 2;
			} else if (nearestpos < 20) {
				rowOfNearest = 3;
			} else if (nearestpos < 25) {
				rowOfNearest = 4;
				
			}
			
			if (nearestpos < currpos) {
				if (posr == rowOfNearest) {
					System.out.println("LEFT");
				} else {
					System.out.println("UP");
				}
			} else {
				if (posr == rowOfNearest) {
					System.out.println("RIGHT");
				} else {
					System.out.println("DOWN");
				}

			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n, r, c;
		n = 5;
		r = in.nextInt();
		c = in.nextInt();
		in.useDelimiter("\n");
		String grid[] = new String[25];
		int counter = 0;
		in.nextLine();
		while (counter < 5) {
			char[] arr = in.nextLine().toCharArray();
			try {
				for (int i = 0; i < n; i++) {
					grid[counter * n + i] = Character.toString(arr[i]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			counter++;
		}
		in.close();
		BotClean bc = new BotClean();
		bc.next_move(r, c, grid);

	}
}
