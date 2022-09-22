import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class task1 {
    /*
    Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные
    пробелом:
    Фамилия Имя Отчество датарождения номертелефона пол

    Форматы данных:
    фамилия, имя, отчество - строки
    дата_рождения - строка формата dd.mm.yyyy
    номер_телефона - целое беззнаковое число без форматирования
    пол - символ латиницей f или m.

    Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код
    ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.

    Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы
    данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы
    java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией,
    что именно неверно.

    Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны
    записаться полученные данные, вида

    <Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>

    Однофамильцы должны записаться в один и тот же файл, в отдельные строки.
    Не забудьте закрыть соединение с файлом.
    При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь
    должен увидеть стектрейс ошибки.
     */

    public static void main(String[] args) throws AmountOfDataException, NoRequiredDataException, IOException {
        String input = "-";
        Scanner scanner = new Scanner(System.in);
        Map<String, String> person = new HashMap<String, String>();


        while (!input.isEmpty()) {

            System.out.print("Введите данные: ");
            input = scanner.nextLine();

            if (!input.isEmpty()) {
                try {
                    person = parseData(input);
                    checkData(person);
                    try {
                        writeToFilePerson(person);
                    }
                    catch (MyFileException ex) {
                        System.out.println(ex.getMessage());
                        System.out.println(ex.getStackTraceAsString());
                    }
                } catch (AmountOfDataException | NoRequiredDataException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    public static Map<String, String> parseData(String input) throws AmountOfDataException {
        Map<String, String> person = new HashMap<String, String>();
        List<String> list = new ArrayList<String>(Arrays.asList(input.split(" ")));
        System.out.println(Arrays.toString(list.toArray()));
        if (list.size() > 6) {
            throw new AmountOfDataException("Введено больше данных!");
        }
        if (list.size() < 6) {
            throw new AmountOfDataException("Введено меньше данных!");
        }

        for (String element : list) {
            if (element.equals("f") || element.equals("m")) {
                person.put("sex", element);
            } else if (element.matches("^[0-9]+$")) {
                person.put("phone", element);
            } else if (element.matches("^\\s*(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})\\s*$")) {
                person.put("date", element);
            } else {
                if (!person.containsKey("surname")) {
                    person.put("surname", element);
                } else if (!person.containsKey("name")) {
                    person.put("name", element);
                } else {
                    person.put("patronymic", element);
                }
            }
        }
        return person;
    }

    public static void checkData(Map<String, String> person) throws NoRequiredDataException {
        if (!person.containsKey("sex")) {
            throw new NoRequiredDataException("Не указан пол!");
        }

        if (!person.containsKey("phone")) {
            throw new NoRequiredDataException("Не указан номер телефона!");
        }

        if (!person.containsKey("date")) {
            throw new NoRequiredDataException("Не указана дата!");
        }

        if (!person.containsKey("surname")) {
            throw new NoRequiredDataException("Не указаны Фамилия, Имя или Отчество!");
        }

        person.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
        });

    }

    public static void writeToFilePerson(Map<String, String> person) throws MyFileException {
        File f = new File(person.get("surname"));
        try {
            if (!f.exists()) {
                f.createNewFile();
            }

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();


            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(person.get("surname"), true)))) {

                out.println("<" + person.get("surname") +
                        "><" + person.get("name") + "><" +
                        person.get("patronymic") + "><" +
                        person.get("date") + "> <" +
                        person.get("phone") + "><" +
                        person.get("sex") + ">");
            }
            catch (IOException ex) {
                throw new MyFileException("Ошибка записи в файл!", ex.getStackTrace());
            }
        } catch (IOException ex) {
            throw new MyFileException("Ошибка создания файла!", ex.getStackTrace());
        }
    }
}
