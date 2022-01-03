package jsonparser;

import enums.Cities;

import java.util.ArrayList;
import java.util.List;

public class InitialData {
    private List<Child> children;
    private List<Gift> santaGiftsList;

    public InitialData() {
        this.children = null;
        this.santaGiftsList = null;
    }

    public InitialData(List<Child> children, List<Gift> santaGiftsList) {
        this.children = children;
        this.santaGiftsList = santaGiftsList;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public void addChild(Child child) {
        if (this.children == null) {
            this.children = new ArrayList<>();
            this.children.add(child);
        } else {
            children.add(child);
        }
    }

    public List<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    public void setSantaGiftsList(List<Gift> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }

    public void addSantaGift(Gift gift) {
        if (this.santaGiftsList == null) {
            this.santaGiftsList = new ArrayList<>();
            this.santaGiftsList.add(gift);
        } else {
            this.santaGiftsList.add(gift);
        }
    }

    @Override
    public String toString() {
        return "InitialData{" +
                "children=" + children +
                ", santaGiftsList=" + santaGiftsList +
                '}';
    }
}
