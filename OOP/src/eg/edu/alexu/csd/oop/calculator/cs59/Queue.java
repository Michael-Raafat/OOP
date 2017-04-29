package eg.edu.alexu.csd.oop.calculator.cs59;
/**
*
* @author Michael
*
*/
public class Queue  {
   /**
    * index variables and counter.
    */
    private int c = 0;
    /**
     * array.
     */
	private Object[]a;
	/**
	 * constructor.
	 * @param n
	 * length of the array
	 */
	public Queue(final int n) {
		a = new Object[n];
	}
	/**
	 * to enter an expression in our queue.
	 * @param item Object to be stored in queue.
	 */
	public final void enqueue(final Object item) {
		// TODO Auto-generated method stub
		if (c == a.length) {
			this.dequeue();
		}
		a[c] = item;
		c++;
	}
	/**
	 *
	 * @return the first element in queue.
	 */
	public final Object dequeue() {
		// TODO Auto-generated method stub
		if (c == 0) {
			throw new RuntimeException();
		}
		Object v = a[0];
		for (int i = 0; i < this.size() - 1; i++) {
			a[i] = a[i + 1];
		}
		a[this.size() - 1] = null;
		c--;
		return v;
	}
	/**
	 *
	 * @param current index of object needed.
	 * @return Object needed.
	 */
	public final Object get(final int current) {
		return a[current - 1];
	}
	/**
	 * to check if our queue is empty or not.
	 * @return answer if our queue is empty or not.
	 */
	public final boolean isEmpty() {
		// TODO Auto-generated method stub
		return (c == 0);
	}
	/**
	 *
	 * @return size of queue.
	 */
	public final int size() {
		// TODO Auto-generated method stub
		return c;
	}
}
