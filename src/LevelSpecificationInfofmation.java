
import java.util.List;

/**
 * The type Level specification infofmation.
 */
public class LevelSpecificationInfofmation implements LevelInformation {
    private int ballsNumber;
    private List<Velocity> velocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite getBackground;
    private int numberOfBlocksToRemove;
    private List<Block> blockList;


    /**
     * Instantiates a new Level specification infofmation.
     *
     * @param velocities1             the velocities 1
     * @param paddleSpeed1            the paddle speed 1
     * @param paddleWidth1            the paddle width 1
     * @param levelName1              the level name 1
     * @param getBackground1          the get background 1
     * @param numberOfBlocksToRemove1 the number of blocks to remove 1
     * @param blocks                  the blocks
     */
    public LevelSpecificationInfofmation(List<Velocity> velocities1, int paddleSpeed1,
                                         int paddleWidth1, String levelName1, Sprite getBackground1,
                                         int numberOfBlocksToRemove1, List<Block> blocks) {
        //this.ballsNumber = ballsNumber1;
        this.velocities = velocities1;
        this.paddleSpeed = paddleSpeed1;
        this.paddleWidth = paddleWidth1;
        this.levelName = levelName1;
        this.getBackground = getBackground1;
        this.numberOfBlocksToRemove = numberOfBlocksToRemove1;
        this.blockList = blocks;


    }

    /**
     * Sets balls number.
     *
     * @param ballsNumber1 the balls number
     */
    public void setBallsNumber(int ballsNumber1) {
        this.ballsNumber = ballsNumber1;
    }

    @Override
    public int numberOfBalls() {
        return this.ballsNumber;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.getBackground;
    }

    @Override
    public List<Block> blocks() {
        return this.blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
