/**
 * CalculatorView.java
 * @version $ID: CalculatorView.java, v 1.8 09/2/2015 7:02pm 
 * 
 * Revision: 6.1 09/5/2015 11:43am
 *
 */

import java.util.*;

/**
 * The Class represents the view for the Calculator design
 * 
 * @author Aditya Pulekar
 *
 */
public class CalculatorView {
	Vector<String> v = new Vector<String>(); // To accept the expression
	String finalSolution; // To store the final result

	/**
	 * The method should prompt the user to input the mathematical expression
	 * and accordingly accept the input
	 *
	 * @return mathematical expression
	 */
	public Vector<String> getTheExpression() {
		int itr = 0;
		String input;
		CalculatorView Cview = new CalculatorView();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the expression: \n");
		while (itr < 50) {
			input = sc.nextLine();
			if (input.equals("")) {
				break;
			}
			Cview.v.add(itr, input);
			itr++;
		}
		this.v = Cview.v;
		sc.close();
		return (this.v);
	}

	/**
	 * The method should fetch the result of the calculation
	 *
	 * @return gets the result
	 */
	public String getCalculatorSolution() {
		return (this.finalSolution);
	}

	/**
	 * The method should set the value of the final result
	 * 
	 *
	 * @param finalSolution
	 *            sets the final result onto the console
	 * 
	 */
	public void setCalculatorSolution(String finalSolution) {
		this.finalSolution = finalSolution;
		System.out.println("Result of the calculation: "
				+ getCalculatorSolution());
	}

}
