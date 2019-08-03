/***********************************************
 * Implementation: an example program about the
 * implementation of the MovingPoint.
 * 
 * This is example (1), which topics the first
 * steps.
 */


public class Implementation extends MovingPoint{

    public static void main(String[] args) {
        // instantiate the MovingPoint, it will instantiate its own JFrame, Draw, DrawListener, MouseListener, KeyListener
        MovingPoint g = new MovingPoint(); 

        // now you can use the methods of the MovingPoint such as setSpawn()
        g.setSpawn(0.5, 0.5);

        // or change the MovingPoint's speed
        g.setSpeed(1); 

        // if you want to draw from this subclass you will have to instantiate the DrawListener from g via getDraw()
        Draw d = g.getDraw(); 
        
        // your program has to be written in this loop after all settings are done.
        while(true){
            // this will refresh your screen
            g.move(); 
            // make your program sleep 50ms => 20 frames per second
            g.sleep(50); 
        }
    }
}