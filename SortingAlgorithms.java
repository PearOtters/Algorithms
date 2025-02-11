import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SortingAlgorithms
{
    public static final Map<String, Function<int[], int[]>> funcs = new LinkedHashMap<>();
    static {
        funcs.put("Insertion sort", SortingAlgorithms::insertionSort); 
        funcs.put("Selection sort", SortingAlgorithms::selectionSort); 
        funcs.put("Shell sort", SortingAlgorithms::shellSort); 
        funcs.put("Merge sort", SortingAlgorithms::mergeSort); 
        funcs.put("Merge-Insertion sort", SortingAlgorithms::hybridMergeSort); 
        funcs.put("Bottom Up Merge sort", SortingAlgorithms::bottomUpMergeSort); 
        funcs.put("Quick sort", SortingAlgorithms::quickSort); 
        funcs.put("Quick-Insertion sort", SortingAlgorithms::hybridQuickSort); 
        funcs.put("Median of 3 Quick sort", SortingAlgorithms::medianOf3); 
        funcs.put("Dutch Flag Quick sort", SortingAlgorithms::dutchFlag); 
    }

    public static int[] getInputFromText(String filename)
    {
        List<Integer> list = new ArrayList<Integer>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] input = new int[list.size()];
        for (int i = 0; i < input.length; i++)
        {
            input[i] = list.get(i);
        }
        return input;
    }

    private static boolean isSorted(int[] input, int[] result) {
        Arrays.sort(input);
        try
        {
            for (int i = 0; i < input.length - 1; i++) {
                if (input[i] != result[i]) {
                    return false;
                }
            }
        } catch(Exception e) { return false; }
        return true;
    }

    public static boolean testSortingAlgorithm(Function<int[], int[]> func, int[] input, boolean print)
    {
        int[] result = func.apply(Arrays.copyOf(input, input.length));
        if (print)
        {
            System.out.println("Input:  " + Arrays.toString(input));
            System.out.println("Result: " + Arrays.toString(result));
        }
        return isSorted(input, result);
    }

    public static long timeAlgorithm(Function<int[], int[]> func, int[] input, boolean print)
    {
        long startTime = System.nanoTime();
        if (print) { System.out.println("Start Time: " + startTime); }
        func.apply(Arrays.copyOf(input, input.length));
        long endTime = System.nanoTime();
        if (print) { System.out.println("End Time: " + endTime); }
        return endTime - startTime;
    }

    public static void swap(int[] input, int i, int j)
    {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

    public static int[] insertionSort(int[] input)
    {
        int n = input.length;
        for (int j = 1; j < n; j++)
        {
            int key = input[j];
            int i = j - 1;
            while (i >= 0 && input[i] > key)
            {
                input[i + 1] = input[i];
                i--;
            }
            input[i + 1] = key;
        }
        return input;
    }

    public static void insertionSort(int[] input, int p, int r)
    {
        for (int j = p + 1; j <= r; j++)
        {
            int key = input[j];
            int i = j - 1;
            while (i >= p && input[i] > key)
            {
                input[i + 1] = input[i];
                i--;
            }
            input[i + 1] = key;
        }
    }

    public static int[] selectionSort(int[] input)
    {
        int n = input.length;
        for (int i = 0; i < n; i++)
        {
            int minIndex = i;
            for (int j = i; j < n; j++)
            {
                if (input[j] < input[minIndex])
                {
                    minIndex = j;
                }
            }
            swap(input, i, minIndex);
        }
        return input;
    }
    
    public static int[] shellSort(int[] input)
    {
        int h = 1; int n = input.length;
        while (h < n / 3)
        {
            h = 3 * h + 1;
        }
        while (h >= 1)
        {
            for (int i = h; i < n; i++)
            {
                for (int j = i; j >= h && input[j] < input[j-h]; j-=h)
                {
                    swap(input, j, j - h);
                }
            }
            h /= 3;
        }
        return input;
    }

    public static int[] mergeSort(int[] input)
    {
        mergeSort(input, 0, input.length - 1);
        return input;
    }

    private static void mergeSort(int[] input, int p, int r)
    {
        if (p < r)
        {
            int q = (p + r) / 2;
            mergeSort(input, p, q);
            mergeSort(input, q + 1, r);
            merge(input, p, q, r);
        }
    }

    private static void merge(int[] input, int p, int q, int r)
    {
        int[] L = IntStream.concat(Arrays.stream(Arrays.copyOfRange(input, p, q + 1)), Arrays.stream(new int[]{ Integer.MAX_VALUE })).toArray();
        int[] R = IntStream.concat(Arrays.stream(Arrays.copyOfRange(input, q + 1, r + 1)), Arrays.stream(new int[]{ Integer.MAX_VALUE })).toArray();
        int i = 0, j = 0;
        for (int k = p; k <= r; k++)
        {
            if (L[i] <= R[j])
            {
                input[k] = L[i];
                i++;
            }
            else
            {
                input[k] = R[j];
                j++;
            }
        }
    }

    public static int[] hybridMergeSort(int[] input)
    {
        hybridMergeSort(input, 0, input.length - 1);
        return input;
    }

    private static void hybridMergeSort(int[] input, int p, int r)
    {
        if (p < r)
        {
            if (r - p + 1 > 64)
            {
                int q = (p + r) / 2;
                hybridMergeSort(input, p, q);
                hybridMergeSort(input, q + 1, r);
                merge(input, p, q, r);
            }
            else
            {
                insertionSort(input, p, r);
            }
        }
    }

    public static int[] bottomUpMergeSort(int[] input)
    {
        bottomUpMergeSort(input, 0, input.length - 1);
        return input;
    }

    public static void bottomUpMergeSort(int[] input, int p, int r)
    {
        int n = r - p + 1;
        for (int sz = 1; sz < n; sz*=2)
        {
            for (p = 0; p < n-sz; p+=sz*2)
            {
                merge(input, p, p+sz-1, Math.min(p+sz*2-1, n-1));
            }
        }
    }

    public static int[] quickSort(int[] input)
    {
        quickSort(input, 0, input.length-1);
        return input;
    }

    public static void quickSort(int[] input, int p, int r)
    {
        if (p < r)
        {
            int q = partition(input, p, r);
            quickSort(input, p, q-1);
            quickSort(input, q+1, r);
        }
    }

    public static int partition(int[] input, int p, int r)
    {
        int x = input[r];
        int i = p - 1;
        for (int j = p; j < r; j++)
        {
            if (input[j] <= x)
            {
                i++;
                swap(input, i, j);
            }
        }
        swap(input, i + 1, r);
        return i + 1;
    }

    public static int[] hybridQuickSort(int[] input)
    {
        hybridQuickSort(input, 0, input.length-1);
        insertionSort(input, 0, input.length-1);
        return input;   
    }

    private static void hybridQuickSort(int[] input, int p, int r)
    {
        if (r - p <= 64) return;
        int q = partition(input, p, r);
        hybridQuickSort(input, p, q-1);
        hybridQuickSort(input, q+1, r);
    }

    public static int[] medianOf3(int[] input)
    {
        medianOf3(input, 0, input.length-1);
        return input;
    }

    public static void medianOf3(int[] input, int p, int r)
    {
        if (r <= p) return;
        int v = input[r];
        int i = p-1, j=r, l=p-1, q=r, k;
        for (;;)
        {
            while (input[++i] < v);
            while (v < input[--j]) if (j == p) break;
            if (i >= j) break;
            swap(input, i, j);
            if (input[i] == v) { l++; swap(input, l, i); }
            if (v == input[j]) { q--; swap(input, q, j); }
        }
        swap(input, i, r);
        j = i-1;
        i++;
        for (k = p; k <= l; k++, j--) swap(input, k, j);
        for (k = r-1; k >= q; k--, i++) swap(input, k, i);
        medianOf3(input, p, j);
        medianOf3(input, i, r);
    }

    public static int[] dutchFlag(int[] input)
    {
        return input;
    }
}