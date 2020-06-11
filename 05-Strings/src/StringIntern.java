import javax.annotation.processing.SupportedSourceVersion;
import java.lang.reflect.Field;

public class StringIntern {

    private static final int UP_TO = 100;
    private static int ARRAY_SIZE = 1024*1024;

    public static void main(String[] args) throws InterruptedException {
        String str1 = "hello";
        String str2 = "hello";
        System.out.println("str1 == str2 " +( str1 == str2));
        String str3 = new String("hello");
        String str4 = "h".concat("ello");
        System.out.println("str3 == str4 " + (str3 == str4));
        System.out.println("str1 == str3 " + (str1 == str3));
/*        String strA = str4.intern();
        System.out.println("strA == str4 " + (strA == str4));
*/
        compareInternalArrayReferences(str3, str4);
        compareInternalArrayReferences(str1, str4);

        System.gc();
        System.out.println("str1==str3 " + (str1==str3));

        compareInternalArrayReferences(str3, str4);
        compareInternalArrayReferences(str1, str4);

    }

    public static void compareInternalArrayReferences(String strA, String strB) {
        try {
            Field valueField = String.class.getDeclaredField("value");
            valueField.setAccessible(true);
            System.out.println(valueField.get(strA) == valueField.get(strB));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
