package com.company;

import java.io.IOException;

public class OFB {

    public static String[] encrypt(char [] IV,String secretKey,StringBuilder result) throws IOException {

        String str ="",result1Bin="",encryptedString="",copy;
        int [] result1;
        int size=result.length(),i,j = 0,nr=0,index=0,length;

        for(i=0;i<IV.length;i++)
        {
            str+=String.valueOf(IV[i]);
        }
        encryptedString=Convert.toHexadecimal(AES.encrypt(str,secretKey));
        length=encryptedString.length();
        char[] vector = new char[encryptedString.length()],str1=new char[encryptedString.length()];
        result.getChars(0,encryptedString.length(),vector,0);
        String[] encrypted=new String[size/encryptedString.length()+1];
        for(i=0;i<length;i++)
        {
            str1[i]=encryptedString.charAt(i);
        }
        result1=Convert.xorFunction(str1,vector);
        for(i=0;i<result1.length;i++)
        {
            result1Bin+=result1[i];
        }
        encrypted[0]=Convert.binToHex(result1Bin);
        copy= encryptedString;
        for(i=encryptedString.length();i<size && i+encryptedString.length()<size;i+=encryptedString.length()) {
            j = i + encryptedString.length();
            result.getChars(i, j, vector, 0);
            result1Bin="";
            encryptedString=Convert.toHexadecimal(AES.encrypt(copy,secretKey));
            for(i=0;i<length;i++)
            {
                str1[i]=encryptedString.charAt(i);
            }
            result1 =Convert.xorFunction(str1, vector);
            for (int value : result1) {
                result1Bin+= String.valueOf(value);
            }
            index++;
            encrypted[index]=Convert.binToHex(result1Bin);
            copy= encryptedString;
        }

        if(size%length!=0) {
            result1Bin="";
            for(i=j;i<size;i++) {
                vector[nr]=result.charAt(i);
                nr++;
            }
            for(i=nr;i<length;i++) vector[i]='0';

            encryptedString=Convert.toHexadecimal(AES.encrypt(copy,secretKey));

            for(i=0;i<length;i++)
            {
                str1[i]=encryptedString.charAt(i);
            }
            result1=Convert.xorFunction(vector,str1);
            for(i=0;i<result1.length;i++)
            {
                result1Bin+=String.valueOf(result1[i]);
            }
            index++;
            encrypted[index]=Convert.binToHex(result1Bin);
        }
        return encrypted;
    }
}
