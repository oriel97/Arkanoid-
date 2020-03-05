/**
 * ShowHiScoresTask class.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * ShowHiScoresTask.
     *
     * @param runner              - the runner animation.
     * @param highScoresAnimation - the animation.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
    }

    /**
     * run - show the high score.
     *
     * @return null.
     */
    public Void run() {
        this.runner.run(this.highScoresAnimation);
        return null;
    }
}