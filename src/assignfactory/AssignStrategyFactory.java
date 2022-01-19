package assignfactory;

import elffactory.ElfActionFactory;
import enums.CityStrategyEnum;

public class AssignStrategyFactory {
    private static AssignStrategyFactory factory;

    private AssignStrategyFactory() {

    }

    public static AssignStrategyFactory getAssignStrategyFactory() {
        if (factory == null) {
            factory = new AssignStrategyFactory();
        }
        return factory;
    }

    public AssignStrategy makeAssignStrategy(final CityStrategyEnum cityStrategyEnum) {
        return switch (cityStrategyEnum) {
            case ID -> new IdAssignStrategy();
            case NICE_SCORE -> new NiceScoreAssignStrategy();
            case NICE_SCORE_CITY -> new NiceScoreCityAssignStrategy();
        };
    }
}
