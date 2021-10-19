/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client;

import java.io.*;
import java.net.*;

/**
 *
 * @author Utente
 */
public class Clientstr {
    String NomeServer="localhost";
    int SocketServer=6789;
    Socket miosocket;
    BufferedReader tastiera;
    String StringaUtente;
    String StringaRicevutaDalServer;
    DataOutputStream OutVersoServer;
    BufferedReader inDalServer;
    
    public Socket connetti(){
        System.out.println("2 CLIENT partito");
        try{
            tastiera=new BufferedReader(new InputStreamReader(System.in));
            miosocket=new Socket(NomeServer,SocketServer);
            OutVersoServer=new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
        }
        catch(UnknownHostException e){
            System.err.println("Host sconosiuto");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("errore durante la connessione");
            System.exit(1);
        }
        return miosocket;
    }
    
    public void comunica(){
        for(;;){
            try{
                System.out.println("4... utente, inserisci la stringa da trasmettere al server:");
                StringaUtente=tastiera.readLine();
                System.out.println("5... invio la stringa al server e attendo...");
                OutVersoServer.writeBytes(StringaUtente+"\n");
                StringaRicevutaDalServer=inDalServer.readLine();
                System.out.println("7... risposta dal server"+"\n"+StringaRicevutaDalServer);
                if(StringaUtente.equals("FINE")){
                    System.out.println("8 client: termina elaborazione e chiude connessione");
                    miosocket.close();
                    break;
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                System.out.println("Errore durante la comunicazione col server");
                System.exit(1);
            }
        }
        
    }
}
