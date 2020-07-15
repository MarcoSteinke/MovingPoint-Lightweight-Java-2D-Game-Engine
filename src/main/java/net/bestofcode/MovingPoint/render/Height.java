package net.bestofcode.MovingPoint.render;

import java.util.Objects;

public class Height {

    private final int value;

    public Height(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object anotherInteger) {
        if(anotherInteger instanceof Height) {
            return this.getValue() == ((Height) anotherInteger).getValue();
        }
        return false;
    }
}
