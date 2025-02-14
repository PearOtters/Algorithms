package Part1;
public class QuickSort {
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
                SortingAlgorithms.swap(input, i, j);
            }
        }
        SortingAlgorithms.swap(input, i + 1, r);
        return i + 1;
    }

    public static int[] hybridQuickSort(int[] input)
    {
        hybridQuickSort(input, 0, input.length-1);
        InsertionSort.insertionSort(input, 0, input.length-1);
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
        SortingAlgorithms.swap(input, (p+r)/2, r-1);
        if (input[r-1] < input[p]) SortingAlgorithms.swap (input, p, r-1);
        if (input[r] < input[p]) SortingAlgorithms.swap (input, p, r);
        if (input[r] < input[r-1]) SortingAlgorithms.swap (input, r-1, r);
        int q = partition(input, p, r);
        medianOf3(input, p, q-1);
        medianOf3(input, q+1, r);
    }

    public static int[] dutchFlag(int[] input)
    {
        dutchFlag(input, 0, input.length-1);
        return input;
    }

    public static void dutchFlag(int[] input, int p, int r)
    {
        if (r <= p) return;
        int v = input[r];
        int i = p-1, j=r, l=p-1, q=r, k;
        for (;;)
        {
            while (input[++i] < v);
            while (v < input[--j]) if (j == p) break;
            if (i >= j) break;
            SortingAlgorithms.swap(input, i, j);
            if (input[i] == v) { l++; SortingAlgorithms.swap(input, l, i); }
            if (v == input[j]) { q--; SortingAlgorithms.swap(input, q, j); }
        }
        SortingAlgorithms.swap(input, i, r);
        j = i-1;
        i++;
        for (k = p; k <= l; k++, j--) SortingAlgorithms.swap(input, k, j);
        for (k = r-1; k >= q; k--, i++) SortingAlgorithms.swap(input, k, i);
        dutchFlag(input, p, j);
        dutchFlag(input, i, r);
    }
}
