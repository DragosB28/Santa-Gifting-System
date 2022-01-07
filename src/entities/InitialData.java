package entities;

import java.util.ArrayList;
import java.util.List;

public final class InitialData {
    private List<Child> children;
    private List<Gift> santaGiftsList;

    public InitialData() {
        this.children = new ArrayList<>();
        this.santaGiftsList = new ArrayList<>();
    }

    public InitialData(final List<Child> children,
                       final List<Gift> santaGiftsList) {
        this.children = children;
        this.santaGiftsList = santaGiftsList;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(final List<Child> children) {
        this.children = children;
    }


    /**
     * Adds one child to the list of children
     *
     * @param child to be added
     */
    public void addChild(final Child child) {
        this.children.add(child);
    }

    public List<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(final List<Gift> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }

    /**
     * Adds one gift to the list Santa's gifts
     *
     * @param gift to be added
     */
    public void addSantaGift(final Gift gift) {
        this.santaGiftsList.add(gift);
    }

    @Override
    public String toString() {
        return "InitialData{"
                +
                "children=" + children
                +
                ", santaGiftsList=" + santaGiftsList
                +
                '}';
    }
}
