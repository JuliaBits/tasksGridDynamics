package task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class EncryptionController {
    private EncryptionMode encryptionMode = EncryptionMode.ENCRYPTION;
    private String key = " ";
    private String message = " ";
    private String pathToWrite = "";
    private String pathToRead = "";
    private Algorithm algorithm;
    private StringBuffer buffer;
    private static final String ABC = "abcdefghijklmnopqrstuvwxyz";

    public void encryptOrDecrypt(String message, String key) throws IOException {
        int shift = Integer.parseInt(key);
        String result;
        if (algorithm.equals(Algorithm.SHIFT)) {
            if (!pathToRead.equals("")) {
                message = readFromFile(pathToRead);
                char[] messageArray = message.toCharArray();
                if (EncryptionMode.ENCRYPTION.equals(getEncryptionMode())) {
                    result = shiftingEncryption(shift, messageArray);
                } else {
                    result = shiftingDecryption(shift, messageArray);
                }
            } else {
                char[] messageArray = message.toCharArray();
                if (EncryptionMode.ENCRYPTION.equals(getEncryptionMode())) {
                    result = shiftingEncryption(shift, messageArray);
                } else {
                    result = shiftingDecryption(shift, messageArray);
                }
            }
        } else {
            if (!pathToRead.equals("")) {
                message = readFromFile(pathToRead);
                char[] messageArray = message.toCharArray();
                if (EncryptionMode.ENCRYPTION.equals(getEncryptionMode())) {
                    result = unicodeEncryption(shift, messageArray);
                } else {
                    result = unicodeDecryption(shift, messageArray);
                }
            } else {
                char[] messageArray = message.toCharArray();
                if (EncryptionMode.ENCRYPTION.equals(getEncryptionMode())) {
                    result = unicodeEncryption(shift, messageArray);
                } else {
                    result = unicodeDecryption(shift, messageArray);
                }
            }
        }
        if (!pathToWrite.equals("")) {
            writeToFile(pathToWrite, result);
        } else {
            System.out.println(result);
        }
    }

    private void writeToFile(String pathToWrite, String message) throws IOException {
        File file = new File(pathToWrite);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            printWriter.append(message);
        }
    }

    private String readFromFile(String pathToRead) throws FileNotFoundException {
        File file = new File(pathToRead);
        StringBuilder stringBuilder = new StringBuilder();
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNext()) {
                    stringBuilder.append(scanner.nextLine());
                }
            }
        } else {
            System.out.println("Error, such file does not exist!");
        }
        return stringBuilder.toString();
    }

    private String unicodeDecryption(int shift, char[] messageArray) {
        buffer = new StringBuffer();
        for (char current : messageArray) {
            for (int j = 0; j < 127; j++) {
                if (current == j) {
                    char resultChar;
                    int numberByASCII;
                    if (shift > j) {
                        numberByASCII = 127 - (shift - j);
                    } else {
                        numberByASCII = (j - shift);
                    }
                    resultChar = (char) numberByASCII;
                    buffer.append(resultChar);
                    break;
                }
            }
        }
        return buffer.toString();
    }

    private String unicodeEncryption(int shift, char[] messageArray) {
        buffer = new StringBuffer();
        System.out.println(messageArray);
        for (char current : messageArray) {
            for (int j = 0; j < 127; j++) {
                if (current == j) {
                    char resultChar;
                    if (j + shift > 127) {
                        int numberByASCII = shift - (127 - j);
                        resultChar = (char) numberByASCII;

                    } else {
                        resultChar = (char) (j + shift);
                    }
                    buffer.append(resultChar);
                    break;
                }
            }
        }
        return buffer.toString();
    }

    private String shiftingDecryption(int shift, char[] messageArray) {
        buffer = new StringBuffer();
        String upperAlphabet = ABC.toUpperCase(Locale.ROOT);

        char[] abcCharsLowerCase = ABC.toCharArray();
        char[] abcCharsUpperCase = upperAlphabet.toCharArray();
        for (char current : messageArray) {
            if (Character.isAlphabetic(current)) {
                for (int j = 0; j < ABC.length() * 2 - 1; j++) {
                    if (current == abcCharsLowerCase[j]) {
                        char resultChar = shift > j ? abcCharsLowerCase[ABC.length() - (shift - j)] : abcCharsLowerCase[j - shift];
                        buffer.append(resultChar);
                        break;
                    }
                }
                for (int n = 0; n < ABC.length(); n++) {
                    if (current == abcCharsUpperCase[n]) {
                        char resultChar = shift > n ? abcCharsUpperCase[upperAlphabet.length() - (shift - n)] : abcCharsLowerCase[n - shift];
                        buffer.append(resultChar);
                        break;
                    }
                }
            } else {
                buffer.append(current);
            }
        }
        return buffer.toString();
    }

    private String shiftingEncryption(int shift, char[] messageArray) {
        buffer = new StringBuffer();
        System.out.println(messageArray);
        String upperAlphabet = ABC.toUpperCase(Locale.ROOT);

        char[] abcCharsLowerCase = ABC.toCharArray();
        char[] abcCharsUpperCase = upperAlphabet.toCharArray();
        for (char current : messageArray) {
            if (Character.isAlphabetic(current)) {
                for (int j = 0; j < ABC.length(); j++) {
                    if (current == abcCharsLowerCase[j]) {
                        char resultChar = j + shift > ABC.length() ? abcCharsLowerCase[shift - (ABC.length() - j)] : abcCharsLowerCase[j + shift];
                        buffer.append(resultChar);
                        break;
                    }
                }
                for (int n = 0; n < ABC.length(); n++) {
                    if (current == abcCharsUpperCase[n]) {
                        char resultChar = n + shift > ABC.length() ? abcCharsUpperCase[shift - (upperAlphabet.length() - n)] : abcCharsUpperCase[n + shift];
                        buffer.append(resultChar);
                        break;
                    }
                }
            } else {
                buffer.append(current);
            }
        }
        return buffer.toString();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPathToWrite(String pathToWrite) {
        this.pathToWrite = pathToWrite;
    }

    public void setPathToRead(String pathToRead) {
        this.pathToRead = pathToRead;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = Algorithm.findByAlgVal(algorithm);
    }

    public EncryptionMode getEncryptionMode() {
        return encryptionMode;
    }

    public void setEncryptionMode(EncryptionMode encryptionMode) {
        this.encryptionMode = encryptionMode;
    }
}