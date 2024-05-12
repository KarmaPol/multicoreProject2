package prob3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ex1 {
	private static final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(5);
	static class exThread extends Thread {
		private int number;

		public exThread(int number) {
			this.number = number;
		}

		public void run() {
			try {
				blockingQueue.put(number);
				System.out.println("Thread " + number + " started");
				sleep((int)(Math.random() * 10000));
			} catch (InterruptedException e) {}
			try {
				blockingQueue.take();
				System.out.println("Thread " + number + " finished");
			} catch (InterruptedException e) {}
		}
	}

	public static void main(String[] args) {
		Thread threads[] = new Thread[10];

		for(int i = 0; i < 10; i++) {
			threads[i] = new exThread(i);
			threads[i].start();
		}

		for(int i = 0; i < 10; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {}
		}
	}
}
