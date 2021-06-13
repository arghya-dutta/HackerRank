package algorithmseasy;
import java.util.Arrays;

public class flatlandSpaceStations {
	public static void main(String[] args) {
		int[] c = new int[] { 0, 4 };
		int n = 5;

		int[] distances = new int[n];
		for (int i = 0; i < n; i++) {
			int min = n;
			for (int j = 0; j < c.length; j++) {
				int dist =  Math.abs(c[j] - i);
				if (dist == 0) {
					min = dist;
					break;
				}
				if (min > dist) {
					min = dist;
				}
				//System.out.println(min);
			}
		//	System.out.println("----");
			distances[i] = min;
		}
		System.out.println(Arrays.stream(distances).max().getAsInt());
	}
}
