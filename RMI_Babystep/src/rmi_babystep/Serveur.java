/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_babystep;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author alicia
 */
public class Serveur extends UnicastRemoteObject implements Iserveur {
    
    public Serveur() throws RemoteException {
        
    }
    
    @Override
    public void sayHello() throws RemoteException {
        System.out.println("Hello World !");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        Serveur serveur = new Serveur();
        Registry registre = LocateRegistry.createRegistry(2525);
        Naming.bind("rmi://localhost:2525/Hello", serveur);
    }
    
}
