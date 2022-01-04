package main;

import com.fasterxml.jackson.databind.json.JsonMapper;
import jsonparser.*;

import java.io.IOException;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        //Checker.calculateScore();

        InputLoader inputLoader = new InputLoader("C:\\Users\\barbu\\OneDrive\\Desktop\\SantaHW\\tests\\test2.json");
        Input input = inputLoader.readData();
        //System.out.println(input);

        Santa santa = new Santa(input);
        santa.solveRoundZero(input);
        Child testChild = input.getInitialData().getChildren().get(0);
        System.out.println(testChild.getReceivedGifts());
        System.out.println(testChild.getAverageScore());
        System.out.println(testChild.getUpdatedAllocatedBudget());

        System.out.println();
        JsonMapper mapper = new JsonMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(new AnnualChildrenJsonWriter(input.getInitialData().getChildren()));
        System.out.println(jsonString);
    }
}
