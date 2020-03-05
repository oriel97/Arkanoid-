import java.util.ArrayList;
import java.util.List;

/**
 * BlockRemover class.
 */
public class BlockRemover implements HitListener, HitNotifier {
    private GameLevel game;
    private Counter remainingBlocks;
    private List<HitListener> hitListeners;

    /**
     * BlockRemover - constructor.
     *
     * @param game          - the game.
     * @param removedBlocks - removedBlocks count.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
        this.hitListeners = new ArrayList<HitListener>();
    }


    /**
     * hitEvent - Blocks that are hit and reach 0 hit-points should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     *
     * @param beingHit - the block that being hit.
     * @param hitter   - the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        notifyHit(beingHit, hitter);
        if (beingHit.getHit() == 0) {
            beingHit.removeFromGame(this.game);
            this.getRemainingBlocks().decrease(1);

        }
    }

    /**
     * notifyHit - a loop that run withe all the correct listener and notify them
     * that was happen something that they need to react to him.
     *
     * @param beingHit - the block that being hit.
     * @param hitter   - the ball that hit.
     */
    public void notifyHit(Block beingHit, Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(beingHit, hitter);
        }
    }

    /**
     * addHitListener - adding listener to the hit listener list.
     *
     * @param hl - the listener.
     */
    public void addHitListener(HitListener hl) {
        List<HitListener> hitListeners1 = getHitListeners();
        hitListeners1.add(hl);
        this.setHitListeners(hitListeners1);
    }

    /**
     * removeHitListener - removing listener from the hit listener list.
     *
     * @param hl - the listener.
     */
    public void removeHitListener(HitListener hl) {
        List<HitListener> hitListeners1 = getHitListeners();
        hitListeners1.remove(hl);
        this.setHitListeners(hitListeners1);
    }

    /**
     * setHitListeners - setter.
     *
     * @param hitListeners1 - hitListeners list.
     */
    public void setHitListeners(List<HitListener> hitListeners1) {
        this.hitListeners = hitListeners1;
    }

    /**
     * getHitListeners - getter.
     *
     * @return hitListeners - list.
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * getRemainingBlocks - getter.
     *
     * @return - remainingBlocks.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * setRemainingBlocks - setter.
     *
     * @param remainingBlocks1 - remainingBlocks1.
     */
    public void setRemainingBlocks(Counter remainingBlocks1) {
        this.remainingBlocks = remainingBlocks1;
    }
}
