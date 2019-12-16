package com.tarnovskiy;

import java.util.Arrays;
import java.util.Set;

/**
 * @author Tarnovskiy Maksim
 */
public class App {

    public static void main(String[] args) {

        /**
         * Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
         * Найти и вывести список уникальных слов, из которых состоит массив (дубликаты
         * не считаем). Посчитать, сколько раз встречается каждое слово.
         */

        WorkWithArrays workWithArrays = new WorkWithArrays();
        String[] randomArray = workWithArrays.GenerateRandomWords(40);
        System.out.println(Arrays.toString(randomArray));
        Set<String> setUniqumArray = workWithArrays.UniqumWorlds(randomArray);
        System.out.println(setUniqumArray);
        System.out.println("Уникальных слов всего " + setUniqumArray.size());

        /**
         * Написать простой класс Телефонный Справочник, который хранит в себе список
         * фамилий и телефонных номеров. В этот телефонный справочник с помощью метода
         * add() можно добавлять записи, а с помощью метода get() искать номер телефона
         * по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов
         * (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны
         */

        TelephoneDirectory telephoneDirectory = new TelephoneDirectory();
        telephoneDirectory.add("Maks", 89969567779L);
        telephoneDirectory.add("Oks", 89969567771L);
        telephoneDirectory.add("Tod", 12345678901L);
        telephoneDirectory.add("Sok", 89699567774L);
        telephoneDirectory.add("Sok", 89699567773L);
        telephoneDirectory.add("Tod", 89695967714L);
        telephoneDirectory.add("Tod", 89695697743L);
        telephoneDirectory.get("Tod");
        telephoneDirectory.get("Tod");
        telephoneDirectory.get("Oks");

    }
}
