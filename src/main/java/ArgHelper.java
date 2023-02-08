import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

public class ArgHelper {

    public static boolean isValidArgument(String[] args) {

        Pattern argParamSort = compile("^-[adsi]$");
        Pattern argOutFile = compile("^out.txt$");
        Pattern argInputFile = compile("in.+\\.txt$");

        if (args.length < 3) {

            System.out.println("Недостаточно аргументов для запуска программы.");
            return false;

        } else if (!args[0].matches(argParamSort.pattern())) {

            System.out.println("Первым аргументом должен быть режим сортировки (-a или -d) или" +
                    " тип данных (-s или -i), если режим сортировки не задан");
            return false;

        } else if (args[0].matches("^-[ad]$") && !args[2].matches(argOutFile.pattern()) || args[0].matches("^-[si]") && !args[1].matches(argOutFile.pattern())) {

            System.out.println("Не задан выходной файл или тип данных");
            return false;

        } else if (!args[args.length - 1].matches(argInputFile.pattern())) {

            System.out.println("Последним аргументом должно быть одно из имен входных файлов");
            return false;

        }

        return true;
    }

    public static String getSortMode(String[] args) {
        String defaultSortMode = "-a";

        if (args[0].equals("-a") || args[0].equals("-d")) {
            return args[0];
        }

        System.out.println("Режим сортировки будет выбран по умолчанию.");

        return defaultSortMode;
    }

    public static String getDataType(String[] args) {
        String defaultDataType = "-i";

        if (args[0].equals("-s") || args[0].equals("-i")) {
            return args[0];
        } else if (args[1].equals("-s") || args[1].equals("-i")) {
            return args[1];
        }

        System.out.println("Тип данных выбран по умолчанию");

        return defaultDataType;
    }

    public static String getOutFile(String[] args) {
        for (String fileName : args) {
            if (fileName.matches("^out.txt$")) {
                return fileName;
            }
        }
        return null;
    }

    public static List<String> getFileOfNames(String[] args) {
        List<String> result = new ArrayList<>();

        for (String fileName : args) {
            if (fileName.matches("in.+\\.txt$")) {
                result.add(fileName);
            }
        }

        return result;
    }
}
