package net.bestofcode.MovingPoint.event;

public class MovingPointEventManager {

    /**
     * mousePressed() Called when the mouse is pressed. Same functionality as the
     * keyTyped() function according to manipulating the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine. It is useful
     * to override this function.
     *
     * @param x
     * @param y
     */

    public void mousePressed(double x, double y) {

        // instantiate a new net.bestofcode.MovingPointGameEngine.GameObject using this.getMousePosition().x, this.getMousePosition().y

        /*if (!allowGrid)
            this.addGameObject(new EntityWall(this.getMousePosition().x, this.getMousePosition().y, graphicalComponent));
        */
        // if you are using a grid you can set a statesOfAllGridCells state by clicking
        // inside of it.
    }

}
