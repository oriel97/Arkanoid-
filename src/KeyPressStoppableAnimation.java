import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation class.
 *
 * @author sarah de paz
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean flag;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Constructor.
     *
     * @param sensor    is the sensor
     * @param key       is the key
     * @param animation is the animation to run
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.flag = false;
        this.isAlreadyPressed = true;
    }

    /**
     * doOneFrame.
     * Method to do one frame of the animation.
     *
     * @param d draw surface.
     */
    public void doOneFrame(DrawSurface d) {
        //this.animation.doOneFrame(d);
        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        }
        if (!this.sensor.isPressed(this.key)) {
            isAlreadyPressed = false;
        }
    }

    /**
     * shouldStop.
     * The method returns false if the animation should not stop and
     * true if it does.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        if (this.stop) {
            this.stop = false;
            return true;
        }
        return false;
    }
}
