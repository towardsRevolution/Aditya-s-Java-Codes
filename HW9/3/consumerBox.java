
public class consumerBox extends Thread{
	static String boxLock;
	public consumerBox(String s){
		consumerBox.boxLock = s;
	}
	
	//Since, in consumerBox class, we create only a single thread, synchronization is 
	//moreover insignificant here. 
	public void fillTheBox(){
		//Number of boxes allowed= 7..So the max number of wrappedCandies that can be stored in 7 boxes is 28
		int maxNoOfBoxes = 7, counter=0;
		consumerWrapper[] boxArray = new consumerWrapper[maxNoOfBoxes];
		synchronized(boxLock){
			while(counter<maxNoOfBoxes){
				//Reason behind passing "box" as a parameter is we want the
				//all the four wrapped candies to be synchronized on the same object
				boxArray[counter]= new consumerWrapper("Box"); 
				boxArray[counter].start();
				try {
					boxLock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				counter++;
			}
			System.out.println("Box Full! All Candies have been stocked");
		}
		
	}
	public void run(){
		fillTheBox();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		consumerBox cbox = new consumerBox("Box");
		cbox.start();

	}

}
