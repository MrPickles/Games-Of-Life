package console;

public class ConsoleGrid {

    private Organism[][] gridOfOrganisms;
    private int size;

    public ConsoleGrid(int size) {
	this.size = size;
	this.gridOfOrganisms = new Organism[size][size];
    }

    public ConsoleGrid(ConsoleGrid copy) {
	// deep copy
	this.size = copy.getSize();
	Organism[][] copyGrid = copy.getGrid();
	this.gridOfOrganisms = new Organism[copyGrid.length][copyGrid.length];
	for (int i = 0; i < copyGrid.length; i++) {
	    for (int j = 0; j < copyGrid.length; j++) {
		if (copyGrid[i][j] != null) {
		    this.gridOfOrganisms[i][j] = new Organism();
		}
	    }
	}
    }

    public int getSize() {
	return this.size;
    }

    public Organism[][] getGrid() {
	return this.gridOfOrganisms;
    }

    public void act() {
	Organism[][] copy = new ConsoleGrid(this).getGrid();
	for (int row = 0; row < size; row++) {
	    for (int col = 0; col < size; col++) {
		if (copy[row][col] == null) {
		    if (Organism.shouldBeBorn(row, col, size, copy)) {
			this.gridOfOrganisms[row][col] = new Organism();
		    }
		} else {
		    if (!Organism.shouldSurvive(row, col, size, copy)) {
			this.gridOfOrganisms[row][col] = null;
		    }
		}
	    }
	}
    }

    public void setGrid(Organism[][] myGrid) {
	// TODO Auto-generated method stub
	this.gridOfOrganisms = myGrid;
    }

}
