package com.leasen01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Person {

    private List<String> list = new ArrayList<>();
    private int stamina;

    public Person(String[] name) {
        for (int i = 0; i < name.length; i++) {
            list.add(String.valueOf(name[i]));
        }
        stamina = new Random().nextInt(50) + 50;
    }

    public int getStamina() {
        return stamina;
    }

    public String getName(int i) {
        return list.get(i);
    }
}
