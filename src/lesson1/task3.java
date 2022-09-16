package lesson1;

import java.util.Arrays;

public class task3 {
    /*
    3) Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый массив,
    каждый элемент которого равен разности элементов двух входящих массивов в той же ячейке. Если длины массивов не
    равны, необходимо как-то оповестить пользователя.
    */

    public static void main(String[] args) {

        int[] arr1 = new int[] {1,2,3,4,5,6,7,8,9};
        int[] arr2 = new int[] {9,8,7,6,5,4,3,2,1};

        System.out.println(Arrays.toString(ftask3(arr1,arr2)));

    }

    public static int[] ftask3(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            throw new RuntimeException("Разная длина массивов!");
        }

        int[] resultArray = new int[arr1.length];

        for (int i = 0; i < arr1.length; i++) {

            resultArray[i] = arr1[i] - arr2[i];

        }

        return resultArray;

    }


}
