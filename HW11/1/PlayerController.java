/**
 * PlayerControllerModel.java
 * @version $ID: PlayerControllerModel.java, v 1.8 09/2/2015 7:02pm 
 * 
 * Revision: 6.22 09/5/2015 11:43am
 *
 */
/**
 * The Class represents the controller for the Connect4Field game (Player vs
 * Player)
 * 
 * @author Aditya Pulekar, Mandar Badave
 *
 */
public class PlayerController extends Thread {
	static boolean notification; // Updated by the Model class
	static int ThreadCount = 0;

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */

	public static void main(String[] args) {
		PlayerModel PModel = new PlayerModel();
		PlayerView PView = new PlayerView();
		while (!PModel.isItaDraw()) {
			if (ThreadCount < 2) {
				ThreadCount += 4; // So that unless and until both the
				// threads are done executing, they
				// won't be given an
				// opportunity to execute again
				Thread A = new Thread(new PlayerView(new PlayerModel(), "A",
						'+'));
				Thread B = new Thread(new PlayerView(new PlayerModel(), "B",
						'*'));
				A.setName("Player A");
				B.setName("Player B");
				A.start();
				B.start();
			}
			try {
				while (ThreadCount != 0) {
					sleep(1000);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
