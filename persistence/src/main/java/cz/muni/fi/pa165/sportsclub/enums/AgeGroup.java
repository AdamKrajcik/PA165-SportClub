package cz.muni.fi.pa165.sportsclub.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public enum AgeGroup {
    M16(0, 16), M20(17, 20), M24(21, 24), MS(25, 100);

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


    public static List<AgeGroup> getAllAscending() {
        return new ArrayList<>(Arrays.asList(AgeGroup.values()));
    }

    public AgeGroup oneAbove() {
        List<AgeGroup> sorted = getAllAscending();

        ListIterator<AgeGroup> iterator = sorted.listIterator(sorted.indexOf(this));
        iterator.next();

        return iterator.hasNext()
                ? iterator.next()
                : null;
    }

    public AgeGroup oneBelow() {
        List<AgeGroup> sorted = getAllAscending();

        ListIterator<AgeGroup> iterator = sorted.listIterator(sorted.indexOf(this));

        return iterator.hasPrevious()
                ? iterator.previous()
                : null;
    }

    @Override
    public String toString(){
        return "M" + this.getLowerBoundary()+ "-" + this.getUpperBoundary();
    }
}
