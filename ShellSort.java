public class ShellSort {
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
                    SortingAlgorithms.swap(input, j, j - h);
                }
            }
            h /= 3;
        }
        return input;
    }
}
