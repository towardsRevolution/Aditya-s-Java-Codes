/**
 * PlayerCompController.java
 * 
 * @version $ID: PlayerCompController.java, v 1.8 09/2/2015 7:02pm
 * 
 * Revision: 6.23 09/5/2015 11:43am
 *
 */
/**
 * The Class represents the controller for the Connec0t4Field game design(Player
 * vs Computer)
 * 
 * @author Aditya Pulekar
 *
 */
public class PlayerCompController {
	static boolean notification; // Updated by the Model class

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		PlayerCompModel PModel = new PlayerCompModel();
		PlayerCompView PView = new PlayerCompView();
		PlayerCompView aPlayer = new PlayerCompView(new PlayerCompModel(), "A",
				'+');
		PlayerCompView bPlayer = new PlayerCompView(new PlayerCompModel(), "B",
				'*');
		int toCheckIfItIsComps1stTurn = 0;
		while (!PModel.isItaDraw()) {
			int returnValue = PView.getPlayerInput(aPlayer, bPlayer,
					toCheckIfItIsComps1stTurn);
			toCheckIfItIsComps1stTurn = 0;
			if (returnValue == 1) {
				break;
			}
		}
	}
}
