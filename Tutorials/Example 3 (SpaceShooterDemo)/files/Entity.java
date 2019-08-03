/************************************************
 * 
 * Entity defines an object which can be used to
 * create any type of entity, e.g. walls, enemies
 * or items.
 * 
 * @author <a href="http://steinke-it.com">Marco Steinke
 */

public class Entity{
    public double[] loc = new double[2];
    public Entity drawObject = null;
    public Draw draw;

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

    public Entity(double x,  double y, Entity e){
        this.loc[0] = x;
        this.loc[1] = y;
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

    public Entity(double x, double y){
        this.loc[0] = x;
        this.loc[1] = y;
    }

    /** Constructor 3
     *  runs without any arguments
     */

    public Entity(){
        this.loc[0] = this.loc[1];
    }

    /** draw()
     * 
     *  The draw-method is used to animate/display/draw the entity at its
     *  location inside of the MovingPoint-frame. This function MUST be 
     *  implemented in all subclasses since
     * 
     *  @param drawObject - reference to the object, which will deliver a
     *  draw-method for the given entity. It must be a subclass and must 
     *  implement the draw()-function. Otherwise the entities can not be
     *  spawned/displayed in the MovingPoint library.    
     */

    public void draw(){
        this.drawObject.draw();
    }
}