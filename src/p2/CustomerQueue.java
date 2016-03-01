package p2;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
	private final Object lock;
	private Customer[] queue;
	private int queueMaxSize;
	private int queueSize;
	private int first;
	private int last;
	
	private Gui gui;
	
	public CustomerQueue(int queueLength, Gui gui) {
		System.out.println("QUEUE CONSTRUCTOR CALLED");
		queueMaxSize = queueLength;
		queueSize = 0;
		first = 0;
		last = 0;
		lock = new Object();
		
		queue = new Customer[queueLength];
		this.gui = gui;
		
	}
	
	public Customer take(int pos) {
		synchronized (lock) {
			while (emptyQueue()) {
				try {
					lock.wait();
				} catch (InterruptedException e) {}
			}
			gui.println("Barber #" + pos + " is taking a new customer.");
			queueSize--;
			first = (first+1) % queueMaxSize;
			gui.emptyLoungeChair(first);
			lock.notifyAll();				
			return queue[first];		
		}
		
	}
	
	public void put(Customer customer) {
		synchronized (lock) {
			while (fullQueue()) {
				try {
					lock.wait();
				} catch (InterruptedException e) {}
			}
			gui.println("Doorman is adding a customer.");
			queueSize++;
			last = (last+1) % queueMaxSize;
			gui.fillLoungeChair(last, customer);
			queue[last] = customer;;
			lock.notifyAll();							
		}

	}

	public boolean fullQueue() {
		return queueSize==queueMaxSize;
	}

	public boolean emptyQueue() {
		return queueSize == 0;
	}
}
