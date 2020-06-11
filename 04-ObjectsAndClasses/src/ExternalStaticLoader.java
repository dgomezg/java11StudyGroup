public class ExternalStaticLoader {


    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("Started");
        Thread.sleep(5000);
        System.out.println("Loading class");
        Class clazz = Class.forName("StaticAndInstanceInitializers");
        System.out.println("Class Loaded");
        Thread.sleep(5000);
        System.out.println("Creating instance");
        System.out.println(clazz.newInstance());
    }
}
