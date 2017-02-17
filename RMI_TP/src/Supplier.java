
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alicia
 */
public class Supplier extends UnicastRemoteObject implements ISupplier {
    
    private int indice;
    
    public Supplier(int indice) throws RemoteException{
        this.indice = indice;
    }

    @Override
    public String question() throws RemoteException {
        return "Hello !";
    }

    @Override
    public String question(String s) throws RemoteException {
        return System.getProperty(s);
    }

    @Override
    public String name() throws RemoteException {
        return this.toString();
    }
    
    @Override
    public String toString(){
        return "supplier"+indice;
    }
    
}
