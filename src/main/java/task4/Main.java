package task4;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        EncryptionController encryptionController = new EncryptionController();
        encryptionController.encryptOrDecrypt("asdfg", "2", Algorithm.UNICODE, "ENCRYPTION");
    }


}
