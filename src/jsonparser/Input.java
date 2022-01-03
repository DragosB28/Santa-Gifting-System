package jsonparser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Input {
    private int numberOfYears;
    private double santaBudget;
    private InitialData initialData;
    private List<AnnualChanges> annualChanges;

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
        return "Input{" +
                "numberOfYears=" + numberOfYears +
                ", santaBudget=" + santaBudget +
                ", initialData=" + initialData +
                ", annualChanges=" + annualChanges +
                '}';
    }
}
