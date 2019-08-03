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

import src.*;
import java.awt.event.MouseEvent;
import java.text.*;
import javax.swing.JFrame;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

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

    /* Boolean to toggle if the MovingPoint shall be drawn or not */
    public boolean allowMP = true;

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


    /* These two variables store the Draw-panel's size */
    public int sizeX = 1000;
    public int sizeY = 1000;

    /* Stores the zoom-factor of the MovingPoint-panel */
    public double zoomF = 1;

    /* When changing ranges on the x- and y-axes, these variables will store the
       range for easier calculations, example is the generation of a grid.*/

    /* stores minimum x-value */
    public double Xmin = -1;
    public double Xmin_ = Xmin;

    /* stores maximum x-value */
    public double Xmax = 1;
    public double Xmax_ = Xmax;

    /* stores minimum y-value */
    public double Ymin = -1;
    public double Ymin_ = Ymin;

    /* stores maximum y-value */
    public double Ymax = 1;
    public double Ymax_ = Ymax;

    /* control-system store keys */
    public int keyUp    = 87;
    public int keyLeft  = 65;
    public int keyRight = 68;
    public int keyDown  = 83;

    /* boolean values to check if controlKey's are pressed */
    public boolean keyUpPressed    = false;
    public boolean keyLeftPressed  = false;
    public boolean keyRightPressed = false;
    public boolean keyDownPressed  = false;

    /* set the rotation-speed for your unit */
    public double turnSpeed = 0.1;

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

    /* stores the range of the x- and y-axes, used for grids */
    public double span = Math.abs(Xmax - Xmin);

    /* array which stores information about the state of all cells in the grid */
    public int[][] cells;

    /* array which stores information about each cell's coordinates*/
    public double[][][] cellsXY;

    /* information if grid is enabled or not */
    public boolean gridUsed = false;

    /* setup section for the tile-system */

    /* enable/disable relative movement
    *  By enabling relative movement, while pressing the movement-keys the grid will be moved
    *  but not the player
    */
    public boolean useRelativeMovement = false;

    /** Constructor for the MovingPoint object
     * 
     * @param setCanvasSize - sets the size of the display to 1000x1000px
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

    /** size()
     *  Change the Draw-panels window-size. 
     * 
     *  @param a - size of the x-axis
     *  @param b - size of the y-axis
     */
    public void size(int a, int b){
        draw.setCanvasSize(a, b);
        this.sizeX = a;
        this.sizeY = b;
    }

    /*** setXrange()
     *  Change the range of the Draw-panel's x-axes
     * 
     *  @param a - minimum x-value
     *  @param b - maximum x-value
     */
    public void setXrange(double a, double b){
        draw.setXscale(a, b);
        this.Xmin = a;
        this.Xmax = b;
    }

    /*** setYrange()
     *  Change the range of the Draw-panel's y-axes
     * 
     *  @param a - minimum y-value
     *  @param b - maximum y-value
     */
    public void setYrange(double a, double b){
        draw.setYscale(a, b);
        this.Ymin = a;
        this.Ymax = b;
    }

    /** keyInput()
     *  Returns if a certain key is pressed or not!
     * 
     *  @param k - KeyCode of the pressed key 
     *  
     */
    public boolean keyInput(int k){
        if(draw.isKeyPressed(k)) return true;
        else return false;
    }

    /** zoom()
     *  Zoom inside of your Draw-panel by pressing "+" and "-"
     */
    public void zoom(){
        if(gridUsed){
            if(this.keyInput(521)){
                zoomF -= .05;
            }
            if(this.keyInput(45)){
                zoomF += .05;
            }
        }       
    }

    /*** grid()
     *  Draw a grid of size n*n in your canvas.
     *  Also sets a boolean if grid is enabled.
     *  If this is the first draw of your grid, 
     *  all cells centers will be calculated and 
     *  stored in the array cellsXY. Check the
     *  comment on cellsXY to understand how it is used.
     * 
     *  @param n - number of cells
     */
    public void grid(int n){
        if(this.sizeX != this.sizeY) {
            System.out.println("Error on method grid from MovingPoint: You can only create grids if the Draw-panel's sides have the same length!");
            System.exit(0);
        }

        // this will fix a graphics bug
        if((this.sizeX == 1000 && this.sizeY == 1000) && this.gridUsed == false)
            this.size(1000, 1000);

        double step = this.span / n;

        // calculate centers of each cell:
        if(!this.gridUsed){
            int R = 0;
            int S = 0;
            cells   = new int[n][n];

            // loop through all cells of the grid and set their states to -1
            // so there is no NullPointerException
            for(int t = 0; t < n; t++)
                for(int q = 0; q < n; q++)
                    cells[t][q] = -1;

            cellsXY = new double[n][n][2];
            for(double j = this.Ymax - ((step/2)/span); S < cells.length; j -= (step/span)){
                for(double i = ((step/2)/span); R < cells[0].length; i += (step/span)){
                    cellsXY[R][S][0] = i;
                    cellsXY[R][S][1] = j;
                    R++;
                }
                R = 0;
                S++;
            }
                
        }

        this.gridUsed = true;

        double i = this.Xmin;
        while(i <= this.Xmax){
            draw.line(i, this.Ymin, i, this.Ymax);
            draw.line(this.Xmin, i, this.Xmax, i);
            i += step/span;
        }
    }

    /*** grid()
     *  Draw a grid of size n*n in your canvas surrounded by
     *  a border, useful to display texts outside of the grid.
     *  Also sets a boolean if grid is enabled. If this is the
     *  first draw of your grid, all cells centers will be
     *  calculated and stored in the array cellsXY. Check the
     *  comment on cellsXY to understand how it is used.
     * 
     *  @param n - number of cells
     *  @param b - range of the border around the grid.
     */
    public void grid(int n, double b){
        if(this.sizeX != this.sizeY) {
            System.out.println("Error on method grid from MovingPoint: You can only create grids if the Draw-panel's sides have the same length!");
            System.exit(0);
        }

        // this will fix a graphics bug
        if((this.sizeX == 1000 && this.sizeY == 1000) && this.gridUsed == false)
            this.size(1000, 1000);

        double step = (this.span - 2*b) / n;

        // calculate centers of each cell:
        if(!this.gridUsed){
            draw.setXscale(Xmin, Xmax);
            draw.setYscale(Ymin, Ymax);
            int R = 0;
            int S = 0;
            cells   = new int[n][n];

            // loop through all cells of the grid and set their states to -1
            // so there is no NullPointerException
            for(int t = 0; t < n; t++)
                for(int q = 0; q < n; q++)
                    cells[t][q] = -1;

            cellsXY = new double[n][n][2];
                                    // refactor this term, seems complicated
            for(double j = this.Ymax - (((step/2)+b)/span); S < cells.length; j -= step/span){

                for(double i = (((step/2)+b)/span); R < cells[0].length; i += step/span){

                    cellsXY[R][S][0] = i;
                    cellsXY[R][S][1] = j;
                    R++;
                }
                R = 0;
                S++;
            }
                
        }

        this.gridUsed = true;

        double i = this.Xmin+b;
        while(i <= this.Xmax-b){
            draw.line(i, (this.Ymin+b), i, (this.Ymax-b));
            draw.line((this.Xmin+b), i, (this.Xmax-b), i);
            i += step;
        }
    }

    /*** grid()
     *  Draw a grid of size n*n in your canvas surrounded by
     *  a border, useful to display texts outside of the grid.
     *  Also sets a boolean if grid is enabled. You can change
     *  the grid's color by using this method. If this is the
     *  first draw of your grid, all cells centers will be
     *  calculated and stored in the array cellsXY. Check the
     *  comment on cellsXY to understand how it is used.
     * 
     *  @param n - number of cells
     *  @param b - range of the border around the grid.
     */
    public void grid(int n, double b, Colour c){
        if(this.sizeX != this.sizeY) {
            System.out.println("Error on method grid from MovingPoint: You can only create grids if the Draw-panel's sides have the same length!");
            System.exit(0);
        }
        // this will fix a graphics bug
        if((this.sizeX == 1000 && this.sizeY == 1000) && this.gridUsed == false)
            this.draw.setCanvasSize(1000, 1000);

        double step = (this.span - 2*b) / n;

        // calculate centers of each cell:
        if(!this.gridUsed){

            int R = 0;
            int S = 0;

            cells   =  new int[n][n];

            // loop through all cells of the grid and set their states to -1
            // so there is no NullPointerException
            for(int t = 0; t < n; t++)
                for(int q = 0; q < n; q++)
                    cells[t][q] = -1;


            cellsXY = new double[n][n][2];

            for(double j = this.Ymax - ((step/2)/span); S < cells.length; j -= (step/span)){

                for(double i = ((step/2)/span); R < cells[0].length; i += (step/span)){

                    cellsXY[R][S][0] = i;
                    cellsXY[R][S][1] = j;
                    R++;
                }
                R = 0;
                S++;
            }
                
        }

        this.gridUsed = true;

        double i = this.Xmin + b;

        while(i <= this.Xmax - b){
            draw.setPenColor(c);
            draw.line(i, this.Ymin+b, i, this.Ymax-b);
            draw.line(this.Xmin+b, i, this.Xmax-b, i);
            i += step/span;
            draw.setPenColor(Draw.LIGHT_GRAY);
        }
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

                if(imgFile == null && allowMP)
                    draw.filledCircle(this.loc[0], this.loc[1], 0.02);

                else if(imgFile != null) draw.picture(this.loc[0], this.loc[1], imgFile);
                else if(imgFile == null && !allowMP);
            } else {

                this.loc[0] = draw.mouseX();
                this.loc[1] = draw.mouseY();

                if(backgroundFile != null) draw.picture(0, 0, backgroundFile);

                if(imgFile == null)
                    draw.filledCircle(this.loc[0], this.loc[1], 0.02);

                else draw.picture(this.loc[0], this.loc[1], imgFile);
            }
        
            draw.setPenColor(draw.GRAY);
            if(allowMP) {
                draw.line(this.loc[0], 
                          this.loc[1] ,
                          this.loc[0]+2.5*this.vec2D[0]*(1/this.speed), 
                          this.loc[1]+2.5*this.vec2D[1]*(1/this.speed)
                );
            }

            if(mouseHover()) drawInfo();
            zoom();
            

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
     *  Called when the mouse is pressed. Same functionality as the keyTyped() function
     *  according to manipulating the MovingPoint. It is useful to override this 
     *  function.
     * 
     *  @param x
     *  @param y
     */

    public void mousePressed(double x, double y){

        // instantiate a new Entity using mouseX, mouseY
        double mouseX = x;
        double mouseY = y;

        if(!gridUsed)
            this.addEntity(new EntityWall(mouseX, mouseY, draw));

        // if you are using a grid you can set a cells state by clicking
        // inside of it.
    }

    /** nearestCell()
     *  Apply this method to your mouseEvent and change a cell's
     *  state while clicking inside of it. The borders will be used
     *  to determine in which cell the click was performed
     * 
     *  @param state - use an integer as the clicked cell's state
     *                 You can implement your own states in your 
     *                 program and use them as an overlay for
     *                 the integers.
     * 
     */
    public void nearestCell(int state){
        int N = this.cells.length;

        double step = (this.span / N)/span;

        int tmpX = 0;
        int tmpY = 0;

        for(int j = 0; j < N; j++)
            if(draw.mouseX() > j * step && draw.mouseX() < (j+1)*step) tmpX = j ;

        for(int i = 0; i < N; i++)
            if(draw.mouseY() > i * step && draw.mouseY() < (i+1)*step) tmpY = N - i - 1;

        cells[tmpX][tmpY] = state;
        System.out.println(tmpX + " " + tmpY);
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

    /** keyReleased()
     *  Resets the boolean for each key when the key is no longer pressed.
     *  This will help to create a Movement-system in which multiple keys
     *  can be pressed at the same time.
     *  
     *  @param keycode - ID of the released key.
     */

    public void keyReleased(int keycode){
        if      (keycode == keyUp)    keyUpPressed = false;
        else if (keycode == keyLeft)  keyLeftPressed = false;
        else if (keycode == keyRight) keyRightPressed = false;
        else if (keycode == keyDown)  keyDownPressed = false;
    }

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
        if(useRelativeMovement){

            if(keycode == keyUp){

                for(int i = 0; i < cells.length; i++)
                    for(int j = 0; j < cells.length; j++)
                        cellsXY[i][j][1] -= 0.5*(span/cells.length)/span;

                this.loc[1] -= (span/cells.length)/span;
            }
            else if(keycode == keyDown){

                for(int i = 0; i < cells.length; i++)
                    for(int j = 0; j < cells.length; j++)
                        cellsXY[i][j][1] += 0.5*(span/cells.length)/span;

                this.loc[1] += (span/cells.length)/span;
            }
            else if(keycode == keyLeft){

                for(int i = 0; i < cells.length; i++)
                    for(int j = 0; j < cells.length; j++)
                        cellsXY[i][j][0] += 0.5*(span/cells.length)/span;

                this.loc[0] += (span/cells.length)/span;
            }
            else if(keycode == keyRight){

                for(int i = 0; i < cells.length; i++)
                    for(int j = 0; j < cells.length; j++)
                        cellsXY[i][j][0] -= 0.5*(span/cells.length)/span;

                this.loc[0] -= (span/cells.length)/span;
            }
        } else {
            if      (keycode == keyUp) {
                this.vecAdd(this);
                keyUpPressed = true;
            }  
            else if (keycode == keyLeft) {
                this.rotate(0.1);
                keyLeftPressed = true;
            }
            else if (keycode == keyRight) {
                this.rotate(-0.1);
                keyRightPressed = true;
            }
            else if (keycode == keyDown) {
                keyDownPressed = true;
            }
        }
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