package battleship;

/**
 * Class Destroyer represent a ship of length 2.
 * Extends Ship class.
 */
class Destroyer extends Ship {

    // Constructor

    public Destroyer(){
        length = 2;
    }

    /**
     * Overrided method for getting the type of the ship
     * @return "destroyer" - type of the ship
     */
    @Override
    String getShipType() {
        return "destroyer";
    }
}
