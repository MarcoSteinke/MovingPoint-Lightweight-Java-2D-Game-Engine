package src;

/** Sound
 *  The Sound-object is used to play sounds inside of your games
 *  You can simply place your sound-files anywhere in your project and 
 *  link it via the Constructor
 */
public class Sound{

    // wrapped filepath
    private String filePath;

    // Constructor is used to wrap the filepath
    public Sound(String filePath){
        this.filePath = "../" + filePath;
    }

    // This method is used by MovingPoint to receive the wrapped string
    public String getFilePath(){
        return this.filePath;
    }
}