import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class SwitchExpressions {

    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_CHECK = "check";

    public static void main(String[] args) {

        switch (args.length) {
            case 0:
                System.out.println("No arguments provided");
                break;
            case 1:
            case 2:
            case 3:
            case 5:
            case 830:
                System.out.println("Some arguments provided");
                break;
            default:
                System.out.println("too many arguments"); break;
        }

        switch (args.length > 0 ? args[0] : "default") {
            case COMMAND_LIST:
                System.out.println("We are going to list");
                break;
            case "check":
                System.out.println("We are going to check the status");
                break;
            case "shutdown":
                System.out.println("Shutting down");
                break;
            default:
                System.out.println("Unknown command");
        }

        System.out.printf("hashCode for %s is %d\n", COMMAND_LIST, COMMAND_LIST.hashCode());
        System.out.printf("hashCode for %s is %d\n", "check", "check".hashCode());
        System.out.printf("hashCode for %s is %d\n", "shutdown", "shutdown".hashCode());

        //Why the first example compiles and the second one does not?
        //compiles (response numeric promotion of expressions)
        byte b = 10;
        switch (b + 1) {
            case 1000:
                System.out.println("hello!");
        }

        //This does not compile.
        /*
        switch (b) {
            case 1000:
                System.out.println("hello!");

        }
        */

    }
}
