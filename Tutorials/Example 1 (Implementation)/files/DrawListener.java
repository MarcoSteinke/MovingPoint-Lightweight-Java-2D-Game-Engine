/******************************************************************************
 *  Compilation:  javac DrawListener.java
 *  Execution:    none
 *  Dependencies: none
 *
 *  Interface that accompanies Draw.java.
 ******************************************************************************/

public interface DrawListener {

    /**
     * Invoked when the mouse has been pressed.
     *
     * @param x the x-coordinate of the mouse
     * @param y the y-coordinate of the mouse
     */
    void mousePressed(double x, double y);

    /**
     * Invoked when the mouse has been dragged.
     *
     * @param x the x-coordinate of the mouse
     * @param y the y-coordinate of the mouse
     */
    void mouseDragged(double x, double y);

    /**
     * Invoked when the mouse has been released.
     *
     * @param x the x-coordinate of the mouse
     * @param y the y-coordinate of the mouse
     */
    void mouseReleased(double x, double y);

    /**
     * Invoked when a key has been typed.
     *
     * @param c the character typed
     */
    void keyTyped(char c);

    /**
     * Invoked when a key has been pressed.
     *
     * @param keycode the key combination pressed
     */
    void keyPressed(int keycode);

    /**
     * Invoked when a key has been released.
     *
     * @param keycode the key combination released
     */
    void keyReleased(int keycode);
}