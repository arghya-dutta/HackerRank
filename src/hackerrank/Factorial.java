package hackerrank;

import java.math.BigInteger;
import java.util.Scanner;

public class Factorial {
	static void extraLongFactorials(int n) {
		BigInteger bi = new BigInteger("1");
			for (int i = 2; i <= n; i++) {
				bi = bi.multiply(new BigInteger(i + ""));
			}
			System.out.println(bi.toString());
	}

	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int n = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		extraLongFactorials(n);

		scanner.close();
	}
}
