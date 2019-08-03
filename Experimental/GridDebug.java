public class GridDebug extends MovingPoint{


    public static void main(String[] args) {
        MovingPoint gridDebugger = new MovingPoint();

        Draw d = gridDebugger.getDraw();
        gridDebugger.size(500, 500);
        double[][][] centers = gridDebugger.cellsXY;
        gridDebugger.allowMP = false;
        gridDebugger.showHover = false;
        d.setPenRadius(1);
        gridDebugger.setXrange(0, 1);
        gridDebugger.setYrange(0, 1);
         
        gridDebugger.grid(3);

        while(true){
            gridDebugger.move();
            gridDebugger.grid(3);
            for(int i = 0; i < 3; i++)
                for(int j = 0; j < 3; j++)
                    d.point(gridDebugger.cellsXY[i][j][0], gridDebugger.cellsXY[i][j][1]);
            gridDebugger.sleep(50);
        }
    }
}