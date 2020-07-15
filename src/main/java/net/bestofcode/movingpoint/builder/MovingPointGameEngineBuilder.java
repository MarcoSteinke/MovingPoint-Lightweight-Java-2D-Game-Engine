package net.bestofcode.movingpoint.builder;

import net.bestofcode.movingpoint.MovingPointGameEngine;
import net.bestofcode.movingpoint.render.GraphicalComponent;

public class MovingPointGameEngineBuilder {
    private int canvasWidth;
    private int canvasHeight;
    private GraphicalComponent graphicalComponent;

    public MovingPointGameEngineBuilder setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
        return this;
    }

    public MovingPointGameEngineBuilder setCanvasHeight(int canvasHeight) {
        this.canvasHeight = canvasHeight;
        return this;
    }

    public MovingPointGameEngineBuilder setGraphicalComponent(GraphicalComponent graphicalComponent) {
        this.graphicalComponent = graphicalComponent;
        return this;
    }

    public MovingPointGameEngine createMovingPointGameEngine() {
        return new GraphicalComponentBuilder().setCanvasWidth(canvasWidth).setCanvasHeight(canvasHeight).createMovingPointGameEngine();
    }
}