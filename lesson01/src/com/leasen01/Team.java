package com.leasen01;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String nameTeam;
    private List<Person> teamList = new ArrayList<>();

    public Team(String nameTeam, String... name) {
        this.nameTeam = nameTeam;
        for (int i = 0; i < name.length; i++) {
            teamList.add(new Person(name));
        }
    }

    public void run(Band band) {

        int staminaPeople = 0;
        String namePeople;
        for (int i = 0; i < teamList.size(); i++) {
            System.out.println("---------------------------------------------------------------");
            staminaPeople = teamList.get(i).getStamina();
            namePeople = teamList.get(i).getName(i);
            System.out.println(namePeople + " начал участие в забеге");
            System.out.println("Его выносливость " + staminaPeople);
            staminaPeople = band.tired(teamList.get(i));
            if (staminaPeople <= 0) {
                System.out.println(namePeople + " нехватило выносливости");
            } else System.out.println(namePeople + " справился! его выносливасть равна " + staminaPeople);
        }
        System.out.println("---------------------------------------------------------------");
    }
    public void hu() {
        System.out.println("Команда с названием: " + nameTeam.toUpperCase() + " !!!!");
    }
}
