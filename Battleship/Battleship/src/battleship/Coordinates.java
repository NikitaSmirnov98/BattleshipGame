package battleship;

/**
 * Class represents a pair of coordinates: row and column of the ship
 */
final class Coordinates {

    // Fields

    /**
     * Represents a row coordinate of the ship
     */
    int row;

    /**
     * Represents a column coordinate of the ship
     */
    int column;

    // Constructor

    public Coordinates(int row, int column){
        this.row = row;
        this.column = column;
    }

    // Accessors

    /**
     * Get-accessor for getting row
     * @return integer number of row
     */
    public int getRow(){
        return row;
    }

    /**
     * Get-accessor for getting column
     * @return integer number of column
     */
    public int getColumn(){
        return column;
    }
}
