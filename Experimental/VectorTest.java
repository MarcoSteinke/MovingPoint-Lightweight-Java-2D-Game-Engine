import src.Vector;

public class VectorTest{
    
    public static void main(String[] args) {
        MovingPoint myVectorTest = new MovingPoint(500, 500);

        Vector<Double> vector = new Vector<>((double) 1, (double)2, (double)3);

        System.out.println(vector.dimension());
        System.out.println(vector.length());
    }
}