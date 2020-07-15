package net.bestofcode.movingpoint.event.keyboardEvent;

import net.bestofcode.movingpoint.event.keyboardEvent.configuration.DefaultKey;
import net.bestofcode.movingpoint.event.keyboardEvent.configuration.KeyboardConfiguration;

public class KeyboardManager {

    public KeyboardConfiguration keyboardConfiguration;

    public boolean keyUpPressed = false;
    public boolean keyLeftPressed = false;
    public boolean keyRightPressed = false;
    public boolean keyDownPressed = false;

    public void setKeyboardConfiguration(KeyboardConfiguration keyboardConfiguration) {
        this.keyboardConfiguration = keyboardConfiguration;
    }

    public KeyboardManager(KeyboardConfiguration keyboardConfiguration) {
        this.keyboardConfiguration = keyboardConfiguration;
    }

    /**
     * keyReleased() Resets the boolean for each key when the key is no longer
     * pressed. This will help to create a Movement-system in which multiple keys
     * can be pressed at the same time.
     *
     * @param keycode - ID of the released key.
     */

    public void keyReleased(int keycode) {

        if (keycode == this.keyboardConfiguration.getKeyForAction(DefaultKey.MOVE_UP))
            keyUpPressed = false;
        else if (keycode == this.keyboardConfiguration.getKeyForAction(DefaultKey.MOVE_LEFT))
            keyLeftPressed = false;
        else if (keycode == this.keyboardConfiguration.getKeyForAction(DefaultKey.MOVE_RIGHT))
            keyRightPressed = false;
        else if (keycode == this.keyboardConfiguration.getKeyForAction(DefaultKey.MOVE_DOWN))
            keyDownPressed = false;

    }


}
