/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babystep_client_serveur;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author alicia
 */
public class Esclave extends Thread {
    Socket client;
    WorkToDo todo;
    
    
    public Esclave(WorkToDo todo){
        this.todo = todo;
    }
    
    
    public void run () {
        while(true){
            client = todo.get();
            DataInputStream dis = null;
            try {
                InputStream is = client.getInputStream();
                dis = new DataInputStream(is);
            } catch (IOException ex) {
                System.err.println("Erreur input du client serveur");
            }

            long length = -1;
            try {
                length = dis.readLong();
            } catch (IOException ex) {
                System.err.println("Erreur lecture de taille");
            }
            long read = 0;
            while(read < length){
                read++;
                try {
                    dis.read();
                } catch (IOException ex) {
                    System.err.println("Erreur lecture du fichier");
                }
            }
            System.out.println("Received file of size "+ length);

            OutputStream os;
            DataOutputStream dos = null;
            try {
                os = client.getOutputStream();
                dos = new DataOutputStream(os);
            } catch (IOException ex) {
                System.err.println("Erreur transformation du stream sortie serveur");
            }

            Boolean answer = true;
            try {
                dos.writeBoolean(answer);
            } catch (IOException ex) {
                System.err.println("Erreur ecriture du stream sortie serveur");
            }

            try {
                dos.close();
                dis.close();
            } catch (IOException ex) {
                System.err.println("Erreur fermeture des stream" + dos + " " + dis);
            }
        }
    }
    
}
