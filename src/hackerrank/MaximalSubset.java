package hackerrank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class MaximalSubset {

	// Complete the nonDivisibleSubset function below.
	static int nonDivisibleSubset(int k, int[] s) {
		int res = 0;
		int[] a = new int[k];
		Arrays.stream(s).forEach(i -> {
			a[i % k]++;
		});
		if (a[0] >= 1) {
			res++;
		}
		for (int i = 1; i < (k / 2) + 1; i++) {
			if (a[i] > a[k - i]) {
				res = res + a[i];
			} else if (a[i] < a[k - i]) {
				res = res + a[k - i];
			} else if (a[i] == a[k - i] && a[i] !=0) {
				res++;
			}
		}
		return res;
	}

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		// Scanner scanner = new Scanner(new File("tc15.txt"));
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] nk = scanner.nextLine().split(" ");

		int n = Integer.parseInt(nk[0]);

		int k = Integer.parseInt(nk[1]);

		int[] S = new int[n];

		String[] SItems = scanner.nextLine().split(" ");
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			int SItem = Integer.parseInt(SItems[i]);
			S[i] = SItem;
		}

		int result = nonDivisibleSubset(k, S);

		bufferedWriter.write(String.valueOf(result));
		bufferedWriter.newLine();

		bufferedWriter.close();

		scanner.close();
	}
}
