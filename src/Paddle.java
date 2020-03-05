import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Paddle class.
 */
public class Paddle implements Sprite, Collidable {
    //fields
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private Color color;
    private Point leftBorder;
    private Point rightBorder;
    private int speed;


    /**
     * Paddle - constructor.
     *
     * @param speed1      - the paddle speed.
     * @param paddle1     - the paddle.
     * @param leftBorder  - the left border of the board
     * @param rightBorder - the right border of the board
     * @param keyboard    - the keyboard sensor.
     */
    public Paddle(Rectangle paddle1, Point leftBorder, Point rightBorder, biuoop.KeyboardSensor keyboard, int speed1) {
        this.paddle = paddle1;
        this.color = Color.orange;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.keyboard = keyboard;
        this.speed = speed1;

    }

    /**
     * setKeyboard.
     * setter.
     *
     * @param gui - gui.
     */
    public void setKeyboard(GUI gui) {
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * getRightborder.
     * getter.
     *
     * @return the right border.
     */
    public Point getRightborder() {
        return this.rightBorder;
    }

    /**
     * getLeftBorder.
     * getter.
     *
     * @return the left border.
     */
    public Point getLeftBorder() {
        return this.leftBorder;
    }

    /**
     * setColor.
     * setter.
     *
     * @param color1 - the color from the game.
     */
    public void setColor(java.awt.Color color1) {
        this.color = color1;
    }

    /**
     * getColor.
     * getter.
     *
     * @return the color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * moveLeft.
     * move the paddle to the left.
     */
    public void moveLeft() {
        // check if we move the paddle to the  left the paddle go out from the boundaries of the frame.
        Point upLeft = this.paddle.getUpperLeft();
        if (this.paddle.getUpperLeft().getX() - this.getSpeed() < this.leftBorder.getX()) {
            // paste the paddle to the left bored.
            this.paddle.setUpperLeft(new Point(this.leftBorder.getX(), this.leftBorder.getY()));
        } else {
            // else move the padlle to the left.
            this.paddle.setUpperLeft(new Point(this.paddle.getUpperLeft().getX()
                    - this.getSpeed(), this.paddle.getUpperLeft().getY()));
        }
    }

    /**
     * moveRight.
     * move the paddle to the right.
     */
    public void moveRight() {
        // check if we move the paddle to the  right the paddle go out from the boundaries of the frame.
        if (this.paddle.getUpperLeft().getX() + this.getSpeed() > this.rightBorder.getX()) {
            // paste the paddle to the right bored.
            this.paddle.setUpperLeft(new Point(this.rightBorder.getX(), this.rightBorder.getY()));
        } else {
            // else move the paddle to the right.
            this.paddle.setUpperLeft(new Point(this.paddle.getUpperLeft().getX()
                    + this.getSpeed(), this.paddle.getUpperLeft().getY()));
        }
    }

    /**
     * timePassed.
     * check what the user pressed.
     */
    // Sprite
    public void timePassed() {
        // if the user press left call the moveLeft method.
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        // if the user press left call the moveRight method.
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * drawOn.
     * draw the paddle in orange and the bounds with black.
     *
     * @param surface - the surface.
     */
    public void drawOn(DrawSurface surface) {
        double x = this.paddle.getUpperLeft().getX();
        double y = this.paddle.getUpperLeft().getY();
        double height = this.paddle.getHeight();
        double width = this.paddle.getWidth();
        surface.setColor(this.getColor());
        surface.fillRectangle((int) x, (int) y, (int) width, (int) height);
        surface.setColor(Color.black);
        surface.drawRectangle((int) x, (int) y, (int) width, (int) height);
    }

    /**
     * getCollisionRectangle.
     *
     * @return the paddle.
     */
    // Collidable
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * hit.
     * change the velocity of the ball dependets where the ball hit.
     *
     * @param hitter          - the ball that hit.
     * @param collisionPoint  - the collision point.
     * @param currentVelocity - the current velocity.
     * @return the new velocity.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        Random random = new Random();
        this.color = new Color(random.nextInt(0xFFFFFF));
        double sizeOfPart = this.paddle.getWidth() / 5;
        Velocity newVelocity = currentVelocity;
        if (this.getCollisionRectangle().upLine()
                .checkOnTheLine(this.getCollisionRectangle().upLine(), collisionPoint)) {
            // if the x of the collisionPoint is between the uper left x and the uper left x +30.
            if ((collisionPoint.getX() >= this.paddle.getUpperLeft().getX())
                    && (collisionPoint.getX() < this.paddle.getUpperLeft().getX()
                    + sizeOfPart)) {
                //change the velocity with the angle to speed func with angle 300.
                newVelocity = Velocity.fromAngleAndSpeed(
                        300, Math.sqrt((newVelocity.getDx()) * (newVelocity.getDx())
                                + (newVelocity.getDy()) * (newVelocity.getDy())));
                // if the x of the collisionPoint is between the uper left x+30 and the uper left x +60.
            } else if ((collisionPoint.getX()
                    + sizeOfPart >= this.paddle.getUpperLeft().getX()) && (collisionPoint.getX()
                    < this.paddle.getUpperLeft()
                    .getX()
                    + sizeOfPart * 2)) {
                //change the velocity with the angle to speed func with angle 330.
                newVelocity = Velocity.fromAngleAndSpeed(
                        330, Math.sqrt((newVelocity.getDx()) * (newVelocity.getDx())
                                + (newVelocity.getDy()) * (newVelocity.getDy())));
                // if the x of the collisionPoint is between the uper left x+60 and the uper left x +90.
            } else if ((collisionPoint.getX()
                    + sizeOfPart * 2 >= this.paddle.getUpperLeft().getX()) && (collisionPoint.getX()
                    < this.paddle.getUpperLeft()
                    .getX()
                    + sizeOfPart * 3)) {
                // change the velocity as normal- (dy-> -dy).
                newVelocity = new Velocity(currentVelocity.getDx(), ((-1) * (currentVelocity.getDy())));
                // if the x of the collisionPoint is between the uper left x+90 and the uper left x +120.
            } else if ((collisionPoint.getX()
                    + sizeOfPart * 3 >= this.paddle.getUpperLeft().getX()) && (collisionPoint.getX()
                    < this.paddle.getUpperLeft()
                    .getX()
                    + sizeOfPart * 4)) {
                //change the velocity with the angle to speed func with angle 30.
                newVelocity = Velocity.fromAngleAndSpeed(
                        30, Math.sqrt((newVelocity.getDx()) * (newVelocity.getDx())
                                + (newVelocity.getDy()) * (newVelocity.getDy())));
                // if the x of the collisionPoint is between the uper left x+120 and the uper left x +150.
            } else if ((collisionPoint.getX()
                    + sizeOfPart * 4 >= this.paddle.getUpperLeft().getX()) && (collisionPoint.getX()
                    < this.paddle.getUpperLeft()
                    .getX()
                    + sizeOfPart * 5)) {
                //change the velocity with the angle to speed func with angle 30.
                newVelocity = Velocity.fromAngleAndSpeed(
                        60, Math.sqrt((newVelocity.getDx()) * (newVelocity.getDx())
                                + (newVelocity.getDy()) * (newVelocity.getDy())));
            }
            // if the ball come from the top change the ball dy velocity to - dy.
            if (newVelocity.getDy() > 0) {
                newVelocity = new Velocity(newVelocity.getDx(), -1 * newVelocity.getDy());
            }

        }

        // return the new created velocity.
        return newVelocity;
    }


    /**
     * Add this paddle to the game.
     *
     * @param g - is the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * getSpeed - getter.
     *
     * @return speed;
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * getPaddle - getter.
     *
     * @return paddle.
     */
    public Rectangle getPaddle() {
        return paddle;
    }
}