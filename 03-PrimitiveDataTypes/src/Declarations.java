public class Declarations {

    public static void main(String[] args) {

        //Declaration with no initialization
        int x;
         String s = "H";

        //Declaration and initialization
        int i = 10;
        int Y = i;
        String str2 = new String("Hello");
        int m = 20;
        int p = m = 10;


        //int j = k = 10;
        System.out.println(s + " world");

        var value = 10;
        var valuem = m;
        var valuestr = str2;

        System.out.printf("value is of type %s and has value %s", valuestr.getClass().getSimpleName(), valuestr);
    }
}
