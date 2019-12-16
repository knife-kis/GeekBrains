package com.lesson02.tarnovskiy;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Maksim Tarnovskiy
 */
public class App {

    public static void main(String[] args) throws MyArraySizeExeption {
        Scanner input;
        String[][] array1;
        int[][] arrayInt;
        int sumArray;
        FillArray fillArray = new FillArray();

        input = new Scanner(System.in);
        array1 = fillArray.fill(Integer.parseInt(input.next()), Integer.parseInt(input.next())); // задаем размерность массива
        array1 = fillArray.inputArray(array1); // заполняем массив
        arrayInt = fillArray.convertInt(array1);
        sumArray = fillArray.sum(arrayInt);

        System.out.println(Arrays.deepToString(arrayInt));
        System.out.println("Сумма всех чисел ровна: " + sumArray);

    }
}
