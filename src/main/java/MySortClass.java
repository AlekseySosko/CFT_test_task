import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MySortClass {

    public static void sortByMergingMultipleFiles(String sortMode, String dataType, String outFile, List<String> files) {
        String[] dataArray = createArray(files);

        if (dataType.equals("-i") && !arrayContentIsANumber(dataArray)) {
            dataType = "-s";
            System.out.println("Вы попытались отсортировать массив строк, как массив чисел.\n" +
                    "Значение аргумента \"args[1]\" будет изменено на \"-s\".");
        }

        mergeSort(dataArray, dataArray.length, sortMode, dataType);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            for (String data : dataArray) {
                writer.write(data + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(dataArray));
    }


    private static String[] createArray(List<String> files) {
        List<String> resultList = new ArrayList<>();
        boolean error = false;

        for (String file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                while (reader.ready()) {
                    String str = reader.readLine();
                    if (str.matches("^\\S+$")) {
                        resultList.add(str);
                    } else {
                        error = true;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (error) {
            System.out.println("В файлах обнаружены строки с пробелами," +
                    " они будут пропущены и не войдут в итоговый файл");
        }

        String[] resultArray = new String[resultList.size()];

        return resultList.toArray(resultArray);
    }

    private static void mergeSort(String[] array, int arraySize, String sortMode, String dataType) {

        if (arraySize < 2) {
            return;
        }

        int mid = arraySize / 2;
        String[] leftArray = new String[mid];
        String[] rightArray = new String[arraySize - mid];

        System.arraycopy(array, 0, leftArray, 0, mid);
        System.arraycopy(array, mid, rightArray, 0, arraySize - mid);

        mergeSort(leftArray, mid, sortMode, dataType);
        mergeSort(rightArray, arraySize - mid, sortMode, dataType);

        if (dataType.equals("-i")) {
            mergeInt(array, leftArray, rightArray, mid, arraySize - mid, sortMode);
        } else if (dataType.equals("-s")) {
            mergeStr(array, leftArray, rightArray, mid, arraySize - mid, sortMode);
        } else {
            if (arrayContentIsANumber(array)) {
                mergeInt(array, leftArray, rightArray, mid, arraySize - mid, sortMode);
            }
        }

    }

    private static void mergeStr(String[] array, String[] leftArray, String[] rightArray, int leftSize, int rightSize, String sortMode) {
        Comparator<String> comp = Comparator.comparingInt(String::length);

        int leftCounter = 0, rightCounter = 0, resultCounter = 0;

        while (leftCounter < leftSize && rightCounter < rightSize) {
            if (sortMode.equals("-a")) {
                if (comp.compare(leftArray[leftCounter], rightArray[rightCounter]) < 0) {
                    array[resultCounter++] = leftArray[leftCounter++];
                } else {
                    array[resultCounter++] = rightArray[rightCounter++];
                }
            } else if (sortMode.equals("-d")) {
                if (comp.compare(leftArray[leftCounter], rightArray[rightCounter]) > 0) {
                    array[resultCounter++] = leftArray[leftCounter++];
                } else {
                    array[resultCounter++] = rightArray[rightCounter++];
                }
            } else {
                if (comp.compare(leftArray[leftCounter], rightArray[rightCounter]) < 0) {
                    array[resultCounter++] = leftArray[leftCounter++];
                } else {
                    array[resultCounter++] = rightArray[rightCounter++];
                }
            }
        }

        while (leftCounter < leftSize) {
            array[resultCounter++] = leftArray[leftCounter++];
        }

        while (rightCounter < rightSize) {
            array[resultCounter++] = rightArray[rightCounter++];
        }
    }

    private static void mergeInt(String[] array, String[] leftArray, String[] rightArray, int leftSize, int rightSize, String sortMode) {
        int leftCounter = 0, rightCounter = 0, resultCounter = 0;

        while (leftCounter < leftSize && rightCounter < rightSize) {
            if (sortMode.equals("-a")) {
                if (Integer.parseInt(leftArray[leftCounter]) <= Integer.parseInt(rightArray[rightCounter])) {
                    array[resultCounter++] = leftArray[leftCounter++];
                } else {
                    array[resultCounter++] = rightArray[rightCounter++];
                }
            } else if (sortMode.equals("-d")) {
                if (Integer.parseInt(leftArray[leftCounter]) >= Integer.parseInt(rightArray[rightCounter])) {
                    array[resultCounter++] = leftArray[leftCounter++];
                } else {
                    array[resultCounter++] = rightArray[rightCounter++];
                }
            } else {
                if (Integer.parseInt(leftArray[leftCounter]) <= Integer.parseInt(rightArray[rightCounter])) {
                    array[resultCounter++] = leftArray[leftCounter++];
                } else {
                    array[resultCounter++] = rightArray[rightCounter++];
                }
            }
        }

        while (leftCounter < leftSize) {
            array[resultCounter++] = leftArray[leftCounter++];
        }

        while (rightCounter < rightSize) {
            array[resultCounter++] = rightArray[rightCounter++];
        }
    }

    public static boolean arrayContentIsANumber(String[] array) {
        for (String str : array) {
            try {
                Integer.parseInt(str);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}
