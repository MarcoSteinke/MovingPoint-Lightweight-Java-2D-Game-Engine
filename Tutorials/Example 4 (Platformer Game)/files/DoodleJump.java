public class DoodleJump extends MovingPoint{

    public static String picture = "Doodler.png";
    public static boolean up = true;
    public static double jumpSpeed = 0.035;


    public static void moveToMouse(Draw draw, MovingPoint doodle){
        if(Math.abs(doodle.loc[0] - draw.mouseX()) < 0.05)
            picture = "Doodler.png";
        else if(doodle.loc[0] > draw.mouseX() && 
                Math.abs(doodle.loc[0] - draw.mouseX()) > 0.05) {
            picture = "Doodler2.png";
            doodle.loc[0] -= 0.015;
        }
        else if(doodle.loc[0] < draw.mouseX() && 
                Math.abs(doodle.loc[0] - draw.mouseX()) > 0.05){
            picture = "Doodler.png";
            doodle.loc[0] += 0.015;
        }
    }

    public static void jump(MovingPoint doodle, boolean var){
        if(var && doodle.loc[1] < 0.2)
            doodle.loc[1] += jumpSpeed;
        else if(doodle.loc[1] >= 0.2 && var == true)
            up = false;
        if(doodle.loc[1] >= -1.5 && var == false)
            doodle.loc[1] -= jumpSpeed;
        if(doodle.loc[1] <= -0.7 && doodle.loc[0] > -0.3 && doodle.loc[0] < 0.3)
            up = true;


        if(doodle.loc[1] < -1){

            // END SCREEN

            System.out.println("Lost");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        MovingPoint doodle = new MovingPoint();
        Draw draw = doodle.getDraw();
        doodle.setSpawn(0, -0.7);
        doodle.setSpeed(0);
        double[] dummy = {0, 0};
        doodle.vec2D = dummy;
        doodle.showHover = false;
        doodle.drawAtMouse = false;
        doodle.imgFile = "Doodler.png";
        doodle.backgroundFile = "doodle-background.png";
        draw.setCanvasSize(600, 800);
        draw.setXscale(-1, 1);
        draw.setYscale(-1, 1);

        while(true){
            moveToMouse(draw, doodle);
            jump(doodle, up);
            doodle.imgFile = picture;
            doodle.move();
            draw.picture(0, -0.77, "platform.png");
            doodle.sleep(1);
        }
        
        
    }
}