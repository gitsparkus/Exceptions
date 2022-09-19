import java.util.Scanner;

public class task1 {

    /*
        Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float), и возвращает введенное
        значение. Ввод текста вместо числа не должно приводить к падению приложения, вместо этого, необходимо повторно
        запросить у пользователя ввод данных.
    */

    public static void main(String[] args) {
        System.out.println("Вы ввели: " + getFloat());
    }

    public static float getFloat() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите вещественное число: ");
        while(true) {
            try {
                float resultFloat= scanner.nextFloat();
                return resultFloat;
            } catch (Exception e) {
                scanner.nextLine();
                System.err.print("Необходимо ввести вещественное число: ");
            }
        }
    }
}
