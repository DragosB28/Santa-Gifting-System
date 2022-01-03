package main;

import checker.Checker;
import com.fasterxml.jackson.databind.JsonNode;
import jsonparser.Input;
import jsonparser.InputLoader;

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

        InputLoader inputLoader = new InputLoader("C:\\Users\\barbu\\OneDrive\\Desktop\\SantaHW\\tests\\test1.json");
        Input input = inputLoader.readData();
        System.out.println(input);
    }
}
