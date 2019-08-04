import src.Draw;


/** Example x: RotatedImages
 *  This Example teaches you how to deal with rotating images and calculation of degrees.
 *  
 * 
 *  + TODO: Implement method which translates vec2D into degree (direction)
 */
class RotatedImages extends MovingPoint{
    
    /* This function is used to override the old controls and create
       your own controls


       for a better understanding, I replaced the keycodes with their character.
       This should give a quick overview of how to add more keys to your controls*/
       static int W = 87;
       static int A = 65;
       static int S = 83;
       static int D = 68;
   
       static double checkKeyPressed(Draw d, MovingPoint spaceShip, double degree){
           // events for "W" and "S" optional, toggle them by removing comment frame:
           if (d.isKeyPressed(A))      return degree + 10;
           else if (d.isKeyPressed(D)) return degree - 10;
           return degree;
       }
   
    
    
    public static void main(String[] args) {
        
        MovingPoint mov = new MovingPoint();
        Draw graphic = mov.getDraw();

        mov.setSpawn(0,0);
        mov.showHover = false;
        mov.backgroundFile = "../space.png"; 
        double degree = 0;

        while(true){
            mov.move();
            System.out.println(degree);
            graphic.picture(0,0,"../spaceship.png", degree = checkKeyPressed(graphic, mov, degree));
            mov.sleep(50);
        }
    }
}