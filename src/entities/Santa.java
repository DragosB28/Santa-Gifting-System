package entities;

import enums.AgeCategory;
import enums.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Santa {
    private final Input input;
    private List<Child> children;
    private final List<Gift> santaGiftsList;
    private double santaBudget;
    private double budgetUnit;

    public Santa(Input input) {
        this.input = input;
        if (input.getInitialData().getChildren() == null) {
            this.children = new ArrayList<>();
        } else {
            this.children = input.getInitialData().getChildren();
        }
        this.santaGiftsList = input.getInitialData().getSantaGiftsList();
        this.santaBudget = input.getSantaBudget();
        this.budgetUnit = 0;
    }

    public void solveRoundZero(Input input) {
        this.checkAgeRestriction();
        this.calculateAverageScore();
        this.calculateBudgetUnit();
        this.calculateAllocatedBudget();
        this.decideGiftsPerChild();
    }

    public void checkAgeRestriction() {
        List<Child> auxList = this.children;
        children = children.stream().filter(child -> child.getAgeCategory() != AgeCategory.YOUNG_ADULT).collect(Collectors.toList());
        auxList.clear();
        auxList.addAll(children);
        children = auxList;
    }

    public void calculateAverageScore() {
        for (Child child : children) {
            double averageScore;
            AgeCategory ageCategory = child.getAgeCategory();
            if (ageCategory == AgeCategory.BABY) {
                child.setAverageScore(10);
            } else if (ageCategory == AgeCategory.KID) {
                double sum = 0;
                for (double amount : child.getNiceScoreHistory()) {
                    sum += amount;
                }
                averageScore = sum / (double) child.getNiceScoreHistory().size();
                child.setAverageScore(averageScore);
            } else if (ageCategory == AgeCategory.TEEN) {
                double sum = 0;
                double sumOfIndex = 0;
                for (int i = 1; i <= child.getNiceScoreHistory().size(); i++) {
                    sum += child.getNiceScoreHistory().get(i - 1) * i;
                    sumOfIndex += i;
                }
                double newAverageScore = sum / sumOfIndex;
                child.setAverageScore(newAverageScore);
            }
        }

    }

    public void calculateBudgetUnit() {
        double sumOfAverageScores = 0;

        for (Child child : children) {
            sumOfAverageScores += child.getAverageScore();
        }
        this.budgetUnit = this.santaBudget / sumOfAverageScores;
    }

    public void calculateAllocatedBudget() {
        double childBudget = 0;

        for (Child child : children) {
            childBudget = child.getAverageScore() * this.budgetUnit;

            child.setFirstAssignedBudget(childBudget);
        }
    }

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
                if (chosenGift != null && (Double.compare(remainingBudget, chosenGift.getPrice()) > 0) && !child.getReceivedGifts().contains(chosenGift)) {
                    child.addReceivedGift(chosenGift);
                    remainingBudget = remainingBudget - chosenGift.getPrice();
                }
            }
        }
    }

    public void solveOneYearRound(AnnualChanges annualChange) {
        this.incrementAgeEachYear();
        this.addNewChildrenToSantaList(annualChange.getNewChildren());
        this.updateChildAgeCategory();
        this.checkAgeRestriction();
        this.addNewNiceScoreToList(annualChange.getChildrenUpdates());
        this.addNewGiftsPreferences(annualChange.getChildrenUpdates());
        this.addNewSantaGifts(annualChange.getNewGifts());
        this.updateSantaBudget(annualChange.getNewSantaBudget());
        this.calculateAverageScore();
        this.calculateBudgetUnit();
        this.calculateAllocatedBudget();
        this.resetAllPreviousGifts();
        this.decideGiftsPerChild();
    }

    public void incrementAgeEachYear() {
        for (Child child : children) {
            int newAge = child.getAge() + 1;
            child.setAge(newAge);
        }
    }

    public void addNewChildrenToSantaList(List<Child> newChildrenList) {
        for (Child child : newChildrenList) {
            this.children.add(child);
        }
    }

    public void addNewNiceScoreToList(List<ChildUpdate> childrenUpdates) {
        for (ChildUpdate childUpdate : childrenUpdates) {
            for (Child child : children) {
                if (childUpdate.getId() == child.getId()) {
                    child.addNewNiceScore(childUpdate.getNiceScore());
                    break;
                }
            }
        }
    }

    public void addNewGiftsPreferences(List<ChildUpdate> childrenUpdates) {
        for (ChildUpdate childUpdate : childrenUpdates) {
            for (Child child : children) {
                if (child.getId() == childUpdate.getId()) {
                    child.addNewlyGiftsPreferencesAtBeginning(childUpdate.getGiftsPreferences());
                    break;
                }
            }
        }
    }

    public void addNewSantaGifts(List<Gift> newGifts) {
        for (Gift gift : newGifts) {
            this.santaGiftsList.add(gift);
        }
    }

    public void updateSantaBudget(Double newSantaBudget) {
        this.santaBudget = newSantaBudget;
    }

    public void updateChildAgeCategory() {
        for (Child child : children) {
            child.calculateAgeCategory();
        }
    }

    public void resetAllPreviousGifts() {
        for (Child child : children) {
            child.resetReceivedGifts();
        }
    }
}
