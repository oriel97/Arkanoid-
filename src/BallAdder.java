import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * BallAdder class.
 */
public class BallAdder implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    private List<Ball> ballsList;

    /**
     * BallAdder - constructor.
     *
     * @param game  - the game.
     * @param balls - the balls list.
     */
    public BallAdder(GameLevel game, Counter balls) {
        this.game = game;
        this.remainingBalls = balls;
        this.ballsList = new ArrayList<>();
    }

    /**
     * hitEvent - wheb the ball hit the adderBlock add a new ball to the game.
     *
     * @param beingHit - the block that being hit.
     * @param hitter   - the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        this.ballsList.add(new Ball(new Point(400, 558), 6, Color.white, this.game.getEnvironment(), 20, 780));
        int size = this.ballsList.size() - 1;
        this.ballsList.get(size).setVelocity(4, -5);
        this.ballsList.get(size).addToGame(this.game);
        this.remainingBalls.increase(1);


    }
}
