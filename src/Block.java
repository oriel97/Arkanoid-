import biuoop.DrawSurface;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle recangle;
    private Color color;
    private int hitCounter;
    private List<HitListener> hitListeners;
    private boolean hasText = false;
    private String text = "";
    private Map<Integer, String> fill = null;
    private java.awt.Color stroke;
    private Map<Integer, Color> mapColors = null;
    private Map<Integer, Image> mapImages = null;

    /**
     * constructor - Block.
     *
     * @param rec1 - a rectangle.
     * @param hit  - the amount of hit the bloc have.
     */
    public Block(Rectangle rec1, int hit) {
        this.recangle = rec1;
        this.hitCounter = hit;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * constructor - Block.
     *
     * @param rec1 - the rectangle.
     * @param hit  - the hit.
     * @param text - the text.
     */
    public Block(Rectangle rec1, int hit, String text) {
        this.recangle = rec1;
        this.hitCounter = hit;
        this.hitListeners = new ArrayList<HitListener>();
        this.hasText = true;
        this.text = text;
    }

    /**
     * constructor - Block.
     *
     * @param upperLeft  - the point upper left of a rectangle.
     * @param width      - the widt of the rectangle.
     * @param height     - the height of the rectangle.
     * @param color      the color of the block.
     * @param hitCounter - the amount of hit the block have.
     */
    public Block(Point upperLeft, double width, double height, Color color, int hitCounter) {
        Rectangle block = new Rectangle(upperLeft, width, height);
        this.recangle = block;
        this.color = color;
        this.hitCounter = hitCounter;
        this.hitListeners = new ArrayList<HitListener>();
    }


    /**
     * constructor.
     *
     * @param x          start x of the rectangle.
     * @param y          start y of the rectangle.
     * @param hight      of the block.
     * @param width      of the block.
     * @param countOfHit count of hit to hit the block.
     * @param color      map color.
     * @param image      image map.
     */
    public Block(double x, double y, double hight, double width, int countOfHit,
                 Map<Integer, Color> color, Map<Integer, Image> image) {
        this.recangle = new Rectangle(new Point(x, y), width, hight);
        this.hitCounter = countOfHit;
        this.fill = fill;
        this.mapColors = color;
        this.mapImages = image;
        this.color = Color.blue;
        this.hitListeners = new ArrayList<HitListener>();

    }

    /**
     * getHit - getter.
     *
     * @return - the hit number.
     */
    public int getHit() {
        return this.hitCounter;
    }

    /**
     * set the color of the block.
     *
     * @param color1 the new color of the block.
     */
    public void setColor(java.awt.Color color1) {
        this.color = color1;
    }

    /**
     * getCollisionRectangle.
     *
     * @return - the rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.recangle;
    }

    /**
     * getColor - getter.
     *
     * @return - return a default color.
     */
    public Color getColor() {
        this.color = Color.blue;
        return this.color;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  - the collision point.
     * @param currentVelocity - the current velocity.
     * @param hitter          - the ball that hit.
     * @return the new velocity.
     */

    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        this.hitCounter--; // when getting in this function its mean the ball hit a block , and change the
        //number of hit by 1.
        Velocity newVelocity = currentVelocity;

        // check where is the collision point on the rectangle if the collision point is on the right
        // side change the velocity to tell the to go to the left side. and the same with up , down, left.
        if ((this.getCollisionRectangle().upLine()
                .checkOnTheLine(this.getCollisionRectangle().upLine(), collisionPoint))
                || this.getCollisionRectangle().downLine()
                .checkOnTheLine(this.getCollisionRectangle().downLine(), collisionPoint)) {
            newVelocity = new Velocity(currentVelocity.getDx(), ((-1) * (currentVelocity.getDy())));
        }
        if ((this.getCollisionRectangle().leftLine()
                .checkOnTheLine(this.getCollisionRectangle().leftLine(), collisionPoint))
                || this.getCollisionRectangle().rightLine()
                .checkOnTheLine(this.getCollisionRectangle().rightLine(), collisionPoint)) {
            newVelocity = new Velocity((-1) * (currentVelocity.getDx()), (currentVelocity.getDy()));
        }
        // notifi there been a hit to the listners.
        this.notifyHit(hitter);
        // return the new velocity to the moveOne Step.
        return newVelocity;

    }

    /**
     * settext - setting text.
     *
     * @param surface - the surface.
     * @param writing - the string.
     */
    public void settext(DrawSurface surface, String writing) {
        double x = this.getCollisionRectangle().getUpperLeft().getX();
        double y = this.getCollisionRectangle().getUpperLeft().getY();
        double height = this.getCollisionRectangle().getHeight();
        double width = this.getCollisionRectangle().getWidth();
        surface.setColor(Color.black);
        surface.drawText((int) (x - 5 + width / 2), (int) (y + 5 + height / 2), writing, 12);

    }

    /**
     * drawOn - draw the blocks.
     *
     * @param surface - the surface.
     */
    public void drawOn(DrawSurface surface) {
        //define the x,y of the upper left point and the widthe and heigt of the rectangle(block).
//        double x = this.getCollisionRectangle().getUpperLeft().getX();
//        double y = this.getCollisionRectangle().getUpperLeft().getY();
//        double height = this.getCollisionRectangle().getHeight();
//        double width = this.getCollisionRectangle().getWidth();
//        String hitCounteString;
//        hitCounteString = Integer.toString(this.hitCounter); // changing the number of hit from int to string.
//        if (this.hitCounter == 2) {
//            surface.setColor(this.color);
//        } else if (this.hitCounter < 2) {
//            surface.setColor(this.color.darker());
//        } else {
//            surface.setColor(this.color.darker());
//        }
////        surface.setColor(this.color); // change the color of the surface , rectangle lines(boundries).
//        surface.fillRectangle((int) x, (int) y, (int) width, (int) height);
//        surface.setColor(Color.black);
//        surface.drawRectangle((int) x, (int) y, (int) width, (int) height);
//        surface.setColor(Color.black);
//        //if the ball hit the block amount of time that made the hit counter to be under zero or equall
//        // marked with nothing.
//        if (this.hitCounter <= 0) {
//            surface.drawText((int) (this.recangle.getUpperLeft().getX()),
//                    (int) (this.recangle.getUpperLeft().getY()), "", 1);
//        } else { // else marked the new hit counter.
//            surface.setColor(Color.black);
//            surface.drawText((int) (this.recangle.getUpperLeft().getX() - 5 + this.recangle.getWidth() / 2),
//                    (int) (this.recangle.getUpperLeft().getY() + 5 + this.recangle.getHeight() / 2),
//                    "", 15);
//        }
//
//        if (this.hasText) {
//            settext(surface, this.text);
//        }


        Point upperLeft = this.recangle.getUpperLeft();
        double width = this.recangle.getWidth();
        double height = this.recangle.getHeight();
        if (mapImages == null && mapColors == null) {

            //set the color of the block
            surface.setColor(this.color);
            surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
            return;
        }
        if (mapImages.containsKey(this.hitCounter - 1)) {
            Image image = this.mapImages.get(this.hitCounter - 1);
            surface.drawImage((int) this.recangle.getUpperLeft().getX(),
                    (int) this.recangle.getUpperLeft().getY(), image);
        } else if (mapColors.containsKey(this.hitCounter - 1)) {
            surface.setColor(mapColors.get(this.hitCounter - 1));
            surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
        }

        if ((this.stroke != null)) {
            surface.setColor(this.stroke);
            surface.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
        }
        //check if count of the hits is under zero:
        if (this.hitCounter <= 0) {
            return;
        }
        surface.setColor(Color.white);


    }


    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
    }

    /**
     * the method add the block to the game.
     *
     * @param g - is the game.
     */
    public void addToGame(GameLevel g) {
        g.getSprites().addSprite(this);
        g.getEnvironment().addCollidable(this);
    }

    /**
     * removeFromGame - remove this block from the collidable and sprite list.
     *
     * @param game - the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * addHitListener - adding a listner to the list.
     *
     * @param hl - the listner.
     */
    public void addHitListener(HitListener hl) {
        List<HitListener> hitListeners1 = getHitListeners();
        hitListeners1.add(hl);
        this.setHitListeners(hitListeners1);

    }

    /**
     * removeHitListener - remove a listner from the list.
     *
     * @param hl - the listner.
     */
    public void removeHitListener(HitListener hl) {
        List<HitListener> hitListeners1 = getHitListeners();
        hitListeners1.remove(hl);
        this.setHitListeners(hitListeners1);
    }

    /**
     * notifyHit - a loop that run withe all the correct listener and notifi them
     * that was happen somthing that they nead to react to him.
     *
     * @param hitter - the ball that hit.
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * getHitPoints - getter.
     *
     * @return - hitCounter.
     */
    public int getHitPoints() {
        return hitCounter;
    }

    /**
     * getHitListeners - getter.
     *
     * @return - hitListeners list.
     */
    public List<HitListener> getHitListeners() {
        return hitListeners;
    }

    /**
     * setHitListeners - setter.
     *
     * @param hitListeners1 - hitListeners list.
     */
    public void setHitListeners(List<HitListener> hitListeners1) {
        this.hitListeners = hitListeners1;
    }

    /**
     * getRecangle - getter.
     *
     * @return rectangle. recangle
     */
    public Rectangle getRecangle() {
        return recangle;
    }

    /**
     * set the stroke.
     *
     * @param stroke1 color.
     */
    public void setStroke(Color stroke1) {
        this.stroke = stroke1;
    }
}

