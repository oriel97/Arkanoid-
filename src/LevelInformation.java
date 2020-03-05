import java.util.List;

/**
 * LevelInformation interface.
 */
public interface LevelInformation {
    /**
     * numberOfBalls - the number of balls in the game.
     *
     * @return - the number of balls in the game.
     */
    int numberOfBalls();

    /**
     * initialBallVelocities - The initial velocity of each ball.
     *
     * @return - list of all the velocity of the balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed.
     *
     * @return - the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * paddleWidth.
     *
     * @return - the width of the paddle in the game.
     */
    int paddleWidth();

    /**
     * levelName - the level name will be displayed at the top of the screen.
     *
     * @return - the level name.
     */
    String levelName();


    /**
     * getBackground - the background of the game all the draw.
     * go to an other class and with drawOn method in the class draw the
     * background of the level.
     *
     * @return -  new background to the game.
     */
    Sprite getBackground();


    /**
     * blocks - The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return - list with all the blocks in the level.
     */
    List<Block> blocks();
    // Number of blocks that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();

    /**
     * numberOfBlocksToRemove - Number of blocks that should be removed
     * before the level is considered to be "cleared".
     *
     * @return - the number of blocks in the level.
     */
    int numberOfBlocksToRemove();

    /**
     * Sets balls number.
     * @param x ;
     */
    void setBallsNumber(int x);
}