package console;

public class Organism {

    /**
     * The necessary number of neighboring organisms such that an empty space
     * becomes occupied by a new organism. This value should be 0 to 8
     * inclusive.
     */
    public static boolean[] numToBeBorn = { false, false, false, true, false,
	    false, false, false, false };
    /**
     * Boolean array listing the accepted values for number of neighboring
     * organisms such that the central organism survives. The index of the array
     * represents the number of organisms neighboring the central organism.
     */
    public static boolean[] numsToSurvive = { false, false, true, true, false,
	    false, false, false, false };

    public Organism() {
    }

    /**
     * Checks if an empty space has the correct number of neighboring organisms
     * such that an organism should be born in said empty space.
     */
    public static boolean shouldBeBorn(int row, int col, int rowSize,
	    int colSize, Organism[][] gridOfOrganisms) {
	int sum = 0;
	for (int i = -1; i <= 1; i++) {
	    for (int j = -1; j <= 1; j++) {
		if (i != 0 || j != 0) {
		    if ((isInBounds(row + i, rowSize))
			    && (isInBounds(col + j, colSize))
			    && (gridOfOrganisms[row + i][col + j] != null)) {
			sum++;
		    }
		}
	    }
	}
	return numToBeBorn[sum];
    }

    /**
     * Checks if an occupied space has the correct range of neighboring
     * organisms such that the organism in said occupied space will remain in
     * its space.
     */
    public static boolean shouldSurvive(int row, int col, int rowSize,
	    int colSize, Organism[][] gridOfOrganisms) {
	int sum = 0;
	for (int i = -1; i <= 1; i++) {
	    for (int j = -1; j <= 1; j++) {
		if (i != 0 || j != 0) {
		    if ((isInBounds(row + i, rowSize))
			    && (isInBounds(col + j, colSize))
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
