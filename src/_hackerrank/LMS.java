package _hackerrank;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class LMS {

	// Complete the morganAndString function below.
	static String morganAndString(String a, String b) {
		String lms = "";
		Deque<String> adq = new ArrayDeque<>(Arrays.asList(a.split("")));
		Deque<String> bdq = new ArrayDeque<>(Arrays.asList(b.split("")));
		while (!adq.isEmpty() || !bdq.isEmpty()) {
			if (adq.isEmpty()) {
				while (!bdq.isEmpty()) {
					lms += bdq.pop();
				}
				break;
			}

			if (bdq.isEmpty()) {
				while (!adq.isEmpty()) {
					lms += adq.pop();
				}
				break;
			}

			char a_ch = 9999, b_ch = 9999;
			a_ch = adq.peek().charAt(0);
			b_ch = bdq.peek().charAt(0);

			if (a_ch < b_ch) {
				lms += adq.pop();
			} else if (b_ch < a_ch) {
				lms += bdq.pop();
			} else {
				
			}
		}
		return lms;
	}

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(new File("lms.txt"));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("result.txt")));

		int t = scanner.nextInt();
		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int tItr = 0; tItr < t; tItr++) {
			String a = scanner.nextLine();

			String b = scanner.nextLine();

			String result = morganAndString(a, b);

			bufferedWriter.write(result);
			bufferedWriter.newLine();
		}

		bufferedWriter.close();

		scanner.close();
		
		Scanner scanner1 = new Scanner(new File("result.txt"));
		Scanner scanner2 = new Scanner(new File("lMS-exp.txt"));
		if(scanner1.hasNextLine()){
			if(scanner1.nextLine().equals(scanner2.nextLine())){
				System.out.println("matched");
			} else {
				System.out.println("asdas");
			}
		}
		scanner1.close();
		scanner2.close();
	}
}
