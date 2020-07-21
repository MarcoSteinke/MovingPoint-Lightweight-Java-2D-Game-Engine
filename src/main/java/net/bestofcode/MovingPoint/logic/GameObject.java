package net.bestofcode.MovingPoint.logic;

import net.bestofcode.MovingPoint.annotations.Refactor;
import net.bestofcode.MovingPoint.math.Position;
import net.bestofcode.MovingPoint.render.GraphicalComponent;
import net.bestofcode.MovingPoint.render.IGraphicalComponent;

import java.time.LocalTime;

/************************************************
 *
 * net.bestofcode.MovingPointGameEngine.GameObject defines an object which can be used to
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
    public String hashCode;

    /**
     * Constructor 1
     * <p>
     * This constructor uses the two coordinates of
     * the given entity and also uses an entity e
     * which will be the drawObject.
     *
     * @param x - the entities' x - coordinate
     * @param y - the entities' y - coordinate
     * @param graphicalComponent - the kind of entity to be stored in the LinkedList.
     */

    public GameObject(double x, double y, IGraphicalComponent graphicalComponent) {
        this.position = new Position(x, y);
        this.drawObject = graphicalComponent;

        this.hashCode = new SHA1Hash("" + this.hashCode()).getEncryptedInput();
    }

    /**
     * Constructor 2
     * <p>
     * This constructor uses the two coordinates of
     * the given entity.
     *
     * @param x - the entities' x - coordinate
     * @param y - the entities' y - coordinate
     */

    public GameObject(double x, double y) {
        this.position.x = x;
        this.position.y = y;
    }

    /**
     * Constructor 3
     * runs without any arguments
     */

    public GameObject() {
        this.position = new Position(0, 0);
    }

    /**
     * draw()
     * <p>
     * The draw-method is used to animate/display/draw the entity at its
     * location inside of the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine-frame. This function MUST be
     * implemented in all subclasses since
     *
     * @param drawObject - reference to the object, which will deliver a
     *                   draw-method for the given entity. It must be a subclass and must
     *                   implement the draw()-function. Otherwise the entities can not be
     *                   spawned/displayed in the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine library.
     */

    public String getTexture() {
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