package jsonparser;

import com.fasterxml.jackson.annotation.JsonProperty;
import enums.Category;
import enums.Cities;

import java.util.List;

public class ChildrenJsonWriter {
    @JsonProperty("id")
    private int id;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("city")
    private Cities city;

    @JsonProperty("age")
    private int age;

    @JsonProperty("giftsPreferences")
    private List<Category> giftsPreferences;

    @JsonProperty("averageScore")
    private double averageScore;

    @JsonProperty("niceScoreHistory")
    private List<Double> niceScoreHistory;

    @JsonProperty("assignedBudget")
    private double assignedBudget;

    @JsonProperty("receivedGifts")
    private List<Gift> receivedGifts;

    public ChildrenJsonWriter(Child child) {
        this.id = child.getId();
        this.lastName = child.getLastName();
        this.firstName = child.getFirstName();
        this.city = child.getCity();
        this.age = child.getAge();
        this.giftsPreferences = child.getGiftsPreferences();
        this.averageScore = child.getAverageScore();
        this.niceScoreHistory = child.getNiceScoreHistory();
        this.assignedBudget = child.getFirstAssignedBudget();
        this.receivedGifts = child.getReceivedGifts();
    }
}

