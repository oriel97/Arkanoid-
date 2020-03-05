import biuoop.DrawSurface;

import java.awt.Color;

/**
 * BackGround3 class.
 */
public class BackGround3 implements Sprite {
    private Level3 level3 = new Level3();

    /**
     * drawOn - drawing the background for the level.
     *
     * @param d - surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue.darker());
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.black);
        d.drawText(650, 18, "Level:  " + this.getLevel3().levelName(), 15);
        d.setColor(Color.black);
        d.fillRectangle(50, 350, 120, 250);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                d.setColor(Color.white);
                d.fillRectangle(65 + i * 20, 360 + j * 60, 10, 50);
            }
        }
        d.setColor(new Color(122, 121, 121));
        d.fillRectangle(105, 250, 10, 100);
        d.setColor(new Color(81, 75, 75));
        d.fillRectangle(100, 310, 20, 40);
        d.setColor(Color.red);
        d.fillCircle(110, 250, 10);
        d.setColor(Color.decode("#c93636"));
        d.fillCircle(110, 250, 7);
        d.setColor(Color.WHITE);
        d.fillCircle(110, 250, 3);
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
    public Level3 getLevel3() {
        return level3;
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

