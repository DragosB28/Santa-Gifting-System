package assignfactory;

import entities.Child;
import entities.Santa;

import java.util.List;

public interface AssignStrategy {
    void order(Santa santa);
}
