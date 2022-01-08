package scorefactory;

import java.util.List;

public interface AverageScoreStrategy {
    /**
     * Calculates the average score of a child
     * @param scores    the list of scores a child received
     * @return  the average score of the child
     */
    double calculateAverageScore(List<Double> scores);
}
