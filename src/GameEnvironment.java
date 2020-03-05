import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment class.
 */
public class GameEnvironment {
    private List<Collidable> collidableList;
    private static final int BIG_NUMBER = 10000000;
    // add the given collidable to the environment.

    /**
     * constructor that create the collidableList.
     *
     * @param collidableList is "List<Collidable>".
     */
    public GameEnvironment(List<Collidable> collidableList) {
        this.collidableList = collidableList;
    }

    /**
     * GameEnvironment.
     * make a new List.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }

    /**
     * Return the collidableList.
     * e
     *
     * @return List<Collidable> the collidableList.
     */
    public List<Collidable> getList() {
        return this.collidableList;
    }

    /**
     * addCollidable - adding collidable to the list of Collidables.
     *
     * @param c the Collidable
     */
    public void addCollidable(Collidable c) {
        collidableList.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory - the line that in it area we check if there is a colidable.
     * @return - CollisionInfo(min, object) - the information of the collision point - which object and point.
     */

    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidableList.isEmpty()) { // if there is no blocks at all.
            return null;
        }
        // defin list of point and list of Collidable.
        List<Point> intresect = new ArrayList<>();
        List<Collidable> objectList = new ArrayList<>();
        int i;
        // a loop tha run on all the collidable list.
        // if there a collidable in the area of the line add the  points of the rectangle to the line and
        // add the object to the col list.
        for (i = 0; i < collidableList.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(this.collidableList.get(i).
                    getCollisionRectangle()) != null) {
                intresect.add(trajectory.
                        closestIntersectionToStartOfLine(this.collidableList.get(i).getCollisionRectangle()));
                objectList.add(this.collidableList.get(i));
            }
        }
        // if there is no intresect.
        if (intresect.size() == 0) {
            return null;
        }

        double minDistance = BIG_NUMBER;
        Point min = null;
        Collidable object = null;
        // a loop that find the closest point from all the point in the intresect list, and which object is it.
        for (i = 0; i < intresect.size(); i++) {
            if (minDistance >= trajectory.start().distance(intresect.get(i))) {
                minDistance = trajectory.start().distance(intresect.get(i));
                object = objectList.get(i);
                min = intresect.get(i);
            }
        }
        // rerturn the information of the collids(point, object).
        return new CollisionInfo(min, object);
    }
}