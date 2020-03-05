/**
 * BallRemover class.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * BallRemover - constructor.
     *
     * @param game         - a game.
     * @param removedBalls - a counter.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * hitEvent - when the ball hit the death region block the down limit block
     * removing thr ball from the game and decrease the amount of balls by one.
     *
     * @param beingHit - when block being hit.
     * @param hitter   - the ball who hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }

    /**
     * getRemainingBalls - getter.
     *
     * @return - remainingBalls.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * setRemainingBalls - setter.
     *
     * @param remainingBalls1 - remainingBalls.
     */
    public void setRemainingBalls(Counter remainingBalls1) {
        this.remainingBalls = remainingBalls1;
    }
}
