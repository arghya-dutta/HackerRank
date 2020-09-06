package hackerrank;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KingdomDivision {
	private static List<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int no = in.nextInt();
		in.close();
		for (int i = 0; i < no; i++) {
			int source = in.nextInt();
			int target = in.nextInt();
			Node n = new Node();
			n.target = target;
			n.source = source;
			if (graph.get(source - 1) == null) {
				ArrayList<Node> al = new ArrayList<Node>();
				al.add(n);
				graph.add(al);
			} else {
				graph.get(source - 1).add(n);
			}
		}
	}
	
	private static void divide(){
		
	}
	
}

class Node {
	int color;
	int source;
	int target;
}
