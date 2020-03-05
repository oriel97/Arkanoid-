/**
 * ScoreTrackingListener class.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * ScoreTrackingListener - constructor.
     *
     * @param scoreCounter - scoreCounter counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent - if being hit is no zero tha mean after the ball hit the block there is at a least one time
     * the ball can hit the block again incrase the score by 5 point. if it hit the block and break hin
     * increase the score by 15.
     *
     * @param beingHit - the block that being hit.
     * @param hitter   - the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHit() != 0) {
            currentScore.increase(5);
        } else if (beingHit.getHit() == 0) {
            currentScore.increase(15);
        }
    }

    /**
     * getCurrentScore - getter.
     *
     * @return - currentScore.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
}