package ru.academits.nikolenko.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range a) {
        if (this.to < a.getFrom() || a.getTo() < this.from) {
            return null;
        }
        return new Range(Math.max(this.from, a.getFrom()), Math.min(this.to, a.getTo()));
    }

    public Range[] getCombining(Range a) {
        if (this.to < a.getFrom() || a.getTo() < this.from) {
            Range c = new Range(this.from, this.to);
            return new Range[]{a, c};
        }
        return new Range[]{new Range(Math.min(this.from, a.getFrom()), Math.max(this.to, a.getTo()))};
    }

    public Range[] getDifference(Range a) {
        if (this.to < a.getFrom() || a.getTo() < this.from) {
            return new Range[]{new Range(this.from, this.to)};
        }
        if (this.from > a.getFrom() && this.to > a.getTo()) {
            return new Range[]{new Range(a.getTo(), this.to)};
        }
        if (this.from < a.getFrom() && this.to < a.getTo()) {
            return new Range[]{new Range(a.getFrom(), this.to)};
        }
        if (this.from < a.getFrom() && this.to > a.getTo()) {
            Range b = new Range(this.from, a.getFrom());
            Range c = new Range(a.getTo(), this.to);
            return new Range[]{b, c};
        }
        return null;
    }
}