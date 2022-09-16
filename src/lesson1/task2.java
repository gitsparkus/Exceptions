package lesson1;

public class task2 {
    /*
    2) Посмотрите на код, и подумайте сколько разных типов исключений вы тут сможете получить?
        public static int sum2d(String[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
        for (int j = 0; j < 5; j++) {
        int val = Integer.parseInt(arr[i][j]);
        sum += val;
        }
        }
        return sum;
        }
     */

    public static void main(String[] args) {
        //1. Пустой массив. NumberFormatException: Cannot parse null string
//        String[][] arr = new String[1][1];
//        sum2d(arr);

        //2. Длина массива меньше 5. ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1
//        String[][] arr1 = new String[1][1];
//        arr1[0][0] = "11";
//        sum2d(arr1);

        //3. В массиве есть строки, которые нельзя преобразовать в число. NumberFormatException: For input string: "15e"
//        String[][] arr1 = new String[1][5];
//        arr1[0][0] = "11";
//        arr1[0][1] = "12";
//        arr1[0][2] = "13";
//        arr1[0][3] = "14";
//        arr1[0][4] = "15e";
//        sum2d(arr1);

    }

    public static int sum2d(String[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 5; j++) {
                int val = Integer.parseInt(arr[i][j]);
                sum += val;
            }
        }
        return sum;
    }
}
