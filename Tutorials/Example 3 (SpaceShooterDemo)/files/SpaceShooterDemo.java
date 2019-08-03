/******************************************************
 *  SpaceShooterDemo - Example (3.1) - first Game ?
 * 
 * 
 *  (1) Learn how to replace the booooring black 
 *   filledCircle with any image you want.
 * 
 *  (2) As you can see from line 26 to 42... you can also
 *  create your own controls, using a function like
 *  checkKeyPressed and the draw's isKeyPressed()
 *  function.
 * 
 *  (3) Also learn how to apply sounds to your game / simulation
 *  using Princeton's StdAudio library
 * 
 * 
 *  copyright information: space.png is created by myself
 *                         spaceship.png is taken from:
 * 
 *                         <a href="https://image.shutterstock.com/image-vector/rocket-set-vector-technology-ship-450w-428341312.jpg">
 *                         
 *                         engine.wav is taken from:
 *                         <a href="http://www.netshark.us/valve/sound/ambience/rocket_steam1.wav">
 */


public class SpaceShooterDemo extends MovingPoint{

    /* This function is used to override the old controls and create
       your own controls


       for a better understanding, I replaced the keycodes with their character.
       This should give a quick overview of how to add more keys to your controls*/
    public static int W = 87;
    public static int A = 65;
    public static int S = 83;
    public static int D = 68;

    public static void checkKeyPressed(Draw d, MovingPoint spaceShip){
        // events for "W" and "S" optional, toggle them by removing comment frame:
        if      (d.isKeyPressed(W)) /*spaceShip.loc[1] += 0.1*/;
        else if (d.isKeyPressed(S)) /*spaceShip.loc[1] -= 0.1*/;
        else if (d.isKeyPressed(A)) spaceShip.loc[0] -= 0.1;
        else if (d.isKeyPressed(D)) spaceShip.loc[0] += 0.1;
    }


    public static void main(String[] args) {

        /* this is the default setup known from previous
           examples
        */
        MovingPoint spaceShip = new MovingPoint();
        Draw d = spaceShip.getDraw();

        /* instantiate the laser */
        Laser l = new Laser(spaceShip.loc[0], spaceShip.loc[1] + 0.05);

        /* toggle this on "true" to activate the optional
           mouse-controls */
        spaceShip.drawAtMouse = true;

        /* This boolean deactivates the (red) information-panel
           which is shown on hover */
        spaceShip.showHover = false;

        

        /* set the picture displayed at the MovingPoint's
           location and also the background-image:
         */

        spaceShip.imgFile = "spaceship.png";
        spaceShip.backgroundFile = "space.png";

        // set spawn of spaceShip to the bottom of the screen:
        spaceShip.setSpawn(0, -0.6);



        /* The next section will show you how to disable the
           default-control-system.
        */
        
        /* (1) remove the directional vector by setting its length
               to 0 */
        double[] vec = {0, 0};
        spaceShip.vec2D = vec;

        /* (2) set the speed of the MovingPoint to 0 to fully
           deactivate the default controls. 
        */
        spaceShip.setSpeed(0);

        // main loop, as seen in previous examples
        while(true){

            /* if the MovingPoint is in bounds, call your
               homebrew control-system */
            checkKeyPressed(d, spaceShip);

            /* the next two lines are also part of the default-loop */
            spaceShip.move();
            
            l.shoot(d, spaceShip);
            spaceShip.sleep(1);
        }
    }
}