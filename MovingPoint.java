
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
import src.Draw;
import java.text.*;
import java.util.LinkedList;

import javax.swing.JFrame;


public class MovingPoint implements DrawListener {

    /**
     * Initialization of the instance variables
     * 
     * @param drawComponent instantiates a new object of the type Draw, which will be used to display my algorithms in a JPanel.
     * @param Draw is implemented by @Princeton University.
     * @param playerObjectMovementVector will be the directional vector of the MovingPoint instance.
     * @param position will be the location of the MovingPoint instance.
     * @param playerObjectMovementSpeed The double playerObjectMovementSpeed is a constant which will be multiplied 
     * by another value for much better manipulation of the MovingPoint's playerObjectMovementSpeed
     * @param speedMultiplier is being used to change the playerObjectMovementSpeed percentage.
     * @param movingPointColor defines the MovingPoint's colour.
     * @param drawMovingPoint Boolean to toggle if the MovingPoint shall be drawn or not
     * @param origin Set the MovingPoint's spawn-location
     * @param show Activate/Deactivate the MovingPoint's hover-information by toggling this
     * @param canvasWidth, @param canvasHeight will store the canvas-size
     * @param zoomF Stores the zoom-factor of the MovingPoint-panel
     * 
     * When changing ranges on the x- and y-axes, these variables will store the
     * range for easier calculations, example is the generation of a grid.
     * @param minimumValueOnXAxis stores minimum x-value
     * @param minimumValueOnXAxisTemp stores minimum x-value
     * @param maximumValueOnXAxis stores maximum x-value
     * @param maximumValueOnXAxisTemp stores maximum x-value
     * @param minimumValueOnYAxis stores minimum y-value
     * @param minimumValueOnYAxisTemp stores minimum y-value
     * @param maximumValueOnYAxis stores maximum y-value
     * @param maximumValueOnYAxisTemp stores maximum y-value
     * 
     * Control-System, UserInput:
     * @param keyUp set a KeyID for the upkey
     * @param keyLeft set a KeyID for the leftkey
     * @param keyRightset a KeyID for the rightkey
     * @param keyDownset a KeyID for the downkey
     * @param keyUpPressed checks if the upkey is pressed
     * @param keyLeftPressed checks if the leftkey is pressed
     * @param keyRightPressed checks if the rightkey is pressed
     * @param keyDownPressed checks if the downkey is pressed
     * @param turnSpeed speed at which the playerObject turns
     * 
     * @param decimalNumberFormat this DecimalFormat is used to reduce doubles to only two decimal positions
     * @param drawMovingPointAtCursor Tell the library where to spawn the MovingPoint Set this value to true and
     * your MovingPoint will be drawn at your cursor
     * @param playerObjectSprite set a Sprite which will be drawn as the PlayerObject-Sprite
     * @param backgroundFile set a background file for your game
     * @param useRelativeMovement set a property to move the Grid below the PlayerObject instead of moving the PlayerObject on the Grid
     * (known from Games like Pokemon vs Games like Bomberman)
     */

    final double playerObjectMovementSpeed = 0.16;
    private Draw drawComponent = new Draw();
    public Position position = new Position(0,0);
    public Vector playerObjectMovementVector = new Vector( 0.025 * this.playerObjectMovementSpeed, 0.025 * this.playerObjectMovementSpeed );
    public boolean drawMovingPoint = true;
    private double speedMultiplier = 1;
    public Colour movingPointColor = new Colour(0, 0, 0);
    public Position origin = new Position(0,0);
    public boolean show = true;
    public int canvasWidth = 1000;
    public int canvasHeight = 1000;
    public double zoomFactor = 1;
    public double minimumValueOnXAxis = -1;
    public double minimumValueOnXAxisTemp = minimumValueOnXAxis;
    public double maximumValueOnXAxis = 1;
    public double maximumValueOnXAxisTemp = maximumValueOnXAxis;
    public double minimumValueOnYAxis = -1;
    public double minimumValueOnYAxisTemp = minimumValueOnYAxis;
    public double maximumValueOnYAxis = 1;
    public double maximumValueOnYAxisTemp = maximumValueOnYAxis;
    public int keyUp = 87;
    public int keyLeft = 65;
    public int keyRight = 68;
    public int keyDown = 83;
    public boolean keyUpPressed = false;
    public boolean keyLeftPressed = false;
    public boolean keyRightPressed = false;
    public boolean keyDownPressed = false;
    public double turnSpeed = 0.1;
    DecimalFormat decimalNumberFormat = new DecimalFormat("#.##");
    public boolean drawMovingPointAtCursor = false;
    public Sprite playerObjectSprite = null;
    @Refactor
    public LinkedList<Entity> entList = new LinkedList();
    //public LinkedList entList = new LinkedList();
    public Picture backgroundFile = null;
    @Refactor
    /* stores the range of the x- and y-axes, used for grids */
    public double coordinateAxisRange = Math.abs(maximumValueOnXAxis - minimumValueOnXAxis);
    @Refactor
    /* array which stores information about the state of all cells in the grid */
    public int[][] cells;
    @Refactor
    /* array which stores information about each cell's coordinates */
    public double[][][] cellsXY;
    @Refactor
    /* information if grid is enabled or not */
    public boolean allowGrid = false;
    @Refactor
    /*
     * enable/disable relative movement By enabling relative movement, while
     * pressing the movement-keys the grid will be moved but not the player
     */
    public boolean useRelativeMovement = false;

    /**
     * Constructor for the MovingPoint object
     * 
     * @param canvasWidth       - sets the canvas' width
     * @param canvasHeight      - sets the canvas' height
     * @param addListener(this) - adds the MovingPoint to the Draw(Listener)
     * @param clear()           - is used to set the background-colour
     */

    public MovingPoint(int canvasWidth, int canvasHeight) {
        drawComponent.setCanvasSize(this.canvasWidth = canvasWidth, this.canvasHeight = canvasHeight);
        drawComponent.setXscale(-1, 1);
        drawComponent.setYscale(-1, 1);
        drawComponent.addListener(this); // (1)
        drawComponent.clear(drawComponent.LIGHT_GRAY);
        drawComponent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Default-Constructor for the MovingPoint object
     * 
     * @param setCanvasSize     - sets the size of the display to 1000x1000px
     * @param addListener(this) - adds the MovingPoint to the Draw(Listener)
     * @param clear()           - is used to set the background-colour
     */

    public MovingPoint() {
        drawComponent.setCanvasSize(this.canvasWidth = 1000, this.canvasHeight = 1000);
        drawComponent.setXscale(-1, 1);
        drawComponent.setYscale(-1, 1);
        drawComponent.addListener(this); // (1)
        drawComponent.clear(drawComponent.LIGHT_GRAY);
        drawComponent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Constructor for the MovingPoint object (for multiple MovingPoints)
     * 
     * This function gives the possibility to add another MovingPoint to an existing
     * drawComponent drawComponent instance
     * 
     * @param addListener(this) - adds the MovingPoint to the Draw(Listener)
     */

    public MovingPoint(Draw d) {
        drawComponent.addListener(this);
    }

    /** getUIComponent
     *  Returns the underlying JFrame for UI-creation
     *  @return
     */
    public JFrame getUIComponent() {
        return this.drawComponent.getJFrame();
    }

    /**
     * keyTyped() is not the main function, used to move the MovingPoint on the
     * 2D-plane, but it does support the keyPressed() function, which is used for a
     * smoother keycontrol. So keyPressed() is used for the controls, keyTyped() is
     * supporting the controls and will be used to add specific keybinds to the
     * program, for example spawning new MovingPoints or manipulating the current
     * instance of MovingPoint.
     * 
     * @param c
     */

    public void keyTyped(char c) {

    }

    /**
     * setSpeed() This function is the way to go if the user wants to change a
     * MovingPoint's playerObjectMovementSpeed. The default playerObjectMovementSpeed is set to 0.16 * speedMultiplier while m is set to 1
     * on default. For example changing to speedMultiplier = 1.3 will set the playerObjectMovementSpeed to 0.16*1.3
     * which increases it by 30%.
     * 
     * @param speedMultiplier - the speedMultiplier will be multiplied by 0.16 which is the constant of
     *          the DynObject's playerObjectMovementSpeed.
     */

    public void setSpeed(double speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

    public void draw() {

    }

    /** drawLine
     *  Draws a line between two positions
     *  @param positionA starting point of the line
     *  @param positionB ending point of the line
     */

    public void drawLine(Position positionA, Position positionB){
        drawComponent.line(positionA.x, positionA.y, positionB.x, positionB.y);
    }

    /** drawLine
     *  Draws a line between two positions
     *  @param positionA starting point of the line
     *  @param positionB ending point of the line
     */

    public void drawLine(double x1, double y1, double x2, double y2){
        drawComponent.line( x1,  y1,  x2,  y2);
    }

    /** setPenColor
     *  Changes the pencolor to @param color
     *  @param color 
     */
    public void setPenColor(Colour color){
        this.drawComponent.setPenColor(color);
    }

    /***
     * playSound() Here you can get creative and implement amazing sound-effects,
     * which will play a big role in simulations or games.
     * 
     * @param filename - the path to your audio file. Your soundfiles must have the
     *                 .wav or .au format!
     */

    public void playSound(String filename) {
        StdAudio.play(filename);
    }

    /***
     * backgroundSound() Add background music / sounds which will be playing
     * continuously
     * 
     * @param filename - the path to your audio file. Your soundfiles must have the
     *                 .wav or .au format!
     */

    public void backgroundSound(String filename) {
        StdAudio.play(filename);
    }

    /**
     * size() Change the Draw-panels window-size.
     * 
     * @param a - size of the x-axis
     * @param b - size of the y-axis
     */
    public void size(int canvasWidth, int canvasHeight) {
        drawComponent.setCanvasSize(canvasWidth, canvasHeight);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    public void setBackgroundImage(Picture picture){
        this.backgroundFile = picture;
    }

    /***
     * setXrange() Change the range of the Draw-panel's x-axes
     * 
     * @param a - minimum x-value
     * @param b - maximum x-value
     */
    public void setXrange(double minimumValueOnXAxis, double maximumValueOnXAxis) {
        drawComponent.setXscale(minimumValueOnXAxis, maximumValueOnXAxis);
        this.minimumValueOnXAxis = minimumValueOnXAxis;
        this.maximumValueOnXAxis = maximumValueOnXAxis;
    }

    /***
     * setYrange() Change the range of the Draw-panel's y-axes
     * 
     * @param a - minimum y-value
     * @param b - maximum y-value
     */
    public void setYrange(double minimumValueOnYAxis, double maximumValueOnYAxis) {
        drawComponent.setYscale(minimumValueOnYAxis, maximumValueOnYAxis);
        this.minimumValueOnYAxis = minimumValueOnYAxis;
        this.maximumValueOnYAxis = maximumValueOnYAxis;
    }

    /**
     * keyInput() Returns if a certain key is pressed or not!
     * 
     * @param k - KeyCode of the pressed key
     * 
     */
    public boolean keyInput(int KeyCode) {
        if (drawComponent.isKeyPressed(KeyCode))
            return true;
        else
            return false;
    }

    /** getPosition
     *  Returns the position object
     *  @return Returns the position object
     */
    public Position getPosition(){
        return this.position;
    }

    /** getMousePosition()
     *  Accesses the DrawComponent to retrieve information about the mouse-Location
     *  Returns the mouse-X-location and mouse-Y-location as double-values
     *  
     */
    public Position getMousePosition(){
        return new Position(this.drawComponent.mouseX(), this.drawComponent.mouseY());
    }

    /**
     * zoom() Zoom inside of your Draw-panel by pressing "+" and "-"
     */
    public void zoom() {
        if (allowGrid) {
            if (this.keyInput(521)) {
                zoomFactor -= .05;
            }
            if (this.keyInput(45)) {
                zoomFactor += .05;
            }
        }
    }

    /***
     * grid() Draw a grid of size n*n in your canvas. Also sets a boolean if grid is
     * enabled. If this is the first drawComponent of your grid, all cells centers will be
     * calculated and stored in the array cellsXY. Check the comment on cellsXY to
     * understand how it is used.
     * 
     * @param n - number of cells
     */

    @Refactor
    public void grid(int cellsPerRow) {
        if (this.canvasWidth != this.canvasHeight) {
            System.out.println(
                    "Error on method grid from MovingPoint: You can only create grids if the Draw-panel's sides have the same length!");
            System.exit(0);
        }

        // this will fix a graphics bug
        if ((this.canvasWidth == 1000 && this.canvasHeight == 1000) && this.allowGrid == false)
            this.size(1000, 1000);

        double step = this.coordinateAxisRange / cellsPerRow;

        // calculate centers of each cell:
        if (!this.allowGrid) {
            int rowIterator = 0;
            int columnIterator = 0;
            cells = new int[cellsPerRow][cellsPerRow];

            // loop through all cells of the grid and set their states to -1
            // so there is no NullPointerException
            for (int t = 0; t < cellsPerRow; t++)
                for (int q = 0; q < cellsPerRow; q++)
                    cells[t][q] = -1;

            cellsXY = new double[cellsPerRow][cellsPerRow][2];
            for (double secondIterator = this.maximumValueOnYAxis - ((step / 2) / coordinateAxisRange); 
                 columnIterator < cells.length; 
                 secondIterator -= (step / coordinateAxisRange)) {

                for (double iterator = ((step / 2) / coordinateAxisRange); 
                     rowIterator < cells[0].length; 
                     iterator += (step / coordinateAxisRange)) {

                    cellsXY[rowIterator][columnIterator][0] = iterator;
                    cellsXY[rowIterator][columnIterator][1] = secondIterator;
                    rowIterator++;
                }
                rowIterator = 0;
                columnIterator++;
            }

        }

        this.allowGrid = true;

        double iterator = this.minimumValueOnXAxis;
        while (iterator <= this.maximumValueOnXAxis) {
            drawComponent.line(iterator, this.minimumValueOnYAxis, iterator, this.maximumValueOnYAxis);
            drawComponent.line(this.minimumValueOnXAxis, iterator, this.maximumValueOnXAxis, iterator);
            iterator += step / coordinateAxisRange;
        }
    }

    /***
     * grid() Draw a grid of size n*n in your canvas surrounded by a border, useful
     * to display texts outside of the grid. Also sets a boolean if grid is enabled.
     * If this is the first drawComponent of your grid, all cells centers will be calculated
     * and stored in the array cellsXY. Check the comment on cellsXY to understand
     * how it is used.
     * 
     * @param n - number of cells
     * @param b - range of the border around the grid.
     */

    @Refactor
    public void grid(int cellsPerRow, double border) {
        if (this.canvasWidth != this.canvasHeight) {
            System.out.println(
                    "Error on method grid from MovingPoint: You can only create grids if the Draw-panel's sides have the same length!");
            System.exit(0);
        }

        // this will fix a graphics bug
        if ((this.canvasWidth == 1000 && this.canvasHeight == 1000) && this.allowGrid == false)
            this.size(1000, 1000);

        double step = (this.coordinateAxisRange - 2 * border) / cellsPerRow;

        // calculate centers of each cell:
        if (!this.allowGrid) {
            drawComponent.setXscale(minimumValueOnXAxis, maximumValueOnXAxis);
            drawComponent.setYscale(minimumValueOnYAxis, maximumValueOnYAxis);
            int rowIterator = 0;
            int columnIterator = 0;
            cells = new int[cellsPerRow][cellsPerRow];

            // loop through all cells of the grid and set their states to -1
            // so there is no NullPointerException
            for (int t = 0; t < cellsPerRow; t++)
                for (int q = 0; q < cellsPerRow; q++)
                    cells[t][q] = -1;

            cellsXY = new double[cellsPerRow][cellsPerRow][2];
            // refactor this term, seems complicated
            for (double secondIterator = this.maximumValueOnYAxis - (((step / 2) + border) / coordinateAxisRange); 
                 columnIterator < cells.length; 
                 secondIterator -= step / coordinateAxisRange) {

                for (double iterator = (((step / 2) + border) / coordinateAxisRange); 
                     rowIterator < cells[0].length; 
                     iterator += step / coordinateAxisRange) {

                    cellsXY[rowIterator][columnIterator][0] = iterator;
                    cellsXY[rowIterator][columnIterator][1] = secondIterator;
                    rowIterator++;
                }
                rowIterator = 0;
                columnIterator++;
            }

        }

        this.allowGrid = true;

        double iterator = this.minimumValueOnXAxis + border;
        while (iterator <= this.maximumValueOnXAxis - border) {
            this.drawLine(iterator, (this.minimumValueOnYAxis + border), iterator, (this.maximumValueOnYAxis - border));
            this.drawLine((this.minimumValueOnXAxis + border), iterator, (this.maximumValueOnXAxis - border), iterator);
            iterator += step;
        }
    }

    /***
     * grid() Draw a grid of size n*n in your canvas surrounded by a border, useful
     * to display texts outside of the grid. Also sets a boolean if grid is enabled.
     * You can change the grid's color by using this method. If this is the first
     * drawComponent of your grid, all cells centers will be calculated and stored in the
     * array cellsXY. Check the comment on cellsXY to understand how it is used.
     * 
     * @param n - number of cells
     * @param b - range of the border around the grid.
     */

    @Refactor
    public void grid(int cellsPerRow, double border, Colour color) {
        if (this.canvasWidth != this.canvasHeight) {
            System.out.println(
                    "Error on method grid from MovingPoint: You can only create grids if the Draw-panel's sides have the same length!");
            System.exit(0);
        }
        // this will fix a graphics bug
        if ((this.canvasWidth == 1000 && this.canvasHeight == 1000) && this.allowGrid == false)
            this.drawComponent.setCanvasSize(1000, 1000);

        double step = (this.coordinateAxisRange - 2 * border) / cellsPerRow;

        // calculate centers of each cell:
        if (!this.allowGrid) {

            int rowIterator = 0;
            int columnIterator = 0;

            cells = new int[cellsPerRow][cellsPerRow];

            // loop through all cells of the grid and set their states to -1
            // so there is no NullPointerException
            for (int t = 0; t < cellsPerRow; t++)
                for (int q = 0; q < cellsPerRow; q++)
                    cells[t][q] = -1;

            cellsXY = new double[cellsPerRow][cellsPerRow][2];

            for (double secondIterator = this.maximumValueOnYAxis - ((step / 2) / coordinateAxisRange); 
                 columnIterator < cells.length; 
                 secondIterator -= (step / coordinateAxisRange)) {

                for (double iterator = ((step / 2) / coordinateAxisRange); 
                     rowIterator < cells[0].length; 
                     iterator += (step / coordinateAxisRange)) {

                    cellsXY[rowIterator][columnIterator][0] = iterator;
                    cellsXY[rowIterator][columnIterator][1] = secondIterator;
                    rowIterator++;
                }
                rowIterator = 0;
                columnIterator++;
            }

        }

        this.allowGrid = true;

        double iterator = this.minimumValueOnXAxis + border;

        while (iterator <= this.maximumValueOnXAxis - border) {
            drawComponent.setPenColor(color);
            this.drawLine(iterator, this.minimumValueOnYAxis + border, iterator, this.maximumValueOnYAxis - border);
            this.drawLine(this.minimumValueOnXAxis + border, iterator, this.maximumValueOnXAxis - border, iterator);
            iterator += step / coordinateAxisRange;
            drawComponent.setPenColor(Draw.LIGHT_GRAY);
        }
    }

    /** drawPicture()
     *  Used to draw pictures inside of the canvas. Overrides the drawComponent's picture-method
     *  @param picture - Picture object to be drawn
     */
    public void drawPicture(double x, double y, IGraphicalComponent picture){
        this.drawComponent.picture(x, y, picture.getFilePath());
    }

    /** drawBackgroundPicture()
     *  Used to draw pictures inside of the canvas. Overrides the drawComponent's picture-method
     *  @param picture - Picture object to be drawn
     */
    public void drawBackgroundPicture(){
        this.drawComponent.picture(0, 0, this.backgroundFile.getFilePath());
    }

    /**
     * move() This function could also be called "animate" since its only use is in
     * the management of all drawings. This drawings are also supported by the
     * keyTyped() function
     * 
     * Basically this function does clear the screen and set its background to
     * LIGHT_GRAY. Afterwards it sets the PenColor to it's instance's @param col and
     * draws a filledCircle at @param position of the current instance. The last step is
     * the directional vector. This function will calculate a point at a certain
     * distance (3.5*playerObjectMovementVector length) and drawComponent a line between the @param position of the
     * MovingPoint and this calculated point, so we will see a pointer which shows
     * the @param playerObjectMovementVector direction. Additionally the @param picture function will be
     * used to drawComponent a certain picture at a position.
     * 
     */

    @Refactor
    public void move() {
        if ((Math.abs(this.position.x + this.playerObjectMovementVector.x) < 1) || (Math.abs(this.position.y + this.playerObjectMovementVector.y) < 1)) {

            drawComponent.clear(drawComponent.LIGHT_GRAY);
            drawComponent.setPenColor(this.movingPointColor);

            if (this.drawMovingPointAtCursor == false) {

                if (backgroundFile != null)
                    this.drawBackgroundPicture();

                if (playerObjectSprite == null && drawMovingPoint)
                    drawComponent.filledCircle(this.position.x, this.position.y, 0.02);

                else if (playerObjectSprite != null)
                    this.drawPicture(this.position.x, this.position.y, playerObjectSprite);
                else if (playerObjectSprite == null && !drawMovingPoint)
                    ;
            } else {

                this.position.x = this.getMousePosition().x;
                this.position.y = this.getMousePosition().y;

                if (backgroundFile != null)
                    this.drawBackgroundPicture();

                if (playerObjectSprite == null)
                    drawComponent.filledCircle(this.position.x, this.position.y, 0.02);

                else
                    this.drawPicture(this.position.x, this.position.y, playerObjectSprite);
            }

            drawComponent.setPenColor(drawComponent.GRAY);
            if (drawMovingPoint) {
                drawComponent.line(this.position.x, this.position.y, this.position.x + 2.5 * this.playerObjectMovementVector.x * (1 / this.playerObjectMovementSpeed),
                        this.position.y + 2.5 * this.playerObjectMovementVector.y * (1 / this.playerObjectMovementSpeed));
            }

            if (mouseHover())
                drawInfo();
            zoom();

            // drawComponent entitites:

        } else
            return;
    }

    /**
     * distanceTo()
     * 
     * This method returns the function of the MovingPoint to a certain entity. May
     * be useful to create collisions or pathfinding (TODO)
     * 
     * @param entity - The distance to this entity will be calculated
     * @return
     */

    public double distanceTo(Entity entity) {

        double x = this.position.x - entity.position.x;
        double y = this.position.y - entity.position.y;

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * distanceTo()
     * 
     * This method returns the function of the MovingPoint to a certain location.
     * May be useful to create collisions or pathfinding (TODO)
     * 
     * @param position - Position object which includes the object's position
     * @return
     */

    public double distanceTo(Position pos) {
        double x = this.position.x - pos.x;
        double y = this.position.y - pos.y;

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * printPosition()
     * 
     * Use this method to print the MovingPoint's location into the console.
     * 
     */

    public void printPosition() {
        System.out.println("x = " + decimalNumberFormat.format(this.position.x) + " y = " + decimalNumberFormat.format(this.position.y));
    }

    /**
     * vecAdd() Used to add the @param playerObjectMovementVector components to the @param position
     * components. Simple vector addition.
     * 
     * @param movingPoint shows the function which MovingPoint's location has to be updated.
     */

    public void vecAdd(MovingPoint movingPoint) {
        movingPoint.position.x += movingPoint.playerObjectMovementVector.x * movingPoint.speedMultiplier;
        movingPoint.position.y += movingPoint.playerObjectMovementVector.y * movingPoint.speedMultiplier;
    }

    /**
     * rotate() Implemented by using basic matrix multiplication with the 2D
     * rotation matrix. A degree is given by @param degree and is used in the rotation
     * matrix.
     * 
     * @param degree - gives the degree for the rotation
     */

    public void rotate(double degree) {

        double x = this.playerObjectMovementVector.x * Math.cos(degree) - this.playerObjectMovementVector.y * Math.sin(degree);
        double y = this.playerObjectMovementVector.x * Math.sin(degree) + this.playerObjectMovementVector.y * Math.cos(degree);
        this.playerObjectMovementVector.x = x;
        this.playerObjectMovementVector.y = y;
    }

    /**
     * mousePressed() Called when the mouse is pressed. Same functionality as the
     * keyTyped() function according to manipulating the MovingPoint. It is useful
     * to override this function.
     * 
     * @param x
     * @param y
     */

    public void mousePressed(double x, double y) {

        // instantiate a new Entity using this.getMousePosition().x, this.getMousePosition().y

        if (!allowGrid)
            this.addEntity(new EntityWall(this.getMousePosition().x, this.getMousePosition().y, drawComponent));

        // if you are using a grid you can set a cells state by clicking
        // inside of it.
    }

    /**
     * nearestCell() Apply this method to your mouseEvent and change a cell's state
     * while clicking inside of it. The borders will be used to determine in which
     * cell the click was performed
     * 
     * @param state - use an integer as the clicked cell's state You can implement
     *              your own states in your program and use them as an overlay for
     *              the integers.
     * 
     */
    public void nearestCell(int state) {
        int N = this.cells.length;

        double step = (this.coordinateAxisRange / N) / coordinateAxisRange;

        int tmpX = 0;
        int tmpY = 0;

        for (int secondIterator = 0; secondIterator < N; secondIterator++)
            if (this.getMousePosition().x > secondIterator * step && this.getMousePosition().x < (secondIterator + 1) * step)
                tmpX = secondIterator;

        for (int iterator = 0; iterator < N; iterator++)
            if (this.getMousePosition().y > iterator * step && this.getMousePosition().y < (iterator + 1) * step)
                tmpY = N - iterator - 1;

        cells[tmpX][tmpY] = state;
        System.out.println(tmpX + " " + tmpY);
    }

    /**
     * addEntity()
     * 
     * Implement entities according to the examples "Entity.java", "EntityWall.java"
     * and add them to the JPanel by using this function
     * 
     * Note: The entity-system is implemented by using a linked list, which can
     * dynamically store all types of entities (subclasses) and drawComponent them.
     * 
     * @param e - The entity which shall be added to the JPanel.
     */

    public void addEntity(Entity e) {
        entList.add(new Entity());
    }

    /**
     * getDraw()
     * 
     * Return the DrawPanel of your MovingPoint to implement new drawing functions
     * in your subclasses of this library. This return can be caught and stored in a
     * new Draw instance to get access to all existing drawing functions and also to
     * the MouseListener and KeyListener, since they are connected to the DrawPanel.
     * 
     * @return
     */

    public Draw getDraw() {
        return drawComponent;
    }

    /**
     * mouseDragged() will be used to spawn entities.
     * 
     * @param x
     * @param y
     */

    public void mouseDragged(double x, double y) {
        drawComponent.filledCircle(x, y, 0.002);
    }

    /** mouseReleased
     *  This method can be overridden
     */
    public void mouseReleased(double x, double y) {
    }

    /**
     * keyReleased() Resets the boolean for each key when the key is no longer
     * pressed. This will help to create a Movement-system in which multiple keys
     * can be pressed at the same time.
     * 
     * @param keycode - ID of the released key.
     */

    public void keyReleased(int keycode) {
        if (keycode == keyUp)
            keyUpPressed = false;
        else if (keycode == keyLeft)
            keyLeftPressed = false;
        else if (keycode == keyRight)
            keyRightPressed = false;
        else if (keycode == keyDown)
            keyDownPressed = false;
    }

    /**
     * keyPressed() The main controller for the MovingPoint. Its read the keyInput
     * as an integer and compares it to the ASCII-values to check which control-key
     * is pressed.
     * 
     * 87 --> W 65 --> A 68 --> D
     * 
     * @param keycode
     */

    public void keyPressed(int keycode) {
        if (useRelativeMovement) {

            if (keycode == keyUp) {

                for (int iterator = 0; iterator < cells.length; iterator++)
                    for (int secondIterator = 0; secondIterator < cells.length; secondIterator++)
                        cellsXY[iterator][secondIterator][1] -= 0.5 * (coordinateAxisRange / cells.length) / coordinateAxisRange;

                this.position.y -= (coordinateAxisRange / cells.length) / coordinateAxisRange;
            } else if (keycode == keyDown) {

                for (int iterator = 0; iterator < cells.length; iterator++)
                    for (int secondIterator = 0; secondIterator < cells.length; secondIterator++)
                        cellsXY[iterator][secondIterator][1] += 0.5 * (coordinateAxisRange / cells.length) / coordinateAxisRange;

                this.position.y += (coordinateAxisRange / cells.length) / coordinateAxisRange;
            } else if (keycode == keyLeft) {

                for (int iterator = 0; iterator < cells.length; iterator++)
                    for (int secondIterator = 0; secondIterator < cells.length; secondIterator++)
                        cellsXY[iterator][secondIterator][0] += 0.5 * (coordinateAxisRange / cells.length) / coordinateAxisRange;

                this.position.x += (coordinateAxisRange / cells.length) / coordinateAxisRange;
            } else if (keycode == keyRight) {

                for (int iterator = 0; iterator < cells.length; iterator++)
                    for (int secondIterator = 0; secondIterator < cells.length; secondIterator++)
                        cellsXY[iterator][secondIterator][0] -= 0.5 * (coordinateAxisRange / cells.length) / coordinateAxisRange;

                this.position.x -= (coordinateAxisRange / cells.length) / coordinateAxisRange;
            }
        } else {
            if (keycode == keyUp) {
                this.vecAdd(this);
                keyUpPressed = true;
            } else if (keycode == keyLeft) {
                this.rotate(0.1);
                keyLeftPressed = true;
            } else if (keycode == keyRight) {
                this.rotate(-0.1);
                keyRightPressed = true;
            } else if (keycode == keyDown) {
                keyDownPressed = true;
            }
        }
    }

    /**
     * run() Use this function to implement the MovingPoint library on the most
     * simple way possible. An example implementation will be given with the
     * ExtendTest.java
     * 
     * run() initializes the default directional vector and starts a while loop,
     * which will drawComponent the MovingPoint and run all required functions in the
     * background, without implementing it in your program. If you wish to implement
     * the function by yourself, override them or copy the code of the run()
     * function to your program and change, what the loop does by yourself.
     * 
     * @param move()           - animate the MovingPoint
     * @param Thread.sleep(50) - refresh the image every 50 milliseconds (20 times
     *                         per sec) You will need to catch this expression
     */

    public void run() {

        setSpawn(0.5, 0.5);

        this.playerObjectMovementVector.x = 0.025 * this.playerObjectMovementSpeed;
        this.playerObjectMovementVector.y = 0.025 * this.playerObjectMovementSpeed;

        while (true) {

            this.move();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Error sleeping");
            }
        }
    }

    /** mouseHover
     *  Checks if the Player hovers the PlayerObject
     *  @return true if cursor is very close to the PlayerObject or false i
     * */
    public boolean mouseHover() {
        // if the hover-menu is disabled, nothing will happen
        if (this.show == false)
            return false;

        double x = this.position.x - this.getMousePosition().x;
        double y = this.position.y - this.getMousePosition().y;

        double distanceToPlayerObject = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

        if (distanceToPlayerObject <= 0.02)
            return true;
        else
            return false;
    }

    /** drawInfo
     *  This menu shows an example how to implement UI. This will also be the template
     *  for the upcoming UI-System
     */
    public void drawInfo() {
        drawComponent.setPenColor(drawComponent.LIGHT_GRAY);
        drawComponent.filledRectangle(this.position.x - 0.15, this.position.y + 0.3, 0.025 * 9, 0.025 * 5);
        drawComponent.setPenColor(drawComponent.BLACK);
        drawComponent.text(this.position.x - 0.15, this.position.y + 0.4, "MovingPoint");
        drawComponent.text(this.position.x - 0.15, this.position.y + 0.35,
                "x = " + decimalNumberFormat.format(this.position.x) + " y = " + decimalNumberFormat.format(this.position.y));
        drawComponent.text(this.position.x - 0.15, this.position.y + 0.3, "" + decimalNumberFormat.format(this.distanceTo(this.origin)));
        drawComponent.text(this.position.x - 0.15, this.position.y + 0.25, "Color = " + this.movingPointColor);
        drawComponent.text(this.position.x - 0.15, this.position.y + 0.2,
                "Vec2D = (" + decimalNumberFormat.format(this.playerObjectMovementVector.x) + ", " + decimalNumberFormat.format(this.playerObjectMovementVector.y) + ")");

    }

    /** setSpawn
     *  With setSpawn you will set the starting location of your
     *  MovingPoint.
     * 
     *  @param x - double in range [0,1]
     *  @param y - double in range [0,1]
     */

    public void setSpawn(double x, double y) {
        this.position.x = x;
        this.position.y = y;
    }

    /**
     * sleep() Make your program wait a certain amount of time, until it continues
     * fetching the next task.
     * 
     * @param timeInMilliSeconds - number of milliseconds to wait
     */

    public void sleep(int timeInMilliSeconds) {

        drawComponent.show();
        drawComponent.pause(timeInMilliSeconds);
        drawComponent.enableDoubleBuffering();

    }

    /**
     * main()
     * 
     * The main method does exactly the same as the run method. If you don't want to
     * implement your program inside of this library (---> main function), then you
     * will have to use the run method in your own class file.
     * 
     * @param args
     * @param move()            - animate the MovingPoint
     * @param Thread.sleep(50) - refresh the image every 50 milliseconds (20 times
     *                          per sec)
     */

    /**
     * most simple form of a MovingPoint implementation
     * 
     * This main method shows the minimum implementation to use this library. You
     * can get more information in the file "ExtendTest.java" which is Example (1)
     * of a series of example-programs using MovingPoint.
     */
    public static void main(String[] args) {
        MovingPoint movingPoint = new MovingPoint(500, 500);

        movingPoint.setSpawn(0, 0);

        while (true) {
            movingPoint.mouseHover();
            movingPoint.move();
            movingPoint.sleep(50);
        }
    }
}