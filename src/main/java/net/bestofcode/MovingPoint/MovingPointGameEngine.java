package net.bestofcode.MovingPoint;
/******************************************************
 *
 *
 * <p>used libraries:
 *
 *               @author Princeton University
 *               net.bestofcode.MovingPointGameEngine.GraphicalComponent
 *
 *               @author Princeton University
 *               DrawListener
 *
 *               @author Princeton University
 *               net.bestofcode.MovingPointGameEngine.MovingPointAudioMethodCollection
 *
 *               @author Heinrich-Heine-University
 *               net.bestofcode.MovingPointGameEngine.Colour
 *
 *  <p>Visit my website <a href="http://steinke-it.com">
 *  by Marco Steinke (2019)
 *
 *
 *
 ******************************************************/

import net.bestofcode.MovingPoint.annotations.Refactor;
import net.bestofcode.MovingPoint.annotations.Remove;
import net.bestofcode.MovingPoint.audio.MovingPointAudioMethodCollection;
import net.bestofcode.MovingPoint.event.IMovingPointEventManager;
import net.bestofcode.MovingPoint.event.keyboardEvent.KeyboardManager;
import net.bestofcode.MovingPoint.event.keyboardEvent.configuration.DefaultKey;
import net.bestofcode.MovingPoint.event.keyboardEvent.configuration.KeyboardConfiguration;
import net.bestofcode.MovingPoint.logic.GameObject;
import net.bestofcode.MovingPoint.math.Position;
import net.bestofcode.MovingPoint.math.Vector;
import net.bestofcode.MovingPoint.render.Colour;
import net.bestofcode.MovingPoint.render.gamewindow.GameWindow;
import net.bestofcode.MovingPoint.render.GraphicalComponent;
import net.bestofcode.MovingPoint.render.IGraphicalComponent;
import net.bestofcode.MovingPoint.render.gamewindow.GameWindowConfiguration;
import net.bestofcode.MovingPoint.render.grid.Grid;
import net.bestofcode.MovingPoint.render.settings.Height;
import net.bestofcode.MovingPoint.render.settings.Width;
import net.bestofcode.MovingPoint.render.texture.Picture;
import net.bestofcode.MovingPoint.render.texture.Sprite;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.LinkedList;


public class MovingPointGameEngine implements IMovingPointEventManager {

    final double playerObjectMovementSpeed = 1;
    private final KeyboardConfiguration keyboardConfiguration = KeyboardConfiguration.getDefaultKeys();
    private final KeyboardManager keyboardManager = new KeyboardManager(this.keyboardConfiguration);
    public Position position = new Position(0, 0);
    public Vector playerObjectMovementVector = new Vector(0.025 * 0.16 * this.playerObjectMovementSpeed, 0.025 * 0.16 * this.playerObjectMovementSpeed);
    public boolean drawMovingPoint = true;
    public Colour movingPointColor = new Colour(0, 0, 0);
    public boolean show = true;
    public double zoomFactorAsPercentual = 1;
    public double minimumValueOnXAxis = -1;
    public double maximumValueOnXAxis = 1;
    public double minimumValueOnYAxis = -1;
    public double maximumValueOnYAxis = 1;
    public int keyUp = this.keyboardConfiguration.getKeyForAction(DefaultKey.MOVE_UP);
    public int keyLeft = this.keyboardConfiguration.getKeyForAction(DefaultKey.MOVE_LEFT);
    public int keyRight = this.keyboardConfiguration.getKeyForAction(DefaultKey.MOVE_RIGHT);
    public int keyDown = this.keyboardConfiguration.getKeyForAction(DefaultKey.MOVE_DOWN);
    public boolean drawMovingPointAtCursor = false;
    public Sprite playerObjectSprite = null;
    @Refactor
    public LinkedList<GameObject> entityList = new LinkedList();
    //public LinkedList entList = new LinkedList();
    public Picture backgroundFile = null;
    @Refactor
    /* stores the range of the x- and y-axes, used for grids */
    public double coordinateAxisRange = Math.abs(maximumValueOnXAxis - minimumValueOnXAxis);
    @Refactor
    /*
     * enable/disable relative movement By enabling relative movement, while
     * pressing the movement-keys the grid will be moved but not the player
     */
    public boolean useRelativeMovement = false;
    DecimalFormat decimalNumberFormat = new DecimalFormat("#.##");
    private GraphicalComponent graphicalComponent;
    private Grid grid;
    /* information if grid is enabled or not */
    public boolean allowGrid = this.grid != null;
    private double speedMultiplier = 1;
    private GameWindowConfiguration gameWindowConfiguration;
    private Width gameWindowWidth = new Width(1000);
    private Height gameWindowHeight = new Height(1000);

    /**
     * Constructor for the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine object
     *
     * @param canvasWidth  - sets the canvas' width
     * @param canvasHeight - sets the canvas' height
     */

    public MovingPointGameEngine(int canvasWidth, int canvasHeight) {

        this.graphicalComponent = new GraphicalComponent();

        this.gameWindowConfiguration = new GameWindowConfiguration(new Width(canvasWidth), new Height(canvasHeight));

        graphicalComponent.setCanvasSize(
                (this.gameWindowWidth = new Width(canvasWidth)).getValue(),
                (this.gameWindowHeight = new Height(canvasHeight)).getValue()
        );

        graphicalComponent.setXscale(-1, 1);
        graphicalComponent.setYscale(-1, 1);
        graphicalComponent.addListener(this); // (1)
        graphicalComponent.clear(GraphicalComponent.LIGHT_GRAY);
        graphicalComponent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Constructor for the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine object
     *
     * @param canvasWidth  - sets the canvas' width
     * @param canvasHeight - sets the canvas' height
     * @param name         - sets the game name
     */

    public MovingPointGameEngine(int canvasWidth, int canvasHeight, String name) {

        this.graphicalComponent = new GraphicalComponent(name);

        this.gameWindowConfiguration = new GameWindowConfiguration(new Width(canvasWidth), new Height(canvasHeight));

        graphicalComponent.setCanvasSize(
                (this.gameWindowWidth = new Width(canvasWidth)).getValue(),
                (this.gameWindowHeight = new Height(canvasHeight)).getValue()
        );

        graphicalComponent.setXscale(-1, 1);
        graphicalComponent.setYscale(-1, 1);
        graphicalComponent.addListener(this); // (1)
        graphicalComponent.clear(GraphicalComponent.LIGHT_GRAY);
        graphicalComponent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Default-Constructor for the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine object
     */

    public MovingPointGameEngine() {

        this.graphicalComponent = new GraphicalComponent();

        this.gameWindowConfiguration = new GameWindowConfiguration(new Width(1000), new Height(1000));

        graphicalComponent.setCanvasSize(
                (this.gameWindowWidth = this.gameWindowConfiguration.getWidth()).getValue(),
                (this.gameWindowHeight = this.gameWindowConfiguration.getHeight()).getValue()
        );

        graphicalComponent.setXscale(-1, 1);
        graphicalComponent.setYscale(-1, 1);
        graphicalComponent.addListener(this); // (1)
        graphicalComponent.clear(GraphicalComponent.LIGHT_GRAY);
        graphicalComponent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Constructor(2) for the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine object
     *
     * @param name - sets the game name
     */

    public MovingPointGameEngine(String name) {

        this.graphicalComponent = new GraphicalComponent(name);

        this.gameWindowConfiguration = new GameWindowConfiguration(new Width(1000), new Height(1000));

        graphicalComponent.setCanvasSize((this.gameWindowWidth = new Width(1000)).getValue(), (this.gameWindowHeight = new Height(1000)).getValue());
        graphicalComponent.setXscale(-1, 1);
        graphicalComponent.setYscale(-1, 1);
        graphicalComponent.addListener(this); // (1)
        graphicalComponent.clear(GraphicalComponent.LIGHT_GRAY);
        graphicalComponent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * Constructor for the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine object (for multiple MovingPoints)
     * <p>
     * This function gives the possibility to add another net.bestofcode.MovingPointGameEngine.MovingPointGameEngine to an existing
     * net.bestofcode.MovingPointGameEngine.GraphicalComponent graphicalComponent instance
     */

    public MovingPointGameEngine(GraphicalComponent d) {

        graphicalComponent.addListener(this);

    }

    /**
     * most simple form of a net.bestofcode.MovingPointGameEngine.MovingPointGameEngine implementation
     * <p>
     * This main method shows the minimum implementation to use this library. You
     * can get more information in the file "ExtendTest.java" which is Example (1)
     * of a series of example-programs using net.bestofcode.MovingPointGameEngine.MovingPointGameEngine.
     */
    public static void main(String[] args) {

        MovingPointGameEngine movingPoint = new MovingPointGameEngine();

        movingPoint.setSpawn(0, 0);

        movingPoint.addGameObject(new GameObject(0, 0, new Picture("Doodler.png")));
        movingPoint.addGameObject(new GameObject(0, 0, new Picture("gkjgjgjDoodler.png")));


        while (true) {

            movingPoint.mouseHover();
            movingPoint.move();
            movingPoint.sleep(50);

        }
    }

    /**
     * getUIComponent
     * Returns the underlying JFrame for UI-creation
     *
     * @return
     */
    public GameWindow getUIComponent() {

        return this.graphicalComponent.getJFrame();

    }

    /**
     * keyTyped() is not the main function, used to move the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine on the
     * 2D-plane, but it does support the keyPressed() function, which is used for a
     * smoother keycontrol. So keyPressed() is used for the controls, keyTyped() is
     * supporting the controls and will be used to add specific keybinds to the
     * program, for example spawning new MovingPoints or manipulating the current
     * instance of net.bestofcode.MovingPointGameEngine.MovingPointGameEngine.
     *
     * @param c
     */

    public void keyTyped(char c) {

    }

    /**
     * setSpeed() This function is the way to go if the user wants to change a
     * net.bestofcode.MovingPointGameEngine.MovingPointGameEngine's playerObjectMovementSpeed. The default playerObjectMovementSpeed is set to 0.16 * speedMultiplier while m is set to 1
     * on default. For example changing to speedMultiplier = 1.3 will set the playerObjectMovementSpeed to 0.16*1.3
     * which increases it by 30%.
     *
     * @param speedMultiplier - the speedMultiplier will be multiplied by 0.16 which is the constant of
     *                        the DynObject's playerObjectMovementSpeed.
     */

    public void setSpeed(double speedMultiplier) {

        this.speedMultiplier = speedMultiplier;

    }

    public void draw() {

    }

    /**
     * drawLine
     * Draws a line between two positions
     *
     * @param positionA starting point of the line
     * @param positionB ending point of the line
     */

    public void drawLine(Position positionA, Position positionB) {

        graphicalComponent.line(positionA.x, positionA.y, positionB.x, positionB.y);

    }

    /**
     * drawLine
     * Draws a line between two positions
     */

    public void drawLine(double x1, double y1, double x2, double y2) {

        graphicalComponent.line(x1, y1, x2, y2);

    }

    /**
     * setPenColor
     * Changes the pencolor to @param color
     *
     * @param color
     */
    public void setPenColor(Colour color) {

        this.graphicalComponent.setPenColor(color);

    }

    /***
     * playSound() Here you can get creative and implement amazing sound-effects,
     * which will play a big role in simulations or games.
     *
     * @param filename - the path to your audio file. Your soundfiles must have the
     *                 .wav or .au format!
     */

    public void playSound(String filename) {

        MovingPointAudioMethodCollection.play(filename);

    }

    /***
     * backgroundSound() Add background music / sounds which will be playing
     * continuously
     *
     * @param filename - the path to your audio file. Your soundfiles must have the
     *                 .wav or .au format!
     */

    public void backgroundSound(String filename) {

        MovingPointAudioMethodCollection.play(filename);

    }

    /**
     * size() Change the net.bestofcode.MovingPointGameEngine.GraphicalComponent-panels window-size.
     */
    public void size(int canvasWidth, int canvasHeight) {

        graphicalComponent.setCanvasSize(canvasWidth, canvasHeight);
        this.gameWindowWidth = new Width(canvasWidth);
        this.gameWindowHeight = new Height(canvasHeight);

    }

    public void setBackgroundImage(Picture picture) {

        this.backgroundFile = picture;

    }

    /***
     * setXrange() Change the range of the net.bestofcode.MovingPointGameEngine.GraphicalComponent-panel's x-axes
     *
     */
    public void setXrange(double minimumValueOnXAxis, double maximumValueOnXAxis) {

        graphicalComponent.setXscale(minimumValueOnXAxis, maximumValueOnXAxis);
        this.minimumValueOnXAxis = minimumValueOnXAxis;
        this.maximumValueOnXAxis = maximumValueOnXAxis;

    }

    /***
     * setYrange() Change the range of the net.bestofcode.MovingPointGameEngine.GraphicalComponent-panel's y-axes
     *
     * @param a - minimum y-value
     * @param b - maximum y-value
     */
    public void setYrange(double minimumValueOnYAxis, double maximumValueOnYAxis) {

        graphicalComponent.setYscale(minimumValueOnYAxis, maximumValueOnYAxis);
        this.minimumValueOnYAxis = minimumValueOnYAxis;
        this.maximumValueOnYAxis = maximumValueOnYAxis;

    }

    /**
     * keyInput() Returns if a certain key is pressed or not!
     *
     * @param k - KeyCode of the pressed key
     */
    public boolean keyInput(int KeyCode) {

        return graphicalComponent.isKeyPressed(KeyCode);

    }

    /**
     * getPosition
     * Returns the position object
     *
     * @return Returns the position object
     */
    public Position getPosition() {

        return this.position;

    }

    /**
     * getMousePosition()
     * Accesses the DrawComponent to retrieve information about the mouse-Location
     * Returns the mouse-X-location and mouse-Y-location as double-values
     */
    public Position getMousePosition() {

        return new Position(this.graphicalComponent.mouseX(), this.graphicalComponent.mouseY());

    }

    /**
     * zoom() Zoom inside of your net.bestofcode.MovingPointGameEngine.GraphicalComponent-panel by pressing "+" and "-"
     */
    public void zoom() {

        if (allowGrid) {
            if (this.keyInput(521)) {
                zoomFactorAsPercentual -= .05;
            }
            if (this.keyInput(45)) {
                zoomFactorAsPercentual += .05;
            }
        }
    }

    /***
     * grid() net.bestofcode.MovingPointGameEngine.GraphicalComponent a grid of size n*n in your canvas. Also sets a boolean if grid is
     * enabled. If this is the first graphicalComponent of your grid, all statesOfAllGridCells centers will be
     * calculated and stored in the array gridCellCenterCoordinates. Check the comment on gridCellCenterCoordinates to
     * understand how it is used.
     *
     */

    @Refactor
    public void grid(int cellsPerRow) {

        if (this.grid == null) {
            this.grid = Grid.create(cellsPerRow);
        }

        if (this.gameWindowWidth.getValue() != this.gameWindowHeight.getValue()) {

            System.out.println("Error on method grid from net.bestofcode.MovingPointGameEngine.MovingPointGameEngine: You can only create grids if the net.bestofcode.MovingPointGameEngine.GraphicalComponent-panel's sides have the same length!");
            System.exit(0);
        }

        // this will fix a graphics bug
        if ((this.gameWindowWidth.getValue() == 1000 && this.gameWindowHeight == new Height(1000)) && this.allowGrid == false)
            this.size(1000, 1000);

        double step = this.coordinateAxisRange / cellsPerRow;

        // calculate centers of each cell:
        if (!this.allowGrid) {
            int rowIterator = 0;
            int columnIterator = 0;
            this.grid.statesOfAllGridCells = new int[cellsPerRow][cellsPerRow];

            // loop through all statesOfAllGridCells of the grid and set their states to -1
            // so there is no NullPointerException
            for (int t = 0; t < cellsPerRow; t++)
                for (int q = 0; q < cellsPerRow; q++)
                    this.grid.statesOfAllGridCells[t][q] = -1;

            this.grid.gridCellCenterCoordinates = new double[cellsPerRow][cellsPerRow][2];
            for (double secondIterator = this.maximumValueOnYAxis - ((step / 2) / coordinateAxisRange);
                 columnIterator < this.grid.statesOfAllGridCells.length;
                 secondIterator -= (step / coordinateAxisRange)) {

                for (double iterator = ((step / 2) / coordinateAxisRange);
                     rowIterator < this.grid.statesOfAllGridCells[0].length;
                     iterator += (step / coordinateAxisRange)) {

                    this.grid.gridCellCenterCoordinates[rowIterator][columnIterator][0] = iterator;
                    this.grid.gridCellCenterCoordinates[rowIterator][columnIterator][1] = secondIterator;
                    rowIterator++;

                }

                rowIterator = 0;
                columnIterator++;

            }

        }

        this.allowGrid = true;

        double iterator = this.minimumValueOnXAxis;
        while (iterator <= this.maximumValueOnXAxis) {

            graphicalComponent.line(iterator, this.minimumValueOnYAxis, iterator, this.maximumValueOnYAxis);
            graphicalComponent.line(this.minimumValueOnXAxis, iterator, this.maximumValueOnXAxis, iterator);
            iterator += step / coordinateAxisRange;

        }
    }

    /***
     * grid() net.bestofcode.MovingPointGameEngine.GraphicalComponent a grid of size n*n in your canvas surrounded by a border, useful
     * to display texts outside of the grid. Also sets a boolean if grid is enabled.
     * If this is the first graphicalComponent of your grid, all statesOfAllGridCells centers will be calculated
     * and stored in the array gridCellCenterCoordinates. Check the comment on gridCellCenterCoordinates to understand
     * how it is used.
     *
     */

    @Refactor
    public void grid(int cellsPerRow, double border) {

        if (this.grid == null) {
            this.grid = Grid.create(cellsPerRow);
        }

        if (this.gameWindowWidth.getValue() != this.gameWindowHeight.getValue()) {

            System.out.println(
                    "Error on method grid from net.bestofcode.MovingPointGameEngine.MovingPointGameEngine: You can only create grids if the net.bestofcode.MovingPointGameEngine.GraphicalComponent-panel's sides have the same length!");
            System.exit(0);

        }

        // this will fix a graphics bug
        if ((this.gameWindowWidth.getValue() == 1000 && this.gameWindowHeight.getValue() == new Height(1000).getValue()) && this.allowGrid == false)
            this.size(1000, 1000);

        double step = (this.coordinateAxisRange - 2 * border) / cellsPerRow;

        // calculate centers of each cell:
        if (!this.allowGrid) {

            graphicalComponent.setXscale(minimumValueOnXAxis, maximumValueOnXAxis);
            graphicalComponent.setYscale(minimumValueOnYAxis, maximumValueOnYAxis);
            int rowIterator = 0;
            int columnIterator = 0;
            this.grid.statesOfAllGridCells = new int[cellsPerRow][cellsPerRow];

            // loop through all statesOfAllGridCells of the grid and set their states to -1
            // so there is no NullPointerException
            for (int t = 0; t < cellsPerRow; t++)
                for (int q = 0; q < cellsPerRow; q++)
                    this.grid.statesOfAllGridCells[t][q] = -1;

            this.grid.gridCellCenterCoordinates = new double[cellsPerRow][cellsPerRow][2];
            // refactor this term, seems complicated
            for (double secondIterator = this.maximumValueOnYAxis - (((step / 2) + border) / coordinateAxisRange);
                 columnIterator < this.grid.statesOfAllGridCells.length;
                 secondIterator -= step / coordinateAxisRange) {

                for (double iterator = (((step / 2) + border) / coordinateAxisRange);
                     rowIterator < this.grid.statesOfAllGridCells[0].length;
                     iterator += step / coordinateAxisRange) {

                    this.grid.gridCellCenterCoordinates[rowIterator][columnIterator][0] = iterator;
                    this.grid.gridCellCenterCoordinates[rowIterator][columnIterator][1] = secondIterator;
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
     * grid() net.bestofcode.MovingPointGameEngine.GraphicalComponent a grid of size n*n in your canvas surrounded by a border, useful
     * to display texts outside of the grid. Also sets a boolean if grid is enabled.
     * You can change the grid's color by using this method. If this is the first
     * graphicalComponent of your grid, all statesOfAllGridCells centers will be calculated and stored in the
     * array gridCellCenterCoordinates. Check the comment on gridCellCenterCoordinates to understand how it is used.
     *
     */

    @Refactor
    public void grid(int cellsPerRow, double border, Colour color) {

        if (this.grid == null) {
            this.grid = Grid.create(cellsPerRow);
        }

        if (this.gameWindowWidth.getValue() != this.gameWindowHeight.getValue()) {

            System.out.println(
                    "Error on method grid from net.bestofcode.MovingPointGameEngine.MovingPointGameEngine: You can only create grids if the net.bestofcode.MovingPointGameEngine.GraphicalComponent-panel's sides have the same length!");
            System.exit(0);

        }
        // this will fix a graphics bug
        if ((this.gameWindowWidth == new Width(1000) && this.gameWindowHeight.getValue() == new Height(1000).getValue()) && this.allowGrid == false)
            this.graphicalComponent.setCanvasSize(1000, 1000);

        double step = (this.coordinateAxisRange - 2 * border) / cellsPerRow;

        // calculate centers of each cell:
        if (!this.allowGrid) {

            int rowIterator = 0;
            int columnIterator = 0;

            this.grid.statesOfAllGridCells = new int[cellsPerRow][cellsPerRow];

            // loop through all statesOfAllGridCells of the grid and set their states to -1
            // so there is no NullPointerException
            for (int t = 0; t < cellsPerRow; t++)
                for (int q = 0; q < cellsPerRow; q++)
                    this.grid.statesOfAllGridCells[t][q] = -1;

            this.grid.gridCellCenterCoordinates = new double[cellsPerRow][cellsPerRow][2];

            for (double secondIterator = this.maximumValueOnYAxis - ((step / 2) / coordinateAxisRange);

                 columnIterator < this.grid.statesOfAllGridCells.length;
                 secondIterator -= (step / coordinateAxisRange)) {

                for (double iterator = ((step / 2) / coordinateAxisRange);
                     rowIterator < this.grid.statesOfAllGridCells[0].length;
                     iterator += (step / coordinateAxisRange)) {

                    this.grid.gridCellCenterCoordinates[rowIterator][columnIterator][0] = iterator;
                    this.grid.gridCellCenterCoordinates[rowIterator][columnIterator][1] = secondIterator;
                    rowIterator++;
                }
                rowIterator = 0;
                columnIterator++;
            }

        }

        this.allowGrid = true;

        double iterator = this.minimumValueOnXAxis + border;

        while (iterator <= this.maximumValueOnXAxis - border) {

            graphicalComponent.setPenColor(color);
            this.drawLine(iterator, this.minimumValueOnYAxis + border, iterator, this.maximumValueOnYAxis - border);
            this.drawLine(this.minimumValueOnXAxis + border, iterator, this.maximumValueOnXAxis - border, iterator);
            iterator += step / coordinateAxisRange;
            graphicalComponent.setPenColor(GraphicalComponent.LIGHT_GRAY);

        }
    }

    /**
     * drawPicture()
     * Used to draw pictures inside of the canvas. Overrides the graphicalComponent's picture-method
     *
     * @param picture - net.bestofcode.MovingPointGameEngine.Picture object to be drawn
     */
    public void drawPicture(double x, double y, IGraphicalComponent picture) {

        this.graphicalComponent.picture(x, y, picture.getFilePath());

    }

    /**
     * drawBackgroundPicture()
     * Used to draw pictures inside of the canvas. Overrides the graphicalComponent's picture-method
     */
    public void drawBackgroundPicture() {

        this.graphicalComponent.picture(0, 0, this.backgroundFile.getFilePath());

    }

    /**
     * move() This function could also be called "animate" since its only use is in
     * the management of all drawings. This drawings are also supported by the
     * keyTyped() function
     * <p>
     * Basically this function does clear the screen and set its background to
     * LIGHT_GRAY. Afterwards it sets the PenColor to it's instance's @param col and
     * draws a filledCircle at @param position of the current instance. The last step is
     * the directional vector. This function will calculate a point at a certain
     * distance (3.5*playerObjectMovementVector length) and graphicalComponent a line between the @param position of the
     * net.bestofcode.MovingPointGameEngine.MovingPointGameEngine and this calculated point, so we will see a pointer which shows
     * the @param playerObjectMovementVector direction. Additionally the @param picture function will be
     * used to graphicalComponent a certain picture at a position.
     */

    @Refactor
    public void move() {

        if ((Math.abs(this.position.x + this.playerObjectMovementVector.x) < 1) || (Math.abs(this.position.y + this.playerObjectMovementVector.y) < 1)) {

            graphicalComponent.clear(GraphicalComponent.LIGHT_GRAY);
            graphicalComponent.setPenColor(this.movingPointColor);

            if (this.drawMovingPointAtCursor == false) {

                if (backgroundFile != null)
                    this.drawBackgroundPicture();

                if (playerObjectSprite == null && drawMovingPoint)
                    graphicalComponent.filledCircle(this.position.x, this.position.y, 0.02);

                else if (playerObjectSprite != null)
                    this.drawPicture(this.position.x, this.position.y, playerObjectSprite);

                else if (playerObjectSprite == null && !drawMovingPoint);

                if(this.entityList.size() > 0) {
                    int i = 0;
                    for(GameObject gameObject : entityList) {
                        this.graphicalComponent.text(0, i * 0.1, gameObject.getTexture());
                        i++;
                    }
                }

            } else {

                this.position.x = this.getMousePosition().x;
                this.position.y = this.getMousePosition().y;

                if (backgroundFile != null)
                    this.drawBackgroundPicture();

                if (playerObjectSprite == null)
                    graphicalComponent.filledCircle(this.position.x, this.position.y, 0.02);

                else
                    this.drawPicture(this.position.x, this.position.y, playerObjectSprite);
            }

            graphicalComponent.setPenColor(GraphicalComponent.GRAY);
            if (drawMovingPoint) {

                graphicalComponent.line(this.position.x, this.position.y, this.position.x + 2.5 * this.playerObjectMovementVector.x * (1 / 0.16 * this.playerObjectMovementSpeed),
                        this.position.y + 2.5 * this.playerObjectMovementVector.y * (1 / 0.16 * this.playerObjectMovementSpeed));

            }

            if (mouseHover())
                drawInfo();
            zoom();

            // graphicalComponent entitites:

        } else
            return;
    }

    /**
     * distanceTo()
     * <p>
     * This method returns the function of the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine to a certain gameObject. May
     * be useful to create collisions or pathfinding (TODO)
     *
     * @param gameObject - The distance to this gameObject will be calculated
     * @return
     */

    public double distanceTo(GameObject gameObject) {

        double x = this.position.x - gameObject.position.x;
        double y = this.position.y - gameObject.position.y;

        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * printPosition()
     * <p>
     * Use this method to print the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine's location into the console.
     */

    public void printPosition() {

        System.out.println("x = " + decimalNumberFormat.format(this.position.x) + " y = " + decimalNumberFormat.format(this.position.y));

    }

    /**
     * vecAdd() Used to add the @param playerObjectMovementVector components to the @param position
     * components. Simple vector addition.
     *
     * @param movingPoint shows the function which net.bestofcode.MovingPointGameEngine.MovingPointGameEngine's location has to be updated.
     */

    public void vecAdd(MovingPointGameEngine movingPoint) {

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

    /**
     * nearestCell() Apply this method to your mouseEvent and change a cell's state
     * while clicking inside of it. The borders will be used to determine in which
     * cell the click was performed
     *
     * @param state - use an integer as the clicked cell's state You can implement
     *              your own states in your program and use them as an overlay for
     *              the integers.
     */
    public void nearestCell(int state) {

        int N = this.grid.statesOfAllGridCells.length;

        double step = (this.coordinateAxisRange / N) / coordinateAxisRange;

        int tmpX = 0;
        int tmpY = 0;

        for (int secondIterator = 0; secondIterator < N; secondIterator++)
            if (this.getMousePosition().x > secondIterator * step && this.getMousePosition().x < (secondIterator + 1) * step)
                tmpX = secondIterator;

        for (int iterator = 0; iterator < N; iterator++)
            if (this.getMousePosition().y > iterator * step && this.getMousePosition().y < (iterator + 1) * step)
                tmpY = N - iterator - 1;

        this.grid.statesOfAllGridCells[tmpX][tmpY] = state;
        System.out.println(tmpX + " " + tmpY);

    }

    /**
     * addGameObject()
     * <p>
     * Implement entities according to the examples "net.bestofcode.MovingPointGameEngine.GameObject.java", "EntityWall.java"
     * and add them to the JPanel by using this function
     * <p>
     * Note: The entity-system is implemented by using a linked list, which can
     * dynamically store all types of entities (subclasses) and graphicalComponent them.
     *
     * @param gameObject - The entity which shall be added to the JPanel.
     */

    public void addGameObject(GameObject gameObject) {

        entityList.add(gameObject);

    }

    /**
     * getGraphicalComponent()
     * <p>
     * Return the DrawPanel of your net.bestofcode.MovingPointGameEngine.MovingPointGameEngine to implement new drawing functions
     * in your subclasses of this library. This return can be caught and stored in a
     * new net.bestofcode.MovingPointGameEngine.GraphicalComponent instance to get access to all existing drawing functions and also to
     * the MouseListener and KeyListener, since they are connected to the DrawPanel.
     *
     * @return
     */

    public GraphicalComponent getGraphicalComponent() {

        return graphicalComponent;

    }

    /**
     * mouseDragged() will be used to spawn entities.
     *
     * @param x
     * @param y
     */

    public void mouseDragged(double x, double y) {

        graphicalComponent.filledCircle(x, y, 0.002);

    }

    /**
     * mouseReleased
     * This method can be overridden
     */
    public void mouseReleased(double x, double y) {
    }

    @Remove
    /**
     * keyReleased() Resets the boolean for each key when the key is no longer
     * pressed. This will help to create a Movement-system in which multiple keys
     * can be pressed at the same time.
     *
     * @param keycode - ID of the released key.
     */

    public void keyReleased(int keycode) {

        if (keycode == keyUp)
            this.keyboardManager.keyUpPressed = false;
        else if (keycode == keyLeft)
            this.keyboardManager.keyLeftPressed = false;
        else if (keycode == keyRight)
            this.keyboardManager.keyRightPressed = false;
        else if (keycode == keyDown)
            this.keyboardManager.keyDownPressed = false;

    }

    /**
     * keyPressed() The main controller for the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine. Its read the keyInput
     * as an integer and compares it to the ASCII-values to check which control-key
     * is pressed.
     * <p>
     * 87 --> W 65 --> A 68 --> D
     *
     * @param keycode
     */

    public void keyPressed(int keycode) {

        if (useRelativeMovement) {

            if (keycode == keyUp) {

                for (int iterator = 0; iterator < this.grid.statesOfAllGridCells.length; iterator++)
                    for (int secondIterator = 0; secondIterator < this.grid.statesOfAllGridCells.length; secondIterator++)
                        this.grid.gridCellCenterCoordinates[iterator][secondIterator][1] -= 0.5 * (coordinateAxisRange / this.grid.statesOfAllGridCells.length) / coordinateAxisRange;

                this.position.y -= (coordinateAxisRange / this.grid.statesOfAllGridCells.length) / coordinateAxisRange;
            } else if (keycode == keyDown) {

                for (int iterator = 0; iterator < this.grid.statesOfAllGridCells.length; iterator++)
                    for (int secondIterator = 0; secondIterator < this.grid.statesOfAllGridCells.length; secondIterator++)
                        this.grid.gridCellCenterCoordinates[iterator][secondIterator][1] += 0.5 * (coordinateAxisRange / this.grid.statesOfAllGridCells.length) / coordinateAxisRange;

                this.position.y += (coordinateAxisRange / this.grid.statesOfAllGridCells.length) / coordinateAxisRange;
            } else if (keycode == keyLeft) {

                for (int iterator = 0; iterator < this.grid.statesOfAllGridCells.length; iterator++)
                    for (int secondIterator = 0; secondIterator < this.grid.statesOfAllGridCells.length; secondIterator++)
                        this.grid.gridCellCenterCoordinates[iterator][secondIterator][0] += 0.5 * (coordinateAxisRange / this.grid.statesOfAllGridCells.length) / coordinateAxisRange;

                this.position.x += (coordinateAxisRange / this.grid.statesOfAllGridCells.length) / coordinateAxisRange;
            } else if (keycode == keyRight) {

                for (int iterator = 0; iterator < this.grid.statesOfAllGridCells.length; iterator++)
                    for (int secondIterator = 0; secondIterator < this.grid.statesOfAllGridCells.length; secondIterator++)
                        this.grid.gridCellCenterCoordinates[iterator][secondIterator][0] -= 0.5 * (coordinateAxisRange / this.grid.statesOfAllGridCells.length) / coordinateAxisRange;

                this.position.x -= (coordinateAxisRange / this.grid.statesOfAllGridCells.length) / coordinateAxisRange;
            }

        } else {

            if (keycode == keyUp) {
                this.vecAdd(this);
                this.keyboardManager.keyUpPressed = true;
            } else if (keycode == keyLeft) {
                this.rotate(0.1);
                this.keyboardManager.keyLeftPressed = true;
            } else if (keycode == keyRight) {
                this.rotate(-0.1);
                this.keyboardManager.keyRightPressed = true;
            } else if (keycode == keyDown) {
                this.keyboardManager.keyDownPressed = true;
            }
        }
    }

    /**
     * run() Use this function to implement the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine library on the most
     * simple way possible. An example implementation will be given with the
     * ExtendTest.java
     * <p>
     * run() initializes the default directional vector and starts a while loop,
     * which will graphicalComponent the net.bestofcode.MovingPointGameEngine.MovingPointGameEngine and run all required functions in the
     * background, without implementing it in your program. If you wish to implement
     * the function by yourself, override them or copy the code of the run()
     * function to your program and change, what the loop does by yourself.
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

    /**
     * mouseHover
     * Checks if the net.bestofcode.MovingPointGameEngine.Player hovers the PlayerObject
     *
     * @return true if cursor is very close to the PlayerObject or false i
     */
    public boolean mouseHover() {
        // if the hover-menu is disabled, nothing will happen
        if (this.show == false)
            return false;

        double x = this.position.x - this.getMousePosition().x;
        double y = this.position.y - this.getMousePosition().y;

        double distanceToPlayerObject = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

        return distanceToPlayerObject <= 0.02;
    }

    /**
     * drawInfo
     * This menu shows an example how to implement UI. This will also be the template
     * for the upcoming UI-System
     */
    public void drawInfo() {

        graphicalComponent.setPenColor(GraphicalComponent.LIGHT_GRAY);
        graphicalComponent.filledRectangle(this.position.x - 0.15, this.position.y + 0.3, 0.025 * 9, 0.025 * 5);
        graphicalComponent.setPenColor(GraphicalComponent.BLACK);
        graphicalComponent.text(this.position.x - 0.15, this.position.y + 0.4, "MovingPointGameEngine");
        graphicalComponent.text(this.position.x - 0.15, this.position.y + 0.35,
                "x = " + decimalNumberFormat.format(this.position.x) + " y = " + decimalNumberFormat.format(this.position.y));
        graphicalComponent.text(this.position.x - 0.15, this.position.y + 0.25, "" + this.movingPointColor);
        graphicalComponent.text(this.position.x - 0.15, this.position.y + 0.2,
                "Vector = (" + decimalNumberFormat.format(this.playerObjectMovementVector.x) + ", " + decimalNumberFormat.format(this.playerObjectMovementVector.y) + ")");

    }

    /**
     * setSpawn
     * With setSpawn you will set the starting location of your
     * net.bestofcode.MovingPointGameEngine.MovingPointGameEngine.
     *
     * @param x - double in range [0,1]
     * @param y - double in range [0,1]
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

        graphicalComponent.show();
        graphicalComponent.pause(timeInMilliSeconds);
        graphicalComponent.enableDoubleBuffering();

    }
}
