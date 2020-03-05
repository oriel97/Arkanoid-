/**
 * HitNotifier interface.
 */
public interface HitNotifier {


    /**
     * addHitListener - Add hl as a listener to hit events.
     *
     * @param hl - the listener.
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener - Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener.
     */
    void removeHitListener(HitListener hl);
}