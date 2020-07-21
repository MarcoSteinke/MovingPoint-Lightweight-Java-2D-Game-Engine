package net.bestofcode.MovingPoint.player;

import net.bestofcode.MovingPoint.logic.GameObject;
import net.bestofcode.MovingPoint.math.Position;

public class Player {

    Position position;

    /**
     * distanceTo()
     * <p>
     * This method returns the function of the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine to a certain location.
     * May be useful to create collisions or pathfinding (TODO)
     *
     * @param position - net.bestofcode.MovingPointGameEngine.Position object which includes the object's position
     * @return
     */

    public double distanceTo(Position anotherPosition) {

        double x = anotherPosition.x - this.position.x;
        double y = anotherPosition.y - this.position.y;

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double distanceTo(GameObject gameObject) {

        double x = this.position.x - gameObject.position.x;
        double y = this.position.y - gameObject.position.y;

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
