package com.ciphervault;
import com.ciphervault.cipher.CaesarCipher;

public class Main {

    public static void main(String[] args) {

        String text = "Hello World";

        int shift = 3;

        String encrypted = CaesarCipher.encrypt(text, shift);

        String decrypted = CaesarCipher.decrypt(encrypted, shift);

        System.out.println("Original  : " + text);
        System.out.println("Encrypted : " + encrypted);
        System.out.println("Decrypted : " + decrypted);
    }
}
