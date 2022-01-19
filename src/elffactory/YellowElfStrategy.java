package elffactory;

import entities.Child;
import entities.Gift;

import java.util.List;

public class YellowElfStrategy implements ElfActionStrategy {
    @Override
    public void applyElf(Child child, List<Gift> santaGifts) {
        if (child.getReceivedGifts().isEmpty()) {
            double minPrice = Double.MAX_VALUE;
            double auxPrice;
            Gift chosenGift = null;
            for (Gift santaGift : santaGifts) {
                if (santaGift.getCategory() == child.getGiftsPreferences().get(0)) {
                    auxPrice = santaGift.getPrice();
                    if (auxPrice < minPrice) {
                        minPrice = auxPrice;
                        chosenGift = santaGift;
                    }
                }
            }
            if (chosenGift != null && chosenGift.getQuantity() > 0) {
                System.out.println(chosenGift);
                child.addReceivedGift(chosenGift);
                chosenGift.decrementQuantity();
            }
        }
    }
}
