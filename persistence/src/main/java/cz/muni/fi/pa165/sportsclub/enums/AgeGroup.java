package cz.muni.fi.pa165.sportsclub.enums;

public enum AgeGroup {
    M16(0, 16), M20(16, 20), M24(20, 24), MS(24, 100);

    private int lower;
    private int upper;

    private AgeGroup(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public int getLowerBoundary() {
        return lower;
    }

    public int getUpperBoundary() {
        return upper;
    }
}
