package battleship;

/**
 * Class Cruiser represent a ship of length 3.
 * Extends Ship class.
 */
class Cruiser extends Ship {

    // Constructor

    public Cruiser(){
        length = 3;
    }

    /**
     * Overrided method for getting the type of the ship
     * @return "cruiser" - type of the ship
     */
    @Override
    String getShipType() {
        return "cruiser";
    }
}
