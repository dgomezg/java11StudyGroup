

public class CompileTimeConstant {

    public static void main(String[] args) {
        int val;
        final int i = 0; //Compile constant (because of final)
        //int i = 0; //Not compile time constant -> System.out.println on last line won't compile.
        if (i == 0) {
            val = 10;
        }
        System.out.println(val);
    }
}
