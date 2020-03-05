import biuoop.DrawSurface;

/**
 * Sprite.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d - surface.
     */
    void drawOn(DrawSurface d);


    /**
     * timePassed.
     * notify the sprite that time has passed
     */
    void timePassed();

    /**
     * addToGame - adding the game.
     *
     * @param g - the game.
     */
    void addToGame(GameLevel g);
}