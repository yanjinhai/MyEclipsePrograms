package sudoku;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SudokuCreator {
	// declares variables
	static int problem[][] = {
			{ 6, 4, 1, 8, 7, 3, 9, 2, 5 },
			{ 2, 9, 3, 6, 4, 5, 1, 7, 8 },
			{ 5, 8, 7, 1, 2, 9, 6, 3, 4 },
			{ 7, 2, 9, 3, 8, 4, 5, 1, 6 },
			{ 3, 1, 8, 9, 5, 6, 2, 4, 7 },
			{ 4, 5, 6, 2, 1, 7, 8, 9, 3 },
			{ 1, 7, 5, 4, 9, 8, 3, 6, 2 },
			{ 8, 6, 2, 7, 3, 1, 4, 5, 9 },
			{ 9, 3, 4, 5, 6, 2, 7, 8, 1 },
	};

	static int work[][] = new int[9][9];
	static int work2[][] = new int[9][9];
	static int f = 0;
	static int L = 0;
	static int d = 0;
	static int e = 0;

	public static void main(String[] args) throws IOException {
		FileWriter fwr = new FileWriter(args[0]);
		BufferedWriter wr = new BufferedWriter(fwr);
		for (int c = 0; c < 5; c++) {
			for (int h = 0; h < 9; h++) {
				L = 0;
				Random rn = new Random();
				initialize();
				// create pattern and problem
				boolean b = oneCell(0);
				if (b) {
					// change problem
					for (int i = 0; i < 9; i++) {
						for (int j = 0; j < 9; j++) {
							problem[i][j] = work[i][j];
						}
					}
					boolean q = false;
					while (q == false) {
						initialize();
						// make it so it has only 1 solution
						boolean g = twoCell(0);
						if (g) {
							int s = 0;
							for (int i = 0; i < 9; i++) {
								for (int j = 0; j < 9; j++) {
									if (work2[i][j] == work[i][j]) {
										s++;
									}
								}
							}
							if (s == 81) {
								f++;
								wr.write("Success!" + f + "\n");
								for (int a = 0; a < 9; a++) {
									for (int aa = 0; aa < 9; aa++) {
										wr.write(problem[a][aa] + " ");
									}
									wr.write("\n");
								}
								if (d == 8) {
									d = 0;
									if (e != 8) {
										e++;
									}
								} else {
									if (h == 9) {
										h = 0;
										d++;
									}
								}
								reset();
								q = true;
							} else {
								int r = Math.abs(rn.nextInt() % 9);
								int t = Math.abs(rn.nextInt() % 9);
								problem[r][t] = work2[r][t];
							}
						}
					}
				}
			}
		}
		wr.close();
	}

	static void reset() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				problem[i][j] = 0;
				work[i][j] = 0;
				work2[i][j] = 0;
			}
		}
	}

	static void initialize() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				work[i][j] = problem[i][j];
			}
		}
	}

	static boolean oneCell(int nCell) {
		Random rn = new Random();
		if (nCell == 81) {
			return true;
		}
		int i = nCell / 9;
		int j = nCell % 9;
		//Do I need this?
		if (problem[i][j] != 0) {
			return oneCell(nCell + 1);
		}
		/////////////////
		List<Integer> possible = new ArrayList<>();
		for (int k = 0; k < 9; k++) {
			possible.add(Integer.valueOf(k + 1));
		}
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
		while (!possible.isEmpty()) {
			int p = rn.nextInt() % possible.size();
			if (p < 0) {
				p = -p;
			}
			int mytry = possible.remove(p);
			work[i][j] = mytry;
			boolean b = oneCell(nCell + 1);
			if (b) {
				work2[i][j] = work[i][j];
				if (L < 64) {
					if (rn.nextInt(3) != 1) {
						L++;
						work[i][j] = 0;
					}
				}
				return true;
			}
		}
		work[i][j] = 0;
		work2[i][j] = 0;
		return false;
	}

	static boolean twoCell(int nCell) {
		if (nCell == 81) {
			return true;
		}
		int i = nCell / 9;
		int j = nCell % 9;
		if (problem[i][j] != 0) {
			return twoCell(nCell + 1);
		}
		List<Integer> possible = new ArrayList<>();
		for (int k = 0; k < 9; k++) {
			possible.add(Integer.valueOf(k + 1));
		}
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
		while (!possible.isEmpty()) {
			int mytry = possible.remove(0);
			work[i][j] = mytry;
			boolean b = twoCell(nCell + 1);
			if (b) {
				return true;
			}
		}
		work[i][j] = 0;
		return false;
	}
}