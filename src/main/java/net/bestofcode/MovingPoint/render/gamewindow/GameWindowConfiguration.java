package net.bestofcode.MovingPoint.render.gamewindow;

import net.bestofcode.MovingPoint.render.settings.Height;
import net.bestofcode.MovingPoint.render.settings.Width;

public class GameWindowConfiguration {


    private Width width = new Width(1000);
    private Height height = new Height(1000);

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
}
