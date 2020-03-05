import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Level2 class.
 */
public class Level2 implements LevelInformation {
    /**
     * numberOfBalls - the number of balls in the game.
     *
     * @return - the number of balls in the game.
     */
    public int numberOfBalls() {
        int numberOfBalls1 = 10;
        return numberOfBalls1;
    }

    /**
     * initialBallVelocities - The initial velocity of each ball.
     * a loop that initial the velocity.
     *
     * @return - list of all the velocity of the balls.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < this.numberOfBalls() / 2; i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(180 - i * 10 - 35, 10));
            velocityList.add(Velocity.fromAngleAndSpeed(i * 10 + 35, 10));
        }
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
        int paddleWidth1 = 200;
        return paddleWidth1;
    }

    /**
     * levelName - the level name will be displayed at the top of the screen.
     *
     * @return - the level name.
     */
    public String levelName() {
        String levelName = "Wide Easy";
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
        return new BackGround2();
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
        colors.add(Color.red);
        colors.add(Color.red);
        colors.add(Color.orange);
        colors.add(Color.orange);
        colors.add(Color.yellow);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.green);
        colors.add(Color.green);
        colors.add(Color.blue);
        colors.add(Color.blue);
        colors.add(Color.pink);
        colors.add(Color.pink);
        colors.add(Color.cyan);
        colors.add(Color.cyan);
        //loop that create the blocks.
        for (int i = 0; i < 15; i++) {
            Block block = (new Block(new Rectangle(
                    new Point(20 + i * (50.6), 270), 50.6, 20), 1));
            block.setColor(colors.get(i));
            blocksList.add(block);
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

    }
}
