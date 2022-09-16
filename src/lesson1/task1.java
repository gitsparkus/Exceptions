package lesson1;

import java.io.File;
import java.io.IOException;

public class task1 {

    /*
        Реализуйте 3 метода, чтобы в каждом из них получить разные исключения
    */

    public static void main(String[] args) throws IOException {

        infinity(1);
        //System.out.println(filename());
        //typeError();
    }

    //Бесконечная рекурсия
    public static void infinity(Integer i) {
        infinity(i + 1);
    }

    // Недопустимое имя файла
    public static boolean filename() throws IOException {
        File myObj = new File("name*.txt");
        return myObj.createNewFile();
    }

    //Ошибка преобразования типов
    public static void typeError() {
        String str = "123f";
        Integer.parseInt(str);
    }


}
