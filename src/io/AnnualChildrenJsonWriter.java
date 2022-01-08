package io;

import com.fasterxml.jackson.annotation.JsonProperty;
import entities.Child;

import java.util.ArrayList;
import java.util.List;

public class AnnualChildrenJsonWriter {
    @JsonProperty("children")
    private final List<ChildrenJsonWriter> annualChildren;

    public AnnualChildrenJsonWriter() {
        this.annualChildren = new ArrayList<>();
    }

    /**
     * Adds each child to the list of annualChildren
     *
     * @param children  list to be added from
     */
    public void addListToJson(final List<Child> children) {
        for (Child child : children) {
            annualChildren.add(new ChildrenJsonWriter(child));
        }
    }
}
