import biuoop.DrawSurface;

import java.awt.Image;
import java.awt.Color;


/**
 * BackGround class.
 */
public class BackGround implements Sprite {
    private Image image;
    private Color color;
    private String levelName;

    /**
     * constructor - BackGround.
     *
     * @param image1     - the image.
     * @param color1     - the color.
     * @param levelName1 - the name of the level.
     */
    public BackGround(Image image1, Color color1, String levelName1) {
        this.color = color1;
        this.image = image1;
        this.levelName = levelName1;
    }

    /**
     * drawOn - draw the background on the surface.
     *
     * @param d - surface.
     */
    public void drawOn(DrawSurface d) {
        int length = this.levelName.length();
        // if the background is a image.
        if (this.getColor() == null) {
            d.drawImage(20, 20, getImage());
            d.setColor(Color.white);
            d.fillRectangle(0, 0, 800, 20);
            d.setColor(Color.black);
            d.drawText(680 - (length * 6), 18, "Level:  " + this.levelName, 15);
            // if the background is a color.
        } else if (this.getImage() == null) {
            d.setColor(this.getColor());
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.white);
            d.fillRectangle(0, 0, 800, 20);
            d.setColor(Color.black);
            d.drawText(680 - (length * 6), 18, "Level:  " + this.levelName, 15);
            // else - the deafult status is white background.
        } else {
            d.setColor(Color.white);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(Color.white);
            d.fillRectangle(0, 0, 800, 20);
            d.setColor(Color.black);
            d.drawText(680 - (length * 6), 18, "Level:  " + this.levelName, 15);
        }
    }

    /**
     * timePassed.
     */
    public void timePassed() {

    }

    /**
     * addToGame.
     * adding the sprite to the game.
     *
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * getColor - getter.
     *
     * @return color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * getImage- getter.
     *
     * @return image.
     */
    public Image getImage() {
        return this.image;
    }
}
