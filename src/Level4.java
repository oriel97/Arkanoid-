import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level4 class.
 */
public class Level4 implements LevelInformation {
    /**
     * numberOfBalls - the number of balls in the game.
     *
     * @return - the number of balls in the game.
     */
    public int numberOfBalls() {
        /**
         * numberOfBalls - the number of balls in the game.
         *
         * @return - the number of balls in the game.
         */
        int numberOfBalls = 3;
        return numberOfBalls;
    }

    /**
     * initialBallVelocities - The initial velocity of each ball.
     *
     * @return - list of all the velocity of the balls.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(new Velocity(4, -5));
        velocityList.add(new Velocity(5, -4));
        velocityList.add(new Velocity(4, -6));
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
        int paddleWidth1 = 150;
        return paddleWidth1;
    }

    /**
     * levelName - the level name will be displayed at the top of the screen.
     *
     * @return - the level name.
     */
    public String levelName() {
        String levelName1 = "Final Four";
        return levelName1;
    }

    /**
     * getBackground - the background of the game all the draw.
     * go to an other class and with drawOn method in the class draw the
     * background of the level.
     *
     * @return -  new background to the game.
     */
    public Sprite getBackground() {
        return new BackGround4();
    }

    /**
     * blocks - The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return - list with all the blocks in the level.
     */
    public List<Block> blocks() {
        List<Block> blocksList = new ArrayList<>();
        List<Color> colors = new ArrayList<>();
        colors.add(Color.lightGray);
        colors.add(Color.red);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.white);
        colors.add(Color.pink);
        colors.add(Color.cyan);
        //loop that create a rows of blocks.
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 14; j++) {
                Block block = new Block(new Rectangle(new Point(730 - 55 * (j), 110 + 30 * (i)),
                        55, 30), 2);
                block.setColor(colors.get(i));
                blocksList.add(block);
            }
        }
        return blocksList;
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
        return;
    }
}
