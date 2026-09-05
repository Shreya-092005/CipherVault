package com.ciphervault.cipher;

public class VigenereCipher {

    public static String encrypt(String text, String key) {

        if (text == null || key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Text and key cannot be null or empty");
        }

        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();

        int keyIndex = 0;

        for (char ch : text.toCharArray()) {

            if (Character.isLetter(ch)) {

                int shift = key.charAt(keyIndex % key.length()) - 'A';

                if (Character.isUpperCase(ch)) {
                    result.append((char) ('A' + (ch - 'A' + shift) % 26));
                } else {
                    result.append((char) ('a' + (ch - 'a' + shift) % 26));
                }

                keyIndex++;

            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static String decrypt(String text, String key) {

        if (text == null || key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Text and key cannot be null or empty");
        }

        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();

        int keyIndex = 0;

        for (char ch : text.toCharArray()) {

            if (Character.isLetter(ch)) {

                int shift = key.charAt(keyIndex % key.length()) - 'A';

                if (Character.isUpperCase(ch)) {
                    result.append((char) ('A' + (ch - 'A' - shift + 26) % 26));
                } else {
                    result.append((char) ('a' + (ch - 'a' - shift + 26) % 26));
                }

                keyIndex++;

            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }
}