package net.bestofcode.MovingPoint.render.gamewindow;

import net.bestofcode.MovingPoint.render.settings.Height;
import net.bestofcode.MovingPoint.render.settings.Width;

public class GameWindowConfiguration {


    private Width width;
    private Height height;

    private double minimumValueOnXAxis = -1;
    private double maximumValueOnXAxis = 1;
    private double minimumValueOnYAxis = -1;
    private double maximumValueOnYAxis = 1;

    public GameWindowConfiguration(Width width, Height height) {
        this.width = width;
        this.height = height;
    }

    public Width getWidth() {
        return width;
    }

    public Height getHeight() {
        return height;
    }

    public double getMinimumValueOnXAxis() {
        return minimumValueOnXAxis;
    }

    public double getMaximumValueOnXAxis() {
        return maximumValueOnXAxis;
    }

    public double getMinimumValueOnYAxis() {
        return minimumValueOnYAxis;
    }

    public double getMaximumValueOnYAxis() {
        return maximumValueOnYAxis;
    }

    public void setMinimumValueOnXAxis(double minimumValueOnXAxis) {
        this.minimumValueOnXAxis = minimumValueOnXAxis;
    }

    public void setMaximumValueOnXAxis(double maximumValueOnXAxis) {
        this.maximumValueOnXAxis = maximumValueOnXAxis;
    }

    public void setMinimumValueOnYAxis(double minimumValueOnYAxis) {
        this.minimumValueOnYAxis = minimumValueOnYAxis;
    }

    public void setMaximumValueOnYAxis(double maximumValueOnYAxis) {
        this.maximumValueOnYAxis = maximumValueOnYAxis;
    }
}
