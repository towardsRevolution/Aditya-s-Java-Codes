// original from: http://rosettacode.org/wiki/Mandelbrot_set#Java
// modified for color
/**
 * Mandelbrot.java
 * @version $ID: Mandelbrot.java, v 1.8 10/16/2015 7:02pm 
 * 
 * Revision: 8.21 10/19/2015 11:43am
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * This program executes the Mandelbrot algorithm using Multithreading
 * 
 * @author Aditya Pulekar
 *
 */
public class Mandelbrot extends JFrame {
	private final int MAX = 5000;
	private final int LENGTH = 800;
	private final double ZOOM = 1000;
	private BufferedImage theImage;
	private int[] colors = new int[MAX];
	private int noOfAvailableProcessors;
	private int ThreadCount;

	public Mandelbrot() {
		super("Mandelbrot Set");

		initColors();
		setBounds(100, 100, LENGTH, LENGTH);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void createSet() {
		theImage = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_RGB);
		double zx, zy, cX, cY;
		int pixelCounter = 0;
		parallelMandelbrot[] pixelThreads = new parallelMandelbrot[640000];

		/*
		 * Note: It doesn't matter if the main method terminates first, since
		 * the application will only terminate when all the user threads have
		 * been terminated.
		 */
		for (int y = 0; y < getHeight(); y++) { // 800 x 800 window
			for (int x = 0; x < getWidth(); x++) {

				zx = zy = 0;
				cX = (x - LENGTH) / ZOOM;
				cY = (y - LENGTH) / ZOOM;

				/*
				 * The Second way of making Mandelbrot parallel:
				 */
				/*
				 * if(ThreadCount<4){ ThreadCount++; parallelMandelbrot
				 * pixelThread = new parallelMandelbrot(zx, zy, cX, cY, x, y);
				 * pixelThread.start(); }
				 * System.out.println("Number of active Threads: "+
				 * ThreadCount);
				 */

				pixelThreads[pixelCounter++] = new parallelMandelbrot(zx, zy,
						cX, cY, x, y);
			}
		}
		int counter = 0;

		/*
		 * Note: The main thread will keep iterating over the while loop until
		 * counter equals pixelCounter. This proves that we can keep a thread
		 * waiting at a point using loops.
		 */
		while (counter < pixelCounter) {
			if (ThreadCount < noOfAvailableProcessors) {
				ThreadCount++;
				pixelThreads[counter++].start();
			}
			if (counter == pixelCounter) {
				break;
			}

			/*
			 * Note: Finalizer thread, Reference handler thread, Signal
			 * Dispatcher thread,Low memory Detector thread, Hotspot compiler
			 * thread come under the "Systems group".The "Main
			 * group" under the "Systems group" contains the main thread and the
			 * other threads it creates). Also note that "Systems group" has all
			 * the daemon threads. Daemon threads are the service provider
			 * threads which provide services to the user threads.
			 */
			/*
			 * Thread.activeCount: Returns an estimate of the number of active
			 * threads in the current thread's thread group and its subgroups.
			 * So, as per the definition, this statement would never include the
			 * count of active daemon threads in the "Systems group" since the
			 * systems group is super to the "Main Group".
			 */

			/*
			 * Doubt: Why does Thread.activeCount add 2 more threads when only
			 * the main thread is active apart from the user threads created in
			 * this method.
			 */
			System.out
					.println("Number of active Threads at this point of time: "
							+ ThreadCount);
		}
		repaint();
	}

	/*
	 * An inner class formed to support parallelism
	 */
	class parallelMandelbrot extends Thread {
		double zx, zy, cX, cY;
		int x, y;

		/*
		 * Parameterized constructor
		 * 
		 * @param zx
		 * 
		 * @param zy
		 * 
		 * @param cX
		 * 
		 * @param cY
		 * 
		 * @param x
		 * 
		 * @param y
		 */
		public parallelMandelbrot(double zx, double zy, double cX, double cY,
				int x, int y) {
			this.zx = zx;
			this.zy = zy;
			this.cX = cX;
			this.cY = cY;
			this.x = x;
			this.y = y;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			int iter = 0;
			double tmp;
			while ((zx * zx + zy * zy < 10) && (iter < MAX - 1)) {
				tmp = zx * zx - zy * zy + cX;
				zy = 2.0 * zx * zy + cY;
				zx = tmp;
				iter++;
			}
			if (iter > 0)
				theImage.setRGB(x, y, colors[iter]);
			else
				theImage.setRGB(x, y, iter | (iter << 8));
			ThreadCount--;
		}
	}

	public void initColors() {
		for (int index = 0; index < MAX; index++) {
			colors[index] = Color.HSBtoRGB(index / 256f, 1, index
					/ (index + 8f));
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(theImage, 0, 0, this);
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		Mandelbrot aMandelbrot = new Mandelbrot();
		System.out.println("No. of cores: "
				+ Runtime.getRuntime().availableProcessors());
		aMandelbrot.noOfAvailableProcessors = Runtime.getRuntime()
				.availableProcessors();
		aMandelbrot.setVisible(true);
		aMandelbrot.createSet();

		/*
		 * Core 2 Duo and Dual core processors are not hyper-threaded, hence it
		 * has 2 cores and can schedule only two threads at the same time (i.e.
		 * it has 2 physical and 2 logical processors). A hyper-threaded
		 * processor can schedule 2 threads per core.
		 */

		System.out.println("Main thread exiting!");
		System.exit(0);
	}
}