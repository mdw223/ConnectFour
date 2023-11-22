import java.util.*; // utilizing Scanner

/**
 * This class represents a blueprint for a Player object and assigns them
 * a name and designated piece: X or O
 */
public class Player {

    /** instance of object: representing name of player */
    private String name;
    /** instance of object: representing piece */
    private String piece;
    
    /**
     * Constructor for Player object
     */
    public Player(String name, String piece) {
        this.name = name;
        this.piece = piece;
    }

    /**
     * Accessor for name: instance of player object
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor for piece: instance of player object 
     */
    public String getPiece() {
        return piece;
    }


}
