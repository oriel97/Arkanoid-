import biuoop.DrawSurface;

import java.awt.Color;

/**
 * LivesIndicator class.
 */
public class LivesIndicator implements Sprite {
    private Counter livesCounter;

    /**
     * LivesIndicator - constructor.
     *
     * @param livesCounter1 - live counter.
     */
    public LivesIndicator(Counter livesCounter1) {
        this.livesCounter = livesCounter1;
    }

    /**
     * drawOn - wright on the top of the screen the amount of lives. in the color black.
     *
     * @param d - surface.
     */
    public void drawOn(DrawSurface d) {
        String hitCounteString;
        d.setColor(Color.black);
        hitCounteString = Integer.toString(this.getLivesCounter().getValue());
        d.drawText(20, 18, "lives:  " + hitCounteString, 15);


    }

    /**
     * getLivesCounter - getter.
     *
     * @return - livesCounter.
     */
    public Counter getLivesCounter() {
        return livesCounter;
    }

    /**
     * timePassed - nothing.
     */
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {

    }

}
