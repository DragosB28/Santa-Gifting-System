package elffactory;

import entities.Child;
import entities.Gift;

import java.util.List;

public interface ElfActionStrategy {
    void applyElf(Child child, List<Gift> santaGifts);
}
