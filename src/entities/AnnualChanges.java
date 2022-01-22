package entities;

import enums.CityStrategyEnum;

import java.util.ArrayList;
import java.util.List;

public final class AnnualChanges {
    private double newSantaBudget;
    private List<Gift> newGifts;
    private List<Child> newChildren;
    private List<ChildUpdate> childrenUpdates;
    private CityStrategyEnum cityStrategyEnum;

    public AnnualChanges() {
        this.newSantaBudget = 0;
        this.newGifts = new ArrayList<>();
        this.newChildren = new ArrayList<>();
        this.childrenUpdates = new ArrayList<>();
        this.cityStrategyEnum = null;
    }

    public AnnualChanges(final double newSantaBudget, final List<Gift> newGifts,
                         final List<Child> newChildren,
                         final List<ChildUpdate> childrenUpdates,
                         final CityStrategyEnum cityStrategyEnum) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
        this.cityStrategyEnum = cityStrategyEnum;
    }

    public double getNewSantaBudget() {
        return newSantaBudget;
    }

    public void setNewSantaBudget(final int newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    public List<Gift> getNewGifts() {
        return newGifts;
    }

    public void setNewGifts(final List<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    public List<Child> getNewChildren() {
        return newChildren;
    }

    public void setNewChildren(final List<Child> newChildren) {
        this.newChildren = newChildren;
    }

    public List<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    public void setChildrenUpdates(final List<ChildUpdate> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    public CityStrategyEnum getCityStrategyEnum() {
        return cityStrategyEnum;
    }

    public void setCityStrategyEnum(final CityStrategyEnum cityStrategyEnum) {
        this.cityStrategyEnum = cityStrategyEnum;
    }

    @Override
    public String toString() {
        return "AnnualChanges{"
                +
                "newSantaBudget=" + newSantaBudget
                +
                ", newGifts=" + newGifts
                +
                ", newChildren=" + newChildren
                +
                ", childrenUpdates=" + childrenUpdates
                +
                ", cityStrategyEnum=" + cityStrategyEnum
                +
                '}';
    }
}
