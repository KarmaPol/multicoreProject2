package prob2;

import java.util.concurrent.Semaphore;

class ParkingGarageSemaphore {
	private Semaphore semaphore;

	public ParkingGarageSemaphore(int places) {
		if (places < 0)
			places = 0;
		semaphore = new Semaphore(places, true);
	}

	public void enter() {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void leave() {
		try {
			semaphore.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


class Car extends Thread {
	private ParkingGarageSemaphore parkingGarage;
	public Car(String name, ParkingGarageSemaphore p) {
		super(name);
		this.parkingGarage = p;
		start();
	}

	private void tryingEnter()
	{
		System.out.println(getName()+": trying to enter");
	}


	private void justEntered()
	{
		System.out.println(getName()+": just entered");

	}

	private void aboutToLeave()
	{
		System.out.println(getName()+":                                     about to leave");
	}

	private void Left()
	{
		System.out.println(getName()+":                                     have been left");
	}

	public void run() {
		while (true) {
			try {
				sleep((int)(Math.random() * 10000)); // drive before parking
			} catch (InterruptedException e) {}
			tryingEnter();
			parkingGarage.enter();
			justEntered();
			try {
				sleep((int)(Math.random() * 20000)); // stay within the parking garage
			} catch (InterruptedException e) {}
			aboutToLeave();
			parkingGarage.leave();
			Left();

		}
	}
}


public class ParkingSemaphore {
	public static void main(String[] args){
		ParkingGarageSemaphore parkingGarage = new ParkingGarageSemaphore(7);
		for (int i=1; i<= 10; i++) {
			Car c = new Car("Car "+i, parkingGarage);
		}
	}
}
