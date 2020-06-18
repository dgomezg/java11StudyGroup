package eu.javaspecialists.util;

import java.lang.management.ManagementFactory;
import java.util.Optional;

public class VMArgsChecker {
    public static Optional<String> findVMArg(String arg) {
        return ManagementFactory.getRuntimeMXBean()
                .getInputArguments().stream()
                .filter(vmarg -> vmarg.startsWith(arg))
                .findFirst();
    }
}
