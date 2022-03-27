package task2;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Action {
    ADD("add"),
    ASK("ask"),
    REMOVE("remove"),
    IMPORT("import"),
    EXPORT("export"),
    HARDEST_CARD("hardest card"),
    RESET_STATS("reset stats"),
    LOG("log"),
    EXIT("exit"),
    UNKNOWN("unknown");

    public final String option;
    Action(String option) {
        this.option = option;
    }

    public static Action findByOption(String option) {
        return Arrays.stream(Action.values()).filter(s -> s.getOption().equals(option)).findAny().orElse(UNKNOWN);
    }

    public String getOption() {
        return option;
    }

    public static String actionsToString() {
        return Arrays.stream(Action.values()).map(Action::getOption).filter(Option -> !Option.equals("unknown")).collect(Collectors.joining(", "));
    }
}
