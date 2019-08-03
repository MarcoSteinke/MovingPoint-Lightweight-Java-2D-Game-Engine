package src;

public class Node{
    public Entity element    = null;
    public Node next         = null;

    public Node(Entity e, Node n){
        this.element = e;
        this.next    = n;
    }

    public Node(Entity e){
        this.element = e;
    }

    

}