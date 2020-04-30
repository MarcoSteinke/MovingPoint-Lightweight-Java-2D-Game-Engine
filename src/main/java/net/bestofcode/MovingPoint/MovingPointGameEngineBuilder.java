package net.bestofcode.MovingPoint;

import net.bestofcode.MovingPoint.render.GraphicalComponent;

public class MovingPointGameEngineBuilder {
    private int canvasWidth;
    private int canvasHeight;
    private GraphicalComponent d;

    public MovingPointGameEngineBuilder setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
        return this;
    }

    public MovingPointGameEngineBuilder setCanvasHeight(int canvasHeight) {
        this.canvasHeight = canvasHeight;
        return this;
    }

    public MovingPointGameEngineBuilder setD(GraphicalComponent d) {
        this.d = d;
        return this;
    }

    public MovingPointGameEngine createMovingPointGameEngine() {
        return new MovingPointGameEngine(canvasWidth, canvasHeight);
    }
}