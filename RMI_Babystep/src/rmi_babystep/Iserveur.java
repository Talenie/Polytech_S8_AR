/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi_babystep;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author alicia
 */
public interface Iserveur extends Remote{
    public void sayHello() throws RemoteException;
}
