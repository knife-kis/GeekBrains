package com.leasen01;

public class Band {

    private int distance;
    private int countBand;
    private Barrirer barrirer;

    public Band(int distance, int countBand, Barrirer barrirer) {
        this.distance = distance;
        this.countBand = countBand;
        this.barrirer = barrirer;
    }

    public Band() {
        distance = 100;
        countBand = 0;
        barrirer = Barrirer.SMALL;
    }

    public int tired(Person person) {
        int i = 0;
        if (countBand > 0) {
            switch (barrirer) {
                case SMALL:
                    i = person.getStamina() - (distance / 10) - (countBand * 2);
                    break;
                case MEDIUM:
                    i = person.getStamina() - (distance / 10) - (countBand * 5);
                    break;
                case BIG:
                    i = person.getStamina() - (distance / 10) - (countBand * 10);
                    break;
            }
        } else i = person.getStamina() - (distance / 10);
        return i;
    }
}
