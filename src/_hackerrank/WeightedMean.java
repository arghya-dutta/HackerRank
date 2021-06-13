package _hackerrank;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeightedMean {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int no = in.nextInt();
		List<Integer> numbers = new ArrayList<Integer>();
		List<Integer> weights = new ArrayList<Integer>();
		for (int i = 0; i < no; i++) {
			numbers.add(in.nextInt());
		}
		for (int i = 0; i < no; i++) {
			weights.add(in.nextInt());
		}
		double sum = 0, avg_sum = 0;
		for (int i = 0; i < no; i++) {
			sum = sum + numbers.get(i) * weights.get(i);
			avg_sum = avg_sum + weights.get(i);
		}
		double weighted_avg = sum / avg_sum;
		DecimalFormat df = new DecimalFormat("#.0");
//		df.setRoundingMode(RoundingMode.CEILING);
		System.out.println(df.format(weighted_avg));
//		System.out.println(weighted_avg);
		in.close();
	}
}
