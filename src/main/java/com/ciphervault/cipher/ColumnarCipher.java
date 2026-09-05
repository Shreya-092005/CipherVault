package com.ciphervault.cipher;

import java.util.Arrays;

public class ColumnarCipher {

    public static String encrypt(String text, String key) {

        if (text == null || key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Text and key cannot be null or empty");
        }

        int columns = key.length();
        int rows = (int) Math.ceil((double) text.length() / columns);

        char[][] matrix = new char[rows][columns];

        int index = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {

                if (index < text.length()) {
                    matrix[r][c] = text.charAt(index++);
                } else {
                    matrix[r][c] = 'X';
                }
            }
        }

        Integer[] order = getColumnOrder(key);

        StringBuilder result = new StringBuilder();

        for (int column : order) {

            for (int row = 0; row < rows; row++) {
                result.append(matrix[row][column]);
            }
        }

        return result.toString();
    }

    public static String decrypt(String text, String key) {

        if (text == null || key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Text and key cannot be null or empty");
        }

        int columns = key.length();
        int rows = text.length() / columns;

        char[][] matrix = new char[rows][columns];

        Integer[] order = getColumnOrder(key);

        int index = 0;

        for (int column : order) {

            for (int row = 0; row < rows; row++) {
                matrix[row][column] = text.charAt(index++);
            }
        }

        StringBuilder result = new StringBuilder();

        for (int row = 0; row < rows; row++) {

            for (int column = 0; column < columns; column++) {
                result.append(matrix[row][column]);
            }
        }

        return result.toString().replaceAll("X+$", "");
    }

    private static Integer[] getColumnOrder(String key) {

        Integer[] order = new Integer[key.length()];

        for (int i = 0; i < key.length(); i++) {
            order[i] = i;
        }

        Arrays.sort(order, (a, b) -> {

            char charA = Character.toUpperCase(key.charAt(a));
            char charB = Character.toUpperCase(key.charAt(b));

            if (charA == charB) {
                return Integer.compare(a, b);
            }

            return Character.compare(charA, charB);
        });

        return order;
    }
}