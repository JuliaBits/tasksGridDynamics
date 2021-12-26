package task4;

import java.util.Arrays;

public enum Algorithm {
    SHIFT("shift"), UNICODE("unicode");

    private final String algorithmVal;

    Algorithm(String algorithmVal) {
        this.algorithmVal = algorithmVal;
    }

    public static Algorithm findByAlgVal(String algVal) {
        return Arrays.stream(Algorithm.values()).filter(algorithm -> algorithm.getAlgorithmVal().equals(algVal)).findFirst().orElse(null);
    }

    public String getAlgorithmVal() {
        return algorithmVal;
    }
}
