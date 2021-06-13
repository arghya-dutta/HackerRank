package algorithmseasy;
import java.io.IOException;

class Result {

	/*
	 * Complete the 'timeConversion' function below.
	 *
	 * The function is expected to return a STRING. The function accepts STRING s as
	 * parameter.
	 */

	public static String timeConversion(String s) {
		// Write your code here
		String converted = "";
		String hr = s.substring(0, s.indexOf(":"));
		String newhr = "";
		if (s.endsWith("PM")) {
			if (hr.equals("12")) {
				newhr = hr;
			} else {
				newhr = Integer.toString(Integer.parseInt(hr) + 12);
			}

		} else {
			if (hr.equals("12")) {
				newhr = "00";
			} else {
				newhr = hr;
			}
		}
		converted = s.replace(hr, newhr).substring(0, s.length() - 2);
		return converted;

	}

}

public class timeconversion {
	public static void main(String[] args) throws IOException {

		String result = Result.timeConversion("12:01:00AM");

		System.out.println(result);

	}
}
