package assignfactory;

import entities.Santa;

public interface AssignStrategy {
    /**
     * Orders the list of children according to the required strategy
     * @param santa used to access the list of children
     */
    void order(Santa santa);
}
