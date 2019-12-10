package com.lesson01.tarnovskiy.Track;

/**
 * @author Tarnovskiy Maksim
 */
public class Wall {
    private double height = 0.0;
    private Barrirer barrirer;

    public Wall(Barrirer barrirer) {
        switch (barrirer){
            case SMALL:
                height = 1.0;
                break;
            case MEDIUM:
                height = 1.5;
                break;
            case BIG:
                height = 2.0;
                break;
            case NAN:
                height = 0.0;
                break;
        }
    }
    public double getHeight() {
        return height;
    }
}
