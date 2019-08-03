public class TicTacToe extends MovingPoint{

    public static boolean runGame = true;
    public static int winner = -1;

    /** checkMouse()
     *  This function is called on each frame and
     *  checks if the mouse is pressed.
     *  Case (1): Mouse pressed:
     *      - check if it is player1's or player2's
     *        turn by diving n (turn number) by 2
     *        and checking if rest is 0 or 1.
     *      - if 0, draw a cross, if 1 draw a circle.
     *      - increment turn number by 1 and let the
     *      - program sleep for 0.1s so only one picture
     *        will be drawn.
     *      - return the new n.
     * 
     *  Case(2): Mouse not pressed:
     *      - return n unchanged.
     * 
     *  @param lay - MovingPoint-instance used in this game
     *  @param canvas - Draw-panel of the MovingPoint 
     *                  (could also be read through lay.getDraw())
     *  @param n - turn-number
     *         
     */

    public static int checkMouse(MovingPoint lay, Draw canvas, int n){

            if(canvas.isMousePressed()){
                if(n % 2 == 0) lay.nearestCell(1);
                else lay.nearestCell(0);
                n++;
                lay.sleep(1);
            }
            for(int i = 0; i < lay.cells.length; i++)
                for(int j = 0; j < lay.cells.length; j++){
                    if(lay.cells[j][i] == 0)      canvas.picture(lay.cellsXY[j][i][0], lay.cellsXY[j][i][1], "circle.png");
                    else if(lay.cells[j][i] == 1) canvas.picture(lay.cellsXY[j][i][0], lay.cellsXY[j][i][1], "cross.png");
                }      
            return n;
    }

    /** checkWinner()
     *  Read all cells and check all winning-patterns for both players (states).
     * 
     *  @param lay - The MovingPoint-instance used in this game. 
     */

    public static void checkWinner(MovingPoint lay){
        for(int q = 0; q <= 1; q++) {
            if(lay.cells[0][0] == q &&
               lay.cells[1][0] == q &&
               lay.cells[2][0] == q) {
                    winner = q;
            }
            else if(lay.cells[0][1] == q &&
                    lay.cells[1][1] == q &&
                    lay.cells[2][1] == q) {
                        winner = q;
                        runGame = false;
                    }
            else if(lay.cells[0][2] == q &&
                    lay.cells[1][2] == q &&
                    lay.cells[2][2] == q) {
                        winner = q;
                        runGame = false;
                    }
            else if(lay.cells[0][0] == q &&
                    lay.cells[0][1] == q &&
                    lay.cells[0][2] == q) {
                        winner = q;
                        runGame = false;
                    }
            else if(lay.cells[1][0] == q &&
                    lay.cells[1][1] == q &&
                    lay.cells[1][2] == q) {
                        winner = q;
                        runGame = false;
                    }
            else if(lay.cells[2][0] == q &&
                    lay.cells[2][1] == q &&
                    lay.cells[2][2] == q) {
                        winner = q;
                        runGame = false;
                    }
            else if(lay.cells[0][0] == q &&
                    lay.cells[1][1] == q &&
                    lay.cells[2][2] == q) {
                        winner = q;
                        runGame = false;
                    }
            else if(lay.cells[2][0] == q &&
                    lay.cells[1][1] == q &&
                    lay.cells[0][2] == q) {
                        winner = q;
                        runGame = false;
                    }    
        }     
    }

    public static void main(String[] args) {

        //instantiate a new MovingPoint named layout2D
        MovingPoint layout2D = new MovingPoint();

        // instantiate an object to gain access on the Draw-panel
        Draw canvas = layout2D.getDraw();

        // disable the mouseHover-event.
        layout2D.showHover = false;

        // Create a 500x500 canvas
        canvas.setCanvasSize(500, 500);

        /* Note: We set the canvas to a size of 500x500 and will use a border of 100 pixels which results
                 in 300 pixels we can use to draw our grid in. Thatswhy each cell of the grid will have
                 the size of 100x100 pixels.
        */

        // The next steps are initiated to disable the MovingPoint. You could also set its image to an
        // empty file.

        // set the color of MovingPoint to the background's color.
        layout2D.col = new Colour(185, 185, 185);

        // set the length of the MovingPoint's directional vector to 0
        double[] vec = {0, 0};
        layout2D.vec2D = vec;

        // set the MovingPoint's speed to 0
        layout2D.setSpeed(0);

        canvas.picture(0, 0, "space.png");

        // set the turn-counter to 0
        int n = 1;

        while(runGame){  
            layout2D.move();
            layout2D.grid(3, 0, new Colour(255, 255, 255));
            n = checkMouse(layout2D ,canvas, n);
            checkWinner(layout2D);
            layout2D.sleep(100);           
        }
        System.out.println("end");
        canvas.clear(canvas.LIGHT_GRAY);
        layout2D.move(); 
        String res = "";
        if(winner == 0) res = "Congratulations to Circle for winning this game!";
        else if (winner == 1) res = "Congratulations to Cross for winning this game!";
        canvas.text(0, 0, res);
        layout2D.move(); 
        layout2D.sleep(100); 
        
    }
}