package eu.javaspecialists.util;
import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;
import java.util.stream.*;

/**
 * This is meant to be an extremely simple GC parser, only to be used for our
 * exercises.  For more serious analysis, we recommend JClarity's Censum or
 * HP JMeter (not Apache JMeter, that's something else).
 */
public class SuperSimpleGCParser {
    public static void main(String... args) {
        if (args.length == 0) {
            System.err.println("Usage: java SuperSimpleGCParser " +
                    "filename1 filename2 ...\n" +
                    "\tGC Log should be generated with just -Xlog:gc:filename");
            System.exit(1);
        }

        System.out.println("Disclaimer: This is not a robust GC viewer.  " +
                "Please use JClarity Censum for serious analysis.");
        Stream.of(args)
                .map(GCStatistics::new)
                .forEach(System.out::println);
    }

    private static boolean registered = false;

    public static void showGCLogSummaryAtExit() {
        if (!registered) {
            Optional<String> xloggc = VMArgsChecker.findVMArg("-Xlog:gc:");
            String gclog = xloggc.orElseThrow(() ->
                    new AssertionError("Please use -Xlog:gc:filename.vgc VM argument!"))
                    .substring("-Xlog:gc:".length());
            Runtime.getRuntime().addShutdownHook(new Thread(() -> main(gclog)));
            registered = true;
        }
    }

    private enum Type {YOUNG, OLD, UNDEFINED}

    private static class Event {
        private final String line;
        private final Type type;
        private final long timestampInMilliseconds;
        private final long memoryBefore;
        private final long memoryAfter;
        private final double durationInMilliseconds;


        public Event(String line) {
            this.line = line;
            type = extractType(line);
            timestampInMilliseconds = extractTimeStamp(line);
            memoryBefore = extractMemoryBefore(line);
            memoryAfter = extractMemoryAfter(line);
            durationInMilliseconds = extractDuration(line);
        }

        public boolean isOld() {
            return type == Type.OLD;
        }

        public boolean isYoung() {
            return type == Type.YOUNG;
        }

        public long getMemoryReclaimed() {
            return memoryBefore - memoryAfter;
        }

        public double getTimeInGC() {
            return durationInMilliseconds;
        }

        public long getHeapAfterGC() {
            return memoryAfter;
        }
    }

    private static Type extractType(String line) {
        if (line.contains("Pause Young")) return Type.YOUNG;
        else if (line.contains("Pause Mixed")) return Type.YOUNG;
        else if (line.contains("Pause Full")) return Type.OLD;
        else if (line.contains("Pause Initial")) return Type.OLD;
        else if (line.contains("Pause Remark")) return Type.OLD;
        else if (line.contains("Pause Cleanup")) return Type.OLD;
        else return Type.UNDEFINED;
    }

    private static long extractTimeStamp(String line) {
        line = line.substring(1); // skip the first "["
        line = line.substring(0, line.indexOf("s]")); // cut anything after the time stamp "s]..."
        line = line.replaceAll("\\.", "");
        line = line.replaceAll(",", "");
        line = line.replaceAll("^0*", "");
        return Long.parseLong(line);
    }

    private static long extractMemoryBefore(String line) {
        line = line.replaceAll("M->.*", "");
        line = line.replaceAll(".* ", "");
        return Long.parseLong(line);
    }

    private static long extractMemoryAfter(String line) {
        line = line.replaceAll(".*M->", "");
        line = line.replaceAll("M.*", "");
        return Long.parseLong(line);
    }

    private static double extractDuration(String line) {
        line = line.replaceAll("ms$", "");
        line = line.replaceAll(".* ", "");
        line = line.replace(',', '.');
        return Double.parseDouble(line);
    }

    private static class GCStatistics {
        private final String filename;
        private final long durationOfLog;
        private final int numberOfGCs;
        private final int numberOfYoungGCs;
        private final int numberOfOldGCs;
        private final int numberOfUndefinedGCs;
        private final long memoryReclaimedDuringYoung;
        private final long memoryReclaimedDuringOld;
        private final double timeInGCs;
        private final double timeInYoungGCs;
        private final double timeInOldGCs;
        private final long maxHeapAfterGC;
        private final long totalMemoryAllocated;
        private final double averageCreationRate;
        private final double percentageOfTimeInGC;
        private final IntSummaryStatistics averateTimeInYoungGCs;

        public GCStatistics(String filename) {
            try (
                    Stream<String> lines = Files.lines(Paths.get(filename))
            ) {
                List<Event> events = lines
                        .collect(Collectors.toList())
                        .stream()
//                    .parallel()
                        .filter(line -> line.contains("s][info][gc] GC("))
                        .filter(line -> line.matches(".*\\dM->\\d*M.*"))
                        .map(Event::new).collect(Collectors.toList());
                this.filename = filename;
                if (events.isEmpty()) {
                    durationOfLog = 0;
                    numberOfGCs = 0;
                    numberOfYoungGCs = 0;
                    numberOfOldGCs = 0;
                    numberOfUndefinedGCs = 0;
                    memoryReclaimedDuringYoung = 0;
                    memoryReclaimedDuringOld = 0;
                    timeInGCs = 0;
                    timeInYoungGCs = 0;
                    timeInOldGCs = 0;
                    maxHeapAfterGC = 0;
                    totalMemoryAllocated = 0;
                    averageCreationRate = 0;
                    percentageOfTimeInGC = 0;
                    averateTimeInYoungGCs = new IntSummaryStatistics();
                } else {
                    Event lastEvent = events.get(events.size() - 1);
                    durationOfLog = (long) (lastEvent.timestampInMilliseconds + lastEvent.durationInMilliseconds);
                    numberOfGCs = events.size();
                    numberOfYoungGCs = (int) events.stream().filter(Event::isYoung).count();
                    numberOfOldGCs = (int) events.stream().filter(Event::isOld).count();
                    numberOfUndefinedGCs = numberOfGCs - numberOfYoungGCs - numberOfOldGCs;
                    memoryReclaimedDuringYoung = events.stream().filter(Event::isYoung).mapToLong(Event::getMemoryReclaimed).sum();
                    memoryReclaimedDuringOld = events.stream().filter(Event::isOld).mapToLong(Event::getMemoryReclaimed).sum();
                    timeInGCs = events.stream().mapToDouble(Event::getTimeInGC).sum();
                    timeInYoungGCs = events.stream().filter(Event::isYoung).mapToDouble(Event::getTimeInGC).sum();
                    averateTimeInYoungGCs = events.stream().filter(Event::isYoung).mapToDouble(Event::getTimeInGC).mapToInt(time -> (int) time).summaryStatistics();
                    timeInOldGCs = events.stream().filter(Event::isOld).mapToDouble(Event::getTimeInGC).sum();
                    maxHeapAfterGC = events.stream().mapToLong(Event::getHeapAfterGC).max().orElseThrow(AssertionError::new);
                    totalMemoryAllocated = events.stream().mapToLong(Event::getMemoryReclaimed).sum() + lastEvent.getHeapAfterGC();
                    averageCreationRate = totalMemoryAllocated * 1000 / durationOfLog;
                    percentageOfTimeInGC = timeInGCs * 100 / durationOfLog;
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        public String toString() {
            return "GCStatistics for " + filename +
                    "\n\tdurationOfLog=" + Duration.ofMillis(durationOfLog) +
                    "\n\tnumberOfGCs=" + numberOfGCs +
                    "\n\tnumberOfYoungGCs=" + numberOfYoungGCs +
                    "\n\tnumberOfOldGCs=" + numberOfOldGCs +
                    (numberOfUndefinedGCs > 0 ? "\n\tnumberOfUndefinedGCs=" + numberOfUndefinedGCs : "") +
                    "\n\tmemoryReclaimedDuringYoung=" + Memory.format(memoryReclaimedDuringYoung, Memory.MEGABYTES, 3) +
                    "\n\tmemoryReclaimedDuringOld=" + Memory.format(memoryReclaimedDuringOld, Memory.MEGABYTES, 3) +
                    "\n\tmaxHeapAfterGC=" + Memory.format(maxHeapAfterGC, Memory.MEGABYTES, 3) +
                    "\n\ttotalMemoryAllocated=" + Memory.format(totalMemoryAllocated, Memory.MEGABYTES, 3) +
                    "\n\taverageCreationRate=" + Memory.format(averageCreationRate, Memory.MEGABYTES, 2) + "/s" +
                    "\n\ttimeInGCs=" + Duration.ofMillis((long) (timeInGCs)) +
                    "\n\ttimeInYoungGCs=" + Duration.ofMillis((long) (timeInYoungGCs)) +
                    "\n\taverageTimeInYoungGCs=" + String.format(Locale.US, "%.3fms", averateTimeInYoungGCs.getAverage()) +
                    "\n\ttimeInOldGCs=" + Duration.ofMillis((long) (timeInOldGCs)) +
                    "\n\tpercentageOfTimeInGC=" + String.format(Locale.US, "%.2f%%", percentageOfTimeInGC);
        }
    }
}
