import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * HighScoresAnimation class.
 */
public class HighScoresAnimation implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop = false;
    private KeyPressStoppableAnimation keyPressStoppableAnimation;
    private HighScoresTable highScoresTablel;

    /**
     * HighScoresAnimation - constructor.
     *
     * @param highScoresTable1 - the hiscore table.
     * @param k                - the Keyboard.
     */
    public HighScoresAnimation(HighScoresTable highScoresTable1, KeyboardSensor k) {
        this.keyboard = k;
        this.keyPressStoppableAnimation = new KeyPressStoppableAnimation(this.keyboard, "space", this);
        this.highScoresTablel = highScoresTable1;

    }

    /**
     * draw the high score table.
     * and check if pressing the space symbol to stop.
     *
     * @param d - the surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.blue);
        d.drawText(200, 100, "High Score List:", 50);
        List<ScoreInfo> scoreInfoList = this.highScoresTablel.getHighScores();
        if (this.highScoresTablel.size() > 2) {
            d.setColor(Color.getHSBColor(47, 89, 100));
            d.fillCircle(20, 140, 15);
            d.setColor(Color.lightGray);
            d.fillCircle(20, 190, 15);
            d.setColor(Color.getHSBColor(192, 192, 192));
            d.fillCircle(20, 240, 15);
        }
        for (int i = 0; i < this.highScoresTablel.size(); i++) {
            d.setColor(Color.black);
            d.drawText(40, 150 + i * 45, scoreInfoList.get(i).getName(), 30);
            d.setColor(Color.red);
            d.drawText(650, 150 + i * 45, String.valueOf(scoreInfoList.get(i).getScore()), 30);

        }
        this.keyPressStoppableAnimation.doOneFrame(d);
        this.stop = this.keyPressStoppableAnimation.shouldStop();
    }

    /**
     * shouldStop.
     * @return true or false if should stop or not.
     */
    public boolean shouldStop() {
        if (this.stop) {
            this.stop = false;
            return true;
        }
        return false;
    }

    /**
     * getHighScoresTablel - getter.
     * @return - highScoresTablel.
     */
    public HighScoresTable getHighScoresTablel() {
        return highScoresTablel;
    }
}
