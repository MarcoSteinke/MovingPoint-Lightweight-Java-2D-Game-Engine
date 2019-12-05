/** Picture
 *  The Picture-object is used to show pictures inside of your games
 *  You can simply place your Picture-files anywhere in your project and 
 *  link it via the Constructor
 */
public class Picture implements IGraphicalComponent{

    // wrapped filepath
    private String filePath;

    // Constructor is used to wrap the filepath
    public Picture(String filePath){
        this. filePath = "../" + filePath;
    }

    // This method is used by MovingPoint to receive the wrapped string
    public String getFilePath(){
        return this.filePath;
    }
}