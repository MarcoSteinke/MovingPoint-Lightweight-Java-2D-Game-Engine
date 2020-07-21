package net.bestofcode.MovingPoint.event.keyboardEvent.configuration;

public class CustomKey implements Key {

    private final char keyBinding;
    private final DefaultKey defaultKey;

    CustomKey(DefaultKey defaultKey, char keyBinding) {
        this.keyBinding = keyBinding;
        this.defaultKey = defaultKey;
    }

    public char getKeyBinding() {
        return this.keyBinding;
    }

    public DefaultKey getDefaultKey() {
        return this.defaultKey;
    }
}
