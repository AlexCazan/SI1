package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        FileReader fisier= new FileReader("C:/Users/alexg/Desktop/ML/ML_tree.png");
        String alphabet="0123456789abcdef";
        char[] IV=new char[32];
        StringBuilder result=Convert.transform(fisier);
        String[] encryptedCBC,decryptedCBC,encryptedOFB;
        String secretKey = "00112233445566778889aabbccddeeff";
        Convert.randomize(IV,alphabet);
        encryptedCBC=CBC.encrypt(IV,secretKey,result);
        decryptedCBC=CBC.decrypt(result,IV,secretKey,encryptedCBC);
        encryptedOFB=OFB.encrypt(IV,secretKey,result);
        System.out.println(result);
        System.out.println(Arrays.toString(encryptedCBC));
        System.out.println(Arrays.toString(decryptedCBC));
        //System.out.println(Arrays.toString(encryptedOFB));
    }
}
