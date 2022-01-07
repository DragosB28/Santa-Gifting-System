package jsonparser;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Writer {
    @JsonProperty("annualChildren")
    private List<AnnualChildrenJsonWriter> annualChildrenJsonWriter = new ArrayList<>();

    public void addAnnualChildrenJsonList(AnnualChildrenJsonWriter annualChildrenJsonWriter) {
        this.annualChildrenJsonWriter.add(annualChildrenJsonWriter);
    }
}
