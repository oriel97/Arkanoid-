/**
 * Collidable class.
 */
public interface Collidable {

    /**
     * getCollisionRectangle.
     * Return the "collision shape" of the object.
     *
     * @return the rectangel that the ball hited.
     */

    Rectangle getCollisionRectangle();

    /**
     * hit.
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          - the ball that hit.
     * @param collisionPoint  - the collision point.
     * @param currentVelocity - the current velocity.
     * @return the new velocity.
     */

    Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter);
}