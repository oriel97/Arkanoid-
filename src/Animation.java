import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {
    /**
     * doOneFrame - method that presenting a frame on the surface.
     * the frame is an other frame that not like the frame of the game.
     * the frame will be presenting till the user will press the 'space' button.
     *
     * @param d - the surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop.
     *
     * @return - true or false depend if the loop of the game should stop.
     */
    boolean shouldStop();
}