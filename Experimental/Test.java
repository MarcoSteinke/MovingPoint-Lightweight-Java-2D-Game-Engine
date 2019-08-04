public class Test {

    public static void main(String[] args) {
        MovingPoint movingPoint = new MovingPoint();

        while(true){
            movingPoint.move();
            movingPoint.grid(3, 0.1);
            movingPoint.sleep(50);
        }
    }
}