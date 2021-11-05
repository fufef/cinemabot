package parser;

public class Parser {
    public static int[] parseArrayToArrayOfInt(Integer[] array) {
        int[] result = new int[array.length];
        for (int i = 0; i < result.length; i++)
            result[i] = array[i];
        return result;
    }

    public static int parseObjectToInt(Object number) {
        return ((Number) number).intValue();
    }

    public static double parseObjectToDouble(Object number) {
        return ((Number) number).doubleValue();
    }
}
