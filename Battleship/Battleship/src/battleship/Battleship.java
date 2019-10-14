package battleship;

/**
 * Class Battleship represent a ship of length 4.
 * Extends Ship class.
 */
class Battleship extends Ship {

    // Constructor

    public Battleship(){
        length = 4;
    }

    /**
     * Overrided method for getting the type of the ship
     * @return "battleship" - type of the ship
     */
    @Override
    String getShipType(){
        return "battleship";
    }
}
