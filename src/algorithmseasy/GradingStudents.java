package algorithmseasy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GradingStudents {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(gradingStudents(Arrays.asList(new Integer[] { 38, 40, 41, 43, 42 })));
	}

	public static List<Integer> gradingStudents(List<Integer> grades) {
		// Write your code here
		List<Integer> modified = new ArrayList<Integer>();
		grades.forEach(g -> {
			if (g > 38) {
				int rem = (g + 5) % 5;
				int multOf5 = g + 5 - rem;
				if (multOf5 - g < 3)
					modified.add(multOf5);
				else
					modified.add(g);
			} else
				modified.add(g);
		});
		return modified;
	}
}
