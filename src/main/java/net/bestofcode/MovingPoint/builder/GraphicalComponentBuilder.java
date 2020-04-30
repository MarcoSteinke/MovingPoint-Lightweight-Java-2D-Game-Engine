package net.bestofcode.MovingPoint.builder;

import net.bestofcode.MovingPoint.MovingPointGameEngine;
import net.bestofcode.MovingPoint.render.GraphicalComponent;

public class GraphicalComponentBuilder {
    private int canvasWidth;
    private int canvasHeight;
    private GraphicalComponent graphicalComponent;

    public GraphicalComponentBuilder setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
        return this;
    }

    public GraphicalComponentBuilder setCanvasHeight(int canvasHeight) {
        this.canvasHeight = canvasHeight;
        return this;
    }

    public GraphicalComponentBuilder setGraphicalComponent(GraphicalComponent graphicalComponent) {
        this.graphicalComponent = graphicalComponent;
        return this;
    }

    public MovingPointGameEngine createMovingPointGameEngine() {
        return new MovingPointGameEngine(canvasWidth, canvasHeight);
    }
}