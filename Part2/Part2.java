package Part2;

import Part1.SortingAlgorithms;

public class Part2
{
    public static void main(String[] arg)
    {
        int k = 10; // Number of top views to get
        MinHeap minHeap = new MinHeap(k);
        for (int viewCount : SortingAlgorithms.textfiles.get("int10")) {
            minHeap.insert(viewCount);
        }
        int[] topKViews = new int[k];
        System.out.println("Top " + k + " most viewed videos:");
        for (int i  = 0; i < k; i++) {
            topKViews[i] = minHeap.removeMin();
            System.out.println(topKViews[i]);
        }
    }
}
