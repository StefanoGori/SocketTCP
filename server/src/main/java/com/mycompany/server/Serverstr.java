/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server;

import java.io.*;
import java.net.*;
/**
 *
 * @author Utente
 */
public class Serverstr {
    
    ServerSocket server=null;
    Socket client=null;
    String Stringaricevuta=null;
    String StringaModificata=null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    
    public Socket attendi(){//attesa server
        try{
            System.out.println("1 SERVER partito");
            server=new ServerSocket(6789);//creazione porta del server
            client=server.accept();//la porta si mette in ascolto
            inDalClient=new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient=new DataOutputStream(client.getOutputStream());
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("errore durante l'istanza del server");
            System.exit(1);
        }
        return client;
    }
    
    public void comunica(){
        try{
            System.out.println("3 benvenuto client, scrivi una frase e la trasformo in maiuscolo");
            Stringaricevuta=inDalClient.readLine();//lettura stringa dal client
            System.out.println("6 ricevuta stringa del cliente:"+Stringaricevuta);
            StringaModificata=Stringaricevuta.toUpperCase();//modifica stringa
            System.out.println("7 invio stringa modificata al client");
            outVersoClient.writeBytes(StringaModificata+'\n');//invio dello stream 
            System.out.println("9 server:fine elaborazione");
            client.close();//chiusura socket
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("errore durante la comunicazione col server");
            System.exit(1);
        }
    }
}
