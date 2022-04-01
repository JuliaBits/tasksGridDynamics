package task4;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncryptionControllerTest {
    private final EncryptionController encryptionController = new EncryptionController("ENCRYPTION", "2", "asdfg");
    private final EncryptionController decryptionController = new EncryptionController("DECRYPTION", "2", "cufhi");
    private final EncryptionController encryptionFromFile = new EncryptionController("ENCRYPTION", "3", null, "readFrom.txt");
    private final EncryptionController decryptFromFIle = new EncryptionController("DECRIPTION", "3", null, "toWrite.txt");

    @Test
    public void shouldEncrypt() throws IOException {
        assertEquals("cufhi", encryptionController.encryptOrDecrypt(Algorithm.SHIFT));
    }

    @Test
    public void shouldDecrypt() throws IOException {
        assertEquals("asdfg", decryptionController.encryptOrDecrypt(Algorithm.SHIFT));
    }

    @Test
    public void shouldEncryptWithUnicode() throws IOException {
        assertEquals("cufhi", encryptionController.encryptOrDecrypt(Algorithm.UNICODE));
    }

    @Test
    public void shouldDecryptWithUnicode() throws IOException {
        assertEquals("asdfg", decryptionController.encryptOrDecrypt(Algorithm.UNICODE));
    }

    @Test
    public void shouldNotReadFromFileBecauseFileDoesNotExist() throws IOException {
        EncryptionController encryption = new EncryptionController("ENCRYPTION", "1", null, "unknown.txt");
        assertEquals("This file is empty or does not exist!", encryption.encryptOrDecrypt(Algorithm.SHIFT));
    }

    @Test
    public void shouldEncryptFromFileAndWriteToFile() throws IOException {
        encryptionFromFile.setPathToWrite("toWrite.txt");
        assertEquals("Result has been written to file: toWrite.txt", encryptionFromFile.encryptOrDecrypt(Algorithm.SHIFT));
    }

    @Test
    public void shouldEncryptFromFileAndWriteToFileWithUnicode() throws IOException {
        encryptionFromFile.setPathToWrite("toWrite.txt");
        assertEquals("Result has been written to file: toWrite.txt", encryptionFromFile.encryptOrDecrypt(Algorithm.UNICODE));
    }

    @Test
    public void shouldDecryptFromFile() throws IOException {
        assertEquals("QwertY_~", decryptFromFIle.encryptOrDecrypt(Algorithm.UNICODE));
    }

    @Test
    public void shouldDecryptFromFileWithUnicode() throws IOException {
        assertEquals("QwertY_~", decryptFromFIle.encryptOrDecrypt(Algorithm.UNICODE));
    }

    @Test
    public void shouldDecryptFromFileWithShift() throws IOException {
        assertEquals("qwert\\y\u0002", decryptFromFIle.encryptOrDecrypt(Algorithm.SHIFT));
    }

    @Test
    public void shouldEncryptFromFile() throws IOException {
        assertEquals("Tzhuw\\b\u0002", encryptionFromFile.encryptOrDecrypt(Algorithm.UNICODE));
    }

    @Test
    public void shouldEncryptFromFileAndWriteToNewFile() throws IOException {
        encryptionFromFile.setPathToWrite("new.txt");
        assertEquals("Result has been written to file: new.txt", encryptionFromFile.encryptOrDecrypt(Algorithm.SHIFT));
        File file = new File("new.txt");
        file.delete();
    }
}
