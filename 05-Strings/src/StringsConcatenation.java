import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * It will be interesting to compile this class and see how the bytecode for the concatenation is
 * using StringBuilder underneath.
 *
 * compile the class and execute from the command line
 *
 * javap -c -s StringsConcatenation.class
 *
 * from the out folder.
 */
public class StringsConcatenation {
    static final String s1 = "hello";
    static String s2 = "hello " + "World";
    static String s3 = s1 + " world";

    public static void main(String[] args) {
        System.out.println(s1 + " " + s2 + " " + s3 );
    }

    public static String directConcat(String a, String b, String c) {
        return a + " " + b + " " + c;
    }

    public static String concatInForEach (String[] strings) {
        String s = "";
        for(String string: strings) {
            s = s + string;
        }
        return s;
    }

    public static String concatInFor(String[] strings) {
        String s = "";
        for (int i = 0; i < strings.length; i++) {
            s = s + strings[i];
        }
        return s;
    }

    public static String concatWithStreamsCollector(String[] strings) {
        return Arrays.stream(strings).collect(Collectors.joining(", "));
    }

    public static String concatRandomNumbers(int numOfNumbers) {
        String s = "";
        for (int i = 0; i < numOfNumbers; i++) {
            s += " " + randomNumber();
        }
        return s;
    }

    public static int randomNumber() {
        return (int)Math.random()*100;
    }
}
