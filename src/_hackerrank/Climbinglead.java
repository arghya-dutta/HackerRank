package _hackerrank;

import java.io.IOException;
import java.util.Scanner;

public class Climbinglead { // Complete the climbingLeaderboard function below.
	static int[] climbingLeaderboard(int[] scores, int[] alice) {
		int[] result = new int[alice.length];
		int[] rank = new int[scores.length];
		int r = 1;
		rank[0] = r;
		for (int j = 1; j < scores.length; j++) {
			if (scores[j] == scores[j - 1]) {
				rank[j] = r;
			} else {
				r++;
				rank[j] = r;
			}
		}

		for (int i = 0; i < alice.length; i++) {
			int alic = alice[i];
			int finish = scores.length - 1;
			int start = 0;
			int m = 0;
			int insert = 0;
			while (start <= finish) {
				m = (start + finish) / 2;
				if (alic > scores[m]) {
					finish = m - 1;
					insert = m;
				} else if (alic < scores[m]) {
					start = m + 1;
					insert = start;
				} else {
					result[i] = rank[m];
					break;
				}
			}
			if (result[i] == 0) {
				if (0 <= insert && insert < rank.length) {
					result[i] = rank[insert];
				} else if (insert < 0) {
					result[i] = 1;
				} else {
					result[i] = rank[scores.length - 1] + 1;
				}

			} else {

			}

		}
		return result;
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		// BufferedWriter bufferedWriter = new BufferedWriter(new
		// FileWriter(System.getenv("OUTPUT_PATH")));

		int scoresCount = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[] scores = new int[scoresCount];

		String[] scoresItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < scoresCount; i++) {
			int scoresItem = Integer.parseInt(scoresItems[i]);
			scores[i] = scoresItem;
		}

		int aliceCount = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		int[] alice = new int[aliceCount];

		String[] aliceItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < aliceCount; i++) {
			int aliceItem = Integer.parseInt(aliceItems[i]);
			alice[i] = aliceItem;
		}

		int[] result = climbingLeaderboard(scores, alice);

		for (int i = 0; i < result.length; i++) {
			// bufferedWriter.write(String.valueOf(result[i]));
			System.out.println(String.valueOf(result[i]));
			if (i != result.length - 1) {
				System.out.println("\n");
				// bufferedWriter.write("\n");
			}
		}

		// bufferedWriter.newLine();

		// bufferedWriter.close();

		scanner.close();
	}
}
