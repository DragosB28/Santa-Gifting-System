package jsonparser;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public final class Writer {
    @JsonProperty("annualChildren")
    private List<AnnualChildrenJsonWriter> annualChildrenJsonWriter = new ArrayList<>();

    /**
     * Writes the entire json file with a nice format
     * @param annualChildrenJsonWriter  to be written
     */
    public void addAnnualChildrenJsonList(final AnnualChildrenJsonWriter annualChildrenJsonWriter) {
        this.annualChildrenJsonWriter.add(annualChildrenJsonWriter);
    }
}
