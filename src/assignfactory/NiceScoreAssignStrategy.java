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
        List<Child> children = santa.getChildren();
        List<Child> auxList = children;

        children = children.stream()
                .sorted(Comparator.comparingDouble(Child::getNiceScore).thenComparing(new Comparator<Child>() {
                            @Override
                            public int compare(Child o1, Child o2) {
                                    return o2.getId() - o1.getId();
                            }
                        })
                        .reversed())
                .collect(Collectors.toList());

        auxList.clear();
        auxList.addAll(children);
        children = auxList;

        santa.setChildren(children);
    }
}
