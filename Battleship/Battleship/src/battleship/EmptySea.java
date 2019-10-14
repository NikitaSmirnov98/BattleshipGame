package battleship;

/**
 * Class representing the empty part of the ocean.
 * It still extends Ship, but it's for simplifying the process of coding, not understanding.
 */
class EmptySea extends Ship {

    // Constructor

    public EmptySea(){
        length = 1;
    }

    /**
     * This method overrides shootAt(int row, int column) that is inherited from Ship,
     * and always returns false to indicate that nothing was hit.
     * @param row row coordinate of the shot
     * @param column column coordinate of the shot
     * @return false, because there is no ships
     */
    @Override
    boolean shootAt(int row, int column) {
        hit[0] = true;
        return false;
    }

    /**
     * This method overrides isSunk() that is inherited from Ship,
     * and always returns false to indicate that you didn't sink anything.
     * @return false
     */
    @Override
    boolean isSunk() {
        return false;
    }

    /**
     * This method overrides toString() that is inherited from Object,
     * it returns '-' if there was a shot and '.' otherwise
     * @return symbol of condition
     */
    @Override
    public String toString() {
        if (hit[0]){
            return "-";
        }
        return ".";
    }
}
