package prob3;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ex2 {
	private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private static int currentNumber;
	static class exThread extends Thread {
		private int number = -1;

		public exThread(int number) {
			this.number = number;
		}

		public void run() {
			try {
				readWriteLock.readLock().lock();
				System.out.println("Thread " + number + " started");
				System.out.println("Current number: " + currentNumber);
				sleep((int)(Math.random() * 10000));
				readWriteLock.readLock().unlock();
			} catch (InterruptedException e) {}
			try {
				readWriteLock.writeLock().lock();
				currentNumber = number;
				System.out.println("Thread " + number + " finished");
				System.out.println("Current number: " + currentNumber);
				readWriteLock.writeLock().unlock();
			} catch (Exception e) {}
		}
	}

	public static void main(String[] args) {
		Thread threads[] = new Thread[5];

		for(int i = 0; i < 5; i++) {
			threads[i] = new exThread(i);
			threads[i].start();
		}

		for(int i = 0; i < 5; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {}
		}
	}
}
