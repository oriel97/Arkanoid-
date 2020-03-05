/**
 * class that hold the following information: collision Point and collision Object.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor that create the CollisionInfo.
     *
     * @param collisionPoint1  is a Point- the collision Point.
     * @param collisionObject1 is an object- collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint1, Collidable collisionObject1) {
        this.collisionPoint = collisionPoint1;
        this.collisionObject = collisionObject1;
    }

    /**
     * method that return the point at which the collision occurs.
     *
     * @return Point-the collision Point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the method return the the collidable object involved in the collision.
     *
     * @return collidable- the collidable object.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}