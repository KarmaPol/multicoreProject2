package prob3;

import java.util.concurrent.atomic.AtomicInteger;

public class ex3 {
	private static AtomicInteger currentNumber = new AtomicInteger(0);
	static class exThread extends Thread {
		private int number = -1;

		public exThread(int number) {
			this.number = number;
		}

		public void run() {
			System.out.println("Thread " + number + " started");
			System.out.println("Thread " + number +": Current number: " + currentNumber.get());
			currentNumber.set(0);
			System.out.println("Thread " + number +": Current number: " + currentNumber.getAndAdd(number));
			currentNumber.set(0);
			System.out.println("Thread " + number +": Current number: " + currentNumber.addAndGet(number));
			System.out.println("Thread " + number + " finished");
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
