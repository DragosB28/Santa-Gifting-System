package scorefactory;

import common.Constants;

import java.util.List;

public final class BabyScoreStrategy implements AverageScoreStrategy {
    @Override
    public double calculateAverageScore(final List<Double> scores) {
        return Constants.BABY_AVERAGE;
    }
}
