/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_babystep;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alicia
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Iserveur obj = null;
        try {
            obj = (Iserveur)Naming.lookup("rmi://localhost:2525/Hello");
            obj.sayHello();
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
