package net.bestofcode.MovingPoint.event.keyboardEvent.configuration;

import java.util.HashMap;

public class KeyboardConfiguration implements Configuration {

    private final HashMap<DefaultKey, Character> personKeyConfiguration;

    private KeyboardConfiguration(DefaultKey... keys) {
        this.personKeyConfiguration = new HashMap<>();
        for(DefaultKey key : keys) {
            personKeyConfiguration.put(key, ' ');
        }
    }
}
