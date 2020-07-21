package net.bestofcode.MovingPoint.event.keyboardEvent;

import net.bestofcode.MovingPoint.event.keyboardEvent.configuration.DefaultKey;
import net.bestofcode.MovingPoint.event.keyboardEvent.configuration.KeyboardConfiguration;
import net.bestofcode.MovingPoint.render.GraphicalComponent;

public class KeyboardManager {

    private final GraphicalComponent graphicalComponent;
    public KeyboardConfiguration keyboardConfiguration;

    public boolean keyUpPressed = false;
    public boolean keyLeftPressed = false;
    public boolean keyRightPressed = false;
    public boolean keyDownPressed = false;

    public KeyboardManager(KeyboardConfiguration keyboardConfiguration, GraphicalComponent graphicalComponent) {
        this.keyboardConfiguration = keyboardConfiguration;
        this.graphicalComponent = graphicalComponent;
    }

    public void setKeyboardConfiguration(KeyboardConfiguration keyboardConfiguration) {
        this.keyboardConfiguration = keyboardConfiguration;
    }

    public char getConfigurationForKey(DefaultKey defaultKey) {
        return this.keyboardConfiguration.getKeyForAction(defaultKey);
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

    public boolean isKeyPressed(int keycode) {
        return this.graphicalComponent.isKeyPressed(keycode);
    }


}
