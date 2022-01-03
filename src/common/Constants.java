package common;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Constants {
    public static final String OUTPUT_PATH = "output/out_";
    public static final String REF_PATH = "ref/ref_test";
    public static final String FILE_EXTENSION = ".json";
    public static final Integer SMALL_TEST_POINTS = 2;
    public static final Integer BIG_TEST_POINTS = 3;
    public static final Integer TESTS_NUMBER = 25;
    public static final Integer MAXIMUM_ERROR_CHECKSTYLE = 30;
    public static final Integer CHECKSTYLE_POINTS = 10;
    public static final Integer TESTS_NUMBER_SMALL = 15;

    //Gift Categories
    public static final String BOARD_GAMES = "Board Games";
    public static final String BOOKS = "Books";
    public static final String CLOTHES = "Clothes";
    public static final String SWEETS = "Sweets";
    public static final String TECHNOLOGY = "Technology";
    public static final String TOYS = "Toys";

    //Cities
    public static final String BUCURESTI = "Bucuresti";
    public static final String CONSTANTA = "Constanta";
    public static final String BUZAU = "Buzau";
    public static final String TIMISOARA = "Timisoara";
    public static final String CLUJ = "Cluj-Napoca";
    public static final String IASI = "Iasi";
    public static final String CRAIOVA = "Craiova";
    public static final String BRASOV = "Brasov";
    public static final String BRAILA = "Braila";
    public static final String ORADEA = "Oradea";

    private String value;
    private Constants() {
        //constructor for checkstyle
    }

}
