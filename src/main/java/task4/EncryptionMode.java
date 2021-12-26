package task4;

import java.util.Arrays;

public enum EncryptionMode {
    ENCRYPTION("enc"), DECRYPTION("decr");

    private final String modeVal;

    EncryptionMode(String modeVal) {
        this.modeVal = modeVal;
    }

    public static EncryptionMode findByModeVal(String modeVal) {
        return Arrays.stream(EncryptionMode.values()).filter(encryptionMode -> encryptionMode.getModeVal().equals(modeVal)).findFirst().orElse(null);
    }

    public String getModeVal() {
        return modeVal;
    }
}

