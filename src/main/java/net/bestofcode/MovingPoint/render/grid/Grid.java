package net.bestofcode.MovingPoint.render.grid;

import net.bestofcode.MovingPoint.annotations.Refactor;

public class Grid {

    private final Dimension dimension;
    /* array which stores information about the state of all statesOfAllGridCells in the grid */
    public int[][] statesOfAllGridCells;
    @Refactor
    /* array which stores information about each cell's coordinates */
    public double[][][] gridCellCenterCoordinates;

    public Grid(Dimension dimension) {
        this.dimension = dimension;
    }

    public static Grid create(int cellsPerRow) {
        return new Grid(new Dimension(cellsPerRow));
    }

    public int getDimension() {
        return dimension.getValue();
    }
}
