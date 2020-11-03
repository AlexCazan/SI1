package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

public class CBC {
    public static String[] encrypt(char [] IV,String secretKey,StringBuilder result) throws IOException {

        String str ="",result1Hex="",encryptedString="";
        char[] vector = new char[32];
        int [] result1;
        int size=result.length(),i,j = 0,nr=0,index=0;
        result.getChars(0,32,vector,0);
        result1=Convert.xorFunction(vector,IV);
        String[] encrypted=new String[size/32+1];
        for(i=0;i<result1.length;i++)
        {
            str+=String.valueOf(result1[i]);
        }
         result1Hex=Convert.binToHex(str);
         encryptedString = AES.encrypt(result1Hex, secretKey);
         encrypted[index]=encryptedString;

        for(i=32;i<size && i+32<size;i+=32) {
            str ="";
            j = i + 32;
            result.getChars(i, j, vector, 0);
            result1 =Convert.xorFunction(vector, IV);
            for (int value : result1) {
                str += String.valueOf(value);
            }
            result1Hex=Convert.binToHex(str);
            encryptedString = AES.encrypt(result1Hex, secretKey);
            index++;
            encrypted[index]=encryptedString;
        }

        if(size%32!=0) {
            str ="";
            for(i=j;i<size;i++) {
                vector[nr]=result.charAt(i);
                nr++;
            }
            for(i=nr;i<32;i++) vector[i]='0';
            result1=Convert.xorFunction(vector,IV);
            for(i=0;i<result1.length;i++)
            {
                str+=String.valueOf(result1[i]);
            }
            result1Hex=Convert.binToHex(str);
            encryptedString = AES.encrypt(result1Hex, secretKey);
            index++;
            encrypted[index]=encryptedString;
        }
        return encrypted;
    }
    public static String[] decrypt(StringBuilder result,char [] IV,String secretKey,String[] encrypted) throws IOException {
        //System.out.println(result);
        int i,j,index;
        String str="",decryptedString,str1="";
        String[] decrypted=new String[encrypted.length];
        decryptedString=AES.decrypt(encrypted[0], secretKey);
        char [] convertPredecessor=new char[decryptedString.length()],convertSuccessor,convertPredecessorLong;
        int [] convertBin;
        for(i=0;i<decryptedString.length();i++)
        {
            convertPredecessor[i]=decryptedString.charAt(i);
        }
        convertBin=Convert.xorFunction(convertPredecessor,IV);
        for(i=0;i<convertBin.length;i++)
        {
            str+=String.valueOf(convertBin[i]);
        }
        decrypted[0]=Convert.binToHex(str);
        for(i=1;i<encrypted.length;i++)
        {
            str="";
            decryptedString=AES.decrypt(encrypted[i], secretKey);

            str1=Convert.toHexadecimal(encrypted[i-1]);
            convertPredecessorLong=new char[str1.length()];
            convertSuccessor=new char[str1.length()];
            for(j=0;j<str1.length();j++)
            {
                convertPredecessorLong[j]=str1.charAt(j);
            }
            for(j=0; j< decryptedString.length(); j++)
            {
                convertSuccessor[j]=decryptedString.charAt(j);
            }
            for(j=decryptedString.length();j<convertSuccessor.length;j++){
                convertSuccessor[j]='0';
            }

            convertBin=Convert.xorFunction(convertPredecessorLong,convertSuccessor);
            for(index=0;index<convertBin.length;index++)
            {
                str+=String.valueOf(convertBin[index]);
            }

            decrypted[i]=Convert.binToHex(str);
        }
        return decrypted;
    }

}
