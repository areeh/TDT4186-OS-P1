package p2;

/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
public class Barber implements Runnable{
	/**
	 * Creates a new barber.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	
	private Thread thread;
	private CustomerQueue queue;
	private Gui gui;
	private int pos;
	private boolean waiting;
	
	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		System.out.println("BARBER CONSTRUCTOR CALLED");
		// Incomplete
		this.queue = queue;
		this.gui = gui;
		this.pos = pos;
		waiting = false;
	}

	/**
	 * Starts the barber running as a separate thread.
	 */


	/**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
		stop();
	}

	@Override
	public void run() {
		Thread thisThread = Thread.currentThread();
		while (thread == thisThread) {
			
			//Just for prints
			if (queue.emptyQueue()) {
				if (!waiting) {
					gui.println("Barber #" + pos + " is waiting for customers..");
					waiting = true;
				}
			} else {
				waiting = false;
				Customer c = queue.take(pos);
				gui.fillBarberChair(pos, c);
				work();
				gui.emptyBarberChair(pos);
				gui.barberIsSleeping(pos);
			}		
			try {
				Thread.sleep(Globals.barberSleep);
			} catch (InterruptedException e) {}
			gui.barberIsAwake(pos);
			}
	}
	
	private void work() {
		try {
			Thread.sleep(Globals.barberWork);
		} catch (InterruptedException e) {}
	}
	
	public void stop() {
		thread = null;
	}

	// Add more methods as needed
}

