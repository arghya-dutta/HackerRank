package hackerrank;
import java.util.Arrays;
import java.util.Scanner;

public class Equal {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcases = in.nextInt();
		int colleageus = in.nextInt();
		int[] distribution = new int[colleageus];
		for (int i = 0; i < testcases; i++) {
			distribution[i] = in.nextInt();
		}
		Arrays.sort(distribution);
		for (int i = distribution.length - 1; i > -1; i++) {
			
		}
		in.close();
	}
}
