package enums;

import java.util.Arrays;
import java.util.Optional;

public enum VMSize {

    /*
     * VM enum:
     *      "x bytes" - string for total VM size
     *       y - DS and CS size
     *       z - stack size
     */
    Smallest("32 bytes", 14, 4),
    Small("64 bytes", 28, 8),
    Medium("128 bytes", 56, 16),
    Big("256 bytes", 112, 32),
    Max("512 bytes", 224, 64);

    private final String title;
    private final int segmentSize;
    private final int stackSize;

    VMSize(String title, int segmentSize, int stackSize)
    {
        this.title = title;
        this.segmentSize = segmentSize;
        this.stackSize = stackSize;
    }

    @Override
    public String toString() { return title; }

    /**
     * DS or CS segment size
     * @return
     */
    public int getSegmentSize() { return segmentSize; }

    /**
     * @return stack segment size
     */
    public int getStackSize() { return stackSize; }

    /**
     * @return virtual machine size
     */
    public int getVmSize() {return segmentSize * 2 + stackSize;}

    /**
     * Parses out a VMSize enum from a given vm string
     * @param vm
     * @return
     */
    public static VMSize getVMSize(String vm){
        if (vm == null)
            return null;

        return findCommand(vm).orElse(null);
    }

    private static Optional<VMSize> findCommand(String vm) {
        return Arrays.stream(values())
                .filter(v -> vm.contains(v.toString())).findFirst();
    }
}
