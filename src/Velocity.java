/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    //fields
    private double dx;
    private double dy;
// constructor

    /**
     * Velocity.
     *
     * @param dx - the change of the x
     * @param dy - the change of the y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * getDx- getter.
     *
     * @return dx.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getDy - getter.
     *
     * @return dy.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @param p - a point -(center of the ball)
     * @return a new point withe othe cordinate.
     */
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p) {
        double x;
        double y;
        x = p.getX() + this.dx;
        y = p.getY() + this.dy;
        Point dPoint = new Point(x, y);
        return dPoint;
    }

    /**
     * fromAngleAndSpeed - make a new velocity withe angle and speed
     * entering this function with rhe angle and speed
     * and using sin and cos caculaiting the dx , dy - the change.
     *
     * @param angle - the angle of the ball movment
     * @param speed - the ball speed
     * @return - a new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double newAngle = Math.toRadians(angle);
        double dx = speed * Math.sin(newAngle);
        double dy = speed * Math.cos(newAngle);
        return (new Velocity(dx, dy));
    }
}