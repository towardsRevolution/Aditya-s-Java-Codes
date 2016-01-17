
public class candyProducer extends Thread {
	String onlyWrapper;
	static int candyCounter=0;
	static final int maxNoOfCandies = 28;
	static candyProducer[] candies = new candyProducer[maxNoOfCandies];
	public candyProducer(){
		
	}
	public candyProducer(String foundCandy){
		this.onlyWrapper=foundCandy;
	}
	public void run(){
		synchronized(onlyWrapper){
			if(candyCounter<5){
				candies[candyCounter] = new candyProducer();
				candyWrappingPaperProducer.onlyWrapper.notify();
				candyCounter++;
			}
		}
	}
	
}
