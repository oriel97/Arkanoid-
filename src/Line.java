
import java.util.List;

/**
 * the class create line with 2 points.
 */
public class Line {
    private Point start;
    private Point end;


    /**
     * constructors that build a line by 2 points.
     *
     * @param start is Point,the start of the line
     * @param end   is Point,the end of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructors that build a line by 4 values of x and y.
     *
     * @param x1 is double ,the x value of the start point.
     * @param y1 is double ,the y value of the start point.
     * @param x2 is double ,the x value of the end point.
     * @param y2 is double ,the y value of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculate the length of the line.
     *
     * @return double, the length of the line
     */
    public double length() {
        return (this.start.distance(end));
    }

    /**
     * check what is the middle point od the line.
     *
     * @return Point, the middle point of the line
     */
    public Point middle() {
        double newX = ((this.start.getX() + this.end.getX()) / 2);
        double newY = ((this.start.getY() + this.end.getY()) / 2);
        Point middlePoint = new Point(newX, newY);
        return middlePoint;
    }

    /**
     * the function return the start point of the line.
     *
     * @return point, the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * the function return the end point of the line.
     *
     * @return point, the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * we check if we have Intersecting point between the 2 lines.
     *
     * @param other is a line that we check if we have Intersecting point
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //check by "intersectionWith" if we have intersection point,if we have return true else-false
        return (this.intersectionWith(other) != null);
    }

    /**
     * check if we have intersection point,if we have check what is the point.
     *
     * @param other is Line, the line that we check if we have intersection point with.
     * @return Returns the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        //flag that tell us if the point in the line
        int checkPointIn = 0;
        double x1This = this.start.getX();
        double y1This = this.start.getY();
        double x2This = this.end.getX();
        double y2This = this.end.getY();
        double x1Other = other.start.getX();
        double y1Other = other.start.getY();
        double x2Other = other.end.getX();
        double y2Other = other.end.getY();
        //check if we got points with values.
        if (other.end == null || other.start == null) {
            return null;
        }
        //if both of the line dont have a slope:
        if ((this.start.getX() == this.end.getX()) && (other.end.getX() == other.start.getX())) {
            return null;
        }
        //if this line dont have slope and the other line have:
        if ((x1This == x2This) && (x2Other != x1Other)) {
            double slopeOther = ((y2Other - y1Other) / (x2Other - x1Other));
            double bEquationOther = y1Other - slopeOther * (x1Other);
            double intersectingY = slopeOther * x1This + bEquationOther;

            //check if the intersection point is in the lines by check if it is between the x's
            if (((x1This <= x2Other) && (x1This >= x1Other)) || ((x1This <= x1Other) && (x1This >= x2Other))) {
                //and if its between the y's
                if ((intersectingY >= y1This && intersectingY <= y2This)
                        || (intersectingY <= y1This && intersectingY >= y2This)) {
                    //if it is-creat the intersecting Point.
                    Point intersectingPoint = new Point(x1This, intersectingY);
                    return intersectingPoint;
                }
            } else {
                //if it isnt between the line return null.
                return null;
            }
        }

        //if other line dont have slope and the this line have:
        if ((x1This != x2This) && (x2Other == x1Other)) {

            double slopeThis = ((y2This - y1This) / (x2This - x1This));
            //calculate the 'b' of y=mx+b Equation
            double bEquationThis = y1This - (slopeThis * (x1This));
            //calculae the y value of the intresction point
            double intersectingY = slopeThis * x1Other + bEquationThis;

            //check if the intersection point is in the lines by check if it is between the x's
            if (((x1Other <= x2This) && (x1Other >= x1This)) || ((x1Other <= x1This) && (x1Other >= x2This))) {
                //and if its between the y's
                if ((intersectingY >= y1Other && intersectingY <= y2Other)
                        || (intersectingY <= y1Other && intersectingY >= y2Other)) {
                    //if it is-creat the intersecting Point.
                    Point intersectingPoint = new Point(x1Other, intersectingY);
                    return intersectingPoint;
                }
            } else {
                //if it isnt between the line return null.
                return null;
            }
        }

        double slopeOther = ((y2Other - y1Other) / (x2Other - x1Other));
        double slopeThis = ((y2This - y1This) / (x2This - x1This));
        //if the lines have the same slopes:
        if (slopeOther == slopeThis) {
            return null;
        }

        //calculate the 'b' of y=mx+b Equation
        double bEquationOther = y1Other - (slopeOther * (other.start().getX()));
        double bEquationThis = y1This - slopeThis * (this.start().getX());
        //calculate the x value of the intresection point.
        double intersectingX = ((bEquationOther - bEquationThis) / (slopeThis - slopeOther));

        //check if the value of x is in the line of this
        if ((intersectingX <= x2This) && (intersectingX >= x1This)) {
            checkPointIn++;
        } else if ((intersectingX <= x1This) && (intersectingX >= x2This)) {
            checkPointIn++;
        }

        //check if the value of x is in the line of other
        if ((intersectingX <= x2Other) && (intersectingX >= x1Other)) {
            checkPointIn++;
        } else if ((intersectingX <= x1Other) && (intersectingX >= x2Other)) {
            checkPointIn++;
        }
        //calculate the value of the 'y' in the intersecting point.
        double intersectingY = slopeOther * intersectingX + bEquationOther;

        //if the intersecting point is between the 2 lines:
        if (checkPointIn == 2) {
            //create intersecting point.
            Point intersectingPoint = new Point(intersectingX, intersectingY);
            //return the point
            return intersectingPoint;
        } else {
            //if the point is not between the lines ,return null
            return null;
        }
    }

    /**
     * check if the lines are equals.
     *
     * @param other is a line that we check if it the same line.
     * @return true if its the same line,false if its not.
     */
    public boolean equals(Line other) {
        //flag that tell us if it is the same line.
        int checkEqu = 0;
        /*
        check if all the x,y values of the start/end in 'this' line are the same like start/end 'other' line(in order)
        or check if all the x,y values of the
        start/end in 'this' line are the same like end/start 'other' line(in order)
        */
        if (((this.start.getX() == other.start.getX()) && (this.start.getY() == other.start.getY()))
                && ((this.end.getY() == other.end.getY()) && (this.end.getX() == other.end.getX()))) {
            checkEqu++;
        } else if (((this.start.getX() == other.end.getX()) && (this.start.getY() == other.end.getY()))
                && ((this.end.getY() == other.start.getY()) && (this.end.getX() == other.start.getX()))) {
            checkEqu++;
        }
        // if "checkEqu" is 1 is the same line and return true,else return false.
        return (checkEqu == 1);
    }


    /**
     * the method check if we have intersection point with the rectangle.
     * start of the line.
     *
     * @param rect the rectangle that we check intersection point with.
     * @return Point-if we have intersection point return the closest intersection point to the start of the line.
     * if not,return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point closestDistPoint1 = null;
        //create a least with the intersection point.
        List<Point> pointsList = rect.intersectionPoints(this);
        //use a method that help us to fint the closest.
        closestDistPoint1 = closestDistPoint(pointsList);
        return closestDistPoint1;
    }

    /**
     * a method that get a list of points and check who is the closest point to the start of the line.
     *
     * @param pointsList List<Point> the list of the points.
     * @return the closest point.if the list is empty return null.
     */
    public Point closestDistPoint(List<Point> pointsList) {
        Point closestDist = null;
        double smallestDis;
        //if the list is not empty.
        if (!(pointsList.isEmpty())) {
            //put the first point in the list us the smallest distance.
            smallestDis = this.start.distance(pointsList.get(0));
            closestDist = pointsList.get(0);
            //loop that pass all over the points and check who is the closest point to the start of the line.
            for (int i = 1; i < pointsList.size(); i++) {
                //compare who is closest. and change "smallestDis" to this point.
                if (this.start.distance(pointsList.get(i)) < smallestDis) {
                    smallestDis = this.start.distance(pointsList.get(i));
                    closestDist = pointsList.get(i);
                }
            }
        } else {
            //if the list is empty.
            return null;
        }
        return closestDist;
    }

    /**
     * checkOnTheLine - check if a poind is on a line.
     *
     * @param line1  - the line that we check if a point isn on it.
     * @param point1 - the point that we check if its on the line.
     * @return - true/false if the point is on the line.
     */
    public boolean checkOnTheLine(Line line1, Point point1) {
        //define varible
        boolean check1;
        double x1 = point1.getX();
        double y1 = point1.getY();
        //find the min and max of the start/end of a line.
        double min1x = Math.min(line1.start().getX(), line1.end().getX());
        double min1y = Math.min(line1.start().getY(), line1.end().getY());
        double max1x = Math.max(line1.start().getX(), line1.end().getX());
        double max1y = Math.max(line1.start().getY(), line1.end().getY());
        //check if a point is on the line by checking the limit of point by x,y.
        if ((min1x <= x1 && max1x >= x1) && (min1y <= y1 && max1y >= y1)) {
            check1 = true;
        } else {
            check1 = false;
        }
        return check1;
    }
}
