package net.bestofcode.MovingPoint.math;

import net.bestofcode.MovingPoint.annotations.Remove;

@Remove
public class VectorHasWrongDimensionException extends Exception {

    private static final long serialVersionUID = 1L;
    public String alert;

    public VectorHasWrongDimensionException(String msg) {
        this.alert = msg;
    }
}