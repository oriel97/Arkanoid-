/**
 * HitListener interface.
 */
public interface HitListener {
    /**
     * hitEvent - This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit - the block that being hit.
     * @param hitter   - the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);


}