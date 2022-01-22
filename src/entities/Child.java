package entities;

import common.Constants;
import enums.AgeCategory;
import enums.Category;
import enums.Cities;
import enums.ElvesType;

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
    private double niceScoreBonus;
    private ElvesType elvesType;
    private AgeCategory ageCategory;
    private List<Double> niceScoreHistory;
    private double averageScore;
    private double firstAssignedBudget;
    private List<Gift> receivedGifts;


    public static final class Builder {
        private int id;
        private String lastName;
        private String firstName;
        private int age;
        private Cities city;
        private double niceScore;
        private List<Category> giftsPreferences;
        private double niceScoreBonus = 0;
        private ElvesType elvesType;
        private AgeCategory ageCategory;
        private List<Double> niceScoreHistory;
        private double averageScore;
        private double firstAssignedBudget;
        private List<Gift> receivedGifts;

        public Builder(final int id, final String lastName, final String firstName,
                     final int age, final Cities city, final double niceScore,
                     final List<Category> giftsPreferences,
                     final ElvesType elvesType) {
            this.id = id;
            this.lastName = lastName;
            this.firstName = firstName;
            this.age = age;
            this.city = city;
            this.niceScore = niceScore;
            this.giftsPreferences = giftsPreferences;
            calculateAgeCategory();
            this.elvesType = elvesType;
            this.niceScoreHistory = new ArrayList<>();
            this.niceScoreHistory.add(niceScore);
            this.averageScore = niceScore;
            this.firstAssignedBudget = 0;
            this.receivedGifts = new ArrayList<>();
        }

        /**
         * Adds the optional field of niceScoreBonus to the child instance
         * @param newNiceScoreBonus to be added
         * @return the instance with the added niceScoreBonus
         */
        public Builder addNiceScoreBonus(final double newNiceScoreBonus) {
            this.niceScoreBonus = newNiceScoreBonus;
            return this;
        }

        /**
         * Method to update the age category of a child
         */
        public void calculateAgeCategory() {
            if (this.age < Constants.BABY_AGE_LIMIT) {
                this.ageCategory = AgeCategory.BABY;
            } else if (this.age < Constants.KID_AGE_LIMIT) {
                this.ageCategory = AgeCategory.KID;
            } else if (this.age <= Constants.TEEN_AGE_LIMIT) {
                this.ageCategory = AgeCategory.TEEN;
            } else if (this.age > Constants.TEEN_AGE_LIMIT
            ) {
                this.ageCategory = AgeCategory.YOUNG_ADULT;
            }
        }

        /**
         * Build method from the builder design pattern
         * @return the Child instance
         */
        public Child build() {
            return new Child(this);
        }
    }

    private Child(final Builder builder) {
        this.id = builder.id;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.age = builder.age;
        this.city = builder.city;
        this.niceScore = builder.niceScore;
        this.giftsPreferences = builder.giftsPreferences;
        calculateAgeCategory();
        this.elvesType = builder.elvesType;
        this.niceScoreBonus = builder.niceScoreBonus;
        this.niceScoreHistory = builder.niceScoreHistory;
        this.averageScore = builder.averageScore;
        this.firstAssignedBudget = builder.firstAssignedBudget;
        this.receivedGifts = builder.receivedGifts;
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

    public double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public void setNiceScoreBonus(final double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    public ElvesType getElvesType() {
        return elvesType;
    }

    public void setElvesType(final ElvesType elvesType) {
        this.elvesType = elvesType;
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
        if (this.age < Constants.BABY_AGE_LIMIT) {
            this.ageCategory = AgeCategory.BABY;
        } else if (this.age < Constants.KID_AGE_LIMIT) {
            this.ageCategory = AgeCategory.KID;
        } else if (this.age <= Constants.TEEN_AGE_LIMIT) {
            this.ageCategory = AgeCategory.TEEN;
        } else if (this.age > Constants.TEEN_AGE_LIMIT
        ) {
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
                ", lastName='" + lastName + '\''
                +
                ", firstName='" + firstName + '\''
                +
                ", age=" + age
                +
                ", city=" + city
                +
                ", niceScore=" + niceScore
                +
                ", giftsPreferences=" + giftsPreferences
                +
                ", niceScoreBonus=" + niceScoreBonus
                +
                ", elvesType=" + elvesType
                +
                ", ageCategory=" + ageCategory
                +
                ", niceScoreHistory=" + niceScoreHistory
                +
                ", averageScore=" + averageScore
                +
                ", firstAssignedBudget=" + firstAssignedBudget
                +
                ", receivedGifts=" + receivedGifts
                +
                '}';
    }
}
