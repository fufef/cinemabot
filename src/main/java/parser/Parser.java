package parser;

public class Parser {
    public static int parseToInt(Object number) {
        return ((Number) number).intValue();
    }

    public static double parseToDouble(Object number) {
        return ((Number) number).doubleValue();
    }
}
