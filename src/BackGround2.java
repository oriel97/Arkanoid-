import biuoop.DrawSurface;
import java.awt.Color;
/**
 * BackGround2 class.
 */
public class BackGround2 implements Sprite {
    private Level2 level2 = new Level2();

    /**
     * drawOn - drawing the background for the level.
     *
     * @param d - surface.
     */
    public void drawOn(DrawSurface d) {

        int j = 70;
        d.setColor(Color.decode("#AAF9FB"));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.yellow);
        //draw the Sun rays.
        for (int i = 1; i < 70; i++) {
            d.drawLine(150, 150, i * 10, 270);
        }
        //draw the sun that change colors.
        while (j > 20) {
            d.setColor(Color.yellow);
            d.fillCircle(150, 150, j);
            j--;
        }
        d.setColor(Color.green);
        d.fillOval(0, 550, 275, 130);
        d.setColor(Color.GREEN);
        d.fillOval(267, 550, 280, 140);
        d.setColor(Color.green);
        d.fillOval(267 * 2, 550, 268, 130);
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.black);
        d.drawText(650, 18, "Level:  " + this.getLevel2().levelName(), 15);
    }

    /**
     * timePassed.
     */
    public void timePassed() {

    }

    /**
     * getLevel2 - getter.
     *
     * @return level2.
     */
    public Level2 getLevel2() {
        return level2;
    }

    /**
     * addToGame - adding the background to the game as sprite.
     *
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
