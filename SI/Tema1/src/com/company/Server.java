package com.company;
import java.net.*;
import java.io.*;

public class Server
{

    public static void main(String[] args) throws Exception
    {
        ServerSocket server=new ServerSocket(8888);
        Socket s=server.accept();
        System.out.println("Connection done.");

        PrintStream ps=new PrintStream(s.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String string, string1;
            while ((string = br.readLine()) != null) {
                System.out.println(string);
                string1 = kb.readLine();
                ps.println(string1);
            }
            ps.close();
            br.close();
            kb.close();
            server.close();
            s.close();

            System.exit(0);
        }
    }
}
