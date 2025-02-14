package Part1;
public class InsertionSort {
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

}
