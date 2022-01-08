package scorefactory;

import java.util.List;

public final class KidScoreStrategy implements AverageScoreStrategy {
    @Override
    public double calculateAverageScore(final List<Double> scores) {
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        return sum / (double) scores.size();
    }
}
