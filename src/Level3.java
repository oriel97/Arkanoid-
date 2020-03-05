import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level3 class.
 */
public class Level3 implements LevelInformation {
    /**
     * numberOfBalls - the number of balls in the game.
     *
     * @return - the number of balls in the game.
     */
    public int numberOfBalls() {
        int numberOfBalls = 2;
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
        velocityList.add(new Velocity(5, -5));
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
        int paddleWidth = 150;
        return paddleWidth;
    }

    /**
     * levelName - the level name will be displayed at the top of the screen.
     *
     * @return - the level name.
     */
    public String levelName() {
        String levelName1 = "Blue 3";
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
        return new BackGround3();
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
        colors.add(Color.gray);
        colors.add(Color.red);
        colors.add(Color.yellow);
        colors.add(Color.blue);
        colors.add(Color.white);
        //loop that create the blocks.
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 11 - i; j++) {
                Block block = new Block(new Rectangle(new Point(780 - 60 * (j), 70 + 40 * (i)),
                        60, 40), 1);
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
