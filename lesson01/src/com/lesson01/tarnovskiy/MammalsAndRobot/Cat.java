package com.lesson01.tarnovskiy.MammalsAndRobot;

import com.lesson01.tarnovskiy.Track.Track;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Random;

/**
 * @author Tarnovskiy Maksim
 */
public class Cat implements Movement {
    private final int MIN_STAMINA = 130;
    private final int RANDOM_STAMINA = 40;
    private final double TIRED_RUN = 0.99;
    private final double TIRED_JUMP = 5;
    private String name;
    private double stamina;
    private int distanceBarrirer;

    public Cat(String name, double stamina) {
        this.name = name;
        this.stamina = stamina;
    }

    public Cat(String name) {
        this.name = name;
        stamina = new Random().nextInt(RANDOM_STAMINA) + MIN_STAMINA;
    }

    public Cat() {
        this.name = "Безымянный";
        this.stamina = 1;
    }

    /**
     * Метод бега котов по треку
     * @
     * @param track
     */

    @Override
    public void run(Track track) {
        int distance = track.getDistance();
        int countBarrirer = track.getCountWell();
        if (countBarrirer == 0)
            distanceBarrirer = 0; else
            distanceBarrirer = distance / countBarrirer;
        boolean run = true;

        System.out.println("-------------------------------------------------------------");
        System.out.println("Стартует: " + name);
        System.out.println("Дистация трека равна: " + distance);
        System.out.println("Выносливость равна: " + stamina);

        for (int i = 1; i <= distance; i++) {
            if( distanceBarrirer != 0 && i % distanceBarrirer == 0 )
                jump(track);
            if(!staminaLow(stamina)) break;
            if(i % 100 == 0) {
                DecimalFormatSymbols s = new DecimalFormatSymbols();
                s.setDecimalSeparator('.');
                DecimalFormat f = new DecimalFormat("#,##0.00", s);
                System.out.println("" + name + " бежит " + (i) + "м дистанции, его выносливость равна: " + f.format(stamina));
            }
            stamina -= 100 / (double) distance * TIRED_RUN;

            if(!staminaLow(stamina)) break;
            if(i == distance) {
                System.out.println("" + name + " cправился с бегом");
            }
        }
    }

    @Override
    public void jump(Track track) {
        DecimalFormatSymbols s = new DecimalFormatSymbols();
        s.setDecimalSeparator('.');
        DecimalFormat f = new DecimalFormat("#,##0.00", s);
        stamina -= track.getHeight_barrier() * TIRED_JUMP;

        if(stamina < 0)
            System.out.println("" + name + " не смог перепрыгнуть");
        else System.out.println("Великолепный прыжок! как летит! осталось " + f.format(stamina) + " выносливости");
    }

    public boolean staminaLow(double stamina){
        boolean run = true;
        if (stamina <= 0) {
            System.out.println("Выносливость закончилась у " + name );
            run = false;
        }
        return run;
    }

}
