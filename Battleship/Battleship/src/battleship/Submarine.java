package battleship;

/**
 * Class Submarine represent a ship of length 1.
 * Extends Ship class.
 */
class Submarine extends Ship {

    // Constructor

    Submarine(){
        length = 1;
    }

    /**
     * Overrided method for getting the type of the ship
     * @return "submarine" - type of the ship
     */
    @Override
    String getShipType() {
        return "submarine";
    }
}
