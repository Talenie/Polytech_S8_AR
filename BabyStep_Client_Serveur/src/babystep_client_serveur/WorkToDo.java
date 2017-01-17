/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babystep_client_serveur;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alicia
 */
public class WorkToDo {
    
    BlockingQueue<Socket> todo;
    
    public WorkToDo(int size){
        todo = new ArrayBlockingQueue<>(size);
    }
    
    public void put(Socket s){
        try {
            todo.put(s);
        } catch (InterruptedException ex) {
            System.err.println("babystep_client_serveur.WorkToDo.put() : Interrupted"+ ex);
        }
    }
    
    public Socket get(){
        Socket res = null;
        try {
            res = todo.take();
        } catch (InterruptedException ex) {
            System.err.println("babystep_client_serveur.WorkToDo.get() : Interrupted"+ ex);
        }
        return res;
    }
    
}
