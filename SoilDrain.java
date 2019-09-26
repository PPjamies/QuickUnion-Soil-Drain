package part1;

//Dominic Luu
//Pazuzu Jindrich
//5/8/2019
//CS 401
//HW 2

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SoilDrain {

	public static void main(String[] args) throws FileNotFoundException {
		// Reads list of files 
		// Assumes file represents an nxn matrix consisting of 0's and 1's
		String[] fileList = {"sample1.txt", "sample2.txt", "sample3.txt", "sample4.txt"};
		for (String file : fileList) {
			File input = new File(file);
			Scanner scanner = new Scanner(input);
			
			Scanner tempScanner = new Scanner(input);
		
			int n = 0;
		
			// Find length/width of matrix
			if (tempScanner.hasNextLine()) {
				String line = tempScanner.nextLine();
				for (int i = 0; i < line.length(); i++) {
					char c = line.charAt(i);
					if (c == '0' || c == '1') {
						n++;
					}
				}
			}
		
			int[][] arr = new int[n][n];
			int i = 0;
			
			// Build matrix as 2d integer array 
			while (scanner.hasNextLine()) {
				int j = 0;
				String line = scanner.nextLine();
				for (int k = 0; k < line.length(); k++) {
					if (line.charAt(k) == '0') {
						arr[i][j] = 0;
						j++;
					} else if (line.charAt(k) == '1') {
						arr[i][j] = 1;
						j++;
					}
				}
				i++;
			}
			
			// Print out innput
			printInput(arr);

			// Process matrix
			canSoilDrain(arr);
		}
	}

	// Returns true if inputs x and y represent a valid coordinate.
	// X and y are valid coordinates if they are in the boundaries of the array or nxn matrix
	// of size .;
	private static boolean isValidCoord(int x, int y, int n) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}

	
	// Weighted quick union with path compression
	
	// Assumes that input 2d array represents an n x n matrix of 1's and 0's.
	
	// Input matrix represents a soil sample where 1's adjacent but not diagonal to each other 
	// represent a soil path that water can flow through. 

	// Prints whether or not the soil grid allows water to flow through the bottom.	
	private static void canSoilDrain(int[][] arr) {
		int n = arr.length;
		QuickUnionDisjointSet ds = new QuickUnionDisjointSet(n * n);
		
		// Traverse through matrix and union nodes representing adjacent 1's
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == 1) {
					// Check Left
					if (isValidCoord(i, j - 1, n) &&  arr[i][j - 1] == 1)  {
						ds.union(i * n + j, i * n + j );
					} 
					// Check Right
					if (isValidCoord(i, j + 1 , n) &&  arr[i][j + 1] == 1) {
						ds.union(i * n + j, (i  * n) +  j + 1);
					}
					// Check Above 
					if (isValidCoord(i - 1, j, n) && arr[i - 1][j] == 1) {
						ds.union(i * n + j, i * n + j - n);
					}
					// Check Below 
					if (isValidCoord(i + 1, j, n) && arr[i + 1][j] == 1) {
						ds.union(i * n + j, i * n + j + n - 1);
					}
				}
			}
		}	

		
		// Checking if any ID's in the top row of the matrix are in the bottom row
		boolean flow = false;
		Set<Integer> idSet = new HashSet<Integer>();
	
		
		// First n nodes (top row)
		for (int i = 0; i < n; i++) {
			idSet.add(ds.find(i));
		}
		// Last n nodes (bottom row)
		for (int i = n * n - n; i < n * n; i++) {
			if (idSet.contains(ds.find(i)) ) {
				flow = true;
			}
		}

		// Print result
		printResult(flow);
	}

	
	// Quick Find
	
	// Assumes that input 2d array represents an n x n matrix of 1's and 0's.

	// Input n represents the size of the matrix where the matrix's dimensions are n x n.
	// Input matrix represents a soil sample where 1's adjacent but not diagonal to each other 
	// represent a soil path that water can flow through. 
	
	// Prints whether or not the soil grid allows water to flow through the bottom.
	private static void canSoilDrain(int[][] arr, int n) {
		DisjointSet ds = new DisjointSet(n);
		
		// Traverse through matrix and union nodes representing adjacent 1's
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == 1) {
					// Check Left
					if (isValidCoord(i - 1, j , n) &&  arr[i - 1][j] == 1)  {
						ds.union(i, j, i - 1, j);
					} 
					// Check Right
					if (isValidCoord(i + 1, j , n) &&  arr[i + 1][j] == 1) {
						ds.union(i, j, i + 1, j);
					}
					// Check Above 
					if (isValidCoord(i, j - 1, n) && arr[i][j - 1] == 1) {
						ds.union(i, j, i, j - 1);
					}
					// Check Below 
					if (isValidCoord(i, j + 1, n) && arr[i][j + 1] == 1) {
						ds.union(i, j, i, j + 1);
					}
				}
			}
			
		}
		
		// Checking if any ID's in the top row of the matrix are in the bottom row
		boolean flow = false;
		Set<Integer> ht = new HashSet<Integer>();
		
		for (int i = 0; i < n; i++) {
			ht.add(ds.find(0,  i));
		}
		for (int i = 0; i < n; i++) {
			if (ht.contains(ds.find(n - 1,  i)) ) {
				flow = true;
			}
		}

		// Print result
		printResult(flow);
	}
	
	private static void printResult(boolean flow) {
		System.out.println();
		System.out.println("Output:");
		if (flow) {
			System.out.println("Allows water to drain \n");
		} else {
			System.out.println("Does not allow water to drain \n");
		}
		System.out.println();
	}
	
	private static void printInput(int[][] arr) {
		System.out.println("Input:");
		for (int[] a : arr) {
			for (int num : a) {
				System.out.print(num);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}