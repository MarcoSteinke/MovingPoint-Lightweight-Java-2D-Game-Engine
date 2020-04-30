package net.bestofcode.MovingPoint;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/** net.bestofcode.MovingPointGameEngine.Vector
 *  This Generic describes a vector which is able to have any Number Objects as elements
 *  @param <Type> - Store elements of any type.
 */
public class Vector{

    public double x, y;

    public List<Double> vectorComponents = new ArrayList<>();

    /**
     *  The constructor can take a finite amount of parameters to be stored inside of the vector.
     *  CAUTION: If you use too many parameters, your heap will overflow --> Crash
     *  @param parameter - The parameters to be stored inside of the net.bestofcode.MovingPointGameEngine.Vector
     */
    public Vector(double... parameter){

        for(double type : parameter){

            this.vectorComponents.add(type);

        }

        if(this.dimension() == 2){

            this.x = this.vectorComponents.get(0);
            this.y = this.vectorComponents.get(1);

        }     
    }

    /** dimension
     *  Returns the vector's dimension
     *  @return - Returns the vector's dimension as Integer
     */
    public int dimension(){

        return (int) this.vectorComponents.size();

    }

    /** length
     *  Returns the vector's length after Pythagoras Theorem
     *  @return - Returns the vector's length as Double
     */
    public double length(){

        if(this.vectorComponents.get(0) instanceof Double)
            return Math.sqrt( (double) this.vectorComponents.stream()
                                 .mapToDouble(component -> ((Double) component * (Double) component))
                                 .sum() );
        else return 0;

    }

    /** direction
     *  Returns the vector's direction as an Integer value between 0 and 360.
     *  CAUTION: Only works for two-dimensional vectors
     * 
     *  @return - If the value is between 0 and 360 everything is fine
     *          - If the value is -1 the vectors dimension was not 2
     */
    public int direction(){

        if(this.dimension() == 2){

            return (int) ((360 * Double.parseDouble(this.vectorComponents.get(1).toString()) / (this.length()) * Math.PI));
        
        } else{

            return -1;

        }
    }
}