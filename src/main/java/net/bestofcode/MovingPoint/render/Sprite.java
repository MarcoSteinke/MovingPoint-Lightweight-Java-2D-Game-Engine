package net.bestofcode.MovingPoint.render;

/** net.bestofcode.MovingPointGameEngine.Sprite
 *  The net.bestofcode.MovingPointGameEngine.Sprite-object is used to show pictures inside of your games
 *  You can simply place your net.bestofcode.MovingPointGameEngine.Sprite-files anywhere in your project and
 *  link it via the Constructor
 */
public class Sprite implements IGraphicalComponent {

    // wrapped filepath
    private String filePath;

    // Constructor is used to wrap the filepath
    public Sprite(String filePath){
        this. filePath = "../" + filePath;
    }

    // This method is used by net.bestofcode.MovingPointGameEngine.MovingPointGameEngine to receive the wrapped string
    public String getFilePath(){
        return this.filePath;
    }
}