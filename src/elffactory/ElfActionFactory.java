package elffactory;

import enums.ElvesType;

public class ElfActionFactory {
    private static ElfActionFactory elfFactory;

    private ElfActionFactory() {

    }

    /**
     * Lazy instantiation
     * @return  factory as singleton
     */
    public static ElfActionFactory getElfActionFactory() {
        if (elfFactory == null) {
            elfFactory = new ElfActionFactory();
        }
        return elfFactory;
    }

    /**
     * Decides which strategy to pick for the elf type
     * @param elf  of the child
     * @return  the corespondent strategy for the elf type
     */
    public ElfActionStrategy makeElfStrategy(final ElvesType elf) {
        return switch (elf) {
            case YELLOW -> new YellowElfStrategy();
            case BLACK -> new BlackElfStrategy();
            case PINK -> new PinkElfStrategy();
            case WHITE -> new WhiteElfStrategy();
        };
    }
}
