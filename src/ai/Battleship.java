package ai;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Battleship {

	void next_move(String board[][]) {
		boolean end = false;
		Set<String> visitedPlaces = new HashSet<String>();
		int startRow = 0, startCol = 0;
		while (!end) {
			int posr = -1, posc = -1;

			for (int i = startRow; i < 10; i++) {
				for (int j = startCol; j < 10; j++) {
					if (board[i][j].equals("h")) {
						posr = i;
						posc = j;
						if (!visitedPlaces.contains(posr + "" + posc)) {
							break;
						} else {
							posr = -1;
							posc = -1;
						}
					}
				}
				if (posr != -1) {
					break;
				}
			}
			if (posr > -1) {
// check for trend
				int left = 0, right = 0, up = 0, down = 0;
				for (int i = 1; i < 5; i++) {
					if (posr + i < 10 && board[posr + i][posc].contains("h")) {
						down++;
					}
					if (posr - i > -1 && board[posr - i][posc].contains("h")) {
						up++;
					}
					if (posc + i < 10 && board[posr][posc + i].contains("h")) {
						right++;
					}
					if (posc - i > -1 && board[posr][posc - i].contains("h")) {
						left++;
					}
				}
				boolean goLeft = false, goRight = false, goUp = false, goDown = false;
				if (left > 0) {
					if (posc - left - 1 > -1 && board[posr][posc - left].contains("-")) {
						goLeft = true;
					}
				}
				if (right > 0) {
					if (posc + right + 1 < 10 && board[posr][posc + right + 1].contains("-")) {
						goRight = true;
					}
				}
				if (down > 0) {
					if (posr + down + 1 < 10 && board[posr + down + 1][posc].contains("-")) {
						goDown = true;
					}
				}
				if (up > 0) {
					if (posr - up - 1 > 0 && board[posr - down - 1][posc].contains("-")) {
						goDown = true;
					}
				}
				if (goDown & goUp) {
					posr = posr - up - 1;
					System.out.println(posr + " " + posc);
					return;
				} else if (!goDown & goUp) {
					posr = posr - up - 1;
					System.out.println(posr + " " + posc);
					return;
				} else if (goDown & !goUp) {
					posr = posr + down + 1;
					System.out.println(posr + " " + posc);
					return;
				} else if (goLeft & !goRight) {
					posc = posc - left - 1;
					System.out.println(posr + " " + posc);
					return;
				} else if (!goLeft & goRight) {
					posc = posc + right + 1;
					System.out.println(posr + " " + posc);
					return;
				} else if (goLeft & goRight) {
					posc = posc + right + 1;
					System.out.println(posr + " " + posc);
					return;
				}

				// check for trend ends

				if (posr + 1 < 10 && board[posr + 1][posc].contains("-")) {
					System.out.println((posr + 1) + " " + posc);
					end = true;
				} else if (posc + 1 < 10 && board[posr][posc + 1].contains("-")) {
					System.out.println(posr + " " + (posc + 1));
					end = true;
				} else if (posr - 1 > -1 && board[posr - 1][posc].contains("-")) {
					System.out.println((posr - 1) + " " + posc);
					end = true;
				} else if (posc - 1 > -1 && board[posr][posc - 1].contains("-")) {
					System.out.println(posr + " " + (posc - 1));
					end = true;
				} else {
					startRow = posr;
					startCol = posc;
				}
			} else {
				Random random = new Random();
				int randomRow = random.nextInt(10);
				int randomCol = random.nextInt(10);
				boolean findTarget = true;
				while (findTarget) {
					if (board[randomRow][randomCol].equals("-")) {
						findTarget = false;
						System.out.println(randomRow + " " + randomCol);
						end = true;
					} else {
						randomRow = random.nextInt(10);
						randomCol = random.nextInt(10);
					}

				}
			}
		}

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n, r, c;
		n = 10;
		r = in.nextInt();
		in.useDelimiter("\n");
		String grid[][] = new String[10][10];
		int counter = 0;
		in.nextLine();
		while (counter < 10) {
			char[] arr = in.nextLine().toCharArray();
			try {
				for (int i = 0; i < n; i++) {
					grid[counter][i] = Character.toString(arr[i]);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			counter++;
		}
		in.close();
		Battleship bc = new Battleship();
		bc.next_move(grid);

	}
}
