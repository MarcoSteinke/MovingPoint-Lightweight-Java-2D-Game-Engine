package net.bestofcode.MovingPoint;

import java.time.LocalTime;

/************************************************
 * 
 * net.bestofcode.MovingPoint.GameObject defines an object which can be used to
 * create any type of entity, e.g. walls, enemies
 * or items.
 * 
 * @author <a href="http://steinke-it.com">Marco Steinke
 */

@Refactor
public class GameObject {
    public Position position;
    public IGraphicalComponent drawObject;
    public GraphicalComponent graphicalComponent;

    /** Constructor 1
     * 
     * This constructor uses the two coordinates of
     * the given entity and also uses an entity e 
     * which will be the drawObject.
     * 
     * @param x - the entities' x - coordinate
     * @param y - the entities' y - coordinate
     * @param e - the kind of entity to be stored in the LinkedList.
     */

    public GameObject(double x, double y, IGraphicalComponent e){
        this.position.x = x;
        this.position.y = y;
        this.drawObject = e;
    }

    /** Constructor 2
      * 
      * This constructor uses the two coordinates of
      * the given entity.
      *
      * @param x - the entities' x - coordinate
      * @param y - the entities' y - coordinate
      */

    public GameObject(double x, double y){
        this.position.x = x;
        this.position.y = y;
    }

    /** Constructor 3
     *  runs without any arguments
     */

    public GameObject(){
        this.position = new Position(0,0);
    }

    /** draw()
     * 
     *  The draw-method is used to animate/display/draw the entity at its
     *  location inside of the net.bestofcode.MovingPoint.MovingPoint-frame. This function MUST be
     *  implemented in all subclasses since
     * 
     *  @param drawObject - reference to the object, which will deliver a
     *  draw-method for the given entity. It must be a subclass and must 
     *  implement the draw()-function. Otherwise the entities can not be
     *  spawned/displayed in the net.bestofcode.MovingPoint.MovingPoint library.
     */

    public String getTexture(){
        return this.drawObject.getFilePath();
    }

    public int hashCode() {

        int timeInNanoSeconds = (int) LocalTime.now().toNanoOfDay();
        return timeInNanoSeconds;
    }

    public Position getPosition() {
        return this.position;
    }
}