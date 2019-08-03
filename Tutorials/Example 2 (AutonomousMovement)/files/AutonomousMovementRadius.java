/******************************************************
 *  AutonomousMovementRadius - Example (2.2) - first AI ?
 * 
 *  (This example shows how NPC's in games/RPG's handle
 *   their random movement in a certain zone.)
 * 
 *  In this example you will see, how you can make your
 *  MovingPoint move at random directions
 */

public class AutonomousMovementRadius extends MovingPoint{

    /** moveRandom()
     * 
     *  moveRandom is able to generate "random" movement as long as
     *  the MovingPoint does not leave the DrawPanel. 
     * 
     *  A random number between 1 and 3 will be generated and determines
     *  which movement the MovingPoint has to complete next.
     *  In addition this movement will be completed up to 5 times, but
     *  also this amount of repetitions of the random movement will be
     *  random.
     * 
     * @param g - The MovingPoint which shall complete the random movement
     */

    public static void moveRandom(MovingPoint g){
        // there are 3 possible keys to send to the program!
        int rd = (int) (1 + Math.random() * 3);
        int key = 0;
        if     ( rd == 1 ) key = 87;
        else if( rd == 2 ) key = 65;
        else if( rd == 3 ) key = 68;
    
        for(int i = 0; i < (int) (Math.random() * 5); i++)
            g.keyPressed(key);
        
        g.sleep(50);
    }

    /** vectorFlip()
     * 
     *  vectorFlip()'s functionality is to rotate the directional vector
     *  by 180 degress. It is done via simple skalar multiplication
     * 
     * @param g - the MovingPoint, which's directional 
     *            vector will be flipped
     */

    public static void vectorFlip(MovingPoint g){
        g.vec2D[0] *= -1;
        g.vec2D[1] *= -1;
        System.out.println("Flip");
    }

    public static void main(String[] args) {
        MovingPoint g = new MovingPoint(); 
        
        // reads first argument from the console to
        // determine the allowed radius for random
        // movement.
        double R = Double.parseDouble(args[0]);

        g.setSpawn(0, 0);
        g.setSpeed(1);

        // create the double-array which will include the location
        // the distanceTo() function
        double[] p = {0, 0};

        while(true){
            if((Math.abs(g.loc[0] + g.vec2D[0]) >= 0.9) || // Case 1: x gets too small/large
               (Math.abs(g.loc[1] + g.vec2D[1]) >= 0.9) || // Case 2: y gets too small/large
                g.distanceTo(p) > R) vectorFlip(g);        // Case 3: distance to the origin gets too large
            // make some random steps
            moveRandom(g);
            // print distance to the origin
            System.out.println(g.distanceTo(p));
            g.move();
        }
    }
}