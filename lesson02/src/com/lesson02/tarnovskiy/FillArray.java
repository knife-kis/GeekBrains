package com.lesson02.tarnovskiy;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tarnovskiy Maksim
 */
public class FillArray {

    /**
     * Метод создания массива нужной размерности 4x4
     * @param line
     * @param column
     * @return
     */

    public String[][] fill(int line, int column) {
        String[][] i = new String[line][column];

        try {
            if (line != 4 && column != 4) {
                throw new MyArraySizeExeption();
            }
        } catch (MyArraySizeExeption e) {
            System.out.println("Вы ввели неверную размерность массива, вы ввели: [" + line + "][" + column + "]");
        }
        return i;
    }

    /**
     * Метод конвертаци символов в числа если таковые имеются,
     * если встречаются строки или символы тогда бросаем исключение
     * MyArrayDataException
     *
     * @param stringArray - массив который ввел пользователь
     * @return
     */
    //TODO Надобы дописать в метод чтобы при выбрасывании исключения необходимо было ввести числа заново,
    //TODO Возможно необходимо пробросить исключение в main и там обработать.
    public int[][] convertInt(String[][] stringArray) {
        int[][] arrayInt = new int[stringArray.length][stringArray[0].length];
        for (int i = 0; i < stringArray.length; i++) {
            for (int j = 0; j < stringArray[0].length; j++) {

                try {
                    arrayInt[i][j] = Integer.parseInt(stringArray[i][j]);
                } catch (NumberFormatException e) {
                    Pattern pattern = Pattern.compile("\\D");
                    Matcher matcher = pattern.matcher(stringArray[i][j]);
                    if (matcher.find()) {
                        try {
                            throw new MyArrayDataRxception();
                        } catch (MyArrayDataRxception myArrayDataRxception) {
                            System.out.println("Вы ввели не число! пожалуста повторите ввод");
                            System.out.println("Неверный результат в ячейке " +
                                    "[" + i + "][" + j + "]");
                        }
                    }
                }
            }
        }
        return arrayInt;
    }
    /**
     * Заполняем массив символами
     *
     * @param array1 - массив с которым будем играть
     * @return
     */
    public String[][] inputArray(String[][] array1) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите " + (array1.length * array1[0].length) + " значений для заполнения массива, только цифры");
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[0].length; j++) {
                array1[i][j] = in.next();
            }
        }
        return array1;
    }

    public int sum(int[][] intArray) {
        int count = 0;
        for (int i = 0; i < intArray.length; i++) {
            for (int j = 0; j < intArray[0].length; j++) {
                count += intArray[i][j];
            }
        }
        return count;
    }
}
