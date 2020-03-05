import biuoop.DrawSurface;
import biuoop.Sleeper;


import java.awt.Color;


/**
 * CountdownAnimation - // The animation.CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) secods, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private double wait;

    /**
     * constructor.
     *
     * @param numOfSeconds the num of second to wait at all from the start of the count untill the end.
     * @param countFrom    from what number to count.
     * @param gameScreen   the screen that we display on the count.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.sleeper = new Sleeper();
        this.wait = (this.numOfSeconds / this.countFrom);

    }

    /**
     * draw one frame.
     *
     * @param d is the surface that we draw on.
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.blue);
        d.fillCircle(400, 270, 40);
        d.setColor(Color.white);
        d.drawText(380, d.getHeight() / 2, Integer.toString(countFrom), 80);
        //if is not  the first number,wait before you display it.
        if (wait != this.numOfSeconds / this.countFrom) {
            this.sleeper.sleepFor((long) wait * 1000 + 760);
        }
        this.countFrom = this.countFrom - 1;

    }

    /**
     * stop the animation if it end count.
     *
     * @return true if should stop otherwise false.
     */
    public boolean shouldStop() {
        //if it end to count
        if (countFrom < 0) {
            return true;
        }
        return false;

    }
}