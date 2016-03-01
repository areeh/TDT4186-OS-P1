package p2;

/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
public class Doorman implements Runnable {
	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	
	private Thread thread;
	private CustomerQueue queue;
	private Gui gui;
	private boolean waiting;
	
	public Doorman(CustomerQueue queue, Gui gui) { 
		System.out.println("DOORMAN CONSTRUCTOR CALLED");
		// Incomplete
		this.queue = queue;
		this.gui = gui;
		waiting = false;
	}

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
			if (queue.fullQueue()) {
				if (!waiting) {
					gui.println("Doorman is waiting for free chairs..");
					waiting = true;
					
				}
			} else {
				waiting = false;
				queue.put(new Customer());				
		}
			try {
				Thread.sleep(Globals.doormanSleep);
			} catch (InterruptedException e) {}
		}
		
	}
	
	public void stop() {
		thread = null;
	}

	// Add more methods as needed
}
