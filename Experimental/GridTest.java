public class GridTest{

    public static double x = 0;

    public static void markCell(MovingPoint c, Draw d){
        if(d.isMousePressed()){
            c.nearestCell(1);
        }
        /*if(c.keyInput(87)){
            for(int i = 0; i < c.cells.length; i++)
                for(int j = 0; j < c.cells.length; j++)
                    c.cellsXY[i][j][0] -= (c.span/5)/c.span;
        }*/
        for(int i = 0; i < c.cells.length; i++)
            for(int j = 0; j < c.cells.length; j++)
                if(c.cells[j][i] == 1) d.text(c.cellsXY[j][i][0], c.cellsXY[j][i][1], "+");
    }

    public static void main(String[] args) {
        MovingPoint c = new MovingPoint();
        Draw d = c.getDraw();
        c.size(500,500);
        c.allowMP = true;
        c.size(500, 500);
        double[] vec = {0, 0};
        c.vec2D = vec;
        
        //d.setXscale(c.Xmin, c.Xmax);
        //d.setYscale(c.Ymin, c.Ymax);
        c.useRelativeMovement = true;

        while(true){
            c.move();
            c.grid(5);
            d.text(c.cellsXY[3][4][0], c.cellsXY[3][4][1], "+");
            markCell(c, d);
            c.sleep(200);
            
        }
    }
}