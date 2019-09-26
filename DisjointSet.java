package part1;

//Dominic Luu
//Pazuzu Jindrich
//5/8/2019
//CS 401
//HW 2

//This collection represents disjoint sets of nodes with representative nodes/elements (id's)
//in a 2d array/matrix. It is capable of unioning sets together or finding the representative node

//Implements quick-find algorithm
	
public class DisjointSet {
	 private int[][] id;
	 private int count;
	
	
	 // Initialize an nxn matrix with unique id's
	 public DisjointSet(int n) {
	     int idCount = 0;
	     id = new int[n][n];
	
	     // Initialize id matrix with unique id's
	     for (int i = 0; i < n; i++) {
	         for (int j = 0; j < n; j++) {
	             id[i][j] = idCount;
	             idCount++;
	             count++;
	         }
	     }
	 }
	
	
	 // Gets id index x, y
	 // Throws IllegalArgumentException if x and y are out of matrix bounds
	 public int find(int x, int y) {
	     if (!isValidCoord(x, y)) {
	         throw new IllegalArgumentException();
	     }
	     
	     int parentX = x;
	     int parentY = y;
	     
	     return id[x][y];
	 }
	
	
	 // Given inputs i_1, j_1 representing coordinates for the first node and i_2, j_2 representing
	 // coordinates for the second node, will union those two nodes
	 // Ignores invalid indexes / indexes that are out of bounds
	 public void union(int x_1, int y_1, int x_2, int y_2) {
	     int n = id.length;
	
	     // Bound checking
	     if (isValidCoord(x_1, y_1) && isValidCoord(x_2, y_2)) {
	         
	     	int newId = find(x_1, y_1);
	         int currentId= find(x_2, y_2);
	
	         // Set all old index pointing to old id's to 
	         // point to the new id
	         if (newId != currentId) {
	             for (int i = 0; i < n; i++) {
	                 for (int j = 0; j < n; j++) {
	                     if(id[i][j] == currentId) {
	                         id[i][j] = newId;
	                         count--;
	                     }
	                 }
	             }
	         }
	     }
	 }
	
	 // Returns number of disjoint sets in collection
	 public int size() {
	     return count;
	 }
	 
	 // Returns true if input x and y represent a valid coordinate
	 // A valid coordinate is a coordinate which is in the boundary of the matrix
	 private boolean isValidCoord(int x, int y) {
	 	int n = id.length;
	 	return x >= 0 && x < n && y >= 0 && y < n;
	 }
}