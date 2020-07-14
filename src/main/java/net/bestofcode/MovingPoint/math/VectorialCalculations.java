package net.bestofcode.MovingPoint.math;

public class VectorialCalculations {

    /**
     * distanceTo()
     *
     * This method returns the function of the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine to a certain location.
     * May be useful to create collisions or pathfinding (TODO)
     *
     * @param position - net.bestofcode.MovingPointGameEngine.Position object which includes the object's position
     * @return
     */

    public static double distanceTo(Position position, Position anotherPosition) {

        double x = anotherPosition.x - position.x;
        double y = anotherPosition.y - position.y;

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
