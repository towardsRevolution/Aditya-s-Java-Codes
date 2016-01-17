/**
 * CalculatorControl.java
 * @version $ID: CalculatorControl.java, v 1.8 09/2/2015 7:02pm 
 * 
 * Revision: 6.1 09/5/2015 11:43am
 *
 */
/**
 * The Class represents the controller for the Calculator design
 * 
 * @author Aditya Pulekar, Mandar Badave
 *
 */
public class CalculatorControl {
	/**
	 * The method should manage the interaction between the Model class and the
	 * View class
	 *
	 */
	public void ManageCalculator() {
		CalculatorModel Cmodel = new CalculatorModel();
		CalculatorView Cview = new CalculatorView();
		Cview.finalSolution = Cmodel.CalculatorExpressionAnalysis(Cview
				.getTheExpression());
		Cview.setCalculatorSolution(Cview.finalSolution);

	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		CalculatorControl Ccontrol = new CalculatorControl();
		Ccontrol.ManageCalculator();
	}
}
