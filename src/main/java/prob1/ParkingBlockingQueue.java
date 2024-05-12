package prob1;

import java.util.concurrent.ArrayBlockingQueue;

class ParkingGarageBlockingQueue {
	private ArrayBlockingQueue<Integer> places;

	public ParkingGarageBlockingQueue(int places) {
		if (places < 0)
			places = 0;
		this.places = new ArrayBlockingQueue<>(places);
	}

	public void enter() { // enter parking garage
		try {
			places.put(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void leave() { // leave parking garage
		try {
			places.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


class Car extends Thread {
	private ParkingGarageBlockingQueue parkingGarage;
	public Car(String name, ParkingGarageBlockingQueue p) {
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


public class ParkingBlockingQueue {
	public static void main(String[] args){
		ParkingGarageBlockingQueue parkingGarage = new ParkingGarageBlockingQueue(7);
		for (int i=1; i<= 10; i++) {
			Car c = new Car("Car "+i, parkingGarage);
		}
	}
}
