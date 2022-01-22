package elffactory;

import entities.Child;
import entities.Gift;

import java.util.List;

public final class WhiteElfStrategy implements ElfActionStrategy {
    @Override
    public void applyElf(final Child child, final List<Gift> santaGifts) {
        return;
    }
}
