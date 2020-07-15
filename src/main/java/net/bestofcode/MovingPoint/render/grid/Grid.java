package net.bestofcode.MovingPoint.render.grid;

public class Grid {

    private final Dimension dimension;

    public Grid(Dimension dimension) {
        this.dimension = dimension;
    }

    public int getDimension() {
        return dimension.getValue();
    }
}
