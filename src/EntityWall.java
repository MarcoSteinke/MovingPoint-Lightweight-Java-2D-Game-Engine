package src;

public class EntityWall extends Entity{


    public EntityWall(double x, double y, Draw d){
        super(x, y);
        super.draw = d;
    }

    public EntityWall(){
        this.loc[0] = this.loc[1];
    }

    public void draw(){
        this.draw.filledSquare(this.loc[0], this.loc[1], 0.12/2);
    }
}