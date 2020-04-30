/** Sprite
 *  The Sprite-object is used to show pictures inside of your games
 *  You can simply place your Sprite-files anywhere in your project and 
 *  link it via the Constructor
 */
public class Sprite implements IGraphicalComponent{

    // wrapped filepath
    private String filePath;

    // Constructor is used to wrap the filepath
    public Sprite(String filePath){
        this. filePath = "../" + filePath;
    }

    // This method is used by MovingPoint to receive the wrapped string
    public String getFilePath(){
        return this.filePath;
    }
}