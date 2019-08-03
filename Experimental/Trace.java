public class Trace extends MovingPoint{

    // counter, if n gets 5 I want to draw a circle
    // using a public variable, so the new keyPressed-function is able to count
    public static int n = 0;

    public static void main(String[] args){
    
    // instantiate the MovingPoint object to get access on the library
    // this is instantiated public, so the keyPressed-function can access it
    MovingPoint g = new MovingPoint();

    // set the MovingPoint's spawn to the center (this is not needed)
    g.setSpawn(0, 0);

    // set the MovingPoint's speed
    g.setSpeed(1);

    // get access on the DrawComponent
    Draw d = g.getDraw();

    double[] tmp = g.loc;
    double[] tmp2 = g.loc;

    

    

    // main-loop
    while(true){

        tmp = g.loc;
        if(n == 5){
            n = 0;
            d.line(g.loc[0], g.loc[1], tmp2[0], tmp2[1]);
            tmp2 = g.loc;
        }
        g.move();
        g.sleep(50);
    }

    }
}
