package com.ciphervault.cipher;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RailFenceCipherTest {

    @Test
    void testEncryption() {
        String result = RailFenceCipher.encrypt("HELLOWORLD", 3);

        assertEquals("HOLELWRDLO", result);
    }

    @Test
    void testDecryption() {
        String result = RailFenceCipher.decrypt("HOLELWRDLO", 3);

        assertEquals("HELLOWORLD", result);
    }

    @Test
    void testOneRail() {
        String result = RailFenceCipher.encrypt("HELLO", 1);

        assertEquals("HELLO", result);
    }

    @Test
    void testTwoRails() {
        String encrypted = RailFenceCipher.encrypt("HELLO", 2);
        String decrypted = RailFenceCipher.decrypt(encrypted, 2);

        assertEquals("HELLO", decrypted);
    }

    @Test
    void testNullText() {
        assertThrows(
                IllegalArgumentException.class,
                () -> RailFenceCipher.encrypt(null, 3)
        );
    }
}