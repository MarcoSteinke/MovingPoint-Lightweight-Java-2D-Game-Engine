package net.bestofcode.MovingPoint;

/** net.bestofcode.MovingPoint.Picture
 *  The net.bestofcode.MovingPoint.Picture-object is used to show pictures inside of your games
 *  You can simply place your net.bestofcode.MovingPoint.Picture-files anywhere in your project and
 *  link it via the Constructor
 */
public class Picture implements IGraphicalComponent{

    // wrapped filepath
    private String filePath;

    // Constructor is used to wrap the filepath
    public Picture(String filePath){
        this. filePath = "../" + filePath;
    }

    // This method is used by net.bestofcode.MovingPoint.MovingPoint to receive the wrapped string
    public String getFilePath(){
        return this.filePath;
    }
}