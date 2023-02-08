import java.util.List;

public class Main {

    public static void main(String[] args) {

        if (!ArgHelper.isValidArgument(args)) {
            return;
        }

        String sortMode = ArgHelper.getSortMode(args);
        String dataType = ArgHelper.getDataType(args);
        String outFile = ArgHelper.getOutFile(args);
        List<String> fileOfNames = ArgHelper.getFileOfNames(args);

        if (outFile == null) {
            System.out.println("Выходной файл не задан");
            return;
        }

        MySortClass.sortByMergingMultipleFiles(sortMode, dataType, outFile, fileOfNames);
    }
}