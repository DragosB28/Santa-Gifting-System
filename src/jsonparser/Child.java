package jsonparser;

import enums.AgeCategory;
import enums.Category;
import enums.Cities;

import java.util.ArrayList;
import java.util.List;

public class Child {
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
    private double updatedAllocatedBudget;
    private List<Gift> receivedGifts;

    public Child(int id, String lastName, String firstName, int age, Cities city, double niceScore, List<Category> giftsPreferences) {
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
        this.updatedAllocatedBudget = 0;
        this.receivedGifts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(double niceScore) {
        this.niceScore = niceScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public AgeCategory getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(AgeCategory ageCategory) {
        this.ageCategory = ageCategory;
    }

    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public void setNiceScoreHistory(List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public double getFirstAssignedBudget() {
        return firstAssignedBudget;
    }

    public void setFirstAssignedBudget(double firstAssignedBudget) {
        this.firstAssignedBudget = firstAssignedBudget;
    }

    public double getUpdatedAllocatedBudget() {
        return updatedAllocatedBudget;
    }

    public void setUpdatedAllocatedBudget(double updatedAllocatedBudget) {
        this.updatedAllocatedBudget = updatedAllocatedBudget;
    }

    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public void setReceivedGifts(List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    public void addReceivedGift(Gift gift) {
        this.receivedGifts.add(gift);
    }

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



    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", city=" + city +
                ", niceScore=" + niceScore +
                ", giftsPreferences=" + giftsPreferences +
                '}';
    }
}
