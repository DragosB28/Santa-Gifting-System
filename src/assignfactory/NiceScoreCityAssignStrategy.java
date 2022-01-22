package assignfactory;

import entities.Child;
import entities.Santa;
import enums.Cities;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public final class NiceScoreCityAssignStrategy implements AssignStrategy {
    @Override
    public void order(final Santa santa) {
        List<Child> children = santa.getChildren();
        List<Child> auxList = children;

        HashMap<Cities, Double> map = new HashMap<>();
        HashMap<Cities, Integer> amountMap = new HashMap<>();
        children = children.stream()
                .sorted(Comparator.comparingInt(Child::getId)).collect(Collectors.toList());

        for (Child child : children) {
            map.put(child.getCity(),
                    (double) map.getOrDefault(child.getCity(), 0.0) + child.getNiceScore());
            amountMap.put(child.getCity(), amountMap.getOrDefault(child.getCity(), 0) + 1);
        }

        for (Cities city : map.keySet()) {
            map.put(city, (double) map.getOrDefault(city, 0.0) / amountMap.get(city));
        }


        children = children.stream().sorted(new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                return Double.compare(map.get(o1.getCity()), map.get(o2.getCity()));
            }
        }.thenComparing(new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                if (o1.getCity().toString() == o2.getCity().toString()) {
                    return o1.getId() - o2.getId();
                }
                return o2.getCity().toString().compareTo(o1.getCity().toString());
            }
        }).reversed()).collect(Collectors.toList());


        auxList.clear();
        auxList.addAll(children);
        children = auxList;

        santa.setChildren(children);
    }
}
