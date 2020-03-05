import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * AnimationRunner class.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor.
     *
     * @param gui     is the gui
     * @param sleeper is the sleeper for the animation.
     */
    public AnimationRunner(GUI gui, Sleeper sleeper) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = sleeper;
    }


    /**
     * the loop that actually run all the game.
     *
     * @param animation - the animation.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}