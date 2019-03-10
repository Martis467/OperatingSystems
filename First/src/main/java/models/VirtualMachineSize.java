package models;

public enum VirtualMachineSize {

    smallest("32 bytes", 14, 4),
    small("64 bytes", 28, 8),
    medium("128 bytes", 56, 16),
    big("256 bytes", 112, 32),
    max("512 bytes", 224, 64);

    private final String title;
    private final int segmentSize;
    private final int stackSize;

    VirtualMachineSize(String title, int segmentSize, int stackSize)
    {
        this.title = title;
        this.segmentSize = segmentSize;
        this.stackSize = stackSize;
    }

    @Override
    public String toString() { return title; }

    public int getSegmentSize() { return segmentSize; }

    public int getStackSize() { return stackSize; }
}
