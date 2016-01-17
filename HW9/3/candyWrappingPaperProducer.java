public class candyWrappingPaperProducer extends Thread {
	String wrapperLock;
	static String onlyWrapper = "foundCandy";
	static final int maxNoOfWrappingPapers = 84;
	static int countOfWrappingPapers = 0;
	static candyWrappingPaperProducer[] wrappingPaper = new candyWrappingPaperProducer[maxNoOfWrappingPapers];

	public candyWrappingPaperProducer() {

	}

	public candyWrappingPaperProducer(String s) {
		this.wrapperLock = s; //For the sake that every thread we create has the same lock
	}

	public void run() {
		int counter = 0;
		synchronized (wrapperLock) {
			candyProducer candy = new candyProducer("foundCandy");
			candy.start();
			try {
				onlyWrapper.wait(); // waits till it receives a candy
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// as per the question, now the wrapping papers has to be stored in
			// 3 different locations
			if (countOfWrappingPapers < maxNoOfWrappingPapers) {
				while (counter < 3) {
					wrappingPaper[countOfWrappingPapers++] = new candyWrappingPaperProducer();
					counter++;
				}
			} else {
				System.out.println("No more wrapping papers in the stock!");
			}
			consumerWrapper.wrapperLock.notify();

		}
	}
}
