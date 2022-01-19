package elffactory;

import entities.Child;
import entities.Gift;

import java.util.List;

public class BlackElfStrategy implements ElfActionStrategy {
    @Override
    public void applyElf(Child child, List<Gift> santaGifts) {
        double newBudget = (double) child.getFirstAssignedBudget()
                - (double) (child.getFirstAssignedBudget() * 30) / 100;
        child.setFirstAssignedBudget(newBudget);
    }
}
