/**
 * @author Smirnov Nikita Sergeevich, BSE181
 * @version v1.0
 * @date October, 2019
 *
 * The projects was created as a homework on the Software Construction discipline.
*/
package battleship;

import java.util.Objects;
import java.util.Scanner;

/**
 * The BattleshipGame class is the "main" class--that is, it contains a main method.
 * This class sets the game up, takes shots from user, prints results.
 */
class BattleshipGame {

    /**
     * The main method. Prints all necessary information.
     * Starts the game.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // for initial info
        Scanner in = new Scanner(System.in);
        String input = "";
        printIntro();
        input = in.nextLine();

        // runs the game
        while(!Objects.equals(input, "exit")){
            startGame();
            input = in.nextLine();
        }

        System.out.println("Bye!");
    }

    /**
     * Method which runs the game.
     * Here instance of the ocean is created, ships are placed randomly.
     * Then the game starts. It takes coordinates from user and prints results.
     * It happens until user won't hit all ships.
     */
    static void startGame(){
        printRules();

        // creating ocean
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();

        // the main proccess is here
        while(!ocean.isGameOver()){
            printOcean(ocean);
            Coordinates  coordinates = getCoordinates();
            if (ocean.shootAt(coordinates.getRow(), coordinates.getColumn())){
                System.out.println("---> Hit!");
            } else {
                System.out.println("---> Miss!");
            }
            if (ocean.getShipArray()[coordinates.getRow()][coordinates.getColumn()].isSunk()){
                System.out.println("---> Admiral, we've just sunk enemy " +
                        ocean.getShipArray()[coordinates.getRow()][coordinates.getColumn()].getShipType() + "!");
            }
        }

        // print after game info
        System.out.println("---> Congratulations! You've sank all enemy ships!");
        System.out.println("---> Here is your statistics: ");
        printOcean(ocean);

        System.out.println("If You want to leave the game, type 'exit'.");
        System.out.println("Otherwise press any key to start a new game and defeat the enemy fleet!");
    }

    /**
     * Method for printing the rules of the game.
     */
    static void printRules(){
        System.out.println();
        System.out.println("---> We are glad that You are with us, Admiral!");
        System.out.println("---> I am Your adjutant, let me introduce you our main aim and the rules!");
        System.out.println("---> The enemy fleet consists of 10 ships. They are placed in the limited area (10x10).");
        System.out.println("---> Since their ships are very large, they can't be placed near each other horizontally, " +
                "vertically or diagonally!");
        System.out.println("---> Our battleship has only one cannon, so you need to tell us where to shoot!");
        System.out.println("---> You should type row and column coordinates of enemy ships, when asked!");
        System.out.println("---> Try to shoot accurately! There is a legend about the admiral, who defeated the enemy by " +
                "20 shoots. Try to repeat his success!");
        System.out.println("---> Good luck, Admiral! In You we trust!");
        System.out.println();
    }

    /**
     * Method prints introduction info,
     * when user runs the application for the first time.
     */
    static void printIntro(){
        System.out.println("---> Dear user, you've just runned the BattleShip game!");
        System.out.println("---> If you don't know what it is, please type 'exit' and press 'Enter'!");
        System.out.println("---> Otherwise get to the helm and defeat the enemy fleet!");
        System.out.print("---> ");
    }

    /**
     * Prints Ocean with its ships.
     * At the same time prints statistics about shots, hits and sunk ships.
     * @param ocean ocean, representing the battlefield
     */
    static void printOcean(Ocean ocean){
        ocean.print();
        System.out.println("Shots: " + ocean.getShotsFired());
        System.out.println("Hits: " + ocean.getHitCount());
        System.out.println("Ships sunk: " + ocean.getShipSunk());
    }

    /**
     * Method for getting coordinates from user.
     * We need to get row and column here, so it asks to enter them.
     * @return instatnce of Coordinates class, which contains row and column coordinate
     */
    static Coordinates getCoordinates(){
        System.out.println("---> Admiral, we are waiting your orders. Tell us the coordinates to shoot at!");
        int row = readInt("Enter row coordinate (0-9): ");
        int column = readInt("Enter column coordinate (0-9): ");
        System.out.println("---> Ready! Aim! Fire!");
        System.out.println("---> ...");

        return new Coordinates(row, column);
    }

    /**
     * Gets users input. If it isn't correct informs the user and asks to enter a new number.
     * @param msg message, which informs user, what he should enter
     * @return integer number
     */
    static int readInt(String msg){
        int temp = 0;
        boolean flag;

        do {
            try {
                System.out.print(msg);

                // read new input
                Scanner in = new Scanner(System.in);
                String input = in.nextLine();
                temp = Integer.parseInt(input);

                // if it is out of bounds, inform the user
                if (temp > 9 || temp < 0){
                    flag = false;
                    System.out.println("---> Coordinate is out of the field, Admiral!");
                } else {
                    flag = true;
                }
            } catch(NumberFormatException ex) {
                System.out.println("Incorrect coordinate!");
                flag = false;
            }
        } while (!flag);
        return temp;
    }
}
