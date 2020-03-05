import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level1 class.
 */
public class Level1 implements LevelInformation {
    /**
     * numberOfBalls - the number of balls in the game.
     *
     * @return - the number of balls in the game.
     */
    public int numberOfBalls() {
        int ballsNumber = 1;
        return ballsNumber;
    }

    /**
     * initialBallVelocities - The initial velocity of each ball.
     *
     * @return - list of all the velocity of the balls.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(new Velocity(0, -5));
        return velocityList;
    }

    /**
     * paddleSpeed.
     *
     * @return - the speed of the paddle.
     */
    public int paddleSpeed() {
        int paddleSpeed1 = 10;
        return paddleSpeed1;
    }

    /**
     * paddleWidth.
     *
     * @return - the width of the paddle in the game.
     */
    public int paddleWidth() {
        int paddleWidth = 70;
        return paddleWidth;
    }

    /**
     * levelName - the level name will be displayed at the top of the screen.
     *
     * @return - the level name.
     */
    public String levelName() {
        String levelName = "Direct Hit";
        return levelName;
    }

    /**
     * getBackground - the background of the game all the draw.
     * go to an other class and with drawOn method in the class draw the
     * background of the level.
     *
     * @return -  new background to the game.
     */
    public Sprite getBackground() {
        return new BackGround1();
    }

    /**
     * blocks - The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return - list with all the blocks in the level.
     */
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        blockList.add(new Block((new Point(385, 117)), 30, 30, Color.red, 1));
        return blockList;
    }

    /**
     * numberOfBlocksToRemove - Number of blocks that should be removed
     * before the level is considered to be "cleared".
     *
     * @return - the number of blocks in the level.
     */
    public int numberOfBlocksToRemove() {
        int numberOfBlocks = this.blocks().size();
        return numberOfBlocks;
    }

    @Override
    public void setBallsNumber(int x) {

    }


}
