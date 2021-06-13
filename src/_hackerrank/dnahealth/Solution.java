package _hackerrank.dnahealth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Solution {

	public static List<String> geneList = new ArrayList<String>();
	public static int[] health;
	public static Map<String, List<MultiValueGene>> multivalueGenes;

	private static long min = Long.MAX_VALUE, max = Long.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		// BufferedReader scanner = new BufferedReader(new
		// InputStreamReader(System.in));
		// BufferedReader scanner = new BufferedReader(new
		// FileReader("tc1.txt")); // 3218660
		// 11137051
		BufferedReader scanner = new BufferedReader(new FileReader("tc2.txt")); // 15806635
		// 20688978289
		int n = Integer.parseInt(scanner.readLine());
		// scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		// String[] genes = new String[n];

		String[] genesItems = scanner.readLine().split(" ");
		// scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
		multivalueGenes = new HashMap<String, List<MultiValueGene>>();
		for (int i = 0; i < n; i++) {
			String genesItem = genesItems[i];
			if (geneList.contains(genesItem)) {
				if (multivalueGenes.get(genesItem) == null) {
					List<MultiValueGene> l = new ArrayList<MultiValueGene>();
					MultiValueGene original = new MultiValueGene(geneList.indexOf(genesItem));
					l.add(original);
					MultiValueGene mvg = new MultiValueGene(i);
					l.add(mvg);
					multivalueGenes.put(genesItem, l);
				} else {
					MultiValueGene mvg = new MultiValueGene(i);
					multivalueGenes.get(genesItem).add(mvg);
				}
			}
			geneList.add(genesItem);

		}

		health = new int[n];

		String[] healthItems = scanner.readLine().split(" ");
		// scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		for (int i = 0; i < n; i++) {
			int healthItem = Integer.parseInt(healthItems[i]);
			health[i] = healthItem;
		}
		int s = Integer.parseInt(scanner.readLine());
		// scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

		List<String[]> inputs = new ArrayList<String[]>();
		for (int sItr = 0; sItr < s; sItr++) {
			inputs.add(scanner.readLine().split(" "));
		}
		long start = System.currentTimeMillis();
		ForkJoinPool fjp = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		// fjp.submit(new Runnable() {
		//
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		//
		// }
		// });

		fjp.submit(() -> {
			inputs.parallelStream().forEach(item -> {
				int first = Integer.parseInt(item[0]);

				int last = Integer.parseInt(item[1]);

				String d = item[2];

				Execute e = new Execute();
				e.execute(first, last, d);
				Lock l = new ReentrantLock();
				l.lock();
				if (e.getMin() < min) {
					min = e.getMin();
				}

				if (max < e.getMax()) {
					max = e.getMax();
				}
				l.unlock();
			});
		}).get();

		/*
		 * for (String[] firstLastd : inputs) { int first =
		 * Integer.parseInt(firstLastd[0]);
		 * 
		 * int last = Integer.parseInt(firstLastd[1]);
		 * 
		 * String d = firstLastd[2];
		 * 
		 * Execute e = new Execute(); e.execute(first, last, d);
		 * 
		 * }
		 */
		System.out.println(min + " " + max);
		long end = System.currentTimeMillis();
		System.out.println("processing end : " + (float) (end - start) / 1000);
		scanner.close();
	}

}

class MultiValueGene {
	private int value;
	private boolean usable = true;

	public MultiValueGene(int value) {
		this.value = value;
	}

	public boolean isUsable() {
		return usable;
	}

	public void setUsable(boolean usable) {
		this.usable = usable;
	}

	public int getValue() {
		return value;
	}

}

class SuffixNode {
	private List<String> output;
	private SuffixNode suffixNodes;
	private List<SuffixNode> nodes;
	private String content;
	private String label;
	private boolean isRoot;
	private int depth;

	public SuffixNode(String prev, String content, int depth) {
		this.content = content;
		this.label = prev + content;
		output = new ArrayList<String>();
		nodes = new ArrayList<SuffixNode>();
		this.depth = depth;

	}

	public void setOutPut() {
		output.add(label);
	}

	@Override
	public String toString() {
		return content;
	}

	public final List<SuffixNode> getNodes() {
		return nodes;
	}

	public final void setNodes(List<SuffixNode> nodes) {
		this.nodes = nodes;
	}

	public final String getContent() {
		return content;
	}

	public final void setContent(String content) {
		this.content = content;
	}

	public final boolean isRoot() {
		return isRoot;
	}

	public final void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public final String getLabel() {
		return label;
	}

	public final void setLabel(String label) {
		this.label = label;
	}

	public final List<String> getOutput() {
		return output;
	}

	public final SuffixNode getSuffixNodes() {
		return suffixNodes;
	}

	public final int getDepth() {
		return depth;
	}

	public final void setDepth(int depth) {
		this.depth = depth;
	}

	public final void setSuffixNodes(SuffixNode suffixNodes) {
		this.suffixNodes = suffixNodes;
	}
}

class Execute {
	private Map<Integer, List<SuffixNode>> allnodes = new HashMap<Integer, List<SuffixNode>>();
	SuffixNode root;
	// hardstop flag, denotes text has been parsed or trie has been fully
	// traversed
	private boolean stop = false;
	// used to restart iteration from last matched function
	private int startPos = 0;
	// set is needed if patterns are non-repeating, if they are repeating
	// use
	// list
	// Set<String> matches = new HashSet<String>();
	private List<String> matches = new ArrayList<String>();

	private long min = Long.MAX_VALUE, max = Long.MIN_VALUE;

	public void execute(int first, int last, String d) {
		root = new SuffixNode("", "", 0);
		root.setRoot(true);
		Solution.geneList.subList(first, last + 1).forEach(word -> formTrie(word));
		root.getNodes().forEach(node -> {
			node.getNodes().forEach(sn -> {
				findAndAddSuffix(sn);
				buildSuffixes(sn);
			});
		});
		int len = d.length() - 1;
		startPos = 0;
		long searchStart = System.currentTimeMillis();
		start(d.split(""), startPos, len);

		long result = 0;
		for (String match : matches) {
			if (Solution.multivalueGenes.containsKey(match)) {
				int jump = -1;
				for (MultiValueGene i : Solution.multivalueGenes.get(match)) {
					if (i.isUsable()) {
						jump = i.getValue();
						if (jump <= last) {
							result = result + Solution.health[jump];
							i.setUsable(false);
						}
						break;
					}
				}
			} else {
				result = result + Solution.health[Solution.geneList.indexOf(match)];
			}

		}
		// if(11137051==result){
		// System.out.println(d);
		// }
		if (result < min) {
			min = result;
		}

		if (max < result) {
			max = result;
		}
		matches.clear();
		root = null;
		startPos = 0;
		stop = false;
		Solution.multivalueGenes.values().forEach(mvgList -> {
			mvgList.forEach(mvg -> {
				mvg.setUsable(true);
			});
		});
		System.out.println("loop end : " + (float) (System.currentTimeMillis() - searchStart) / 1000);
		System.out.println(first + " " + last + " " + d + " " + max + " " + min);
	}

	private void buildSuffixes(SuffixNode n) {
		n.getNodes().forEach(sn -> {
			findAndAddSuffix(sn);
			buildSuffixes(sn);
		});
	}

	// failure function f(x)
	private void findAndAddSuffix(SuffixNode node) {
		String content = node.getContent();
		allnodes.get(node.getDepth() - 1).forEach(n -> {
			if (node.getSuffixNodes() == null && n.getContent().equals(content)) {
				node.setSuffixNodes(n);
				if (!n.getOutput().isEmpty()) {
					node.getOutput().addAll(n.getOutput());
				}
				return;
			}
		});
	}

	private void start(String[] text, int index, int end) {
		int i = -999;
		while (stop == false) {
			for (SuffixNode n : root.getNodes()) {
				i = searchTrie(text, startPos, end, n);
				if (stop) {
					break;
				}
				if (i == -1) {
					break;
				}
			}

			if (i == -1) {

			} else {
				startPos++;
			}

		}

	}

	// -1 string parsed , -2 - node didnot match
	private int searchTrie(String[] text, int index, int end, SuffixNode start) {

		if (index == end + 1) {
			stop = true;
			return -1;
		}
		if (start.getContent().equals(text[index])) {
			startPos++;
			if (!start.getOutput().isEmpty()) {
				// System.out.print(start.getOutput());
				start.getOutput().forEach(output -> {
					// System.out.println(health[geneList.indexOf(output)]);
					matches.add(output);
				});
			}

			if (index == end + 1) {
				stop = true;
				return -1;
			}
			// break on match
			int res = 0;
			for (SuffixNode child : start.getNodes()) {
				res = searchTrie(text, startPos, end, child);
				if (res == -1) {
					// stop = true;
					break;
				}
			}
			// break on match, if no match then call fail function
			int subres = 0;
			if (res != -1) {
				// no match found in children,check suffixes
				if (start.getSuffixNodes() != null) {
					for (SuffixNode subSuffix : start.getSuffixNodes().getNodes()) {
						subres = searchTrie(text, startPos, end, subSuffix);
						if (subres == -1) {
							// stop = true;
							break;
						}
					}
				}
			}
			return -1;
		} else {
			return 0;
		}
	}

	private void formTrie(String content) {
		String[] chars = content.split("");
		// if root is empty add there
		if (root.getNodes().isEmpty()) {
			SuffixNode n = new SuffixNode("", chars[0], 1);
			List<SuffixNode> list = new ArrayList<SuffixNode>();
			list.add(n);
			allnodes.put(1, list);
			root.getNodes().add(n);
		}
		// for first addition this block will skip
		// try to find if it is a new symbol or not
		boolean isNew = true;
		SuffixNode matched = null;
		for (SuffixNode n : root.getNodes()) {
			if (n.getContent().equals(chars[0])) {
				matched = n;
				isNew = false;
				break;
			}
		}
		// if new symbol the add to root
		if (isNew) {
			matched = new SuffixNode(root.getLabel(), chars[0], 1);
			allnodes.get(1).add(matched);
			root.getNodes().add(matched);
		}
		// for first addition above block will work

		// if old symbol then remove the matched symbol and do the same check
		// for the next symbol
		fillTrie(matched, chars, 1, 2);
	}

	// traverse trie , checks for new or symbol and populate all suffixes
	private boolean fillTrie(SuffixNode currNode, String[] chars, int index, int depth) {
		SuffixNode matched = null;
		boolean isNew = true;
		boolean isbreak = false;
		if (chars.length - 1 < index) {
			currNode.setOutPut();
			return true;
		}
		if (!currNode.getNodes().isEmpty()) {
			for (SuffixNode n : currNode.getNodes()) {
				if (n.getContent().equals(chars[index])) {
					matched = n;
					isNew = false;
					break;
				}
			}
		}

		if (isNew) {
			SuffixNode newNode = new SuffixNode(currNode.getLabel(), chars[index], depth);
			currNode.getNodes().add(newNode);
			List<SuffixNode> nodes = allnodes.get(depth);
			if (nodes == null) {
				nodes = new ArrayList<SuffixNode>();
				nodes.add(newNode);
				allnodes.put(depth, nodes);
			} else {
				nodes.add(newNode);
			}
			isbreak = fillTrie(newNode, chars, index + 1, depth + 1);
		} else {
			isbreak = fillTrie(matched, chars, index + 1, depth + 1);
		}
		if (isbreak) {
			return true;
		} else {
			return false;
		}
	}

	public final long getMin() {
		return min;
	}

	public final long getMax() {
		return max;
	}
}
