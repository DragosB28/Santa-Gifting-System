package elffactory;

import entities.Child;
import entities.Gift;

import java.util.List;

public class WhiteElfStrategy implements ElfActionStrategy {
    @Override
    public void applyElf(Child child, List<Gift> santaGifts) {
        return;
    }
}
