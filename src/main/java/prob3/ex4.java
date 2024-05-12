package prob3;

import java.util.concurrent.CyclicBarrier;

public class ex4 {
	private static CyclicBarrier barrier = new CyclicBarrier(5);
	static class exThread extends Thread {
		private int number = -1;

		public exThread(int number) {
			this.number = number;
		}

		public void run() {
			System.out.println("Thread " + number + " started");
			try {
				barrier.await();
			} catch (Exception e) {}
			System.out.println("Thread " + number + " finished");
		}
	}

	public static void main(String[] args) {
		Thread threads[] = new Thread[5];

		for(int i = 0; i < 4; i++) {
			threads[i] = new exThread(i);
			threads[i].start();
		}

		try {
			threads[4] = new exThread(4);
			threads[4].sleep(5000);
			threads[4].start();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		for(int i = 0; i < 5; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {}
		}
	}
}
