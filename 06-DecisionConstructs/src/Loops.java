import java.util.ArrayList;

public class Loops {

    public static void main(String[] args) {
        String[] values = {"one", "two", "three"};

        iterateForEnhanced(values);

        iterateFor(values);

    }

    public static void iterateFor(String[] values) {
        for(int i=0; i<values.length; i++) {
            System.out.printf("Value is %s\n", values[i]);
        }
    }

    public static void iterateForEnhanced(String[] values){
        for (String value: values){
            System.out.printf("Value is %s\n", value);
        }
    }

    private static void NestedArraysPerformance() {
        int[][] intArr = {{0,1},{1,2}};
        for(int i=0; i < intArr.length; i++) {
            int[] currArr = intArr[i];
            for (int j=0; j< currArr.length; j++) {
                currArr[j] = i+j;
            }
        }
    }
}
