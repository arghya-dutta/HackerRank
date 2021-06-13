package algorithmseasy;

import java.util.Arrays;
import java.util.List;

public class ApplesandOranges {

	public static void main(String[] args) {
		countApplesAndOranges(2, 3, 1, 5, Arrays.asList(new Integer[] { 2 }), Arrays.asList(new Integer[] { -2 }));
	}

	public static void countApplesAndOranges(int s, int t, int a, int b, List<Integer> apples, List<Integer> oranges) {
		// Write your code here
		int appleCount = 0;
		int orangeCount = 0;
		for (Integer apple : apples) {
			int i = a + apple;
			if (s > a) {
				if (i >= s & i <= t)
					appleCount++;
			} else {
				if (i <= t & i >= s)
					appleCount++;
			}

		}
		System.out.println(appleCount);
		for (Integer orange : oranges) {
			int i = b + orange;
			if (s > b) {
				if (i >= s & i <= t)
					orangeCount++;
			} else {
				if (i <= t & i >= s)
					orangeCount++;
			}
		}
		System.out.println(orangeCount);
	}
}
