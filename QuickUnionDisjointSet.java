package part1;

//Dominic Luu
//Pazuzu Jindrich
//5/8/2019
//CS 401
//HW 2

//This collection represents disjoint sets of nodes with representative nodes/elements (id's)
//in a 2d array/matrix. It is capable of unioning sets together or finding the representative node

//Implements weighted quick-union algorithm w/ path compression
public class QuickUnionDisjointSet {
	private int[] id;
	private int[] weight;
	int count;
	
	public QuickUnionDisjointSet(int n) {
		id = new int[n];
		weight = new int[n];
		count = n;
	
		// Initialize all nodes to point to themselves
		// Initialize all node weights to 1
		for (int i = 0; i < n; i++) {
			id[i] = i;
			weight[i] = 1;
		}
	}
	
	
 // Returns the id representing the root node of the set containing input id
	public int find(int inputId) {
		if (id[inputId] == inputId) { 
			return inputId;
		}
		
		id[inputId] = find(id[inputId]);
		return id[inputId];
	}
	
	// Given two id's will union the two sets containing the two id's. 
	// If the weight of the first set is less than the weight of the second,
	// sets the the parent of the root of the first set to the root of the second. 
	// Otherwise it does the inverse and joins the root of the second set to the root of the first.
	public void union(int id_1, int id_2) {
		// Get root of the set containing input nodes
		int rootA = find(id_1);
		int rootB = find(id_2);
		
		if (rootA != rootB) {
			if (weight[rootA] < weight[rootB]) {
				id[rootA] = rootB;
				weight[rootB]++;
			} else {
				id[rootB] = rootA;
				weight[rootA]++;
			}
		}
	}
	
	public int size() {
		return count;
	}
	
}