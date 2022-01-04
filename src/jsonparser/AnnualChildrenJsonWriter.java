package jsonparser;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class AnnualChildrenJsonWriter {
    @JsonProperty("annualChildren")
    private List<ChildrenJsonWriter> annualChildren = new ArrayList<>();

    public AnnualChildrenJsonWriter(List<Child> children) {
        for (Child child : children) {
            annualChildren.add(new ChildrenJsonWriter(child));
        }
    }
}
