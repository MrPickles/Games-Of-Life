package console;

public class Organism {

    public static int numToBeBorn = 3;
    public static boolean[] numsToSurvive = { false, false, true, true, false,
	    false, false, false, false };

    public Organism() {
    }

    public static boolean shouldBeBorn(int row, int col, int size,
	    Organism[][] gridOfOrganisms) {
	int sum = 0;
	for (int i = -1; i <= 1; i++) {
	    for (int j = -1; j <= 1; j++) {
		if (i != 0 || j != 0) {
		    if ((isInBounds(row + i, size))
			    && (isInBounds(col + j, size))
			    && (gridOfOrganisms[row + i][col + j] != null)) {
			sum++;
		    }
		}
	    }
	}
	return sum == numToBeBorn;
    }

    public static boolean shouldSurvive(int row, int col, int size,
	    Organism[][] gridOfOrganisms) {
	int sum = 0;
	for (int i = -1; i <= 1; i++) {
	    for (int j = -1; j <= 1; j++) {
		if (i != 0 || j != 0) {
		    if ((isInBounds(row + i, size))
			    && (isInBounds(col + j, size))
			    && (gridOfOrganisms[row + i][col + j] != null)) {
			sum++;
		    }
		}
	    }
	}
	return numsToSurvive[sum];
    }

    public static boolean isInBounds(int coordinate, int size) {
	return coordinate >= 0 && coordinate < size;
    }

}
