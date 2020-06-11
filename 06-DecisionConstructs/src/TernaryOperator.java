public class TernaryOperator {

    private static final boolean USE_HARDCODED_RATE = false;
    public static void main(String[] args) {
        printMealDescription(betterIsSweet(100, false));
        printMealDescription(betterIsSweet(150,false));
        printMealDescription(betterIsSweet(110,true));
    }

    public static boolean isSweet(boolean extraSugar) {
        int calories = extraSugar ? 200 : 100;
        boolean isSweet = (calories == 100 ? false : true);
        return isSweet;
    }

    public static boolean betterIsSweet(int calories, boolean extraSugar) {
        int realCalories = calories + (extraSugar ? 100 : 0);
        return realCalories > 150;
    }

    public static void printMealDescription(boolean sweet) {
        System.out.printf("we have a very %s meal\n", sweet? "sweet": "special");
    }
    public static double getRate() {
        return USE_HARDCODED_RATE? 10.0 : getRateFromDB();
    }

    private static double getRateFromDB() {
        return Math.random() * 10;
    }
}
