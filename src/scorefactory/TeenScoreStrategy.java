package scorefactory;

import java.util.List;

public final class TeenScoreStrategy implements AverageScoreStrategy {
    @Override
    public double calculateAverageScore(final List<Double> scores) {
        double sum = 0;
        double sumOfIndex = 0;
        for (int i = 1; i <= scores.size(); i++) {
            sum += scores.get(i - 1) * i;
            sumOfIndex += i;
        }
        return sum / sumOfIndex;
    }
}
