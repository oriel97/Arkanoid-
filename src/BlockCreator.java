/**
 * BlockCreator interface.
 */
public interface BlockCreator {
    /**
     * create - Create a block at the specified location.
     *
     * @param xpos - the x position.
     * @param ypos - the y position.
     * @return - new block.
     */

    Block create(int xpos, int ypos);
}