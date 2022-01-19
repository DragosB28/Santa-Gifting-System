package entities;

import enums.Category;
import enums.ElvesType;

import java.util.List;

public final class ChildUpdate {
    private int id;
    private double niceScore;
    private List<Category> giftsPreferences;
    private ElvesType elvesType;

    public ChildUpdate(final int id, final double niceScore,
                       final List<Category> giftsPreferences, ElvesType elvesType) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
        this.elvesType = elvesType;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public double getNiceScore() {
        return niceScore;
    }

    public void setNiceScore(final double niceScore) {
        this.niceScore = niceScore;
    }

    public List<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public void setGiftsPreferences(final List<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    public ElvesType getElvesType() {
        return elvesType;
    }

    public void setElvesType(ElvesType elvesType) {
        this.elvesType = elvesType;
    }

    @Override
    public String toString() {
        return "ChildUpdate{" +
                "id=" + id +
                ", niceScore=" + niceScore +
                ", giftsPreferences=" + giftsPreferences +
                ", elvesType=" + elvesType +
                '}';
    }
}
