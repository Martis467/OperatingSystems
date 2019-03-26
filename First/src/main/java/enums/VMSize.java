package enums;

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
     *
     * @return stack segment size
     */
    public int getStackSize() { return stackSize; }

    /**
     * Maximum value that the stack may have
     * @return
     */
    public int getMaxVMAddress() { return segmentSize * 2 + stackSize - 1;}
}
