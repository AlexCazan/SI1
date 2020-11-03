package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Convert {
    public static StringBuilder transform(FileReader fisier) throws IOException {
        int ch;
        ch=fisier.read();
        StringBuilder result=new StringBuilder();
        while(ch!=-1)
        {
            result.append(Integer.toHexString(ch));
            ch=fisier.read();
        }
        return result;
    }
    public static void randomize(char[] IV,String alphabet){
        for(int i=0;i<32;i++){
            int pos=Math.abs((int) (Math.random() * (alphabet.length()+ 1)) - 1);
            IV[i]=alphabet.charAt(pos);
        }
    }
    public static int[] xorFunction(char[] a,char[] b) {
        String strA = new String(a);
        String strB = new String(b);
        int[] binA = hexToBin(strA);
        int[] binB = hexToBin(strB);
        for (int i = 0; i < 128; i++) {
            binA[i] = (binA[i] + binB[i]) % 2;
        }
        return binA;

    }

    public static int[] hexToBin(String input)
    {
        int n = input.length() * 4;
        int [] conversie=new int[n];
        input= new BigInteger(input,16).toString(2);
        while (input.length() < n)
            input = "0" + input;
        for(int i=0;i<conversie.length;i++)
        {
            conversie[i]=input.charAt(i)-'0';
        }
        return conversie;
    }
    public static String  binToHex(String input)
    {
        int n = input.length() / 4;
        input = new BigInteger(input,2).toString(16);
        while (input.length() < n)
            input = "0" + input;
        return input;
    }
    public static String toHexadecimal(String str)
    {
        StringBuffer sb = new StringBuffer();
        char ch[] = str.toCharArray();
        for(int i = 0; i < ch.length; i++) {
            String hexString = Integer.toHexString(ch[i]);
            sb.append(hexString);
        }
        String result = sb.toString();
        return result;
    }
}
