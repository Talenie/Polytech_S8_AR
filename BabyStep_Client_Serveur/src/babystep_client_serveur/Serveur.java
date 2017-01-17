package babystep_client_serveur;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alicia
 */
public class Serveur extends Thread {
    int port;
    ServerSocket server;
    WorkToDo todo;
    int nb_esclave;
    
    public Serveur(){
        port = 1552;
        todo = new WorkToDo(2);
        nb_esclave = 1;
    }
    
    
    public void run(){
        
        for(int i = 0; i < nb_esclave; i++){
            Esclave jose = new Esclave(todo);
            jose.start();
        }
        
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            System.err.println("Erreur création serveur");
        }
        
        while(true){
            Socket client = null;
            try {
                System.out.println("-- En attente de connexion --");
                client = server.accept();
            } catch (IOException ex) {
                System.err.println("Erreur acceptation de connexion du serveur");
            }
            
            System.out.println("Client "+ client.getInetAddress() + " connected");
            
            todo.put(client);
            
        }
    }
    
    public static void main(String[] args) {
        Serveur serveur = new Serveur();
        serveur.start();
    }
}
