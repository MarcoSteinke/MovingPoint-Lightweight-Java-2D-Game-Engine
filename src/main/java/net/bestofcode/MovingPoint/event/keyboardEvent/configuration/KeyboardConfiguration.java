package net.bestofcode.MovingPoint.event.keyboardEvent.configuration;

import java.util.HashMap;
import java.util.Map;

public class KeyboardConfiguration implements Configuration {

    private final HashMap<DefaultKey, Character> personKeyConfiguration;

    private KeyboardConfiguration(Key... keys) {
        if(keys.length == 1 && keys[0].getDefaultKey() == DefaultKey.DEFAULT) {
            // fill HashMap with default keys:
            this.personKeyConfiguration = new HashMap<DefaultKey, Character>();
            this.setKey(DefaultKey.MOVE_UP, 87);
            this.setKey(DefaultKey.MOVE_DOWN, 83);
            this.setKey(DefaultKey.MOVE_LEFT, 65);
            this.setKey(DefaultKey.MOVE_RIGHT, 68);
            this.setKey(DefaultKey.JUMP, 32);
            this.setKey(DefaultKey.INVENTORY, 73);
            this.setKey(DefaultKey.MAP, 77);
            this.setKey(DefaultKey.MENU, 27);
            this.setKey(DefaultKey.USE, 81);
            this.setKey(DefaultKey.PICKUP, 69);

        } else {

            this.personKeyConfiguration = new HashMap<>();
            for(Key key : keys) {
                personKeyConfiguration.put((DefaultKey) key.getDefaultKey(), key.getKeyBinding());
            }
        }
    }

    public void setKey(DefaultKey defaultKey, Character keyBinding) {
        this.personKeyConfiguration.put(defaultKey, keyBinding);
    }

    public char getKeyForAction(DefaultKey defaultKey) {
        return this.personKeyConfiguration.get(defaultKey);
    }

    public void setKey(DefaultKey defaultKey, int keyBinding) {
        this.personKeyConfiguration.put(defaultKey,(char) keyBinding);
    }

    public static KeyboardConfiguration getDefaultKeys() {
        return new KeyboardConfiguration(new CustomKey(DefaultKey.DEFAULT, ' '));
    }
}
