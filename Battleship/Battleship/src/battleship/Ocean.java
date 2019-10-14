package battleship;

import java.util.Random;

/**
 * Class contains a 10x10 array of Ships, representing the "ocean,"
 * and some methods to manipulate it.
 */
class Ocean {

    // Constants

    public static final int FIELD_SIZE = 10;
    private static final int NUMBER_BATTLESHIPS = 1;
    private static final int NUMBER_CRUISERS = 2;
    private static final int NUMBER_DESTROYERS = 3;
    private static final int NUMBER_SUBMARINES = 4;


    // Randomizer
    private final Random rnd = new Random();

    // Fields

    /**
     * Used to quickly determine which ship is in any given location.
     */
    Ship[][] ships = new Ship[FIELD_SIZE][FIELD_SIZE];

    /**
     * The total number of shots fired by the user.
     */
    int shotsFired;

    /**
     * The number of times a shot hit a ship.
     * If the user shoots the same part of a ship more than once,
     * every hit is counted, even though the additional "hits" don't do the user any good.
     */
    int hitCount;

    /**
     * The number of ships sunk.
     */
    int shipSunk;

    // Accessors

    /**
     * Get-accessor returns the number of shots fired
     * @return integer number of shots fired
     */
    int getShotsFired(){
        return shotsFired;
    }

    /**
     * Get-accessor returns the number of hits
     * @return integer number of hits
     */
    int getHitCount(){
        return hitCount;
    }

    /**
     * Get-accessor returns the number of sunk ships
      * @return integer number of sunk ships
     */
    int getShipSunk(){
        return shipSunk;
    }

    /**
     * Get-accessor returns the 10x10 array of ships
     * @return the 10x10 array of ships
     */
    Ship[][] getShipArray(){
        return ships;
    }

    /**
     * Constructor.
     * Initializes fields with default values.
     * Initializes array of whips with empty seas.
     */
    Ocean(){
        shotsFired = 0;
        hitCount = 0;
        shipSunk = 0;

        for (int tempRow = 0; tempRow < FIELD_SIZE; tempRow++){
            for (int tempCol = 0; tempCol < FIELD_SIZE; tempCol++){
                ships[tempRow][tempCol] = new EmptySea();
            }
        }
    }

    // Methods

    /**
     * Place all ten ships randomly on the (initially empty) ocean
     */
    void placeAllShipsRandomly(){
        for (int i = 0; i < NUMBER_BATTLESHIPS; i++){
            placeShip(new Battleship());
        }
        for (int i = 0; i < NUMBER_CRUISERS; i++){
            placeShip(new Cruiser());
        }
        for (int i = 0; i < NUMBER_DESTROYERS; i++){
            placeShip(new Destroyer());
        }
        for (int i = 0; i < NUMBER_SUBMARINES; i++){
            placeShip(new Submarine());
        }
    }

    /**
     * Method for placing certain ship to the field.
     * It checks if it allowed to place ship in a random location, and if so, places it
     * @param ship ship to place in the field
     */
    void placeShip(Ship ship){

        // create variables for placing the ship
        int row = 0;
        int column = 0;
        boolean horizontal = false;

        // try to generate such coordinates, where we can place ship
        do{
            horizontal = rnd.nextBoolean();

            if (horizontal){
                row = rnd.nextInt(FIELD_SIZE);
                column = rnd.nextInt(FIELD_SIZE - ship.getLength());
            } else {
                row = rnd.nextInt(FIELD_SIZE - ship.getLength());
                column = rnd.nextInt(FIELD_SIZE);
            }
        }while(!ship.okToPlaceShipAt(row, column, horizontal, this));

        // placing the ship
        ship.placeShipAt(row, column, horizontal,this);
    }

    /**
     * Returns true if the given location contains a ship, false if it does not.
     * @param row integer row coordinate
     * @param column integer column coordinate
     * @return if the ship occupies the coordinate
     */
    boolean isOccupied(int row, int column){
        if (row > 9 || row < 0 || column < 0 || column > 9){
            return true;
        }
        return !(getShipArray()[row][column] instanceof EmptySea);
    }

    /**
     * Returns true if the given location contains a "real" ship,
     * still afloat, (not an EmptySea), false if it does not.
     * In addition, this method updates the number of shots that have been fired,
     * and the number of hits.
     * @param row integer row coordinate
     * @param column integer column coordinate
     * @return true, if contains ship and false otherwise
     */
    boolean shootAt(int row, int column){
        shotsFired++;
        Ship tempShip = getShipArray()[row][column];
        boolean result = tempShip.shootAt(row, column);
        if (result){
            hitCount++;
            if (tempShip.isSunk()){
                shipSunk++;
            }
        }
        return result;
    }

    /**
     * Returns true if all ships have been sunk, otherwise false.
     * @return true if all ships have been sunk, otherwise false.
     */
    boolean isGameOver(){
        return getShipSunk() == NUMBER_SUBMARINES + NUMBER_DESTROYERS + NUMBER_CRUISERS + NUMBER_BATTLESHIPS;
    }

    /**
     * Method checks if coordinate was fired before.
     * @param tempShip current ship (or empty sea)
     * @param tempRow integer row coordinate
     * @param tempCol integer column coordinate
     * @return true, if ship (or empty sea) was fired before, false otherwise
     */
    boolean wasFired(Ship tempShip, int tempRow, int tempCol){
        boolean wasFired;
        if (tempShip instanceof EmptySea){
            wasFired = true;
        } else if (tempShip.isHorizontal()){
            wasFired = tempShip.getHits()[tempCol - tempShip.getBowColumn()];
        } else {
            wasFired = tempShip.getHits()[tempRow - tempShip.getBowRow()];
        }
        return wasFired;
    }

    /**
     * Prints the ocean to the console.
     * Step by step it prints each member of ship array according its status
     */
    void print(){
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int tempRow = 0; tempRow < FIELD_SIZE; tempRow++){
            System.out.print(tempRow + " ");
            for (int tempCol = 0; tempCol < FIELD_SIZE; tempCol++){
                Ship tempShip = getShipArray()[tempRow][tempCol];

                if (wasFired(tempShip, tempRow, tempCol)){
                    System.out.print(tempShip.toString());
                } else {
                    System.out.print(".");
                }
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
}
