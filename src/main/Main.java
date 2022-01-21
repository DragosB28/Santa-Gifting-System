package main;

import checker.Checker;
import com.fasterxml.jackson.databind.json.JsonMapper;
import entities.AnnualChanges;
import entities.Input;
import entities.Santa;
    import io.AnnualChildrenJsonWriter;
    import io.InputLoader;
    import io.Writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }

    /**
     * This method is used to call the checker which calculates the score
     *
     * @param args the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {

        final File folder = new File("tests");

        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            InputLoader inputLoader = new InputLoader(fileEntry.getPath());
            Input input = inputLoader.readData();

            System.out.println("Testul: " + fileEntry.getPath());

            Santa santa = new Santa(input);
            santa.solveRoundZero(input);

            Writer writer = new Writer();
            AnnualChildrenJsonWriter annualChildrenJsonWriter =
                    new AnnualChildrenJsonWriter();
            annualChildrenJsonWriter.addListToJson(input.
                    getInitialData().getChildren());
            writer.addAnnualChildrenJsonList(annualChildrenJsonWriter);

            for (AnnualChanges annualChange : input.getAnnualChanges()
                    .subList(0, input.getNumberOfYears())) {
                santa.solveOneYearRound(annualChange);
                annualChildrenJsonWriter = new AnnualChildrenJsonWriter();
                annualChildrenJsonWriter.addListToJson(input.getInitialData().
                        getChildren());
                writer.addAnnualChildrenJsonList(annualChildrenJsonWriter);
            }

            JsonMapper mapper = new JsonMapper();
            String jsonString = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(writer);
            try {
                File file = new File(fileEntry.getPath()
                        .replace("tests", "output")
                        .replace("test", "out_"));
                file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(jsonString);
                fileWriter.flush();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Checker.calculateScore();
    }
}
