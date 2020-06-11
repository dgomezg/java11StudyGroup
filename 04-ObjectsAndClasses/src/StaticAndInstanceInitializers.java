import javax.tools.JavaCompiler;
import java.util.concurrent.atomic.AtomicInteger;

public class StaticAndInstanceInitializers {

    static final AtomicInteger sequence = new AtomicInteger(1);

    static {
        System.out.println("Static initializer executed");
        System.out.println("Atomic Integer initialized with " + sequence.get());
    }


    {
        System.out.println("Instance initializer executed");
    }


    private final int id;

    public StaticAndInstanceInitializers() {
        id = sequence.incrementAndGet();
        System.out.println("Constructor");
    }

    @Override
    public String toString() {
        return "ClassMembers{" +
                "id=" + id +
                '}';
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        for (int i = 0; i<100; i++) {
            System.out.println(new StaticAndInstanceInitializers().toString());
        }
    }
}

