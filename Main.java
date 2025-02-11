import java.util.LinkedHashMap;
import java.util.Map;

public class Main
{
    public static void main(String[] args)
    {
        Map<String, int[]> textfiles = new LinkedHashMap<>();
        textfiles.put("int10.txt", SortingAlgorithms.getInputFromText("int10.txt")); 
        textfiles.put("int50.txt", SortingAlgorithms.getInputFromText("int50.txt")); 
        textfiles.put("int100.txt", SortingAlgorithms.getInputFromText("int100.txt")); 
        textfiles.put("int1000.txt", SortingAlgorithms.getInputFromText("int1000.txt")); 
        textfiles.put("bad.txt", SortingAlgorithms.getInputFromText("bad.txt")); 
        textfiles.put("int20k.txt", SortingAlgorithms.getInputFromText("int20k.txt")); 
        textfiles.put("dutch.txt", SortingAlgorithms.getInputFromText("dutch.txt")); 
        textfiles.put("int500k.txt", SortingAlgorithms.getInputFromText("int500k.txt")); 
        textfiles.put("intBig.txt", SortingAlgorithms.getInputFromText("intBig.txt")); 

        //boolean isSorted = SortingAlgorithms.testSortingAlgorithm(SortingAlgorithms::medianOf3, textfiles.get("int100.txt").clone(), true);
        //System.out.println("Is sorted: " + isSorted);

        testAlgorithms(textfiles.get("int100.txt"));
        testTimes(textfiles);
    }

    public static void testAlgorithms(int[] input)
    {
        for (String funcName : SortingAlgorithms.funcs.keySet())
        {
            System.out.println(funcName);
            boolean isSorted = SortingAlgorithms.testSortingAlgorithm(SortingAlgorithms.funcs.get(funcName), input.clone(), false);
            System.out.println("Is sorted: " + isSorted);
            System.out.println("____________________________________________________________________\n");
        }
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
}
