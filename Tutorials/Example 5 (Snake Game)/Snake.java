import javax.swing.JFrame;

public class Snake implements DrawListener{

    public Draw draw = new Draw("Snake Game in Java using DrawListener, Draw");
    
    /* Declaration of important variables */
    private double x = 0.575;
    private double y = 0.575;
    public double foodX = 0.975 - ((int) (Math.random()*15)) * 0.05;
    public double foodY = 0.975 - ((int) (Math.random()*15)) * 0.05;
    public int length = 3;
    private double[] tailX = new double[400]; // this saves the locations of the tail-parts.
    private double[] tailY = new double[400];
    private int dir  = (int) (Math.random()*4); // move into random direction after start
    private int bonus = 0;
    private Boolean game = true;

    public Snake(){
        draw.addListener( this ); //Register
        draw.setXscale(0, 1);
        draw.setYscale(0, 1);
        draw.setCanvasSize(600, 600);
        draw.clear(draw.GRAY);
        draw.filledRectangle(x, y, 0.025, 0.025);
        draw.setPenColor(draw.RED);
        draw.filledCircle(foodX, foodY, 0.025);
        draw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // user input:
    public void keyTyped(char c){
        if(c == 'w'      && dir != 3)   dir = 0;
        else if(c == 'a' && dir != 2)   dir = 1;
        else if(c == 's' && dir != 0)   dir = 3;
        else if(c == 'd' && dir != 1)   dir = 2;
    }

    // move the snake:
    public void move(int d, Snake m){
        draw.setPenColor(draw.BLACK);
        if(d == 0)        y += 0.05;
        else if(d == 1)   x -= 0.05;
        else if(d == 2)   x += 0.05;
        else if(d == 3)   y -= 0.05;

        draw.clear( draw.GRAY );

        m.tailX[0] = m.x;
        m.tailY[0] = m.y;
        m.drawFood();

        for(int i = 0; i < m.length; i++){
            draw.filledRectangle(m.tailX[i], m.tailY[i], 0.025, 0.025);
        }
        for(int j = m.length; j > 0; j--){
            m.tailX[j] = m.tailX[j-1];
            m.tailY[j] = m.tailY[j-1];
        }
    }

    public void clear(){
        draw.clear( draw.GRAY );
    }

    // check if the snake is out of screen:
    public boolean inBounds(){
        if(x < 1 && x > 0 && y < 1 && y > 0) return true;
        else return false;
    }

    // print text if inBounds() is false:
    public void print(String t){
        draw.text(0.5, 0.5, t);
    }

    public void printXY(String t, double x, double y){
        draw.setPenColor( draw.WHITE );
        draw.text(x, y, t);
        draw.setPenColor( draw.BLACK );
    }

    public void sleep(int t){

        draw.show();
        draw.pause(t);
        draw.enableDoubleBuffering();
    }


    // draw the food:
    public void drawFood(){
        draw.setPenColor(draw.RED);
        draw.filledCircle(foodX, foodY, 0.009);
        draw.setPenColor(draw.BLACK);
    }

    // spawn a new food after pickup
    public void respawnFood(Snake m){
        foodX = 0.975 - ((int) (Math.random()*15)) * 0.05;
        foodY = 0.975 - ((int) (Math.random()*15)) * 0.05;
        for(int j = m.length; j > 1; j--){
            if(foodX == m.tailX[j] && foodY == m.tailY[j])
                respawnFood(m);
        }
    }

    public void checkCollision(Snake m){
        for(int j = m.length; j > 1; j--)
            if(m.x == m.tailX[j] && m.y == m.tailY[j]) m.game = false;
    }

    public static void main(String[] args) {
        Snake m = new Snake();

        // set the whole array as "unused" !s:
        for(int i = 0; i < m.tailX.length; i++){
            m.tailX[i] = -1;
            m.tailY[i] = -1;
        }

        // main-loop:
        while(m.game && m.inBounds()){
            m.checkCollision(m);
            m.drawFood();
            m.move(m.dir, m);
            m.printXY("Score: " + (((int) ((m.length * 20 + m.length * m.bonus) * 1.2)) - 72), 0.5, 0.9);

            //check if player hit the food:
            if((Math.abs(m.x - m.foodX)) < 0.01 && Math.abs((m.y - m.foodY)) < 0.01){
                m.respawnFood(m);
                m.bonus++;
                m.length += 1;
            }
            m.sleep(200);
        }
        m.draw.disableDoubleBuffering();
        //out of screen:
        m.clear();
        m.printXY("You lost the game", 0.5, 0.55);
        m.printXY("Score: " + (((int) ((m.length * 20 + m.length * m.bonus) * 1.2)) - 72), 0.5, 0.5);
        m.printXY("Please restart the game by running this class again.", 0.5, 0.35);
        m.printXY("This will be patched in the future", 0.5, 0.30);
    }

    public void mousePressed(double x, double y){}

    public void mouseDragged(double x, double y){}

    public void mouseReleased(double x, double y){}

    public void keyPressed(int keycode){}

    public void keyReleased(int keycode){}
}