package io;

import com.fasterxml.jackson.annotation.JsonProperty;
import entities.Child;
import entities.Gift;
import enums.Category;
import enums.Cities;

import java.util.ArrayList;
import java.util.List;

public class ChildrenJsonWriter {
    @JsonProperty("id")
    private final int id;

    @JsonProperty("lastName")
    private final String lastName;

    @JsonProperty("firstName")
    private final String firstName;

    @JsonProperty("city")
    private final Cities city;

    @JsonProperty("age")
    private final int age;

    @JsonProperty("giftsPreferences")
    private final List<Category> giftsPreferences;

    @JsonProperty("averageScore")
    private final double averageScore;

    @JsonProperty("niceScoreHistory")
    private final List<Double> niceScoreHistory;

    @JsonProperty("assignedBudget")
    private final double assignedBudget;

    @JsonProperty("receivedGifts")
    private final List<Gift> receivedGifts;

    public ChildrenJsonWriter(final Child child) {
        this.id = child.getId();
        this.lastName = child.getLastName();
        this.firstName = child.getFirstName();
        this.city = child.getCity();
        this.age = child.getAge();
        this.giftsPreferences = new ArrayList<>(child.getGiftsPreferences());
        this.averageScore = child.getAverageScore();
        this.niceScoreHistory = new ArrayList<>(child.getNiceScoreHistory());
        this.assignedBudget = child.getFirstAssignedBudget();
        this.receivedGifts = new ArrayList<>(child.getReceivedGifts());
    }
}

