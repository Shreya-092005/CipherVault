package com.ciphervault.cipher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VigenereCipherTest {

    @Test
    void testEncryption() {
        String result = VigenereCipher.encrypt("HELLO", "KEY");

        assertEquals("RIJVS", result);
    }

    @Test
    void testDecryption() {
        String result = VigenereCipher.decrypt("RIJVS", "KEY");

        assertEquals("HELLO", result);
    }

    @Test
    void testWithSpaces() {
        String result = VigenereCipher.encrypt("HELLO WORLD", "KEY");

        assertEquals("RIJVS UYVJN", result);
    }

    @Test
    void testLowercase() {
        String result = VigenereCipher.encrypt("hello", "key");

        assertEquals("rijvs", result);
    }

    @Test
    void testEmptyKey() {
        assertThrows(
                IllegalArgumentException.class,
                () -> VigenereCipher.encrypt("HELLO", "")
        );
    }

    @Test
    void testNullText() {
        assertThrows(
                IllegalArgumentException.class,
                () -> VigenereCipher.encrypt(null, "KEY")
        );
    }
}