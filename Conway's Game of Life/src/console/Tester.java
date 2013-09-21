package console;

import java.util.Scanner;

public class Tester {

    public static void showGrid(Organism[][] myGrid) {
	String s = "";
	for (int i = 0; i < myGrid.length; i++) {
	    for (int j = 0; j < myGrid.length; j++) {
		if (myGrid[i][j] == null) {
		    s += "0";
		} else {
		    s += "x";
		}
	    }
	    s += "\n";
	}
	System.out.println(s);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	ConsoleGrid c = new ConsoleGrid(5);
	Organism[][] myGrid = c.getGrid();
	myGrid[2][1] = new Organism();
	myGrid[2][2] = new Organism();
	myGrid[2][3] = new Organism();
	c.setGrid(myGrid);

	while (true) {
	    Scanner sc = new Scanner(System.in);
	    System.out.print("Press Enter to continue: ");
	    String s = sc.nextLine();
	    showGrid(myGrid);
	    c.act();
	}
    }

}
