/*
 * Local Interface
 */
public interface Connect4FieldRMIInterface<T> {
	public boolean checkIfPiecedCanBeDroppedIn(int column);

	public void dropPieces();

	boolean didLastMoveWin();

	public boolean isItaDraw();

	public void init(PlayerInterface playerA, PlayerInterface playerB);

	public String toString();

	public void playTheGame();

	public Object[] addNAnalyze();
}
