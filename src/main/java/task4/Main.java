package task4;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        EncryptionController encryptionController = new EncryptionController();
        HashMap<String, String> arguments = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (i % 2 == 0) {
                arguments.put(args[i], args[i + 1]);
            }
        }
        if (arguments.get(ArgumentKey.MODE.getArgKey()) != null) {
            encryptionController.setEncryptionMode(EncryptionMode.findByModeVal(arguments.get(ArgumentKey.MODE.getArgKey())));
        }
        if (arguments.get(ArgumentKey.KEY.getArgKey()) != null) {
            encryptionController.setKey(arguments.get(ArgumentKey.KEY.getArgKey()));
        } else {
            encryptionController.setKey("0");
        }
        if (arguments.get(ArgumentKey.DATA.getArgKey()) != null && arguments.get(ArgumentKey.IN.getArgKey()) == null) {
            encryptionController.setMessage(arguments.get((ArgumentKey.DATA.getArgKey())));
        } else if (arguments.get(ArgumentKey.DATA.getArgKey()) == null && arguments.get(ArgumentKey.IN.getArgKey()) != null) {
            encryptionController.setPathToRead(arguments.get(ArgumentKey.IN.getArgKey()));
        } else if (arguments.get(ArgumentKey.DATA.getArgKey()) != null && arguments.get(ArgumentKey.IN.getArgKey()) != null) {
            encryptionController.setMessage(arguments.get(ArgumentKey.DATA.getArgKey()));
        }
        if (arguments.get(ArgumentKey.OUT.getArgKey()) != null) {
            encryptionController.setPathToWrite(arguments.get(ArgumentKey.OUT.getArgKey()));
        }
        if (arguments.get(ArgumentKey.ALG.getArgKey()) != null) {
            encryptionController.setAlgorithm(arguments.get(ArgumentKey.ALG.getArgKey()));
        } else {
            encryptionController.setAlgorithm(Algorithm.SHIFT.getAlgorithmVal());
        }
        encryptionController.encryptOrDecrypt(encryptionController.getMessage(), encryptionController.getKey());
    }


}
