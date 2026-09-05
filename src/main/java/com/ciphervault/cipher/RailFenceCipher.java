package com.ciphervault.cipher;

public class RailFenceCipher {

    public static String encrypt(String text, int rails) {

        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }

        if (rails <= 1) {
            return text;
        }

        StringBuilder[] fence = new StringBuilder[rails];

        for (int i = 0; i < rails; i++) {
            fence[i] = new StringBuilder();
        }

        int row = 0;
        boolean down = true;

        for (char ch : text.toCharArray()) {

            fence[row].append(ch);

            if (row == 0) {
                down = true;
            } else if (row == rails - 1) {
                down = false;
            }

            row += down ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();

        for (StringBuilder rail : fence) {
            result.append(rail);
        }

        return result.toString();
    }

    public static String decrypt(String text, int rails) {

        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }

        if (rails <= 1) {
            return text;
        }

        int length = text.length();

        boolean[][] pattern = new boolean[rails][length];

        int row = 0;
        boolean down = true;

        for (int col = 0; col < length; col++) {

            pattern[row][col] = true;

            if (row == 0) {
                down = true;
            } else if (row == rails - 1) {
                down = false;
            }

            row += down ? 1 : -1;
        }

        char[][] matrix = new char[rails][length];

        int index = 0;

        for (int r = 0; r < rails; r++) {

            for (int c = 0; c < length; c++) {

                if (pattern[r][c]) {
                    matrix[r][c] = text.charAt(index++);
                }
            }
        }

        StringBuilder result = new StringBuilder();

        row = 0;
        down = true;

        for (int col = 0; col < length; col++) {

            result.append(matrix[row][col]);

            if (row == 0) {
                down = true;
            } else if (row == rails - 1) {
                down = false;
            }

            row += down ? 1 : -1;
        }

        return result.toString();
    }
}