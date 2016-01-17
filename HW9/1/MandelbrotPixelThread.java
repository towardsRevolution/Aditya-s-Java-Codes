// original from: http://rosettacode.org/wiki/Mandelbrot_set#Java
// modified for color
/**
 * MandelbrotPixelThread.java
 * @version $ID: MandelbrotPixelThread.java, v 1.8 10/23/2015 7:02pm 
 * 
 * Revision: 9.11 10/24/2015 11:43am
 *
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * This program executes the Mandelbrot algorithm using Multithreading
 * wherein, a Manager Thread synchronizes the execution of a pixel thread
 * (the program uses synchronize(), wait(), notify() and notifyAll())
 * 
 * @author Aditya Pulekar
 * @author Mandar Badave
 *
 */

public class MandelbrotPixelThread extends JFrame {
	private final int MAX = 5000;
	private final int LENGTH = 800;
	private final double ZOOM = 1000;
	private BufferedImage theImage;
	private int[] colors = new int[MAX];
	static String identity = "Pixel";
	private double zx, zy, cX, cY;

	public MandelbrotPixelThread() {
		super("Mandelbrot Set");

		initColors();
		setBounds(100, 100, LENGTH, LENGTH);
		setResizable(false);
		theImage = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_RGB);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void createSet() {
		MandelbrotPixelThread forVisibility = new MandelbrotPixelThread();
		forVisibility.setVisible(true);
		MandelbrotManager ManagerThread = forVisibility.new MandelbrotManager();
		ManagerThread.start();
		try {
			ManagerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}

	/*
	 * An inner class formed to support parallelism
	 */
	public class Pixels extends Thread {
		int x, y;

		/*
		 * Parameterized Constructor
		 * 
		 * @param x x-coordinate
		 * 
		 * @param y y-coordinate
		 */
		public Pixels(int x, int y) { // String identity) {
			this.x = x;
			this.y = y;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			synchronized (identity) {
				zx = zy = 0;
				cX = (x - LENGTH) / ZOOM; 
				cY = (y - LENGTH) / ZOOM;
				int iter = 0;
				double tmp;
				while ((zx * zx + zy * zy < 10) && (iter < MAX - 1)) {
					tmp = zx * zx - zy * zy + cX;
					zy = 2.0 * zx * zy + cY;
					zx = tmp;
					iter++;
				}
				if (iter > 0) {
					theImage.setRGB(x, y, colors[iter]);
				} else
					theImage.setRGB(x, y, iter | (iter << 8));
				System.out.println("Pixel Thread's job completed\n"
						+ "Notifying Manager Thread of the completed job!\n");
				identity.notify();
			}
		}
	}

	/*
	 * An inner class formed to create a Manager Thread which synchronizes the
	 * execution of every pixel thread
	 */
	public class MandelbrotManager extends Thread {
		int pixelCounter;

		/**
		 * The method synchronizes the Manager Thread and the pixel Thread
		 * execution
		 * 
		 *
		 */
		public void assignTask() {
			// It, too, needs to be synchronized on identity
			synchronized (identity) {
				int x = 0, y = 0;
				for (y = 0; y < getWidth(); y++) { // 800 x 800 window
					for (x = 0; x < getHeight(); x++) {
						new Pixels(x, y).start();
						System.out.println("Manager Thread waiting...");
						try {
							identity.wait();
						} catch (IllegalMonitorStateException i) {
							System.out
									.println(": IllegalMonitorStateException");

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							System.out.println(": Intrerupted Exception");
						}
					}
				}
				if ((x * y) == 640000) {
					identity.notifyAll();
				}
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Thread#run()
		 */
		public void run() {
			assignTask();
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(theImage, 0, 0, this);
	}

	public void initColors() {
		// System.out.println("Colors[] is being assigned RGB values!");
		for (int index = 0; index < MAX; index++) {
			colors[index] = Color.HSBtoRGB(index / 256f, 1, index
					/ (index + 8f));
		}
	}

	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		MandelbrotPixelThread mpt = new MandelbrotPixelThread();
		mpt.createSet();

		System.exit(0);
	}
}