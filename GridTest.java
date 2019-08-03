import src.Colour;
import src.Draw;

public class GridTest extends MovingPoint {

    public static void main(String[] args) {
        MovingPoint m = new MovingPoint();
        Draw d = m.getDraw();

        while(true){
            m.move();
            m.grid(3, 0.5);
            m.sleep(50);
        }
    }
}