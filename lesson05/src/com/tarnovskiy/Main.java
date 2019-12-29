package com.tarnovskiy;

public class Main {

    public static void main(String[] args) {

        final int sizeArr = 100_000_000;
        final int part = 32;
        float[] arr = new float[sizeArr];
        fillArr(arr, 1);
        long startTime = System.currentTimeMillis();
        chengeValue(arr, 0);
        long timeOne = System.currentTimeMillis();
        System.out.println("Время после пробега по массиву обычным способом " + (timeOne - startTime));

        // ---------------------Второй метод----------------------

        fillArr(arr, 1);
        long startTimeMethodSecond = System.currentTimeMillis();
        for (int i = 0; i < part; i++) {
            calculationValueArrWithThread(arr, part, i);
        }
        calculationRemainderArr(arr, part);
        long timeTwo = System.currentTimeMillis();
        System.out.println("Время после пробега по массиву нитями " + (timeTwo - startTimeMethodSecond));

        checkValueArray(arr);
    }

    /**
     * проверка изменения всех значений в массиве
     * @param arr - изначальный массив.
     */

    private static void checkValueArray(float[] arr) {
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i] == 1){
                System.out.println(i + 1);
            }
        }
    }

    private static void calculationRemainderArr(float[] arr, int part) {
        int valueRemainder = arr.length % part;
        float[] partArr = new float[valueRemainder];
        fillArr(partArr, 1);
        chengeValue(partArr, part);
        System.arraycopy(partArr, 0, arr, arr.length - valueRemainder, valueRemainder);
    }

    private synchronized static void calculationValueArrWithThread(float[] arr, int part, int value) {

        Thread thread = new Thread(() -> {
            int valuePartArr = arr.length / part;
            float[] partArr = new float[valuePartArr];
            long startSplit = System.currentTimeMillis();
            System.arraycopy(arr, value * valuePartArr, partArr, 0, valuePartArr );
            long timeSplit = System.currentTimeMillis();
            System.out.println("Время разбивки " + (value + 1) + " части массива, составило: " + (timeSplit - startSplit));
            chengeValue(partArr, value);
            long timeChengeValue = System.currentTimeMillis();
            System.out.println("Время изменения значения " + (value + 1) + " части массива, составило: " + (timeChengeValue - timeSplit));
            System.arraycopy(partArr, 0, arr, value * valuePartArr, valuePartArr);
            long timeCopyValue = System.currentTimeMillis();
            System.out.println("Время склейки " + (value + 1) + " части массива, составило: " + (timeCopyValue - timeChengeValue));

        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private static void chengeValue(float[] arr, int valueCycle) {
        for (int i = 0; i < arr.length; i++) {
            int valueSharedArray = i + (valueCycle * arr.length);
            arr[i] = (float)(arr[i] * Math.sin(0.2f + valueSharedArray / 5) * Math.cos(0.2f + valueSharedArray / 5) * Math.cos(0.4f + valueSharedArray / 2));
        }
    }

    private static void fillArr(float[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = value;
        }
    }
}
