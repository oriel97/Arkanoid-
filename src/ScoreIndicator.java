import biuoop.DrawSurface;

import java.awt.Color;

/**
 * ScoreIndicator class.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * ScoreIndicator - constructor.
     *
     * @param score1 - the score.
     */
    public ScoreIndicator(Counter score1) {
        this.score = score1;
    }

    /**
     * drawOn - wright on the top of the screen the score. in the color black.
     *
     * @param d - surface.
     */
    public void drawOn(DrawSurface d) {
        String hitCounteString;
        d.setColor(Color.black);
        hitCounteString = Integer.toString(this.score.getValue());
        d.drawText(400, 18, "score:  " + hitCounteString, 15);

    }

    /**
     * timePassed - nothing.
     */
    public void timePassed() {
    }

    @Override
    public void addToGame(GameLevel g) {

    }

    /**
     * getScore - getter.
     *
     * @return the score.
     */
    public Counter getScore() {
        return score;
    }

    /**
     * setScore - setter.
     *
     * @param score1 the counter score.
     */
    public void setScore(Counter score1) {
        this.score = score1;
    }

}
