package printingService.dbms;
/**
 *
 * @author michael.
 *
 */
public class MenuFormat {
    /**
     * print WELCOME.
     */
	public final void printAllWelcome() {
		for (int i = 1; i <= 5; i++) {
			this.printW(i);
			this.printSpaces(2);
			this.printE(i);
			this.printSpaces(2);
			this.printL(i);
			this.printSpaces(2);
			this.printC(i);
			this.printSpaces(2);
			this.printO(i);
			this.printSpaces(2);
			this.printM(i);
			this.printSpaces(2);
			this.printE(i);
			System.out.println();
		}
		printOwners();
	}
	/**
	 * prints owners line.
	 */
	private void printOwners() {
		this.printSpaces(6);
		this.printFirtOwner();
		this.printSpaces(19);
		this.printSpaces(2);
		this.printSecondOwner();
		this.printSpaces(15);
		this.printSpaces(2);
		this.printThirdOwner();
		this.printSpaces(17);
		this.printSpaces(1);
		this.printFourthOwner();
		System.out.println();
	}
	/**
	 * prints W.
	 * @param i
	 * index of rows that W consists of.
	 */
	private void printW(final int i) {
			this.printSpaces(i - 1);
			this.printAstrisk();
			if (i >= 2 && i < 5) {
				this.printInverseV(i);
			} else {
				this.printSpaces(15 - 2 * i );
			}
			this.printAstrisk();
			this.printSpaces(i - 1);
	}
	/**
	 * prints E.
	 * @param i
	 * index of rows that E consists of.
	 */
	private void printE(final int i) {
		if (i == 1 || i == 3 || i == 5) {
			for(int j = 0; j < 9; j++) {
				this.printAstrisk();
			}
		} else {
			this.printAstrisk();
			this.printSpaces(8);
		}
	}
	/**
	 * prints L.
	 * @param i
	 * index of rows that L consists of.
	 */
	private void printL(final int i) {
		if (i == 5) {
			for(int j = 0; j < 9; j++) {
				this.printAstrisk();
			}
		} else {
			this.printAstrisk();
			this.printSpaces(8);
		}
	}
	/**
	 * prints C.
	 * @param i
	 * index of rows that C consists of.
	 */
	private void printC(final int i) {
		if (i == 1 || i == 5) {
			for(int j = 0; j < 9; j++) {
				this.printAstrisk();
			}
		} else {
			this.printAstrisk();
			this.printSpaces(8);
		}
	}
	/**
	 * prints O.
	 * @param i
	 * index of rows that O consists of.
	 */
	private void printO(final int i) {
		if (i == 1 || i == 5) {
			for(int j = 0; j < 9; j++) {
				this.printAstrisk();
			}
		} else {
			this.printAstrisk();
			this.printSpaces(7);
			this.printAstrisk();
		}
	}
	/**
	 * prints M.
	 * @param i
	 * index of rows that M consists of.
	 */
	private void printM(final int i) {
		this.printAstrisk();
		if (i > 1 && i <= 3) {
			this.printV(i);
		} else {
			this.printSpaces(9);
		}
		this.printAstrisk();
	}
	/**
	 * prints V that help in printing W.
	 * @param index
	 * index of rows that V consists of.
	 */
	private void printInverseV(final int index) {
		this.printSpaces((2 - index) * 2 + 5);
			if (index == 2) {
				this.printAstrisk();
			} else {
				this.printAstrisk();
				this.printSpaces((index - 3) * 2 + 1);
				this.printAstrisk();
			}
			this.printSpaces((2 - index) * 2 + 5);
	}
	/**
	 * prints V helps in printing M.
	 * @param index
	 * index of rows that V consists of.
	 */
	private void printV(final int index) {
			if (index == 3) {
				this.printSpaces(4);
				this.printAstrisk();
				this.printSpaces(4);
			} else if (index == 2) {
				this.printSpaces(2);
				this.printAstrisk();
				this.printSpaces(3);
				this.printAstrisk();
				this.printSpaces(2);
			}
	}
	/**
	 * prints astrisk.
	 */
	private void printAstrisk() {
		System.out.print("*");
	}
	/**
	 * print End astrisk.
	 */
	private void printEndAstrisk() {
		System.out.println("*");
	}
	/**
	 * prints Amr.
	 */
	private void printFirtOwner() {
		System.out.print("Amr");
	}
	/**
	 * prints Bishoy.
	 */
	private void printSecondOwner() {
		System.out.print("Bishoy");
	}
	/**
	 * prints Marc.
	 */
	private void printThirdOwner() {
		System.out.print("Marc");
	}
	/**
	 * prints Michael.
	 */
	private void printFourthOwner() {
		System.out.print("Michael");
	}
	/**
	 * prints spaces.
	 * @param index
	 * number of spaces.
	 */
	private void printSpaces(final int index) {
		for ( int i = 0; i < index; i++) {
			System.out.print(" ");
		}
	}
}
