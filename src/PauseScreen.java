import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


import java.awt.Color;
import java.util.Random;

/**
 * PauseScreen class.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private KeyPressStoppableAnimation keyPressStoppableAnimation;

    /**
     * PauseScreen - constructor.
     *
     * @param k - the keyboard.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
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
        // writing on the screen stop!!!! in big.

        d.setColor(Color.red.darker());
        d.fillRectangle(0, 0, 800, 600);
//        d.setColor(Color.YELLOW);
        Random rand = new Random();
        Color rand1 = new Color(rand.nextInt(0xFFFFFF));
        d.setColor(rand1);
        // s
        d.fillRectangle(50, 60, 112, 24);
        d.fillRectangle(50, 60, 24, 112);
        d.fillRectangle(50, 160, 112, 24);
        d.fillRectangle(138, 160, 24, 112);
        d.fillRectangle(50, 260, 112, 24);
        //t
        d.fillRectangle(180, 60, 112, 24);
        d.fillRectangle(226, 60, 24, 224);

        //o
        d.fillRectangle(310, 60, 112, 24);
        d.fillRectangle(310, 60, 24, 224);
        d.fillRectangle(310, 260, 112, 24);
        d.fillRectangle(398, 60, 24, 224);

        // p
        d.fillRectangle(440, 60, 112, 24);
        d.fillRectangle(440, 60, 24, 224);
        d.fillRectangle(440, 160, 112, 24);
        d.fillRectangle(528, 60, 24, 100);

        //!!!!
        d.fillRectangle(580, 60, 24, 180);
        d.fillRectangle(624, 60, 24, 180);
        d.fillRectangle(668, 60, 24, 180);
        d.fillRectangle(712, 60, 24, 180);

        d.fillCircle(592, 272, 12);
        d.fillCircle(636, 272, 12);
        d.fillCircle(680, 272, 12);
        d.fillCircle(724, 272, 12);

        // writing on the screen.
        d.setColor(Color.black);
        d.drawText(170, 440, "if you really want to continue!!", 32);
        d.drawText(170, 480, "press space to continue ", 32);
        // if the user press space return to the loop. this.stop = true.
        //this.stop = this.keyPressStoppableAnimation.shouldStop();
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