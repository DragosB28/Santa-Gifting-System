package assignfactory;

import entities.Child;
import entities.Santa;
import enums.AgeCategory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NiceScoreAssignStrategy implements AssignStrategy {
    @Override
    public void order(Santa santa) {
        santa.updateChildAgeCategory();
        santa.checkAgeRestriction();

        santa.setChildren(santa.getChildren().stream()
                .sorted(Comparator.comparingDouble(Child::getNiceScore).thenComparing(Child::getId)
                        .reversed()        )
                .collect(Collectors.toList()));

        santa.updateChildAgeCategory();
        santa.checkAgeRestriction();

    }
}
