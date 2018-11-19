package mathematics;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.Scanner;

public class ElementaryMath {

	static Scanner myScanner = new Scanner(System.in);
	static Random myRandom = new Random();

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("Welcome to my ElementaryMath Problem Generator!");
		while (true) {
			try {
				System.out.println("\nWhat type of problem do you want to create?");
				System.out.println("\nChoose one of the following: Addition, Subtraction, Multiplication, and Division.");
				String problemTypeName = myScanner.next().toLowerCase();
				System.out.println("\nWhat difficulty would you like to set?");
				System.out.println("\nChoose an integer from 1 to 5.");
				int difficulty = myScanner.nextInt();

				ElementaryMath thisClass = new ElementaryMath();
				Method problemType = thisClass.getClass().getMethod(problemTypeName, int.class);
				String returnVal = (String) problemType.invoke(thisClass, difficulty);
				System.out.println("Here are the generated problem(s):\n" + returnVal);
				break;
			} catch (NoSuchMethodException e) {
				System.out.println("Error: Input not recognized");
			} catch (Exception e) {
				System.out.println("Error: Unknown Exception");
			}
		}
	}

	public String addition(int difficulty) {
		System.out.println("You chose Addition, Difficulty " + difficulty + ".");
		int a = 0;
		int b = 0;
		switch (difficulty) {
		case 1:
			a = 10;
			b = 1;
			break;
		case 2:
			a = 10;
			b = 2;
			break;
		case 3:
			a = 100;
			b = 3;
			break;
		case 4:
			a = 1000;
			b = 3;
			break;
		case 5:
			a = 10000;
			b = 4;
			break;
		}
		String problem = "Solve for ";
		for (int i = 0; i < b; i++) {
			problem += myRandom.nextInt(a) + " + ";
		}
		problem += myRandom.nextInt(a) + " = ";

		return problem;
	}

	// public String subtraction() {
	// System.out.println("You chose Subtraction!");
	//
	// return "";
	// }
	//
	// public String multiplication() {
	// System.out.println("You chose Multiplication!");
	//
	// return "";
	// }
	//
	// public String division() {
	// System.out.println("You chose Division!");
	//
	// return "";
	// }

}
