import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * SubMenuAnimation calss implementing animation.
 *
 * @param <T> - the t represent <Task<Void>>.
 */
public class SubMenuAnimation<T> implements Menu<T> {
    private KeyboardSensor keyboard;
    private boolean stop;
    private KeyPressStoppableAnimation keyPressStoppableAnimation;
    private TreeMap<String, String> hashMap;
    private TreeMap<String, T> hashMap1;
    private List<String> messegeList;
    private T status;
    private List<String> keys;
    private String title;
    private String path;

    /**
     * SubMenuAnimation - constructor.
     *
     * @param k      - the KeyboardSensor.
     * @param title1 - the title.
     */
    public SubMenuAnimation(KeyboardSensor k, String title1) {
        this.keyboard = k;
        this.stop = false;
        this.hashMap = new TreeMap<>();
        this.messegeList = new ArrayList<>();
        this.keys = new ArrayList<>();
        this.title = title1;

    }

    /**
     * addSelection - dont do nothing.
     *
     * @param key       - the key.
     * @param message   - the messege.
     * @param returnVal - the return value.
     */
    public void addSelection(String key, String message, T returnVal) {
        this.hashMap1.put(key, returnVal);
        this.messegeList.add(message);
        this.keys.add(key);
    }

    /**
     * addSelection - adding the level set qualifies to a maps by key-  return value.
     * have a messege list and keys list.
     * check if it dosent contain beacuse its in a loop.
     *
     * @param key       - the key - the keyboard press.
     * @param message   - the message is the difficulty of the level set.
     * @param returnVal - the return val is the path to the level set.
     */
    public void addSelection(String key, String message, String returnVal) {
        if (!this.hashMap.containsKey(key)) {
            this.hashMap.put(key, returnVal);
        }
        if (!this.messegeList.contains(message)) {
            this.messegeList.add(message);
        }
        if (!this.keys.contains(key)) {
            this.keys.add(key);
        }
    }

    /**
     * getStatus - getter.
     *
     * @return the status.
     */
    public T getStatus() {
        return this.status;
    }

    @Override
    public void setFile(File file) {
        return;
    }

    /**
     * doOneFrame - drawing the screen for the sub menu screen.
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
     * shouldStop - check if should stop by checking what the user pressed.
     *
     * @return - true or false depend if the user press one of the keys in the key list.
     */
    public boolean shouldStop() {
        for (int i = 0; i < this.hashMap.size(); i++) {
            if (this.keyboard.isPressed(this.keys.get(i))) {
                this.path = this.hashMap.get(keys.get(i));
                return true;
            }
        }
        return false;
    }

    /**
     * getPath - getter.
     *
     * @return - the path.
     */
    public String getPath() {
        return this.path;
    }
}

