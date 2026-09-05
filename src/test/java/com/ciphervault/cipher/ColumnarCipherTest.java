package com.ciphervault.cipher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColumnarCipherTest {

    @Test
    void testEncryption() {
        String result = ColumnarCipher.encrypt("HELLOWORLD", "ZEBRA");

        assertEquals("ODLREOLLHW", result);
    }

    @Test
    void testDecryption() {
        String encrypted = ColumnarCipher.encrypt("HELLOWORLD", "ZEBRA");

        String result = ColumnarCipher.decrypt(encrypted, "ZEBRA");

        assertEquals("HELLOWORLD", result);
    }

    @Test
    void testEmptyKey() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ColumnarCipher.encrypt("HELLO", "")
        );
    }

    @Test
    void testNullText() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ColumnarCipher.encrypt(null, "KEY")
        );
    }

    @Test
    void testEncryptionDecryption() {

        String original = "INFORMATIONSECURITY";

        String encrypted =
                ColumnarCipher.encrypt(original, "KEY");

        String decrypted =
                ColumnarCipher.decrypt(encrypted, "KEY");

        assertEquals(original, decrypted);
    }
}