/******************************************************
 *  MovingPoint: MovingPoint will be used to create AI,
 *               simulations or simple physics/games.
 * 
 * compile: javac MovingPoint.java
 * run:     java MovingPoint
 * 
 * <p>used libraries:
 * 
 *               @author Princeton University
 *               Draw
 *  
 *               @author Princeton University
 *               DrawListener
 * 
 *               @author Princeton University
 *               StdAudio
 * 
 *               @author Heinrich-Heine-University
 *               Colour
 * 
 *  <p>Visit my website <a href="http://steinke-it.com">
 *  by Marco Steinke (2019)
 * 
 * 
 * 
 ******************************************************/

import java.awt.event.MouseEvent;
import java.text.*;
import javax.swing.JFrame;

public class MovingPoint implements DrawListener{

    /**  Initialization of the instance variables
     * 
     * @param draw - instantiates a new object of the type Draw,
     * which will be used to display my algorithms in a
     * JPanel.
     * @param Draw - is implemented by @Princeton University.
     * @param vec2D - will be the directional vector of the MovingPoint instance.
     * @param loc - will be the location of the MovingPoint instance.
     * @param speed - is a constant which was obtained from tests.
     * @param multiplier - is being used to change the speed percentage.
     * @param col - defines the MovingPoint's colour.
     */

    /* The double speed is a constant which will be multiplied by another value
       for much better manipulation of the MovingPoint's speed */
    final double speed = 0.16;

    /* Instantiate a Draw(Panel) */
    private Draw draw = new Draw();

    /* This array will save the MovingPoint's coordinates */
    public double[] loc = new double[2];

    /* This array will save the components of the MovingPoint's
       directional vector */
    public double[] vec2D = {0.025*this.speed, 0.025*this.speed};

    /* As mentioned before, this multiplier can be used for a
       simplified speed-customisation */
    private double multiplier = 1;

    /* Set the MovingPoint's color */
    public Colour col = new Colour(0, 0, 0);

    /* Set the MovingPoint's spawn-location */
    public double[] origin = {0, 0};

    /* Activate/Deactivate the MovingPoint's hover-information by toggling this
       boolean */
    public boolean showHover = true;


    /* this DecimalFormat is used to reduce doubles to only two decimal
       positions */
    DecimalFormat df = new DecimalFormat("#.##");

    /* Tell the library where to spawn the MovingPoint 
       Set this value to true and your MovingPoint will be drawn
       at your cursor */
    public boolean drawAtMouse = false;

    /* a maximum of 100 entities is allowed due to calculating time */
    public LinkedList entList = new LinkedList();

    /* put in the path to the image which shall be drawn */
    public String imgFile = null;

    /* you can also set an image which shall be displayed in the background */
    public String backgroundFile = null;

    /** Constructor for the MovingPoint object
     * 
     * @param setCanvasSize - sets the size of the display to 800x800px
     * @param addListener(this) - adds the MovingPoint to the Draw(Listener)
     * @param clear() - is used to set the background-colour
     */

    public MovingPoint(){
        draw.setCanvasSize(1000, 1000);
        draw.setXscale(-1, 1);
        draw.setYscale(-1, 1);
        draw.addListener(this); // (1)
        draw.clear(draw.LIGHT_GRAY);
        draw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /** Constructor for the MovingPoint object (for multiple MovingPoints)
     * 
     * This function gives the possibility to add another MovingPoint to an
     * existing draw draw instance
     * 
     * @param addListener(this) - adds the MovingPoint to the Draw(Listener)
     */

    public MovingPoint(Draw d){
        draw.addListener(this);
    }

    /**
     * keyTyped() is not the main function, used to move the MovingPoint on the 2D-plane, but
     * it does support the keyPressed() function, which is used for a smoother keycontrol.
     * So keyPressed() is used for the controls, keyTyped() is supporting the controls and
     * will be used to add specific keybinds to the program, for example spawning new 
     * MovingPoints or manipulating the current instance of MovingPoint.
     * 
     * @param c
     */

    public void keyTyped(char c){

        draw.clear( draw.LIGHT_GRAY );
        draw.setPenColor(this.col);
        if(this.drawAtMouse == false){
            if(backgroundFile != null) draw.picture(0, 0, backgroundFile);
            if(imgFile == null)
                draw.filledCircle(this.loc[0], this.loc[1], 0.02);
            else draw.picture(this.loc[0], this.loc[1], imgFile);
        } else {
            this.loc[0] = draw.mouseX();
            this.loc[1] = draw.mouseY();
            if(backgroundFile != null) draw.picture(0, 0, backgroundFile);
            if(imgFile == null)
                draw.filledCircle(this.loc[0], this.loc[1], 0.02);
            else draw.picture(this.loc[0], this.loc[1], imgFile);
        }
        
        draw.setPenColor(draw.GRAY);
        draw.line(this.loc[0], 
                  this.loc[1] ,
                  this.loc[0]+2.5*this.vec2D[0]*(1/this.speed), 
                  this.loc[1]+2.5*this.vec2D[1]*(1/this.speed)
                 );
        if(mouseHover()) drawInfo();
        // draw entitites:
        Node current = entList.head;

        while(current != null){
            current.element.draw();
            current = current.next;
        }
    }

    /** setSpeed()
     * This function is the way to go if the user wants to change a MovingPoint's speed.
     * The default speed is set to 0.16 * m while m is set to 1 on default. For example
     * changing to m = 1.3 will set the speed to 0.16*1.3 which increases it by 30%.
     * 
     * @param m - the multiplier will be multiplied by 0.16 which is the constant
     *            of the DynObject's speed.
     */

    public void setSpeed(double m){
        this.multiplier = m;
    }

    public void draw(){
        
    }

    /*** playSound()
     *  Here you can get creative and implement amazing sound-effects, which will play
     *  a big role in simulations or games. 
     * 
     * @param filename - the path to your audio file.
     *                   Your soundfiles must have the .wav or .au format!
     */

    public void playSound(String filename){
        StdAudio.play(filename);
    }

    /*** backgroundSound()
     *  Add background music / sounds which will be playing continuously
     * 
     * @param filename - the path to your audio file.
     *                   Your soundfiles must have the .wav or .au format!
     */

    public void backgroundSound(String filename){
        StdAudio.play(filename);
    }


    /** move()
     *  This function could also be called "animate" since its only use is in the
     *  management of all drawings. This drawings are also supported by the keyTyped()
     *  function
     * 
     *  Basically this function does clear the screen and set its background to LIGHT_GRAY.
     *  Afterwards it sets the PenColor to it's instance's @param col and draws a
     *  filledCircle at @param loc of the current instance. The last step is the directional
     *  vector. This function will calculate a point at a certain distance (3.5*vec2D length)
     *  and draw a line between the @param loc of the MovingPoint and this calculated point,
     *  so we will see a pointer which shows the @param vec2D direction. 
     *  Additionally the @param picture function will be used to draw a certain picture at a
     *  position.
     * 
     */

    public void move(){
            if((Math.abs(this.loc[0] + this.vec2D[0]) < 1) || 
               (Math.abs(this.loc[1] + this.vec2D[1]) < 1)){
            draw.clear( draw.LIGHT_GRAY);
            draw.setPenColor( this.col );

            if(this.drawAtMouse == false){
                if(backgroundFile != null) draw.picture(0, 0, backgroundFile);
                if(imgFile == null)
                    draw.filledCircle(this.loc[0], this.loc[1], 0.02);
                else draw.picture(this.loc[0], this.loc[1], imgFile);
            } else {
                this.loc[0] = draw.mouseX();
                this.loc[1] = draw.mouseY();
                if(backgroundFile != null) draw.picture(0, 0, backgroundFile);
                if(imgFile == null)
                    draw.filledCircle(this.loc[0], this.loc[1], 0.02);
                else draw.picture(this.loc[0], this.loc[1], imgFile);
            }
        
            draw.setPenColor(draw.GRAY);
            draw.line(this.loc[0], 
                    this.loc[1] ,
                    this.loc[0]+2.5*this.vec2D[0]*(1/this.speed), 
                    this.loc[1]+2.5*this.vec2D[1]*(1/this.speed)
                    );

            if(mouseHover()) drawInfo();

            // draw entitites:
            Node current = entList.head;

            while(current != null){
                current.element.draw();
                current = current.next;
            }
        }
        else return;
    }

    /** distanceTo()
     * 
     *  This method returns the function of the MovingPoint to a certain entity.
     *  May be useful to create collisions or pathfinding (TODO)
     * 
     * @param e
     * @return
     */

    public double distanceTo(Entity e){
        double x = this.loc[0] - e.loc[0];
        double y = this.loc[1] - e.loc[1];

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /** distanceTo()
     * 
     *  This method returns the function of the MovingPoint to a certain location.
     *  May be useful to create collisions or pathfinding (TODO)
     * 
     * @param arr - double array of length 2, includes two-dimensional point (x, y)
     * @return
     */

    public double distanceTo(double[] arr){
        double x = this.loc[0] - arr[0];
        double y = this.loc[1] - arr[1];

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    

    /** printPosition()
     *  
     *  Use this method to print the MovingPoint's location into the console.
     * 
     */

    public void printPosition(){
        System.out.println("x = " + df.format(this.loc[0]) + 
                           " y = " + df.format(this.loc[1]));
    }

    /** vecAdd()
     * Used to add the @param vec2D components to the @param loc components.
     * Simple vector addition.
     * 
     * @param g shows the function which MovingPoint's location has to be updated.
     */

    public void vecAdd(MovingPoint g){
        g.loc[0] += g.vec2D[0]*g.multiplier;
        g.loc[1] += g.vec2D[1]*g.multiplier;
    }

    /** rotate()
     * Implemented by using basic matrix multiplication with the 2D rotation matrix.
     * A degree is given by @param deg and is used in the rotation matrix.
     * 
     * @param deg - gives the degree for the rotation
     */

    public void rotate(double deg){

        double x = this.vec2D[0] * Math.cos(deg) - this.vec2D[1] * Math.sin(deg);
        double y = this.vec2D[0] * Math.sin(deg) + this.vec2D[1] * Math.cos(deg);
        this.vec2D[0] = x;
        this.vec2D[1] = y;
    }

    /** mousePressed()
     * Called when the mouse is pressed. Same functionality as the keyTyped() function
     * according to manipulating the MovingPoint.
     * 
     * @param x
     * @param y
     */

    public void mousePressed(double x, double y){
        // instantiate a new Entity using mouseX, mouseY
        double mouseX = x;
        double mouseY = y;
        
    }

    /** addEntity()
     * 
     *  Implement entities according to the examples "Entity.java", "EntityWall.java"
     *  and add them to the JPanel by using this function
     * 
     *  Note: The entity-system is implemented by using a linked list, which can
     *        dynamically store all types of entities (subclasses) and draw them.
     * 
     * @param e - The entity which shall be added to the JPanel.
     */

    public void addEntity(Entity e){
        entList.insert(new Node(e));
    }

    /** getDraw()
     * 
     *  Return the DrawPanel of your MovingPoint to implement new drawing functions in 
     *  your subclasses of this library. This return can be caught and stored in a new
     *  Draw instance to get access to all existing drawing functions and also to the
     *  MouseListener and KeyListener, since they are connected to the DrawPanel.
     * 
     * @return
     */

    public Draw getDraw(){
        return draw;
    }

    /** mouseDragged() will be used to spawn entities.
     * 
     * @param x
     * @param y
     */

    public void mouseDragged(double x, double y){
        draw.filledCircle(x, y, 0.002);
    }

    public void mouseReleased(double x, double y){}

    public void keyReleased(int keycode){}

    /** keyPressed()
     *  The main controller for the MovingPoint. Its read the keyInput as an integer
     *  and compares it to the ASCII-values to check which control-key is pressed.
     * 
     *  87 --> W
     *  65 --> A
     *  68 --> D
     * 
     * @param keycode
     */

    public void keyPressed(int keycode){
        if      (keycode == 87) this.vecAdd(this);
        else if (keycode == 65) this.rotate(0.1);
        else if (keycode == 68) this.rotate(-0.1);
    }

    /** run()
     *  Use this function to implement the MovingPoint library on the most simple way possible.
     *  An example implementation will be given with the ExtendTest.java
     * 
     *  run() initializes the default directional vector and starts a while loop, which
     *  will draw the MovingPoint and run all required functions in the background, without
     *  implementing it in your program. If you wish to implement the function by yourself,
     *  override them or copy the code of the run() function to your program and change, what
     *  the loop does by yourself.
     * 
     *  @param move() - animate the MovingPoint
     *  @param Thread.sleep(50) - refresh the image every 50 milliseconds (20 times per sec)
     *  You will need to catch this expression
     */

    public void run(){

        setSpawn(0.5, 0.5);

        this.vec2D[0] = 0.025*this.speed;
        this.vec2D[1] = 0.025*this.speed;

        while(true){

            this.move();
            try {
                Thread.sleep(50);
            }
            catch (InterruptedException e) {
                System.out.println("Error sleeping");
            }
        }
    }

    public boolean mouseHover(){
        if(this.showHover == false) return false;
        double x = this.loc[0] - draw.mouseX();
        double y = this.loc[1] - draw.mouseY();

        double dist = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        if(dist <= 0.02) return true;
        else return false;
    }

    public void drawInfo(){
        draw.setPenColor( draw.RED );
        draw.filledRectangle(this.loc[0] - 0.15, this.loc[1] + 0.3, 0.025*9, 0.025*5);
        draw.setPenColor( draw.BLACK );
        draw.text(this.loc[0] - 0.15, this.loc[1] + 0.4, "MovingPoint");
        draw.text(this.loc[0] - 0.15, this.loc[1] + 0.35, "x = " + df.format(this.loc[0]) + " y = " + df.format(this.loc[1]));
        draw.text(this.loc[0] - 0.15, this.loc[1] + 0.3, ""+df.format(this.distanceTo(this.origin)));
        draw.text(this.loc[0] - 0.15, this.loc[1] + 0.25, "Color = " + this.col);
        draw.text(this.loc[0] - 0.15, this.loc[1] + 0.2, "Vec2D = (" + df.format(this.vec2D[0]) + ", " + df.format(this.vec2D[1]) + ")");
        
    }

    /** setSpawn()
     *  With setSpawn() you will set the starting location of your MovingPoint.
     * 
     *  @param x - double in range [0,1]
     *  @param y - double in range [0,1]
     */

    public void setSpawn(double x, double y){
        this.loc[0] = x;
        this.loc[1] = y;
    }

    /** sleep()
     * Make your program wait a certain amount of time, until it continues fetching the next task.
     * 
     * @param t - number of milliseconds to wait
     */

    public void sleep(int t){

        draw.show();
        draw.pause(t);
        draw.enableDoubleBuffering();
        /*
        try {
            draw.show();
            draw.pause(t);
            draw.enableDoubleBuffering();
            Thread.sleep(t);
        }
        catch (InterruptedException e) {
            System.out.println("Error sleeping");
        }*/
    }

    /** main()
     *  
     *  The main method does exactly the same as the run method. If you don't want to implement
     *  your program inside of this library (---> main function), then you will have to use
     *  the run method in your own class file.
     * 
     *  @param args
     *  @param move() - animate the MovingPoint
     *  @param Thread.sleep(200) - refresh the image every 200 milliseconds (5 times per sec)
     *  You will need to catch this expression
     */

     /** most simple form of a MovingPoint implementation
      * 
      *  This main method shows the minimum implementation to use this library.
      *  You can get more information in the file "ExtendTest.java" which is
      *  Example (1) of a series of example-programs using MovingPoint.
      */
    public static void main(String[] args) {
        MovingPoint g = new MovingPoint();

        g.setSpawn(0, 0);

        while(true){
            g.mouseHover();
            g.move();
            g.sleep(50);
        }
    }   
}