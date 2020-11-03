package com.company;

import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] args) throws Exception
    {
        Socket s=new Socket("localhost",8888);
        System.out.println("Set your name first: ");
        String name= new String(String.valueOf(System.in));
        DataOutputStream dos=new DataOutputStream(s.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
        String string,string1;

        while(!(string=kb.readLine()).equals("exit")){
            dos.writeBytes(string+"\n");
            string1=br.readLine();
            System.out.println(name+":"+string1);
        }
        dos.close();
        br.close();
        kb.close();
        s.close();
    }
}
