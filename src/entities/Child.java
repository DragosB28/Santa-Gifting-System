package entities;

import enums.AgeCategory;
import enums.Category;
import enums.Cities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Child {
    private int id;
    private String lastName;
    private String firstName;
    private int age;
    private Cities city;
    private double niceScore;
    private List<Category> giftsPreferences;
    private AgeCategory ageCategory;
    private List<Double> niceScoreHistory;
    private double averageScore;
    private double firstAssignedBudget;
    private List<Gift> receivedGifts;

    public Child(final int id, final String lastName, final String firstName,
                 final int age, final Cities city, final double niceScore,
                 final List<Category> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
        calculateAgeCategory();
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.add(niceScore);
        this.averageScore = niceScore;
        this.firstAssignedBudget = 0;
        this.receivedGifts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(final Cities city) {
        this.city = city;
    }

    public double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final double niceScore) {
        this.niceScore = niceScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /**
     * Adds a new list of gifts preferences at the beginning of the list,
     * removing the duplicates at the same time
     *
     * @param newGiftsPreferences   received annually
     */
    public void addNewlyGiftsPreferencesAtBeginning(
            List<Category> newGiftsPreferences) {
        newGiftsPreferences = newGiftsPreferences.stream()
                .distinct().collect(Collectors.toList());
        for (Category newGiftPreference : newGiftsPreferences) {
            this.giftsPreferences.remove(newGiftPreference);
        }
        this.giftsPreferences.addAll(0, newGiftsPreferences);
    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    /**
     * Adds a new nice score to the list. The inputLoader returns -1 in case
     * of receiving null from the json
     *
     * @param newNiceScore  received annually
     */
    public void addNewNiceScore(final Double newNiceScore) {
        if (newNiceScore != -1) {
            this.niceScoreHistory.add(newNiceScore);
        }
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(final double averageScore) {
        this.averageScore = averageScore;
    }

    public double getFirstAssignedBudget() {
        return firstAssignedBudget;
    }

    public void setFirstAssignedBudget(final double firstAssignedBudget) {
        this.firstAssignedBudget = firstAssignedBudget;
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(final List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    /**
     * Adds one gift to the list of receivedGifts
     *
     * @param gift  to be added
     */
    public void addReceivedGift(final Gift gift) {
        this.receivedGifts.add(gift);
    }

    /**
     * Updates the age category of a child
     */
    public void calculateAgeCategory() {
        if (this.age < 5) {
            this.ageCategory = AgeCategory.BABY;
        } else if (this.age < 12) {
            this.ageCategory = AgeCategory.KID;
        } else if (this.age <= 18) {
            this.ageCategory = AgeCategory.TEEN;
        } else if (this.age > 18) {
            this.ageCategory = AgeCategory.YOUNG_ADULT;
        }
    }

    /**
     * Clears the list of received gifts
     */
    public void resetReceivedGifts() {
        this.receivedGifts.clear();
    }


    @Override
    public String toString() {
        return "Child{"
                +
                "id=" + id
                +
                ", lastName='" + lastName
                + '\''
                +
                ", firstName='" + firstName
                + '\''
                +
                ", age=" + age
                +
                ", city=" + city
                +
                ", niceScore=" + niceScore
                +
                ", giftsPreferences=" + giftsPreferences
                +
                '}';
    }
}
