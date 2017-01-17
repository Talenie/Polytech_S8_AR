/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babystep_client_serveur;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;


/**
 *
 * @author alicia
 */
public class Client extends Thread {
    String serverHost;
    int serverPort;
    String filename;
    
    
    public Client(String file){
        serverHost = "localhost";
        serverPort = 1552;
        filename = file;
    }
    
        
    public void run(){
        Socket server = null;
        try {
            server = new Socket(serverHost,serverPort);
        } catch (IOException ex) {
            System.err.println("Erreur création socket client");
        }
        System.out.println("Connected to "+ server.getInetAddress());
        
        OutputStream os;
        DataOutputStream dos = null;
        
        InputStream is;
        DataInputStream dis = null;
        
        // Ouverture Lecture
        try {
            os = server.getOutputStream();
            dos = new DataOutputStream(os);
        } catch (IOException ex) {
            System.err.println("Erreur de création des steams");
        }
        
        File f = new File(filename);
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(f);
            dos.writeLong(f.length());
            System.out.println("taille " + f.length());
        } catch (IOException ex) {
            System.err.println("Erreur debut de fichier");
        }
        int sent = 0;
            while(sent < f.length()){
                sent++;
                try {
                    dos.write(fi.read());
                } catch (IOException ex) {
                    System.err.println("Erreur ecriture de byte " + sent + ex);
                    ex.printStackTrace();
                }
            }
        
        // Ouverture écriture
        try {
            is = server.getInputStream();
            dis = new DataInputStream(is);
        } catch (IOException ex) {
            System.err.println("Erreur de création des steams");
        }
        
        Boolean answer;
        try {
            answer = (Boolean) dis.readBoolean();
            System.out.println("File received : "+answer);
        } catch (IOException ex) {
            System.err.println("Erreur lecture");
        }
            
       
        try {
            dos.close();
            dis.close();
        } catch (IOException ex) {
                System.err.println("Erreur fermeture");
        }
            
        
        
    }
    
    public static void main(String[] args) {
        Client client = new Client("/home/alicia/Images/demande_vignette.png");
        client.start();
    }
    
    
    
}
