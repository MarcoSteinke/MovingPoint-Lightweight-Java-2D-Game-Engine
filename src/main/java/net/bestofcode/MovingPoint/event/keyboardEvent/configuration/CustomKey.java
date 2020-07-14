package net.bestofcode.MovingPoint.event.keyboardEvent.configuration;

public class CustomKey implements Key {

    private char keyBinding;

    CustomKey(char keyBinding) {
        this.keyBinding = keyBinding;
    }

    public char getKeyBinding() {
        return this.keyBinding;
    }
}
