import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * SpriteCollection.
 */
public class SpriteCollection {
    private List<Sprite> spriteList = new ArrayList<Sprite>();

    /**
     * constructor.
     * build a new spriteList.
     *
     * @param spriteList is "List<Sprite>".
     */
    public SpriteCollection(List<Sprite> spriteList) {
        this.spriteList = spriteList;
    }

    /**
     * getSpriteList - getter.
     *
     * @return spriteList.
     */
    public List<Sprite> getSpriteList() {
        return spriteList;
    }

    /**
     * addSprite - adding sprite to the list of sprites.
     *
     * @param s - the sprite.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * notifyAllTimePassed - use a loop to tell the sprites that the time passed.
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        int length = spriteList.size();
        int i;
        for (i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).timePassed();
        }
    }

    /**
     * drawAllOn - draw all the sprites on the surface.
     *
     * @param d -  the surface.
     */
    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        int i;
        for (i = 0; i < spriteList.size(); i++) {
            spriteList.get(i).drawOn(d);
        }
    }

}