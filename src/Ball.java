import biuoop.DrawSurface;

/**
 * ball class.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment environment;
    private double upLeft;
    private double start;
    private double end;

    /**
     * constructor - Ball - creating a new ball.
     *
     * @param center - center of the ball.
     * @param r      - radius
     * @param color  - bal color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * constructor - Ball - creating a new ball.
     *
     * @param start    - the first limit
     * @param end      - the end limit
     * @param center   - center of the ball.
     * @param r        -  radius
     * @param color    - ball color
     * @param gameEnv1 - environment of the game.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnv1, double start, double end) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.environment = gameEnv1;
        this.start = start;
        this.end = end;
    }

    /**
     * getEnd.
     * getter.
     *
     * @return the end
     */

    public double getEnd() {
        return this.end;
    }

    /**
     * getStart.
     * getter.
     *
     * @return the start.
     */

    public double getStart() {
        return this.start;
    }

    /**
     * Ball - constructor - creacting new ball.
     *
     * @param x     - center x of the point(center)
     * @param y     - center y of the point(center)
     * @param r     - radius of the ball
     * @param color - color of the ball
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;

    }


    /**
     * getX.
     * accessors - getX - the x
     *
     * @return - center.getX()
     */
    public double getX() {
        return this.center.getX();
    }

    /**
     * getY.
     * accessors - getY - the y
     *
     * @return the y as int.
     */
    public double getY() {
        return this.center.getY();
    }

    /**
     * setGameEnvierment.
     * setter.
     *
     * @param game - the game envioerment.
     */
    public void setGameEnvierment(GameEnvironment game) {
        this.environment = game;
    }

    /**
     * getSize- accessors - the radius.
     *
     * @return the radius as int.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * getColor accessors - the start limit.
     *
     * @return - the color.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * drawOn - draw the ball on the given DrawSurface.
     *
     * @param surface - the surface where the ball is
     */
    public void drawOn(DrawSurface surface) {
        //save the ball definition (radius, point(x,y),color).
        //define the color
        surface.setColor(this.getColor());
        //enter this function with a ball(x,y,r) and draw by color.
        int x = (int) this.center.getX();
        int y = (int) this.center.getY();
        int radius = this.r;
        surface.fillCircle(x, y, radius);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * setVelocity.
     *
     * @param velocity - creat a set
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * setVelocity - creact a  set Velocity with other way, withe dx,dy.
     *
     * @param dx - the change of the x
     * @param dy - the change of the y
     */
    public void setVelocity(double dx, double dy) {
        //creating a new velocity with the given dx,dy
        Velocity velocity = new Velocity(dx, dy);
        this.v = velocity;
    }

    /**
     * getVelocity - geter.
     *
     * @return velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * moveOneStep-
     * the function change the center of the ball by the velocity of the ball.
     * moving te ball one strp by the  velocity of the ball if the ball dosent touch a block
     * if id does toch a block is using the hit function and change the velocity and move the ball one step.
     */


    public void moveOneStep() {
        Point predictedCenter = new Point(this.getX() + this.getVelocity().getDx(),
                this.getY() + this.getVelocity().getDy());
        Line trajectory = new Line(this.center, predictedCenter);
        CollisionInfo closest = this.environment.getClosestCollision(trajectory);
        if (closest != null) {
            this.setVelocity(closest.collisionObject().hit(closest.collisionPoint(), this.getVelocity(), this));
            Point newpredictedCenter = this.getVelocity().applyToPoint(this.center);
            trajectory = new Line(this.center, newpredictedCenter);
            closest = this.environment.getClosestCollision(trajectory);
            if (closest != null) {
                this.setVelocity(closest.collisionObject().hit(closest.collisionPoint(),
                        this.getVelocity(), this));
            }

        }

        this.center = this.getVelocity().applyToPoint(this.center);

    }


    /**
     * the method add the block to the game.
     *
     * @param g - is the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removeFromGame - remove this ball from the game.
     *
     * @param game - game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}