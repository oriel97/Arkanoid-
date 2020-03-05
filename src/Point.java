/**
 * point class.
 */
public class Point {

    private double x;
    private double y;

    /**
     * point - constructor
     * creact a point with x and y.
     *
     * @param x - x
     * @param y - y
     */
    // constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance-the method find the ditance from to point.
     *
     * @param other - get in with point called other who have x,y
     * @return distance - the distance between to point.
     */
    public double distance(Point other) {
        double distance;
        double x1 = other.getX();
        double y1 = other.getY();
        distance = Math.sqrt((this.y - y1) * (this.y - y1)
                + (this.x - x1) * (this.x - x1));
        return distance;
    }

    /**
     * equals - a method that check if to point are eqales.
     *
     * @param other - a point. check withe this point.
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        boolean check2;
        //if the x and the y of point are equals (means they are the same)
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            check2 = true;
        } else {
            check2 = false;
        }
        return check2;
    }

    /**
     * // Return the x value of this point.
     *
     * @return x
     */
    // Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * getY.
     * getter.
     * Return the y value of this point.
     *
     * @return y
     */
    public double getY() {
        return this.y;
    }

    /**
     * setX.
     * setter.
     *
     * @param x1 - the x.
     */
    public void setX(double x1) {
        this.x = x1;
    }

    /**
     * setY.
     * setter.
     *
     * @param y1 - the y.
     */
    public void setY(double y1) {
        this.y = y1;
    }
}
