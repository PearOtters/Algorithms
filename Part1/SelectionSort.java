package Part1;
public class SelectionSort {
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
            SortingAlgorithms.swap(input, i, minIndex);
        }
        return input;
    }
}
