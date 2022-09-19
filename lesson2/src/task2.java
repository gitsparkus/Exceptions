public class task2 {
    /*
        Если необходимо, исправьте данный код:
        try {
            int d = 0;
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (ArithmeticException e) {
            System.out.println("Catching exception: " + e);
        }

    */


    // В задании не указано назначение этого кода. Деление на 0 не вызывает исключение в случае с типом double.
    // Если имелось в виду, что необходимо ограничить деление на 0, то нужно добавить проверку, что d не равно 0.
    // Так как цель программы явно не обозначена, и программа работает, я считаю, что исправлять нечего.

    public static void main(String[] args) {
        double[] intArray = new double[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        try {
            int d = 0;
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch (ArithmeticException e) {
            System.out.println("Catching exception: " + e);
        }
    }
}


