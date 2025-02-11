import java.util.function.Function;
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
    public static final Map<String, int[]> textfiles = new LinkedHashMap<>();

    static {
        funcs.put("Insertion sort", InsertionSort::insertionSort); 
        funcs.put("Selection sort", SelectionSort::selectionSort); 
        funcs.put("Shell sort", ShellSort::shellSort); 
        funcs.put("Merge sort", MergeSort::mergeSort); 
        funcs.put("Merge-Insertion sort", MergeSort::hybridMergeSort); 
        funcs.put("Bottom Up Merge sort", MergeSort::bottomUpMergeSort); 
        funcs.put("Quick sort", QuickSort::quickSort); 
        funcs.put("Quick-Insertion sort", QuickSort::hybridQuickSort); 
        funcs.put("Median of 3 Quick sort", QuickSort::medianOf3); 
        funcs.put("Dutch Flag Quick sort", QuickSort::dutchFlag); 

        textfiles.put("int10.txt", SortingAlgorithms.getInputFromText("int10.txt")); 
        textfiles.put("int50.txt", SortingAlgorithms.getInputFromText("int50.txt")); 
        textfiles.put("int100.txt", SortingAlgorithms.getInputFromText("int100.txt")); 
        textfiles.put("int1000.txt", SortingAlgorithms.getInputFromText("int1000.txt")); 
        textfiles.put("bad.txt", SortingAlgorithms.getInputFromText("bad.txt")); 
        textfiles.put("int20k.txt", SortingAlgorithms.getInputFromText("int20k.txt")); 
        textfiles.put("dutch.txt", SortingAlgorithms.getInputFromText("dutch.txt")); 
        textfiles.put("int500k.txt", SortingAlgorithms.getInputFromText("int500k.txt")); 
        textfiles.put("intBig.txt", SortingAlgorithms.getInputFromText("intBig.txt")); 
    }

    public static void main(String[] args)
    {
        testAlgorithms(textfiles.get("int100.txt"));
        testTimes(textfiles);
    }

    public static boolean testAlgorithms(int[] input)
    {
        for (String funcName : SortingAlgorithms.funcs.keySet())
        {
            System.out.println(funcName);
            boolean isSorted = SortingAlgorithms.testSortingAlgorithm(SortingAlgorithms.funcs.get(funcName), input.clone(), false);
            System.out.println("Is sorted: " + isSorted);
            System.out.println("____________________________________________________________________\n");
            if (!isSorted) return false;
        }
        return true;
    }

    public static void testTimes(Map<String, int[]> textfiles)
    {
        for (String textfile : textfiles.keySet())
        {
            System.out.println("Testing for " + textfile + "\n");
            for (String funcName : SortingAlgorithms.funcs.keySet())
            {
                long algDuration = SortingAlgorithms.timeAlgorithm(SortingAlgorithms.funcs.get(funcName), textfiles.get(textfile).clone(), false);
                System.out.println(funcName + " took " + algDuration + "ns or " + algDuration / 1_000_000 + "ms to sort");
            }
            System.out.println("\n____________________________________________________________________\n\n");
        }
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
}