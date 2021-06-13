package algorithmseasy;

public class NumberLIneJumps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(kangaroo(43, 2, 70, 2));
	}

	public static String kangaroo(int x1, int v1, int x2, int v2) {
		// Write your code here
		if (x1 > x2 && v1 > v2)
			return "NO";
		if (x2 > x1 && v2 > v1)
			return "NO";
		if (v1 == v2 & x1 == x2) {
			return "YES";
		}
		if (v1 == v2) {
			return "NO";
		}
		if (((x2 - x1) % (v2 - v1)) == 0)
			return "YES";
		return "NO";
	}
}
