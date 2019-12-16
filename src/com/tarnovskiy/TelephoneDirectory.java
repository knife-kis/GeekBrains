package com.tarnovskiy;

import java.util.*;

/**
 * @author Tarnovskiy Maksim
 */
public class TelephoneDirectory {

    private Map<String, ArrayList<Long>> telephoneBook = new HashMap<>();

    public void add(String name, long telephoneNumber) {
        final String phoneNumber = String.valueOf(telephoneNumber);
        final int SOTOVIY_FORMAT_NUMBER = 11;
        final int CITY_FORMAT_NUMBER = 7;

        if((phoneNumber.length() == SOTOVIY_FORMAT_NUMBER
                || phoneNumber.length() == CITY_FORMAT_NUMBER)
                && (phoneNumber.startsWith("8") || phoneNumber.startsWith("2")))
        {
            ArrayList<Long> phoneNumberList = telephoneBook.get(name);
            if (phoneNumberList == null) {
                phoneNumberList = new ArrayList<>();
            }
            phoneNumberList.add(telephoneNumber);
            telephoneBook.put(name, phoneNumberList);
        } else
            System.out.println("Вы ввели не правильный формат номера");
    }

    public void get(String peoplesName){
        for (Long o :
                telephoneBook.get(peoplesName) ) {
            System.out.print(o + " ");
        }
        System.out.println();
    }
}
