package entities;

import java.util.List;

public final class Input {
    private final int numberOfYears;
    private final double santaBudget;
    private final InitialData initialData;
    private final List<AnnualChanges> annualChanges;

    public Input() {
        this.numberOfYears = 0;
        this.santaBudget = 0;
        this.initialData = null;
        this.annualChanges = null;
    }

    public Input(final int numberOfYears, final double santaBudget,
                 final InitialData initialData,
                 final List<AnnualChanges> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.initialData = initialData;
        this.annualChanges = annualChanges;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public double getSantaBudget() {
        return santaBudget;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public List<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }

    @Override
    public String toString() {
        return "Input{"
                +
                "numberOfYears=" + numberOfYears
                +
                ", santaBudget=" + santaBudget
                +
                ", initialData=" + initialData
                +
                ", annualChanges=" + annualChanges
                +
                '}';
    }
}
