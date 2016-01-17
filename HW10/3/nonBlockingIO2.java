/**
 * nonBlockingIO2.java
 * @version $ID: nonBlockingIO2.java, v 1.8 10/28/2015 3:04pm 
 * 
 * Revision: 10.31 11/2/2015 11:43am
 *
 */
import java.io.*;
import java.util.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * This program implements mainly a non-blocking I/O class.
 * 
 * @author Aditya Pulekar
 *
 */

public class nonBlockingIO2 extends Thread {
	int countOfBytes = 0;
	String readerThread = "readerThread";  //Object to be synchronized on
	static FileChannel fc;		
	static Queue<Byte> byteQueue = new LinkedList<Byte>();  //Buffer

	/*
	 * Default constructor of the class Player
	 */
	public nonBlockingIO2() {

	}
	
	/*
	 * The method reads the given '.txt' file, converts the data into bytes and
	 * stores it in a byte array. Then, it adds the initial 10k bytes from the
	 * byte array to the queue and passes the queue, byte array and the file
	 * channel instance to the read method for data processing
	 * 
	 * @param f     a RandomAccessFileInstance
	 * @param nbio  an instance of this class
	 */
	public void open(RandomAccessFile f, nonBlockingIO2 nbio) {
		// Before we use a file channel, we need to open it.

		// We can obtain a file channel through an inputStream, outputStream or
		// a RandomAccessFile

		// The Function converts the given data into a bytes by opening a File
		// Channel and writes the bytes into byteFile[]

		int readBytes, index = 0;
		FileChannel fc = f.getChannel();
		try {
			byte[] byteFile = new byte[(int) f.length()];
			long channelSize = fc.size();
			ByteBuffer bbuf = ByteBuffer.allocate((int) f.length());
			// Why is the below statement necessary????
			fc.read(bbuf);

			// flip() switches the buffer from the writing to the reading mode
			bbuf.flip();

			System.out
					.println("Reading the data from the files and converting it into a byte array....");
			while (countOfBytes < channelSize) {
				byteFile[countOfBytes] = bbuf.get();
				countOfBytes++;
			}
			System.out.println("AnyMore Bytes remaining? "
					+ bbuf.hasRemaining());
			System.out.println("CountOfBytes: " + countOfBytes);

			/*
			 * ByteFile[] successfully filled with bytes! Note: If we do not
			 * close the file channel and the file, we get a Buffer-
			 * UnderflowException
			 */
			fc.close();
			f.close();

			/*
			 * Note: The queue acts as the FIFO buffer Putting the first 10k of
			 * the file into the byteQueue
			 */
			if (byteFile.length > 10000) {
				for (index = 0; index < 10000; index++) {
					byteQueue.add(byteFile[index]);
				}
			} else {
				System.out.println("Given file is not large enough!");
				System.exit(0);
			}
			nbio.read(byteFile, fc, byteQueue);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The method enables a reader to read, at the max, 1k bytes from the buffer,
	 * in response to which the buffer grabs the next 1k bytes from the byte array
	 * into it. After there are no more bytes in the file to fill the buffer with,
	 * the buffer flushes its data onto the file.
	 * 
	 * @param byteFile
	 *            byte array containing the bytes
	 * @param fc
	 *            File channel for reading data
	 * @param bytesQueue
	 *            buffer
	 */
	public void read(byte[] byteFile, FileChannel fc, Queue<Byte> bytesQueue) {
		// Now the reader reads and removes 1k data at a time from the queue
		int countOfBytes = 10000;
		synchronized (readerThread) {
			long startTime = System.currentTimeMillis();
			here: while (countOfBytes < byteFile.length) {
				nonBlockingIO2 reader = new nonBlockingIO2();
				reader.start();

				/*
				 * Reader blocks any addition in the queue when it is reading
				 */
				try {
					readerThread.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Add the next 1k from the file
				if (countOfBytes + 1000 < byteFile.length) {
					System.out
					.println("1kb read from the file...Now lets add the next 1k bytes to the queue!");
					for (int index2 = countOfBytes; index2 < countOfBytes + 1000; index2++) {
						bytesQueue.add(byteFile[index2]);
					}
				} else {
					int index = countOfBytes;
					while (index < byteFile.length) {
						bytesQueue.add(byteFile[index++]);
					}
					break here;
				}
				System.out.println("Buffer (Queue) refilled... Size: "
						+ bytesQueue.size() + "\n\n");
				countOfBytes += 1000;

			}
			System.out.println("\n\nDONE!...................................");
			long endTime = System.currentTimeMillis();
			System.out
					.println("Data Processing Time: " + (endTime - startTime));
			System.out.println("Data remaining in the Buffer: "
					+ bytesQueue.size());
			System.out
					.println("Emptying/Flushing the Buffer onto the file......");
			bytesQueue.clear();
			System.out.println("Size of the Buffer at the end: "
					+ bytesQueue.size());
		}

	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		synchronized (readerThread) {
			int countTillOneK = 0;
			System.out
					.println("\nReader is reading 1kb data from the buffer....");
			// Dequeueing 1k data from the queue
			while (countTillOneK < 1000) {
				byteQueue.poll();
				countTillOneK++;
			}
			System.out.println("Size of the Buffer on dequeueing 1kb (Queue): "
					+ byteQueue.size());
			System.out.println("Reader DONE reading 1kb data from the buffer.");
			readerThread.notify();
		}
	}
	
	/**
	 * The main program.
	 *
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		nonBlockingIO2 nbio = new nonBlockingIO2();
		try {
			RandomAccessFile file = new RandomAccessFile("wordsAdi.txt", "r");
			nbio.open(file, nbio);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
