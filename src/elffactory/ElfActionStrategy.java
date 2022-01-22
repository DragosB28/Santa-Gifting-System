package elffactory;

import entities.Child;
import entities.Gift;

import java.util.List;

public interface ElfActionStrategy {
    /**
     * Applies the chosen elf actions
     * @param child to access the elf and the budget
     * @param santaGifts needed for the yellow elf
     */
    void applyElf(Child child, List<Gift> santaGifts);
}
