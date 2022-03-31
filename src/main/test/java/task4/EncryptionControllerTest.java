package task4;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EncryptionControllerTest {

    @Test
    public void shouldEncryptOrDecrypt() throws IOException {
        EncryptionController encryptionController = new EncryptionController();
        assertEquals("cufhi", encryptionController.encryptOrDecrypt("asdfg", "2", Algorithm.UNICODE, "ENCRYPTION"));
    }
    @Test
    public void shouldDecryptOrDecrypt() throws IOException {
        EncryptionController encryptionController = new EncryptionController();
        assertEquals("asdfg", encryptionController.encryptOrDecrypt("cufhi", "2", Algorithm.UNICODE, "DECRYPTION"));
    }
}
