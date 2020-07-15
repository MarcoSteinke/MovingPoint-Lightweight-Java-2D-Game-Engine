package net.bestofcode.movingpoint.render;

import net.bestofcode.movingpoint.annotations.Refactor;

@Refactor
public class Colour extends java.awt.Color {

    public Colour(int r, int g, int b) {
        super(r,g,b);    
    }
    
    public Colour(int rgb) {
        super(rgb);    
    }
}
