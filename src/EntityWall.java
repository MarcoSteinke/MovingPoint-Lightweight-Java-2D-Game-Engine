package src;

@Remove
public class EntityWall extends GameObject{


    public EntityWall(double x, double y, GraphicalComponent d){
        super(x, y);
        super.graphicalComponent = d;
    }

    public EntityWall(){
        this.position.x = this.position.y;
    }

    public void draw(){
        this.graphicalComponent.filledSquare(this.position.x, this.position.y, 0.12/2);
    }
}