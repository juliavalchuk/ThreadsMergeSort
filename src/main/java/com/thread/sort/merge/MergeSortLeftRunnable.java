package com.thread.sort.merge;

import java.util.Arrays;

/**
 * Created by julia
 */
public class MergeSortLeftRunnable<T extends Comparable<? super T>> implements Runnable{
    T[] array;

    public MergeSortLeftRunnable(T[] a){
        array = a;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        array = sort(array);
    }

    private T[] sort(T[] array){
        if(array.length == 1){
            return array;
        }

        int middle = array.length / 2;
        T[] left = Arrays.copyOf(array, middle);
        T[] right = Arrays.copyOfRange(array, middle, array.length);

        MergeSortLeftRunnable<T> rightRunnable = new MergeSortLeftRunnable<T>(right);
        Thread thread = new Thread(rightRunnable);
        thread.start();

        left = sort(left);
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        right = rightRunnable.getArray();

        return merge(left, right);
    }

    private T[] merge(T[] left, T[] right){
        T[] result = (T[])new Comparable[left.length + right.length];

        int i = 0, j = 0, k = 0;

        while (k < result.length) {
            if (i == left.length) {
                result[k] = right[j];
                j++;
            } else if (j == right.length) {
                result[k] = left[i];
                i++;
            } else {
                if (left[i].compareTo(right[j]) < 1) {
                    result[k] = left[i];
                    i++;
                } else {
                    result[k] = right[j];
                    j++;
                }
            }

            k++;
        }

        return result;
    }

    public T[]  getArray(){
        return array;
    }
}
