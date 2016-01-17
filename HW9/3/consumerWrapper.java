public class consumerWrapper extends Thread {
	String boxLock;
	static String wrapperLock = "Wrapper";
	static final int maxNoOfWrappedCandies= 28;
	static consumerWrapper[] conWrapper = new consumerWrapper[maxNoOfWrappedCandies];
	public consumerWrapper(){
	
	}
	public consumerWrapper(String s) {
		this.boxLock = s;
	}

	public void run() {
		int noOfCandiesInOneBox = 0; // noOfWrappingPapers =0;
		synchronized (boxLock) {
			while (noOfCandiesInOneBox < 4) {
				// candyWrappingPaperProducer[] cWrapPaper = new
				// candyWrappingPaperProducer[3];
				// while(noOfWrappingPapers<3){
				// cWrapPaper[noOfWrappingPapers]= new
				// candyWrappingPaperProducer();
				candyWrappingPaperProducer cWrapPaper = new candyWrappingPaperProducer("Wrapper");
				cWrapPaper.start();
				try {
					wrapperLock.wait(); //waits till it gets a wrapped candy
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				conWrapper[noOfCandiesInOneBox]= new consumerWrapper();
				noOfCandiesInOneBox++;
			}
			consumerBox.boxLock.notify();
		}

	}
}
