import java.util.Arrays;
import java.util.Random;

class SortingAlgorithms {
    // пузырьковая сортировка
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
    // сортировка вставками
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}

public class Main {
    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] smallArray = generateRandomArray(1000);
        int[] largeArray = generateRandomArray(10000);

        long startTime, elapsedTime;

        // Пузырьковая сортировка для небольшого массива
        System.out.println("Пузырьковая сортировка для небольшого массива:");
        System.out.println(Arrays.toString(smallArray));
        startTime = System.nanoTime();
        SortingAlgorithms.bubbleSort(smallArray);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println(Arrays.toString(smallArray));
        System.out.println("Затраченное время: " + elapsedTime + " ns\n");

        // Пузырьковая сортировка для большого массива
        System.out.println("Пузырьковая сортировка для большого массива:");
        System.out.println(Arrays.toString(largeArray));
        startTime = System.nanoTime();
        SortingAlgorithms.bubbleSort(largeArray);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println(Arrays.toString(largeArray));
        System.out.println("Затраченное время: " + elapsedTime + " ns\n");

        smallArray = generateRandomArray(1000);
        largeArray = generateRandomArray(10000);

        // Сортировка вставками для небольшого массива
        System.out.println("Сортировка вставками для небольшого массива:");
        System.out.println(Arrays.toString(smallArray));
        startTime = System.nanoTime();
        SortingAlgorithms.insertionSort(smallArray);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println(Arrays.toString(smallArray));
        System.out.println("Затраченное время: " + elapsedTime + " ns\n");

        // Сортировка вставками для большого массива
        System.out.println("Сортировка вставками для большого массива:");
        System.out.println(Arrays.toString(largeArray));
        startTime = System.nanoTime();
        SortingAlgorithms.insertionSort(largeArray);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println(Arrays.toString(largeArray));
        System.out.println("Затраченное время: " + elapsedTime + " ns");
    }
}