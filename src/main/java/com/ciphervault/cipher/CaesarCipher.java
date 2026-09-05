package com.ciphervault.cipher;

public class CaesarCipher {

    public static String encrypt(String text, int shift) {

        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }

        StringBuilder result = new StringBuilder();

        shift = shift % 26;

        for (char ch : text.toCharArray()) {

            if (ch >= 'A' && ch <= 'Z') {

                char encrypted = (char) ('A' +
                        (ch - 'A' + shift + 26) % 26);

                result.append(encrypted);

            } else if (ch >= 'a' && ch <= 'z') {

                char encrypted = (char) ('a' +
                        (ch - 'a' + shift + 26) % 26);

                result.append(encrypted);

            } else {

                result.append(ch);
            }
        }

        return result.toString();
    }

    public static String decrypt(String text, int shift) {

        return encrypt(text, -shift);
    }
}