package com.thread.sort.merge;

/**
 * Created by Yuliia_Valchuk
 */
public class Main {

    public static void main(String... args){

        int N = 100;
        Integer[] array = createArray(N);
        systemOutArray(array);

        MergeSortLeftRunnable<Integer> runnable = new MergeSortLeftRunnable<Integer>(array);
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Comparable[] carray = runnable.getArray(); // I have problem with this
        systemOutArray(carray);

    }

    public static Integer[] createArray(int N){
        Integer[] array = new Integer[N];

        for(int i = 0; i < N; ++i){
            array[i] = (int)( Math.random() * N);
        }
        return array;
    }

    public static void systemOutArray(Object[] array){
        int n = array.length;
        for(int i = 0; i < n; ++i){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
