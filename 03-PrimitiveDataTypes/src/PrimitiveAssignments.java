

class MyPrivateClass {
    int value;
}

public class PrimitiveAssignments {
    static byte b = 10;
    static char c = 'x';
    static short s = 300;
    static int i;
    static long l;
    static float f;
    static double d;

    MyPrivateClass myClazz;
    int instanceId;


    public void printme() {

        System.out.println(l);
    }

    public static void main(String[] args) {

        explicitNarrowingWithSpillage();

    }

    public static void implicitWideningConversions() {
        i = b;
        i = s;
        l = i;
        f = i;
        d = f;
    }

    public static void implicitNarrowing() {
        final int ten = 10;
        b = ten;
    }

    public static void explicitNarrowingViaCast() {
        d = 10.1;
        f = (float) d;
        i = (int) f;
        System.out.println(f);
        System.out.println(i);

        char c = (char) i;
        b = (byte) i;
    }

    public static void explicitNarrowingWithSpillage() {
        int oneHundredAndTwentyEight = 128; /*  “00000000 00000000 00000000 10000000” */
        byte by = (byte) oneHundredAndTwentyEight; /* 10000000 == 0000000 + sign bit set to 1 (-)) */
        System.out.println(by);
        System.out.println(Integer.toBinaryString(oneHundredAndTwentyEight));

    }


}


