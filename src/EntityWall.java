package src;

@Remove
public class EntityWall extends Entity{


    public EntityWall(double x, double y, Draw d){
        super(x, y);
        super.draw = d;
    }

    public EntityWall(){
        this.position.x = this.position.y;
    }

    public void draw(){
        this.draw.filledSquare(this.position.x, this.position.y, 0.12/2);
    }
}