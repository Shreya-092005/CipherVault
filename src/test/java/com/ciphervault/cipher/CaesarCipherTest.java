package com.ciphervault.cipher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaesarCipherTest {

    @Test
    void testEncryption() {
        String result = CaesarCipher.encrypt("HELLO", 3);

        assertEquals("KHOOR", result);
    }

    @Test
    void testDecryption() {
        String result = CaesarCipher.decrypt("KHOOR", 3);

        assertEquals("HELLO", result);
    }

    @Test
    void testLowercase() {
        String result = CaesarCipher.encrypt("hello", 3);

        assertEquals("khoor", result);
    }

    @Test
    void testSpaces() {
        String result = CaesarCipher.encrypt("HELLO WORLD", 3);

        assertEquals("KHOOR ZRUOG", result);
    }

    @Test
    void testZeroShift() {
        String result = CaesarCipher.encrypt("HELLO", 0);

        assertEquals("HELLO", result);
    }

    @Test
    void testLargeShift() {
        String result = CaesarCipher.encrypt("ABC", 29);

        assertEquals("DEF", result);
    }

    @Test
    void testSpecialCharacters() {
        String result = CaesarCipher.encrypt("Hello@123!", 3);

        assertEquals("Khoor@123!", result);
    }

    @Test
    void testEmptyText() {
        String result = CaesarCipher.encrypt("", 3);

        assertEquals("", result);
    }

    @Test
    void testNullText() {
        assertThrows(
                IllegalArgumentException.class,
                () -> CaesarCipher.encrypt(null, 3)
        );
    }
}