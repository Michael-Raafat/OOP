package eg.edu.alexu.csd.oop.calculator.cs59;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import eg.edu.alexu.csd.oop.calculator.Calculator;
/**
 *
 * @author Michael.
 *
 */
public class MyCalculator implements Calculator {
    /**
     * magic.
     */
    private final int magic5 = 5;
	/**
     * evaluator.
     */
    private Evaluator eva = new Evaluator();
    /**
	 * history queue.
	 */
	private Queue history = new Queue(magic5);
	/**
	 * current counter.
	 */
	private int current = 0;
    @Override
	public final void input(final String s) {
		// TODO Auto-generated method stub
    	history.enqueue(s);
        current = history.size();
    	}
	@Override
	public final String getResult() {
		// TODO Auto-generated method stub
		String here = eva.infixToPostfix(
				String.valueOf(history.get(current)));
		return String.valueOf(eva.evaluate(here));
	}

	@Override
	public final String current() {
		// TODO Auto-generated method stub
		if (current == 0 || history.size() == 0) {
			return null;
		}
		return String.valueOf(history.get(current));
	}

	@Override
	public final String prev() {
		// TODO Auto-generated method stub
		current--;
		if (current <= 0) {
			current++;
			return null;
		}
		return String.valueOf(history.get(current));
	}

	@Override
	public final String next() {
		// TODO Auto-generated method stub
		current++;
		if (current > history.size()) {
			current--;
			return null;
		}
		return String.valueOf(history.get(current));
	}

	@Override
	public final void save() {
		// TODO Auto-generated method stub
		try {
			FileOutputStream file = new FileOutputStream(
					new File("Calc.txt"));
			BufferedWriter write = new BufferedWriter(
					new OutputStreamWriter(file));
		    write.write(String.valueOf(current));
		    write.newLine();
			for (int i = 1; i <= history.size(); i++) {
				write.write(String.valueOf(history.get(i)));
				write.newLine();
			}
			write.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
	@Override
	public final void load() {
		// TODO Auto-generated method stub
		try {
			Scanner read = new Scanner(new File("Calc.txt"));
			while (!history.isEmpty()) {
				history.dequeue();
			}
			if (read.hasNext()) {
				current = Integer.parseInt(read.nextLine());
			}
			while (read.hasNext()) {
				history.enqueue(read.nextLine());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

}
