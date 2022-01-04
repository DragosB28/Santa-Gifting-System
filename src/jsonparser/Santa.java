package jsonparser;

import enums.AgeCategory;
import enums.Category;

import java.util.List;

public class Santa {
    private Input input;
    private List<Child> children;
    private List<Gift> santaGiftsList;
    private double santaBudget;
    private double budgetUnit;

    public Santa(Input input) {
        this.input = input;
        this.children = input.getInitialData().getChildren();
        this.santaGiftsList = input.getInitialData().getSantaGiftsList();
        this.santaBudget = input.getSantaBudget();
        this.budgetUnit = 0;
    }

    public void solveRoundZero(Input input) {
        this.checkAgeRestriction();
        this.roundZeroAverageScore();
        this.calculateBudgetUnit();
        this.calculateAllocatedBudget();
        this.decideGiftsPerChild();
    }

    public void checkAgeRestriction() {
        for (int i = children.size() - 1; i >= 0; i--) {
            if (children.get(i).getAgeCategory() == AgeCategory.YOUNG_ADULT) {
                children.remove(i);
            }
        }
    }

    public void roundZeroAverageScore() {
        for (Child child : children) {
            AgeCategory ageCategory = child.getAgeCategory();
            if (ageCategory == AgeCategory.BABY) {
                child.setAverageScore(10);
            } else if (ageCategory == AgeCategory.KID) {
                child.setAverageScore(child.getNiceScore());
            } else if (ageCategory == AgeCategory.TEEN) {
                child.setAverageScore(child.getNiceScore());
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
            child.setUpdatedAllocatedBudget(childBudget);
        }
    }

    public void decideGiftsPerChild() {
        for (Child child : children) {
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
                if (chosenGift != null && child.getUpdatedAllocatedBudget() > chosenGift.getPrice()) {
                    child.addReceivedGift(chosenGift);
                    //this.santaGiftsList.remove(chosenGift);
                    double newBudget = child.getUpdatedAllocatedBudget() - chosenGift.getPrice();
                    child.setUpdatedAllocatedBudget(newBudget);
                }
            }
        }
    }
}
