/**
 * CalculatorModel.java
 * @version $ID: CalculatorModel.java, v 1.8 09/2/2015 7:02pm 
 * 
 * Revision: 6.1 09/5/2015 11:43am
 *
 */

import java.util.*;

/**
 * The Class represents the model for the Calculator design
 * 
 * @author Aditya Pulekar
 *
 */
public class CalculatorModel {
	public static int postf_z = 0; // To keep track of the number of elements in
									// the expression
	String finalResult; // To store the final result

	/**
	 * The method should resolve the order of every operator between every
	 * opening and closing parenthesis as per the precedence defined
	 *
	 * @param v
	 *            ,paren,num_arr,st reordering the operators within the
	 *            parenthesis
	 * 
	 * @return return the index of the stack
	 * 
	 */
	public int exp_paren(Vector<String> v, int paren, String[] num_arr,
			Stack<String> st) {
		while (!(v.elementAt(paren).equals(")"))) {
			if ((v.elementAt(paren).equals("+"))
					|| (v.elementAt(paren).equals("-"))
					|| (v.elementAt(paren).equals("%"))
					|| (v.elementAt(paren).equals("*"))
					|| (v.elementAt(paren).equals("/"))
					|| (v.elementAt(paren).equals("^"))
					|| (v.elementAt(paren).equals("("))) {
				if (st.empty()) {
					st.push(v.elementAt(paren));
				} else if (v.elementAt(paren).equals("*")
						&& (st.peek().equals("+") || st.peek().equals("-") || st
								.peek().equals("%"))) {
					st.push(v.elementAt(paren));
				} else if (v.elementAt(paren).equals("/")
						&& (st.peek().equals("+") || st.peek().equals("-")
								|| st.peek().equals("%") || st.peek().equals(
								"*"))) {
					st.push(v.elementAt(paren));
				} else if (v.elementAt(paren).equals("^")
						&& (st.peek().equals("+") || st.peek().equals("-")
								|| st.peek().equals("%")
								|| st.peek().equals("*") || st.peek().equals(
								"/"))) {
					st.push(v.elementAt(paren));
				} else if (v.elementAt(paren).equals("%")
						&& (st.peek().equals("+") || st.peek().equals("-"))) {
					st.push(v.elementAt(paren));
				} else if (v.elementAt(paren).equals("+")
						&& st.peek().equals("-")) {
					st.push(v.elementAt(paren));
				} else if (v.elementAt(paren).equals("(")) {
					st.push(v.elementAt(paren));
					paren = paren + 1;
					paren = exp_paren(v, paren, num_arr, st);
				} else {

					/*
					 * Pop the operators in the stack if a lower precedence or
					 * equal precedence operator is to be pushed on the stack
					 * and add it to the array
					 */

					while (!(st.peek().equals("("))) {
						num_arr[postf_z++] = (String) st.pop();
					}
					st.push(v.elementAt(paren));
				}
			} else {
				if (!(v.elementAt(paren).equals(")"))
						|| !(v.elementAt(paren).equals("(")))
					num_arr[postf_z++] = v.elementAt(paren);
			}
			paren++;
		}

		// After Closing Parenthesis are encountered

		while (!(st.peek().equals("("))) {
			num_arr[postf_z++] = (String) st.pop();
		}
		st.pop();
		System.out.println("\n");
		return (paren);
	}

	/**
	 * Given the mathematical expression, the method should reorder the
	 * operators as per the precedence
	 *
	 * @param v
	 *            evaluating the mathematical expression
	 * 
	 * @return result of the calculation
	 * 
	 */
	public String CalculatorExpressionAnalysis(Vector<String> v) {
		int index = 0, paren = 0;
		Stack<String> s = new Stack<String>();
		Stack<String> st = new Stack<String>();
		String[] num_arr = new String[30];
		while (index < v.size()) {
			if ((v.elementAt(index).equals("+"))
					|| (v.elementAt(index).equals("-"))
					|| (v.elementAt(index).equals("%"))
					|| (v.elementAt(index).equals("*"))
					|| (v.elementAt(index).equals("/"))
					|| (v.elementAt(index).equals("("))
					|| (v.elementAt(index).equals("^"))) {
				if (s.empty()) {
					s.push(v.elementAt(index));
				} else {

					/*
					 * Push the operator on the stack if the operator on the top
					 * of the stack is lower precedence
					 */
					if (v.elementAt(index).equals("*")
							&& (s.peek().equals("+") || s.peek().equals("-") || s
									.peek().equals("%"))) {
						s.push(v.elementAt(index));
					} else if (v.elementAt(index).equals("/")
							&& (s.peek().equals("+") || s.peek().equals("-")
									|| s.peek().equals("%") || s.peek().equals(
									"*"))) {
						s.push(v.elementAt(index));
					} else if (v.elementAt(index).equals("^")
							&& (s.peek().equals("+") || s.peek().equals("-")
									|| s.peek().equals("%")
									|| s.peek().equals("*") || s.peek().equals(
									"/")) || s.peek().equals("^")) {
						s.push(v.elementAt(index));
					} else if (v.elementAt(index).equals("%")
							&& (s.peek().equals("+") || s.peek().equals("-"))) {
						s.push(v.elementAt(index));
					} else if (v.elementAt(index).equals("+")
							&& s.peek().equals("-")) {
						s.push(v.elementAt(index));
					} else if (v.elementAt(index).equals("(")) {
						st.push(v.elementAt(index));
						paren = index + 1;
						index = exp_paren(v, paren, num_arr, st);
					} else {
						/*
						 * Pop the operators in the stack if a lower precedence
						 * or equal precedence operator is to be pushed on the
						 * stack and add it to the array
						 */
						while (!s.empty()) {
							num_arr[postf_z++] = (String) s.pop();
						}
						s.push(v.elementAt(index));
					}
				}
			} else {

				// If an operand,then add it to the array

				num_arr[postf_z++] = v.elementAt(index);
			}
			index++;
			if (index == v.size()) {
				while (!s.empty()) {
					num_arr[postf_z++] = (String) s.pop();
				}
			}
		}
		String solution = getCalculationResult(num_arr);
		return solution;
	}

	/**
	 * The function should use the postfix expression from the num_arr[] to
	 * evaluate the result of the calculation
	 *
	 * @param num_arr
	 *            performs the mathematical calculation
	 * 
	 * @return result of the calculation
	 * 
	 */
	public String getCalculationResult(String[] num_arr) {
		int index2 = 0;
		Stack<String> s = new Stack<String>();
		double res;
		while (index2 < postf_z) {
			if (num_arr[index2].equals("+") || num_arr[index2].equals("-")
					|| num_arr[index2].equals("*")
					|| num_arr[index2].equals("/")
					|| num_arr[index2].equals("%")
					|| num_arr[index2].equals("^")) {

				// Pushing the result of an operation on the stack

				if (!s.empty()) {
					String p = (String) s.pop();
					double y1 = Double.valueOf(p);
					String q = (String) s.pop();
					double x1 = Double.valueOf(q);
					if (num_arr[index2].equals("+")) {
						res = x1 + y1;
						finalResult = String.valueOf(res);
						s.push(finalResult);
					} else if (num_arr[index2].equals("*")) {
						res = x1 * y1;
						finalResult = String.valueOf(res);
						s.push(finalResult);
					} else if (num_arr[index2].equals("/")) {

						/*
						 * Illegal Argument exception to be thrown if
						 * denominator is zero
						 */

						if (y1 == 0) {
							throw new IllegalArgumentException(
									"Denominator is zero!!");
						} else {
							res = x1 / y1;
							finalResult = String.valueOf(res);
							s.push(finalResult);
						}
					} else if (num_arr[index2].equals("-")) {
						res = x1 - y1;
						finalResult = String.valueOf(res);
						s.push(finalResult);
					} else if (num_arr[index2].equals("%")) {
						res = x1 % y1;
						finalResult = String.valueOf(res);
						s.push(finalResult);
					} else if (num_arr[index2].equals("^")) {
						res = Math.pow(x1, y1);
						finalResult = String.valueOf(res);
						s.push(finalResult);
					}
				}
			} else {

				// Pushing the operand on the stack

				s.push(num_arr[index2]);
			}
			index2++;
		}

		/*
		 * Popping the result out of the stack (last element in the stack) and
		 * printing it
		 */

		finalResult = (String) s.pop();
		return finalResult;
	}
}
