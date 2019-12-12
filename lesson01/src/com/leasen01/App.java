package com.leasen01;

/*
    Autor Tarnovskiy Maxim
*/
public class App {

    public static void main(String[] args) {

        Team team1 = new Team("Boy", "Andru", "Bob", "Dak", "Klark", "Jo");
        Band band = new Band(400, 5, Barrirer.MEDIUM);

        team1.hu();
        team1.run(band);
    }
}
