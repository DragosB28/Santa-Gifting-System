package scorefactory;

import enums.AgeCategory;

public final class AverageScoreFactory {
    private static AverageScoreFactory factory;

    private AverageScoreFactory() {

    }

    /**
     * Lazy instantiation
     * @return  factory as singleton
     */
    public static AverageScoreFactory getAverageScoreFactory() {
        if (factory == null) {
            factory = new AverageScoreFactory();
        }
        return factory;
    }

    /**
     * Decides which strategy to pick for the average score
     * @param ageCategory   of the child
     * @return  the corespondent strategy for the age category
     */
    public AverageScoreStrategy makeStrategy(final AgeCategory ageCategory) {
        return switch (ageCategory) {
            case BABY -> new BabyScoreStrategy();
            case KID -> new KidScoreStrategy();
            case TEEN -> new TeenScoreStrategy();
            case YOUNG_ADULT -> null;
        };
    }
}
