package entities;

import enums.AgeCategory;
import enums.Category;
import scorefactory.AverageScoreFactory;
import scorefactory.AverageScoreStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Santa {
    private final Input in;
    private List<Child> children;
    private final List<Gift> santaGiftsList;
    private double santaBudget;
    private double budgetUnit;

    public Santa(final Input input) {
        this.in = input;
        if (input.getInitialData().getChildren() == null) {
            this.children = new ArrayList<>();
        } else {
            this.children = input.getInitialData().getChildren();
        }
        this.santaGiftsList = input.getInitialData().getSantaGiftsList();
        this.santaBudget = input.getSantaBudget();
        this.budgetUnit = 0;
    }

    /**
     * Solves the initial round
     *
     * @param input the data to work with
     */
    public void solveRoundZero(final Input input) {
        this.checkAgeRestriction();
        this.calculateAverageScoreForAllChildren();
        this.calculateBudgetUnit();
        this.calculateAllocatedBudget();
        this.decideGiftsPerChild();
    }

    /**
     * Removes all the young adults from the list
     */
    public void checkAgeRestriction() {
        List<Child> auxList = this.children;
        children = children.stream().
                filter(child -> child.getAgeCategory() != AgeCategory.YOUNG_ADULT)
                .collect(Collectors.toList());
        auxList.clear();
        auxList.addAll(children);
        children = auxList;
    }

    /**
     * Calculates the average score of each child
     */
    public void calculateAverageScoreForAllChildren() {
//        for (Child child : children) {
//            double averageScore;
//            AgeCategory ageCategory = child.getAgeCategory();
//            if (ageCategory == AgeCategory.BABY) {
//                child.setAverageScore(10);
//            } else if (ageCategory == AgeCategory.KID) {
//                double sum = 0;
//                for (double amount : child.getNiceScoreHistory()) {
//                    sum += amount;
//                }
//                averageScore = sum / (double) child.getNiceScoreHistory().size();
//                child.setAverageScore(averageScore);
//            } else if (ageCategory == AgeCategory.TEEN) {
//                double sum = 0;
//                double sumOfIndex = 0;
//                for (int i = 1; i <= child.getNiceScoreHistory().size(); i++) {
//                    sum += child.getNiceScoreHistory().get(i - 1) * i;
//                    sumOfIndex += i;
//                }
//                double newAverageScore = sum / sumOfIndex;
//                child.setAverageScore(newAverageScore);
//            }
//        }
        AverageScoreFactory factory = AverageScoreFactory.getAverageScoreFactory();
        AverageScoreStrategy strategy;
        for (Child child : children) {
            strategy = factory.makeStrategy(child.getAgeCategory());
            double averageScore = strategy.calculateAverageScore(child.getNiceScoreHistory());
            child.setAverageScore(averageScore);
        }
    }

    /**
     * Calculates the budget unit
     */
    public void calculateBudgetUnit() {
        double sumOfAverageScores = 0;

        for (Child child : children) {
            sumOfAverageScores += child.getAverageScore();
        }
        this.budgetUnit = this.santaBudget / sumOfAverageScores;
    }

    /**
     * Calculates the allocated budget of each child
     */
    public void calculateAllocatedBudget() {
        double childBudget = 0;

        for (Child child : children) {
            childBudget = child.getAverageScore() * this.budgetUnit;
            child.setFirstAssignedBudget(childBudget);
        }
    }

    /**
     * Searches through each child's gift preference list and santa's available
     * gifts, decides which is the cheapest gift to offer and then add it to the
     * child's list and updates the remaining budget.
     */
    public void decideGiftsPerChild() {
        for (Child child : children) {
            double remainingBudget = child.getFirstAssignedBudget();
            List<Category> childList = child.getGiftsPreferences();

            for (Category giftType : childList) {
                double minPrice = Double.MAX_VALUE;
                double auxPrice;
                Gift chosenGift = null;
                for (Gift santaGift : this.santaGiftsList) {
                    if (santaGift.getCategory() == giftType) {
                        auxPrice = santaGift.getPrice();
                        if (auxPrice < minPrice) {
                            minPrice = auxPrice;
                            chosenGift = santaGift;
                        }
                    }
                }
                if (chosenGift != null && (Double.
                        compare(remainingBudget, chosenGift.getPrice()) > 0)
                        && !child.getReceivedGifts().contains(chosenGift)) {
                    child.addReceivedGift(chosenGift);
                    remainingBudget = remainingBudget - chosenGift.getPrice();
                }
            }
        }
    }

    /**
     * Does all the work needed to solve one annnualChange round
     *
     * @param annualChange  which annualChange to solve
     */
    public void solveOneYearRound(final AnnualChanges annualChange) {
        this.incrementAgeEachYear();
        this.addNewChildrenToSantaList(annualChange.getNewChildren());
        this.updateChildAgeCategory();
        this.checkAgeRestriction();
        this.addNewNiceScoreToList(annualChange.getChildrenUpdates());
        this.addNewGiftsPreferences(annualChange.getChildrenUpdates());
        this.addNewSantaGifts(annualChange.getNewGifts());
        this.updateSantaBudget(annualChange.getNewSantaBudget());
        this.calculateAverageScoreForAllChildren();
        this.calculateBudgetUnit();
        this.calculateAllocatedBudget();
        this.resetAllPreviousGifts();
        this.decideGiftsPerChild();
    }

    /**
     * After passing one year, increments the age of all the children
     */
    public void incrementAgeEachYear() {
        for (Child child : children) {
            int newAge = child.getAge() + 1;
            child.setAge(newAge);
        }
    }

    /**
     * Adds the new children list to the Santa's children list
     *
     * @param newChildrenList   to be added
     */
    public void addNewChildrenToSantaList(final List<Child> newChildrenList) {
        for (Child child : newChildrenList) {
            this.children.add(child);
        }
    }

    /**
     * Adds the new nice score list to each child's list
     *
     * @param childrenUpdates   to be added
     */
    public void addNewNiceScoreToList(final List<ChildUpdate> childrenUpdates) {
        for (ChildUpdate childUpdate : childrenUpdates) {
            for (Child child : children) {
                if (childUpdate.getId() == child.getId()) {
                    child.addNewNiceScore(childUpdate.getNiceScore());
                    break;
                }
            }
        }
    }

    /**
     * Adds the new gifts preferences to each child's preference list
     *
     * @param childrenUpdates   to be added
     */
    public void addNewGiftsPreferences(final List<ChildUpdate> childrenUpdates) {
        for (ChildUpdate childUpdate : childrenUpdates) {
            for (Child child : children) {
                if (child.getId() == childUpdate.getId()) {
                    child.addNewlyGiftsPreferencesAtBeginning(childUpdate.getGiftsPreferences());
                    break;
                }
            }
        }
    }

    /**
     * Adds the new gifts available to the Santa's gift list
     *
     * @param newGifts  to be added
     */
    public void addNewSantaGifts(final List<Gift> newGifts) {
        for (Gift gift : newGifts) {
            this.santaGiftsList.add(gift);
        }
    }

    /**
     * Updates Santa's new budget
     *
     * @param newSantaBudget    to be updated
     */
    public void updateSantaBudget(final Double newSantaBudget) {
        this.santaBudget = newSantaBudget;
    }

    /**
     * Goes through the list of children and updates the age category for each one
     */
    public void updateChildAgeCategory() {
        for (Child child : children) {
            child.calculateAgeCategory();
        }
    }

    /**
     * Goes through the list of children and clears the previous list of received gifts
     */
    public void resetAllPreviousGifts() {
        for (Child child : children) {
            child.resetReceivedGifts();
        }
    }
}
