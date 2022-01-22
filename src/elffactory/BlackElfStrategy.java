package elffactory;

import common.Constants;
import entities.Child;
import entities.Gift;

import java.util.List;

public final class BlackElfStrategy implements ElfActionStrategy {
    @Override
    public void applyElf(final Child child, final List<Gift> santaGifts) {
        double newBudget = (double) child.getFirstAssignedBudget()
                - (double) (child.getFirstAssignedBudget() * Constants.ELF_PERCENT)
                / Constants.PERCENT_100;
        child.setFirstAssignedBudget(newBudget);
    }
}
