package task4;

import java.util.Arrays;

public enum ArgumentKey {
    MODE("-mode"), KEY("-key"), DATA("-data"), OUT("-out"), IN("-in"), ALG("-alg");

    private final String argKey;

    ArgumentKey(String argKey) {
        this.argKey = argKey;
    }

    public String getArgKey() {
        return this.argKey;
    }
}
