package sudoku;

public class CalculatePi {

	public static void main(String[] args) {
		int i = 3;
		for (int d = 1; d < 9999999; d++) {
			i = i + 4 / ((d + 1) * (d + 2) * (d + 3)) - 4 / ((d + 3) * (d + 4) * (d + 5));
		}
		System.out.println(i);
		// 3 + 4/(2*3*4) - 4/(4*5*6) + 4/(6*7*8) - 4/(8*9*10) + 4/(10*11*12) -
		// 4/(12*13*14)
	}

}
