package com.tarnovskiy;

public class Main {

    public static void main(String[] args) {
        final int sizeArr = 10_000_000;
        final int part = 77;
        float[] arr = new float[sizeArr];
        fillArr(arr, 1);
        long startTime = System.currentTimeMillis();
        chengeValue(arr);
        long timeOne = System.currentTimeMillis();
        System.out.println("Время после пробега по массиву обычным способом " + (timeOne - startTime));

        // ---------------------Второй метод----------------------

        fillArr(arr, 1);
        long startTimeMethodSecond = System.currentTimeMillis();

        for (int i = 0; i < part; i++) {
            threadValue(arr, part, i);
        }
        long timeTwo = System.currentTimeMillis();
        System.out.println("Время после пробега по массиву нитями " + (timeTwo - startTimeMethodSecond));
        System.out.println();
    }

    private synchronized static void threadValue(float[] arr, int part, int value) {
        Thread thread = new Thread(() -> {
            int valuePartArr = arr.length / part;
            float[] partArr = new float[valuePartArr];
            System.arraycopy(arr, value * valuePartArr, partArr, 0, valuePartArr );
            chengeValue(partArr);
            System.arraycopy(partArr, 0, arr, value * valuePartArr, valuePartArr);
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void chengeValue(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    private static void fillArr(float[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = value;
        }
    }
}
