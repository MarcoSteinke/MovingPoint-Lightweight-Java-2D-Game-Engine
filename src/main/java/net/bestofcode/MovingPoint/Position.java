package net.bestofcode.MovingPoint;

/** net.bestofcode.MovingPointGameEngine.Position
 *  The position class describes a location of an object inside of the canvas.
 *  Therefore it stores two double values which represent the x- and y-coordinates
 */
public class Position{
    public double x, y;

    /** net.bestofcode.MovingPointGameEngine.Position
     * 
     *  @param x - x-Coordinate
     *  @param y - y-Coordinate
     */
    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }

    /** print
     *  Prints the position to the Terminal in a readable way.
     */
    public void print(){
        System.out.println("x = " + this.x + ", y = " + this.y);
    }
}