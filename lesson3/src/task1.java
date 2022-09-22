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

    // Основная программа
    public static void main(String[] args) throws AmountOfDataException, NoRequiredDataException, IOException {
        String input = "-";
        Scanner scanner = new Scanner(System.in);
        Map<String, String> person = new HashMap<String, String>();


        while (!input.isEmpty()) {

            System.out.print("Введите данные через пробел в формате " +
                    "Фамилия Имя Отчество датарождения номертелефона пол. Или нажмите Enter для завершения: ");
            input = scanner.nextLine();

            if (!input.isEmpty()) {
                try {
                    person = parseData(input);
                    checkData(person);
                    try {
                        writeToFilePerson(person);
                    } catch (MyFileException ex) {
                        System.out.println(ex.getStackTraceAsString());
                    }
                } catch (AmountOfDataException | NoRequiredDataException ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    // Метод проверяет, что передано достаточное количество аргументов, преобразует их в список, разделив по пробелу,
    // выделяет из них соответствующие условиям и возвращает в виде хэш-таблицы
    public static Map<String, String> parseData(String input) throws AmountOfDataException {
        Map<String, String> person = new HashMap<String, String>();
        List<String> list = new ArrayList<String>(Arrays.asList(input.split(" ")));

        if (list.size() > 6) {
            throw new AmountOfDataException("Введено больше данных!");
        }
        if (list.size() < 6) {
            throw new AmountOfDataException("Введено меньше данных!");
        }

        for (String element : list) {
            if (!person.containsKey("sex") && (element.equals("f") || element.equals("m"))) {
                person.put("sex", element);
            } else if (!person.containsKey("phone") && (element.matches("^[0-9]+$"))) {
                person.put("phone", element);
            } else if (!person.containsKey("date") && (element.matches("^\\s*(3[01]|[12][0-9]|0?[1-9])\\.(1[012]|0?[1-9])\\.((?:19|20)\\d{2})\\s*$"))) {
                person.put("date", element);
            } else {
                if (element.matches("^[А-Яа-я]+$")) {
                    if (!person.containsKey("surname")) {
                        person.put("surname", element);
                    } else if (!person.containsKey("name")) {
                        person.put("name", element);
                    } else {
                        person.put("patronymic", element);
                    }
                }
            }
        }
        return person;
    }

    // Метод проверяет наличие всех необходимых данных о человеке и в случае их отсутствия бросает исключения
    public static void checkData(Map<String, String> person) throws NoRequiredDataException {
        if (!person.containsKey("sex")) {
            throw new NoRequiredDataException("Не указан пол!");
        }

        if (!person.containsKey("phone")) {
            throw new NoRequiredDataException("Не указан или неверно указан номер телефона!");
        }

        if (!person.containsKey("date")) {
            throw new NoRequiredDataException("Не указана или неверно указана дата!");
        }

        if (!person.containsKey("surname")) {
            throw new NoRequiredDataException("Не удалось обнаружить Фамилию!");
        }

        if (!person.containsKey("name")) {
            throw new NoRequiredDataException("Не удалось обнаружить Имя!");
        }

        if (!person.containsKey("patronymic")) {
            throw new NoRequiredDataException("Не удалось обнаружить Отчество!");
        }

    }

    // Метод записывает или дописывает валидные данные в файл. В случае исключения бросает ошибку записи.
    public static void writeToFilePerson(Map<String, String> person) throws MyFileException {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(person.get("surname"), true)))) {
            out.println("<" + person.get("surname") +
                    "><" + person.get("name") + "><" +
                    person.get("patronymic") + "><" +
                    person.get("date") + "> <" +
                    person.get("phone") + "><" +
                    person.get("sex") + ">");
        } catch (IOException ex) {
            throw new MyFileException("Ошибка записи в файл!");
        }
    }
}
