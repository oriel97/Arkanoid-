import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.KeyboardSensor;

/**
 * Game class.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private BlockRemover remover;
    private BallRemover ballRemover;
    private ScoreIndicator scoreIndicator;
    private ScoreTrackingListener scoreTrackingListener;
    private Counter countScore;
    private Counter countLive;
    private Paddle paddle;
    private Boolean hasAText;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * constructor - GameLevel.
     *
     * @param levelInformation1 - the level information - the level.
     * @param gui1              - the gui.
     * @param animationRunner   - the loop.
     * @param ks                - the KeyboardSensor.
     * @param countLive1        - count lives counter.
     * @param countScore1       - count score counter.
     * @param sleeper1          - the sleeper.
     */
    public GameLevel(LevelInformation levelInformation1, GUI gui1, AnimationRunner animationRunner,
                     KeyboardSensor ks, Counter countLive1, Counter countScore1, Sleeper sleeper1) {
        List<Sprite> spritList1 = new ArrayList<>();
        List<Collidable> collList1 = new ArrayList<>();
        this.sprites = new SpriteCollection(spritList1);
        this.environment = new GameEnvironment(collList1);
        this.countScore = new Counter(0);
        this.countLive = new Counter(4);
        this.levelInformation = levelInformation1;
        this.keyboard = ks;
        this.runner = animationRunner;
        this.countLive = countLive1;
        this.countScore = countScore1;
        this.gui = gui1;
        this.sleeper = sleeper1;
    }

    /**
     * addCollidable.
     * adding colidablr to the list.
     *
     * @param c - colidable.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * addSprite.
     * adding sprit to the list.
     *
     * @param s - sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * removeCollidable - remove collidable from the game.
     *
     * @param c - the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getList().remove(c);
    }

    /**
     * removeSprite - removing sprite from the list.
     *
     * @param s - the sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSpriteList().remove(s);
    }

    /**
     * initialize.
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */

    public void initialize() {
        // new Block remover.
        BlockRemover blockRemover = new BlockRemover(this, new Counter(0));
        this.setRemover(blockRemover);
        // adding to the sprite list the background.
        this.getSprites().addSprite(this.getLevelInformation().getBackground());

        List<Block> blockList = this.getLevelInformation().blocks();
        // loop that run on all the blocks in the game and adding them to the sprite list and make them listen to the
        // get rempover to make sure that ball can delete them.
        for (Block b1 : blockList) {
            b1.addToGame(this);
            b1.addHitListener(this.getRemover());
            blockRemover.getRemainingBlocks().increase(1);
        }

        Block leftBound = new Block((new Rectangle(new Point(0, 20), 20, 580)), 0);
        Block upBound = new Block((new Rectangle(new Point(0, 20), 800, 20)), 0);
        Block deathRegion = new Block((new Rectangle(new Point(0, 599), 800, 1)), 0);
        Block rightBound = new Block((new Rectangle(new Point(780, 20), 20, 580)), 0);
        Block updeathRegion = new Block((new Rectangle(new Point(0, 20), 800, 1)), 0);
        Block[] boundsBlocks = new Block[4];
        boundsBlocks[0] = leftBound;
        boundsBlocks[1] = upBound;
        // the down block - a death region block.
        boundsBlocks[3] = deathRegion;
        boundsBlocks[2] = rightBound;
        // creating a new live indicator and give him a counter.
        LivesIndicator livesIndicator = new LivesIndicator(getCountLive());
        // adding him to the sprite list and now on the top will be write the amount of lives.
        this.getSprites().addSprite(livesIndicator);
        // creating a new score indicator giving him a counter(keep score counter) adding him
        // to the sprite list and now we can how many point we have on the top.
        ScoreIndicator scoreIndicator1 = new ScoreIndicator(getCountScore());
        this.getSprites().addSprite(scoreIndicator1);
        // create a new ScoreTrackingListener and adding him the same counter for keeping score.
        // add the scoreTrackingListener1 to be a listener to the block remover.
        ScoreTrackingListener scoreTrackingListener1 = new ScoreTrackingListener(getCountScore());
        blockRemover.addHitListener(scoreTrackingListener1);
        this.setScoreTrackingListener(scoreTrackingListener1);
        // create a new ball remover and make him a listener to the deathRegion block (down limit).
        Counter countBalls = new Counter(0);
        BallRemover ballRemover1 = new BallRemover(this, countBalls);
        deathRegion.addHitListener(ballRemover1);
        updeathRegion.addHitListener(ballRemover1);


        // set color to the boundaries.
        this.setBallRemover(ballRemover1);
        for (int i = 0; i < 3; i++) {
            boundsBlocks[i].setColor(Color.GRAY);
        }
        boundsBlocks[3].setColor(Color.blue);
        updeathRegion.setColor(Color.blue);
        // adding the boundries to the game.

        leftBound.addToGame(this);
        upBound.addToGame(this);
        deathRegion.addToGame(this);
        rightBound.addToGame(this);
        updeathRegion.addToGame(this);

    }

    /**
     * playOneTurn method.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle(); // or a similar method
        this.runner.run(new CountdownAnimation(2, 3, this.getSprites())); // countdown before turn starts.
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * createBallsOnTopOfPaddle - create balls and paddle. and add them to the game.
     *
     * @return the paddle.
     */
    private Paddle createBallsOnTopOfPaddle() {
        // loop to create the balls of the level on to the level.
        for (int i = 0; i < this.getLevelInformation().numberOfBalls(); i++) {
            Ball ball1 = new Ball(new Point(400, 500), 6, Color.white, this.environment, 20, 780);
            LevelInformation levelInformation2 = this.getLevelInformation();
            List<Velocity> velocityList = levelInformation2.initialBallVelocities();
            Velocity v = velocityList.get(i);
            ball1.setVelocity(v);
            ball1.addToGame(this);
        }

        this.getBallRemover().getRemainingBalls().setCounters(this.getLevelInformation().numberOfBalls());


        // renew the amount of ball in the game to 2 in the ball counter.

        // remove the current paddle from the game.
        this.removeCollidable(this.getPaddle());
        this.removeSprite(this.getPaddle());
        // creat a new paddle and put him in the middle after losing lives.
        this.keyboard = gui.getKeyboardSensor();
        Rectangle thepaddle = new Rectangle(new Point((800 - this.getLevelInformation().paddleWidth()) / 2, 520),
                this.getLevelInformation().paddleWidth(), 20);
        this.setPaddle(new Paddle(thepaddle, new Point(20, 520), new Point(
                780 - this.getLevelInformation().paddleWidth(), 520),
                this.getKeyboard(), this.getLevelInformation().paddleSpeed()));
        this.paddle.addToGame(this); // adding the paddle to the game.
        return this.paddle;
    }

    /**
     * doOneFrame - method that presenting a frame on the surface.
     * the frame is an other frame that not like the frame of the game.
     * the frame will be presenting till the user will press the 'space' button.
     *
     * @param d - the surface.
     */
    public void doOneFrame(DrawSurface d) {
        // if there no more block in the game.
        if (this.getRemover().getRemainingBlocks().getValue() == 0) {
            // adding 100 points.
            this.getCountScore().increase(100);
            this.running = false;
        }
        // if there no more balls in the game - they fall.
        if (this.getBallRemover().getRemainingBalls().getValue() <= 0) {
            getCountLive().decrease(1);
            this.running = false;
        }

        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        // if the player press p pause the game.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
    }

    /**
     * shouldStop.
     *
     * @return - true or false depend if the loop of the game should stop.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * setScoreTrackingListener - setter.
     *
     * @param scoreTrackingListener3 - listner.
     */
    public void setScoreTrackingListener(ScoreTrackingListener scoreTrackingListener3) {
        this.scoreTrackingListener = scoreTrackingListener3;
    }

    /**
     * setRemover - setter.
     *
     * @param remover1 - remove blocks.
     */
    public void setRemover(BlockRemover remover1) {
        this.remover = remover1;
    }

    /**
     * getRemover - getter.
     *
     * @return - remove blocks.
     */
    public BlockRemover getRemover() {
        return remover;
    }

    /**
     * getScoreTrackingListener - getter.
     *
     * @return - scoreTrackingListener.
     */
    public ScoreTrackingListener getScoreTrackingListener() {
        return scoreTrackingListener;
    }

    /**
     * getCountScore - getter.
     *
     * @return - countScore.
     */
    public Counter getCountScore() {
        return countScore;
    }

    /**
     * getCountLive - getter.
     *
     * @return - countLive.
     */
    public Counter getCountLive() {
        return countLive;
    }

    /**
     * getPaddle - getter.
     *
     * @return - paddle.
     */
    public Paddle getPaddle() {
        return paddle;
    }

    /**
     * setPaddle - setter.
     *
     * @param paddle1 - the paddle.
     */
    public void setPaddle(Paddle paddle1) {
        this.paddle = paddle1;
    }


    /**
     * getBallRemover - getter.
     *
     * @return - ballRemover.
     */
    public BallRemover getBallRemover() {
        return ballRemover;
    }

    /**
     * setBallRemover - setter.
     *
     * @param ballRemover1 - ballRemover1.
     */
    public void setBallRemover(BallRemover ballRemover1) {
        this.ballRemover = ballRemover1;
    }

    /**
     * SpriteCollection.
     * getter.
     *
     * @return the sprite.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * GameEnvironment.
     *
     * @return the environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * setGui.
     * setter.
     *
     * @param gui1 - the gui.
     */
    public void setGui(GUI gui1) {
        this.gui = gui1;
    }


    /**
     * getGui.
     * getter.
     *
     * @return - the gui.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * getSleeper - getter.
     *
     * @return sleeper.
     */
    public Sleeper getSleeper() {
        return sleeper;
    }

    /**
     * getKeyboard - getter.
     *
     * @return keyboard.
     */
    public KeyboardSensor getKeyboard() {
        return keyboard;
    }

    /**
     * getLevelInformation - getter.
     *
     * @return - levelInformation.
     */
    public LevelInformation getLevelInformation() {
        return levelInformation;
    }
}
