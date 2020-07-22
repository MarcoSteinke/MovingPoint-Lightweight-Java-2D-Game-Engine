package net.bestofcode.MovingPoint.render.grid;

import net.bestofcode.MovingPoint.annotations.Refactor;
import net.bestofcode.MovingPoint.render.gamewindow.GameWindowConfiguration;

public class Grid {

    private final Dimension dimension;
    private GameWindowConfiguration gameWindowConfiguration;

    // values used for grid calculation
    public double coordinateAxisRange = Math.abs(this.gameWindowConfiguration.getMaximumValueOnXAxis() - this.gameWindowConfiguration.getMinimumValueOnXAxis());

    /* array which stores information about the state of all statesOfAllGridCells in the grid */
    public int[][] statesOfAllGridCells;
    @Refactor
    /* array which stores information about each cell's coordinates */
    public double[][][] gridCellCenterCoordinates;

    public Grid(Dimension dimension, GameWindowConfiguration gameWindowConfiguration) {
        this.dimension = dimension;
        this.gameWindowConfiguration = gameWindowConfiguration;
    }

    public static Grid create(int cellsPerRow, GameWindowConfiguration gameWindowConfiguration) {
        return new Grid(new Dimension(cellsPerRow), gameWindowConfiguration);
    }

    public int getDimension() {
        return dimension.getValue();
    }
}
