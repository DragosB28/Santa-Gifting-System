package assignfactory;

import entities.Child;
import entities.Santa;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class IdAssignStrategy implements AssignStrategy {
    @Override
    public void order(Santa santa) {
        List<Child> children = santa.getChildren();
        List<Child> auxList = children;

        children = children.stream()
                .sorted(Comparator.comparingInt(Child::getId))
                .collect(Collectors.toList());
        auxList.clear();
        auxList.addAll(children);
        children = auxList;

        santa.setChildren(children);
    }
}
