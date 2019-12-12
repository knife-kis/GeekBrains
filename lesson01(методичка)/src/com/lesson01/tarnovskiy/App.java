package com.lesson01.tarnovskiy;

import com.lesson01.tarnovskiy.MammalsAndRobot.Cat;
import com.lesson01.tarnovskiy.MammalsAndRobot.Robot;
import com.lesson01.tarnovskiy.Track.Barrirer;
import com.lesson01.tarnovskiy.Track.Track;
import com.lesson01.tarnovskiy.Track.Wall;
import com.lesson01.tarnovskiy.MammalsAndRobot.Human;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        List<Human> listHum = new ArrayList<>();
        listHum.add(new Human("Алешка"));
        listHum.add(new Human("Ванюшка"));
        listHum.add(new Human("GOD", 999999));

        List<Cat> listCat = new ArrayList<>();
        listCat.add(new Cat("Мурлычио"));
        listCat.add(new Cat("Мурмур"));
        listCat.add(new Cat("Кыса"));

        List<Robot> listRobot = new ArrayList<>();
        listRobot.add(new Robot("R-2" ));
        listRobot.add(new Robot("Ty-7"));
        listRobot.add(new Robot("Ka-8"));

        List<Wall> listWall = new ArrayList<>();
        listWall.add(new Wall(Barrirer.SMALL));
        listWall.add(new Wall(Barrirer.MEDIUM));
        listWall.add(new Wall(Barrirer.BIG));

        Cat cat = new Cat("Мурзик");

        Track track1 = new Track(new Random().nextInt(1000) + 10, listWall.get(1).getHeight());

        for (int i = 0; i < listCat.size(); i++) {
            listCat.get(i).run(track1);
            listHum.get(i).run(track1);
            listRobot.get(i).run(track1);
        }
    }
}
