package com.lesson01.tarnovskiy.Track;

/**
 * @author Tarnovskiy Maksim
 */
public class Track {
    private final int STEP_WELL = 160; // щаг барьера
    private int distance;
    private int countWell;
    private double height_barrier;

    public double getHeight_barrier() {
        return height_barrier;
    }

    public Track(int distance, double height_barrier) {

        this.distance = distance;
        if(distance <= 0) System.out.println("Бежать некуда");
        else {
            countWell = distance / STEP_WELL;
        }
        this.height_barrier = height_barrier;
    }

    public int getDistance() {
        return distance;
    }

    public int getCountWell() { return countWell; }
}
