/******************************************************
 *  SpaceShooterDemo - Example (3.2) - Laser-Object
 * 
 *  (This example will teach you how to apply images to
 *   your project.)
 * 
 *  (1) Learn how to replace the booooring black 
 *   filledCircle with any image you want.
 * 
 *  (2) As you can see from line x to y... you can also
 *  create your own controls, using a function like
 *  checkKeyPressed and the draw's isKeyPressed()
 *  function.
 * 
 * 
 *  @param laser.wav - Soundfile used by Michael Schöttner in one of his lectures:
 *                     <a href="https://mediathek.hhu.de/playlist/359"
 *  
 */
public class Laser{
    public Entity element    = null;
    public double x, y;


    /*** Laser
     *  This is the constructor used to instantiate a Laser
     * 
     *  @param x - x-coordinate of the Laser
     *  @param y - y-coordinate of the Laser
     */
    public Laser(double x, double y){
        this.x = x;
        this.y = y;
    }

    /*** move()
     *  At this point the Laser's location will be updated.
     *  
     *  @param this.y - y-coordinate of the Laser will be increased by 0.5 every tick
     */
    public void move(){
        this.y += 0.5;
    }

    /*** shoot()
     *  A Laser will appear if the previous one already left the screen. Otherwise
     *  the current Laser will fly up until it collides or leaves the screen.
     * 
     *  @param d - Draw of the MovingPoint
     *  @param spaceShip - MovingPoint instance
     */
    public void shoot(Draw d, MovingPoint spaceShip){
        double tmp = this.y;
        if(this.y + 0.5 < 1.6){
            
            this.move();
            d.setPenColor(Draw.GREEN);
            d.line(this.x, this.y, this.x, this.y + 0.1);
            d.setPenColor(Draw.GRAY);
        } else {
            spaceShip.playSound("laser.wav");
            this.x = spaceShip.loc[0];
            this.y = spaceShip.loc[1];
        }
    }
}