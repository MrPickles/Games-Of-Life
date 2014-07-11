package console;

/**
 * The variable gridOfOrganisms contains the matrix of organism locations. Each
 * element in the array is either a null value or an Organism object. The static
 * instance variables in the Organism class should be changed by the GUI.
 * 
 */
public class ConsoleGrid {

    private Organism[][] gridOfOrganisms;
    private int rowSize;
    private int colSize;

    public ConsoleGrid(int row, int col) {
	rowSize = row;
	colSize = col;
	gridOfOrganisms = new Organism[row][col];
    }

    /**
     * Makes a deep copy of the constructor.
     */
    public ConsoleGrid(ConsoleGrid copy) {
	// deep copy
	rowSize = copy.getRowSize();
	colSize = copy.getColSize();
	Organism[][] copyGrid = copy.getGrid();
	gridOfOrganisms = new Organism[copyGrid.length][copyGrid[0].length];
	for (int i = 0; i < copyGrid.length; i++) {
	    for (int j = 0; j < copyGrid[0].length; j++) {
		if (copyGrid[i][j] != null) {
		    gridOfOrganisms[i][j] = new Organism();
		}
	    }
	}
    }

    public int getRowSize() {
	return rowSize;
    }

    public int getColSize() {
	return colSize;
    }

    public Organism[][] getGrid() {
	return gridOfOrganisms;
    }

    public void setGrid(Organism[][] myGrid) {
	gridOfOrganisms = myGrid;
    }

    /**
     * Mimics an iteration of the Game of Life.
     */
    public void act() {
	Organism[][] copy = new ConsoleGrid(this).getGrid();
	for (int row = 0; row < rowSize; row++) {
	    for (int col = 0; col < colSize; col++) {
		if (copy[row][col] == null) {
		    if (Organism.shouldBeBorn(row, col, rowSize, colSize, copy)) {
			gridOfOrganisms[row][col] = new Organism();
		    }
		} else {
		    if (!Organism.shouldSurvive(row, col, rowSize, colSize,
			    copy)) {
			gridOfOrganisms[row][col] = null;
		    }
		}
	    }
	}
    }

}
