package net.bestofcode.MovingPoint.audio;

/** net.bestofcode.MovingPointGameEngine.Sound
 *  The net.bestofcode.MovingPointGameEngine.Sound-object is used to play sounds inside of your games
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

    // This method is used by net.bestofcode.MovingPointGameEngine.MovingPointGameEngine to receive the wrapped string
    public String getFilePath(){
        return this.filePath;
    }
}