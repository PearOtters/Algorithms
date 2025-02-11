import java.util.Arrays;
import java.util.stream.IntStream;

public class MergeSort {
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
                InsertionSort.insertionSort(input, p, r);
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

}
