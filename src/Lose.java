import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Lose class.
 */
public class Lose implements Animation {
    private Counter score;
    private KeyboardSensor keyboard;
    private boolean stop;
    private KeyPressStoppableAnimation keyPressStoppableAnimation;

    /**
     * Lose - constructor.
     *
     * @param k      - the keyboard.
     * @param score1 - the score of the game.
     */
    public Lose(KeyboardSensor k, Counter score1) {
        this.keyboard = k;
        this.stop = false;
        this.score = score1;
        this.keyPressStoppableAnimation = new KeyPressStoppableAnimation(this.keyboard, "space", this);
    }

    /**
     * doOneFrame - method that presenting a frame on the surface.
     * the frame is an other frame that not like the frame of the game.
     * the frame will be presenting till the user will press the 'space' button.
     *
     * @param d - the surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.red);
        d.drawText(70, 250, "loser!!", 250);
        d.setColor(Color.white);
        // writing on the screen.
        d.drawText(170, 450, "Game Over!!!", 32);
        d.drawText(170, 500, "You lost... your score is:  " + score.getValue(), 32);
        // back to the big loop if the user press space.
        this.keyPressStoppableAnimation.doOneFrame(d);
        this.stop = this.keyPressStoppableAnimation.shouldStop();

    }

    /**
     * shouldStop.
     *
     * @return - true or false depend if the loop of the game should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
