import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        /*
            Разработайте программу, которая выбросит Exception, когда пользователь вводит пустую строку. Пользователю
            должно показаться сообщение, что пустые строки вводить нельзя.
        */

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите непустую строку: ");
        String resultString = scanner.nextLine();
        if (resultString.length() == 0) {
            throw new RuntimeException("Строка должны быть непустой!");
        }

    }
}
