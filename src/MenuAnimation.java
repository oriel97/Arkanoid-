import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


/**
 * MenuAnimation class.
 *
 * @param <T> - <task<Void>>.
 */
public class MenuAnimation<T> implements Menu<T> {
    private KeyboardSensor keyboard;
    private boolean stop;
    private KeyPressStoppableAnimation keyPressStoppableAnimation;
    private TreeMap<String, T> hashMap;
    private List<String> messegeList;
    private T status;
    private List<String> keys;
    private String title;

    /**
     * MenuAnimation - constructor.
     *
     * @param k      the keyboard.
     * @param title1 - the title.
     */
    public MenuAnimation(KeyboardSensor k, String title1) {
        this.keyboard = k;
        this.stop = false;
        this.hashMap = new TreeMap<>();
        this.messegeList = new ArrayList<>();
        this.keys = new ArrayList<>();
        this.title = title1;
    }

    /**
     * addSelection - adding the key , value , messege to map and list.
     *
     * @param key       - the key pressed in the keyboard.
     * @param message   - the messege.
     * @param returnVal - the task.
     */
    public void addSelection(String key, String message, T returnVal) {
        this.hashMap.put(key, returnVal);
        this.messegeList.add(message);
        this.keys.add(key);
    }

    /**
     * getStatus - getter.
     *
     * @return status.
     */
    public T getStatus() {
        return this.status;
    }

    @Override
    public void setFile(File file) {
        return;
    }

    /**
     * doOneFrame - draw the menu on the surface.
     *
     * @param d - the surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.blue);
        d.drawText(300, 200, this.title, 60);
        for (int i = 0; i < messegeList.size(); i++) {
            d.drawText(200, 300 + (i * 100), "(" + keys.get(i) + ")" + messegeList.get(i), 30);
        }

    }

    /**
     * check if should stop the animation and move to the next.
     * and get the status-task of the next animation.
     *
     * @return - true or false to start the animation or not.
     */
    public boolean shouldStop() {
        for (int i = 0; i < this.hashMap.size(); i++) {
            if (this.keyboard.isPressed(this.keys.get(i))) {
                this.status = this.hashMap.get(keys.get(i));
                return true;
            }
        }
        return false;
    }
}

