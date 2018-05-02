package sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuSolver {
	// declare problem grid
	static int problem[][] = { 
			{ 6, 0, 1, 8, 0, 3, 0, 0, 0 }, 
			{ 0, 0, 0, 6, 4, 0, 0, 7, 8 },
			{ 0, 8, 0, 0, 0, 0, 6, 0, 0 }, 
			{ 0, 2, 0, 0, 0, 4, 0, 0, 0 }, 
			{ 3, 0, 8, 0, 5, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 0, 0, 0, 9, 3 }, 
			{ 0, 0, 0, 4, 0, 8, 3, 0, 0 }, 
			{ 0, 0, 2, 7, 0, 0, 0, 5, 0 },
			{ 9, 0, 0, 0, 6, 0, 0, 8, 0 } 
	};

	// create working grid
	static int work[][] = new int[9][9];

	// if all numbers are filled out correctly, show it on console
	public static void main(String[] args) {
		initialize();
		boolean b = oneCell(0);
		if (b)
			printSuccess();
	}

	// adds numbers given into working grid
	static void initialize() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				work[i][j] = problem[i][j];
			}
		}
	}

	// prints "Success" when finished
	static void printSuccess() {
		System.out.println("Success!");
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(work[i][j] + " ");
			}
			System.out.println();
		}
	}

	// look at each cell until all cells checked
	static boolean oneCell(int nCell) {
		// if all cells checked return true
		if (nCell == 81) {
			return true;
		}
		int i = nCell / 9;
		int j = nCell % 9;
		// skips cells already given
		if (problem[i][j] != 0) {
			return oneCell(nCell + 1);
		}
		// creates possibilities list
		List<Integer> possible = new ArrayList<>();
		for (int k = 0; k < 9; k++) {
			possible.add(Integer.valueOf(k + 1));
		}
		// checks row, column, and 3X3 block
		for (int k = 0; k < 9; k++) {
			possible.remove(Integer.valueOf(work[i][k]));
		}
		for (int k = 0; k < 9; k++) {
			possible.remove(Integer.valueOf(work[k][j]));
		}
		int m = 3 * (i / 3);
		int n = 3 * (j / 3);
		for (int k = m; k < m + 3; k++) {
			for (int l = n; l < n + 3; l++) {
				possible.remove(Integer.valueOf(work[k][l]));
			}
		}
		// pick first number and if wrong then pick next number. continues until
		// empty or returns true.
		while (!possible.isEmpty()) {
			int mytry = possible.remove(0);
			work[i][j] = mytry;
			boolean b = oneCell(nCell + 1);
			if (b) {
				return true;
			}
		}
		// if it is empty, try again
		work[i][j] = 0;
		return false;
	}
}
