package com.tarnovskiy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author Tarnovskiy Maksim
 */
public class WorkWithArrays {

    private Set<String> word = new HashSet<>();

    public String[] GenerateRandomWords (int numberWords){
        String[] randomStrings = new String[numberWords];
        Random random = new Random();
        for(int i = 0; i < numberWords; i++)
        {
            char[] word = new char[random.nextInt(3) + 2];
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(3));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    public Set<String> UniqumWorlds (String[] randomString){

        word.addAll(Arrays.asList(randomString));
        return word;
    }

}
