import biuoop.DrawSurface;

import java.awt.Color;

/**
 * BackGround4 class.
 */
public class BackGround4 implements Sprite {
    private Level4 level4 = new Level4();

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
        d.setColor(Color.black);
        d.drawText(650, 18, "Level:  " + this.getLevel4().levelName(), 15);
        d.setColor(Color.yellow);
        d.fillRectangle(20, 450, 760, 150);
        //sea
        d.setColor(Color.blue);
        d.fillRectangle(20, 200, 760, 250);

        d.setColor(Color.cyan);
        d.fillRectangle(20, 40, 760, 160);
        // sun
        d.setColor(Color.yellow);
        d.fillCircle(400, 120, 80);

        //cloud
        d.setColor(Color.white);
        d.fillCircle(110, 90, 30);
        d.fillCircle(150, 90, 30);
        d.fillCircle(90, 120, 30);
        d.fillCircle(130, 120, 30);
        d.fillCircle(170, 120, 30);

        //cloud

        d.fillCircle(690, 90, 30);
        d.fillCircle(650, 90, 30);
        d.fillCircle(710, 120, 30);
        d.fillCircle(670, 120, 30);
        d.fillCircle(630, 120, 30);

        //men
        d.setColor(Color.black);
        d.drawLine(40, 550, 65, 525);
        d.drawLine(65, 525, 90, 550);
        d.drawLine(65, 525, 65, 480);
        d.drawLine(65, 480, 90, 505);
        d.drawLine(65, 480, 40, 505);
        d.drawCircle(65, 460, 20);
        d.drawCircle(70, 455, 3);
        d.drawCircle(60, 455, 3);
        d.drawLine(60, 470, 70, 470);

        // men

        d.setColor(Color.black);
        d.drawLine(240, 550, 265, 525);
        d.drawLine(265, 525, 290, 550);
        d.drawLine(265, 525, 265, 480);
        d.drawLine(265, 480, 290, 505);
        d.drawLine(265, 480, 240, 505);
        d.drawCircle(265, 460, 20);
        d.drawCircle(270, 455, 3);
        d.drawCircle(260, 455, 3);
        d.drawLine(260, 470, 270, 470);

        //ball
        d.setColor(Color.red);
        d.fillCircle(90, 540, 10);
        d.setColor(Color.black);
        d.drawLine(100, 550, 110, 550);
        d.drawLine(100, 540, 110, 540);
        d.drawLine(100, 530, 110, 530);


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
     * getLevel2 - getter.
     *
     * @return level2.
     */
    public Level4 getLevel4() {
        return level4;
    }
}
