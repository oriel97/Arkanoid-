import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle class.
 */
class Rectangle {
    //fields
    private Point upperLeft;
    private double width;
    private double height;
    private static final int ARRAY_SIZE = 4;

    /**
     * constuctor.
     * Create a new rectangle with location and width/height.
     *
     * @param upperLeft - the point upper left.
     * @param width     - the width of the rec.
     * @param height    - the height of the rec.
     */
    Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * intersectionPoints.
     * Return a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line - the line that we check if he intresect with one of the rectangle bounds(lines).
     * @return the list of intersection points
     */

    public java.util.List<Point> intersectionPoints(Line line) {
        int i, j;
        // define a point list.
        List<Point> pointArrayList = new ArrayList<Point>();
        // line array in the size of 4.
        Line[] lineArray = new Line[ARRAY_SIZE];
        // define point:uper right ,down right and lefr. line: up , left , right ,down.
        Point uperRight = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        Point downRight = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY()
                + this.getHeight());
        Point downLeft = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        Line upLine = new Line(this.getUpperLeft(), uperRight);
        Line leftLine = new Line(this.getUpperLeft(), downLeft);
        Line rightLine = new Line(uperRight, downRight);
        Line downLine = new Line(downLeft, downRight);
        // array of the lines.
        lineArray[0] = upLine;
        lineArray[1] = leftLine;
        lineArray[2] = downLine;
        lineArray[3] = rightLine;
        // loop that run and check if the line intresect with each bound of the rect.
        for (i = 0; i < ARRAY_SIZE; i++) {
            if (line.isIntersecting(lineArray[i])) {
                pointArrayList.add(line.intersectionWith(lineArray[i]));
            }
        }
        // return the the list of the points.
        return pointArrayList;
    }

    /**
     * Return the width and height of the rectangle.
     * getter.
     *
     * @return the width of the rec
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getter.
     *
     * @return the height of the rec.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     * getter.
     *
     * @return the point upper left.
     */

    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * setUpperLeft.
     * setter.
     *
     * @param upperLeft1 - point upper left.
     */
    public void setUpperLeft(Point upperLeft1) {
        this.upperLeft = upperLeft1;
    }

    /**
     * a method that use the up left point and creat a point right to creat the up line.
     *
     * @return the up line.
     */
    public Line upLine() {
        Point uperRight = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        Line upLine = new Line(this.getUpperLeft(), uperRight);
        return upLine;
    }

    /**
     * a method that use the up left , down right , down left points and creat  the down line.
     *
     * @return the down line.
     */
    public Line downLine() {
        Point downRight = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY()
                + this.getHeight());
        Point downLeft = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        Line downLine = new Line(downLeft, downRight);
        return downLine;
    }

    /**
     * a method that use the down left , up left points and creat  the left line.
     *
     * @return the left line.
     */
    public Line leftLine() {
        Point downLeft = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        Line leftLine = new Line(this.getUpperLeft(), downLeft);
        return leftLine;
    }

    /**
     * a method that use the up right , down right points and creat  the right line.
     *
     * @return the right line.
     */
    public Line rightLine() {
        Point uperRight = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        Point downRight = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY()
                + this.getHeight());
        Line rightLine = new Line(uperRight, downRight);
        return rightLine;
    }

}