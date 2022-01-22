package assignfactory;

import enums.CityStrategyEnum;

public final class AssignStrategyFactory {
    private static AssignStrategyFactory factory;

    private AssignStrategyFactory() {

    }
    /**
     * Lazy instantiation
     * @return  factory as singleton
     */
    public static AssignStrategyFactory getAssignStrategyFactory() {
        if (factory == null) {
            factory = new AssignStrategyFactory();
        }
        return factory;
    }
    /**
     * Decides which strategy to pick for the assigning method
     * @param cityStrategyEnum required to pick the corresponding method
     * @return  the corespondent strategy for the given CityStrategyEnum
     */
    public AssignStrategy makeAssignStrategy(final CityStrategyEnum cityStrategyEnum) {
        return switch (cityStrategyEnum) {
            case ID -> new IdAssignStrategy();
            case NICE_SCORE -> new NiceScoreAssignStrategy();
            case NICE_SCORE_CITY -> new NiceScoreCityAssignStrategy();
        };
    }
}
