import biuoop.DrawSurface;
import java.awt.Color;

/**
 * BackGround1 class.
 */
public class BackGround1 implements Sprite {
    private Level1 level1 = new Level1();

    /**
     * drawOn - drawing the background for the level.
     *
     * @param d - surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.blue);
        d.drawCircle(400, 132, 42);
        d.drawCircle(400, 132, 63);
        d.drawCircle(400, 132, 84);
        d.drawLine(380, 132, 303, 132);
        d.drawLine(418, 132, 497, 132);
        d.drawLine(400, 113, 400, 25);
        d.drawLine(400, 150, 400, 230);
        d.setColor(Color.black);
        d.drawText(680, 18, "Level:  " + this.getLevel1().levelName(), 15);
    }

    /**
     * timePassed.
     */
    public void timePassed() {

    }

    /**
     * addToGame - adding the background to the game as sprite.
     *
     * @param g - the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * getLevel1 - getter.
     *
     * @return level1.
     */
    public Level1 getLevel1() {
        return level1;
    }
}
