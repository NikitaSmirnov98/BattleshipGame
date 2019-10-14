package battleship;

/**
 * This describes characteristics common to all the ships
 */
abstract class Ship {

    // Constants

    private static final int MAX_COORDINATE = Ocean.FIELD_SIZE - 1;
    private static final int MIN_COORDINATE = 0;

    // Fields

    /**
     * The row (0 to 9) which contains the bow (front) of the ship.
     */
    private int bowRow;

    /**
     * The column (0 to 9) which contains the bow (front) of the ship
     */
    private int bowColumn;

    /**
     * The number of squares occupied by the ship
     */
    int length;

    /**
     * True if the ship occupies a single row, false otherwise
     */
    boolean horizontal;

    /**
     * An array of booleans telling whether that part of the ship has been hit
     */
    boolean [] hit = new boolean[4];


    // Accessors

    /**
     * Get-accessor returns the length of this particular ship
     * @return the length of this particular ship
     */
    int getLength(){
        return length;
    }

    /**
     * Get-accessor returns bowRow
     * @return integer bowRow value
     */
   int getBowRow(){
        return bowRow;
    }

    /**
     * Get-accessor returns bowColumn
     * @return integer bowColumn value
     */
    int getBowColumn(){
        return bowColumn;
    }

    /**
     * Get-accessor returns hit array
     * @return integer array of hits
     */
    boolean [] getHits(){
        return hit;
    }

    /**
     * Get-accessor return orientation of the ship
     * @return boolean horizontal value
     */
    boolean isHorizontal(){
        return horizontal;
    }

    /**
     * Get-accessor return type of the ship
     * @return string ship type
     */
    String getShipType(){
        return null;
    }

    /**
     * Set-accessor sets the value to row field
     * @param row integer number of row
     */
    private void setBowRow(int row){
        bowRow = row;
    }

    /**
     * Set-accessor sets the value to column field
     * @param column integer number of column
     */
    void setBowColumn(int column){
        bowColumn = column;
    }

    /**
     * Set-accessor sets the value to horizontal field
     * @param horizontal boolean value of horizontal orientation
     */
    void setHorizontal(boolean horizontal){
        this.horizontal = horizontal;
    }

    // Methods

    /**
     * Returns true if it is okay to put a ship of this length with its bow in this location,
     * with the given orientation, and returns false otherwise.
     * The ship must not overlap another ship, or touch another ship
     * (vertically, horizontally, or diagonally), and it must not "stick out" beyond the array.
     * Does not actually change either the ship or the Ocean, just says whether it is legal to do so.
     * @param row integer row coordinate
     * @param column integer column coordinate
     * @param horizontal boolean horizontal orientation
     * @param ocean object of the battlefield
     * @return true, if its ok to place ship there, false otherwise
     */
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean){

        // first of all check if ship is in bounds
        if (horizontal && column + getLength() - 1 > MAX_COORDINATE){
            return false;
        }
        if (!horizontal && row + getLength() - 1 > MAX_COORDINATE){
            return false;
        }

        // checking for ships near our ship
        boolean flag = true;

        if (horizontal){
            for (int tempRow = row - 1; tempRow <= row + 1; tempRow++){
                for (int tempCol = column - 1; tempCol <= column + getLength() + 1; tempCol++){
                    // checking if coordinate is in field
                    flag = checkCoordinate(tempRow, tempCol, ocean);
                }
            }
        } else {
            for (int tempRow = row - 1; tempRow <= row + getLength() + 1; tempRow++) {
                for (int tempCol = column - 1; tempCol <= column + 1; tempCol++) {
                    // checking if coordinate is in field
                    flag = checkCoordinate(tempRow, tempCol, ocean);
                }
            }
        }
        return flag;
    }

    /**
     * Method is checking if coordinate is in field and isn't occupied
     * @param row integer number of row
     * @param col integer number of column
     * @param ocean link to the current battlefield
     * @return true, if coordinate isn't occupied
     */
    boolean checkCoordinate(int row, int col, Ocean ocean){
        boolean flag = true;
        boolean isInField = row >= MIN_COORDINATE && row <= MAX_COORDINATE &&
                col >= MIN_COORDINATE && col <= MAX_COORDINATE;
        if (isInField && ocean.isOccupied(row, col)){
            flag = false;
        }
        return flag;
    }

    /**
     * "Puts" the ship in the ocean.
     * This involves giving values to the bowRow, bowColumn, and horizontal
     * instance variables in the ship, and it also involves putting a reference
     * to the ship in each of 1 or more locations (up to 4) in the ships array in the Ocean object.
     * @param row integer row coordinate
     * @param column integer column coordinate
     * @param horizontal boolean horizontal orientation
     * @param ocean object of the battlefield
     */
   void placeShipAt(int row, int column, boolean horizontal, Ocean ocean){

       // set fields
        setBowColumn(column);
        setBowRow(row);
        setHorizontal(horizontal);

        // initialize ship array
        if (horizontal){
            for (int i = 0; i < getLength(); i++){
                ocean.getShipArray()[row][column + i] = this;
            }
        } else {
            for (int i = 0; i < getLength(); i++){
                ocean.getShipArray()[row + i][column] = this;
            }
        }
    }

    /**
     * If a part of the ship occupies the given row and column,
     * and the ship hasn't been sunk, mark that part of the ship as "hit"
     * (in the hit array, 0 indicates the bow) and return true, otherwise return false.
     * @param row integer row coordinate
     * @param column integer column coordinate
     * @return true, if a part of the ship occupies the coordinate
     */
    boolean shootAt(int row, int column){
        if (!isSunk()) {
            if (isHorizontal()) {
                hit[column - getBowColumn()] = true;
            } else if (!isHorizontal()) {
                hit[row - getBowRow()] = true;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Return true if every part of the ship has been hit, false otherwise.
     * @return true if every part of the ship has been hit, false otherwise.
     */
    boolean isSunk(){
        boolean flag = true;
        for (int i = 0; i < getLength(); i++){
            if (!hit[i]){
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Overrided method for printing the ship to the console screen
     * 'S' means hit, 'x' means sunk ship
     * @return symbol according the status of the ship
     */
    @Override
    public String toString() {
        if (isSunk()){
            return ("x");
        }
        return "S";
    }
}

