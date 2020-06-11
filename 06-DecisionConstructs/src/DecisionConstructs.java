public class DecisionConstructs {

    public static void main(String[] args) {

        int x = 0;
        if (x++ > 0 ) {
            System.out.println("Inside first if. value of x = " + x);
            x--;
            System.out.println("Exiting first if. value of x = " + x);
        }

        if (++x == 2) {
            System.out.println("Inside 2nd if. value of x = " + x);
            x++;
            System.out.println("Exiting 2nd if. value of x = " + x);
        }

        System.out.println(x);
    }
}
