package lesson1;

import java.util.Arrays;

public class task4 {
    /*
    4) Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый массив,
    каждый элемент которого равен частному элементов двух входящих массивов в той же ячейке. Если длины массивов не
    равны, необходимо как-то оповестить пользователя. Важно: При выполнении метода единственное исключение, которое
    пользователь может увидеть - RuntimeException, т.е. ваше
    */

    public static void main(String[] args) {

        int[] arr1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] arr2 = new int[]{9, 8, 7, 6, 5, 4, 3, 0, 1};

        System.out.println(Arrays.toString(ftask4(arr1, arr2)));

    }

    public static int[] ftask4(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            throw new RuntimeException("Разная длина массивов!");
        }

        int[] resultArray = new int[arr1.length];

        for (int i = 0; i < arr1.length; i++) {
            if (arr2[i] == 0) {
                throw new RuntimeException("Элемент № " + (i + 1) + " второго массива равен 0. Деление на ноль запрещено!");
            }
            resultArray[i] = arr1[i] / arr2[i];

        }

        return resultArray;

    }


}

